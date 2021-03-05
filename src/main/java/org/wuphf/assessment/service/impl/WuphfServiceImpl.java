package org.wuphf.assessment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wuphf.assessment.model.enums.PromoTypes;
import org.wuphf.assessment.model.enums.TransactionTypes;
import org.wuphf.assessment.exception.InsufficientFundsException;
import org.wuphf.assessment.model.dto.*;
import org.wuphf.assessment.model.jpa.FreeWagersJpa;
import org.wuphf.assessment.model.jpa.TransactionJpa;
import org.wuphf.assessment.model.mapper.TransactionMapper;
import org.wuphf.assessment.repository.FreeWagersRepository;
import org.wuphf.assessment.repository.TransactionRepository;
import org.wuphf.assessment.service.WuphfService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.wuphf.assessment.model.enums.TransactionTypes.DEDUCTION;
import static org.wuphf.assessment.model.enums.TransactionTypes.DEPOSIT;

@Service
public class WuphfServiceImpl implements WuphfService {

    public static final String SWORDFISH = "swordfish";
    private TransactionRepository transactionRepository;
    private FreeWagersRepository freeWagersRepository;

    @Autowired
    public WuphfServiceImpl(TransactionRepository transactionRepository, FreeWagersRepository freeWagersRepository) {
        this.transactionRepository = transactionRepository;
        this.freeWagersRepository = freeWagersRepository;
    }

    @Override
    public PlayerBalanceDto calcBalance(long playerId) {
        List<TransactionJpa> transactions = transactionRepository.findAllByPlayerId(playerId);
        PlayerBalanceDto balanceDto = new PlayerBalanceDto();

        transactions.forEach(z -> {
            if (isDeduction(z.getType()) && z.getFreeWagerUid() == 0) {
                deduction(balanceDto, z.getAmount());
            }
            if (isDeposit(z.getType())) {
                deposit(balanceDto, z.getAmount());
            }
        });
        return balanceDto;
    }

    @Override
    public LastTenDto getLastTen(LastTenDto dto) {
        List<TransactionJpa> transactions = new ArrayList<>();
        if(dto.getPassword().equals(SWORDFISH)){
            transactions = transactionRepository.findAllByPlayerIdOrderByUidDesc(dto.getPlayerId());
        }
        dto.setTopTen(TransactionMapper.buildDBTransactionDtos(transactions));
        return dto;
    }

    @Override
    public DeductDto deduct(DeductDto deductDto) throws Exception {
        PlayerBalanceDto balance = calcBalance(deductDto.getPlayerId());

        if ((balance.getBalance() - deductDto.getAmount()) < 0) {
            throw new InsufficientFundsException();
        }

        processPromoCodeIfPresent(deductDto);

        TransactionJpa existing = transactionRepository.findByTransactionId(deductDto.getTransactionId());
        Optional<FreeWagersJpa> freeWager = freeWagersRepository.findAllByPlayerIdOrderByUidDesc(deductDto.getPlayerId()).stream().findFirst();
        freeWager.ifPresent(z -> {
            List<TransactionJpa> transactions = transactionRepository.findAllByPlayerIdAndFreeWagerUid(deductDto.getPlayerId(), z.getUid());
            if (transactions.size() < z.getFreewagers()) {
                deductDto.setFreeWagerUid(z.getUid());
            }
        });

        if (existing == null) {
            transact(deductDto, DEDUCTION);
        }

        return deductDto;
    }

    private void processPromoCodeIfPresent(DeductDto deductDto) {
        if (StringUtils.hasLength(deductDto.getPromoCode())) {
            FreeWagersJpa freeWagersJpa = new FreeWagersJpa();
            freeWagersJpa.setPlayerId(deductDto.getPlayerId());
            freeWagersJpa.setFreewagers(PromoTypes.getAmountForCode(deductDto.getPromoCode()));//uses the PromoTypes for configurable promo codes.
            freeWagersRepository.save(freeWagersJpa);
        }
    }

    @Override
    public DepositDto deposit(DepositDto depositDto) {

        TransactionJpa existing = transactionRepository.findByTransactionId(depositDto.getTransactionId());

        if (existing == null) {
            transact(depositDto, DEPOSIT);
        }

        return depositDto;
    }

    private void transact(AbstractTransactionDto dto, TransactionTypes type) {
        TransactionJpa jpa = new TransactionJpa();
        jpa.setTransactionId(dto.getTransactionId());
        jpa.setPlayerId(dto.getPlayerId());
        jpa.setType(type.getKey());
        jpa.setAmount(dto.getAmount());
        if (dto instanceof DeductDto) {
            jpa.setFreeWagerUid(((DeductDto) dto).getFreeWagerUid());
        }
        transactionRepository.save(jpa);
    }

    private void deduction(PlayerBalanceDto balanceDto, double amount) {
        balanceDto.setBalance(balanceDto.getBalance() - amount);
    }

    private void deposit(PlayerBalanceDto balanceDto, double amount) {
        balanceDto.setBalance(balanceDto.getBalance() + amount);
    }


    private boolean isDeduction(String val) {
        return val.equals(DEDUCTION.getKey());
    }

    private boolean isDeposit(String val) {
        return val.equals(DEPOSIT.getKey());
    }


}
