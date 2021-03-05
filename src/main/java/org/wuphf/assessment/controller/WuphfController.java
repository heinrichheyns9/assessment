package org.wuphf.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.wuphf.assessment.api.request.DeductRequest;
import org.wuphf.assessment.api.request.DepositRequest;
import org.wuphf.assessment.api.request.LastTenRequest;
import org.wuphf.assessment.api.response.DeductResponse;
import org.wuphf.assessment.api.response.DepositResponse;
import org.wuphf.assessment.api.response.LastTenResponse;
import org.wuphf.assessment.api.response.PlayerBalanceResponse;
import org.wuphf.assessment.exception.InsufficientFundsException;
import org.wuphf.assessment.model.dto.DeductDto;
import org.wuphf.assessment.model.dto.DepositDto;
import org.wuphf.assessment.model.dto.LastTenDto;
import org.wuphf.assessment.model.dto.PlayerBalanceDto;
import org.wuphf.assessment.model.mapper.DeductMapper;
import org.wuphf.assessment.model.mapper.DepositMapper;
import org.wuphf.assessment.model.mapper.LastTenMapper;
import org.wuphf.assessment.service.WuphfService;

import javax.validation.Valid;

@RestController
@RequestMapping("/wuphf")
public class WuphfController {

    private WuphfService wuphfService;

    @Autowired
    public WuphfController(WuphfService wuphfService) {
        this.wuphfService = wuphfService;
    }

    @InitBinder("deductRequest")
    public void initDeductValidator(WebDataBinder binder) {
        binder.addValidators();
    }

    @GetMapping("/player/balance/{playerid}")
    ResponseEntity<PlayerBalanceResponse> getPlayerBalance(@PathVariable long playerid) {
        PlayerBalanceDto balanceDto = wuphfService.calcBalance(playerid);
        return ResponseEntity.ok(new PlayerBalanceResponse(balanceDto.getBalance()));
    }

    @PostMapping(
            value = "/player/deduct",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<DeductResponse> deduct(@Valid @RequestBody DeductRequest deductRequest) {
        DeductDto dto = DeductMapper.buildDeductDto(deductRequest);
        try {
            dto = wuphfService.deduct(dto);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(418).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(DeductMapper.buildDeductResponse(dto));
    }

    @PostMapping(
            value = "/player/deposit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<DepositResponse> deposit(@Valid @RequestBody DepositRequest depositRequest) {
        DepositDto dto = DepositMapper.buildDeductDto(depositRequest);
        wuphfService.deposit(dto);
        return ResponseEntity.ok(DepositMapper.buildDepositResponse(dto));
    }

    @PostMapping(
            value = "/player/lastten",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<LastTenResponse> topTen(@Valid @RequestBody LastTenRequest lastTenRequest) {
        LastTenDto dto = LastTenMapper.buildLastTenDto(lastTenRequest);
        wuphfService.getLastTen(dto);
        return ResponseEntity.ok(LastTenMapper.buildLastTenResponse(dto));
    }

}
