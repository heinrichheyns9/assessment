package org.wuphf.assessment.model.mapper;

import org.wuphf.assessment.api.request.DepositRequest;
import org.wuphf.assessment.api.response.DepositResponse;
import org.wuphf.assessment.model.dto.DepositDto;

public class DepositMapper {

    public static DepositDto buildDeductDto(DepositRequest request) {
        DepositDto dto = new DepositDto();
        dto.setAmount(request.getAmount());
        dto.setPlayerId(request.getPlayerId());
        dto.setTransactionId(request.getTransactionId());
        return dto;
    }

    public static DepositResponse buildDepositResponse(DepositDto depositDto) {
        DepositResponse response = new DepositResponse();
        response.setAmount(depositDto.getAmount());
        response.setPlayerId(depositDto.getPlayerId());
        response.setTransactionId(depositDto.getTransactionId());
        return response;
    }

}
