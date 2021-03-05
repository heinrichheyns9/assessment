package org.wuphf.assessment.service;

import org.springframework.stereotype.Service;
import org.wuphf.assessment.model.dto.DeductDto;
import org.wuphf.assessment.model.dto.DepositDto;
import org.wuphf.assessment.model.dto.LastTenDto;
import org.wuphf.assessment.model.dto.PlayerBalanceDto;

@Service
public interface WuphfService {

    PlayerBalanceDto calcBalance(long playerId);

    LastTenDto getLastTen(LastTenDto dto);

    DeductDto deduct(DeductDto dto) throws Exception;

    DepositDto deposit(DepositDto dto);

}
