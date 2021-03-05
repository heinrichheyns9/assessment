package org.wuphf.assessment.model.mapper;

import org.wuphf.assessment.api.request.DeductRequest;
import org.wuphf.assessment.api.response.DeductResponse;
import org.wuphf.assessment.model.dto.DeductDto;

public class DeductMapper {

    public static DeductDto buildDeductDto(DeductRequest request) {
        DeductDto dto = new DeductDto();
        dto.setAmount(request.getAmount());
        dto.setPlayerId(request.getPlayerId());
        dto.setTransactionId(request.getTransactionId());
        dto.setPromoCode(request.getPromoCode());
        return dto;
    }

    public static DeductResponse buildDeductResponse(DeductDto deductDto) {
        DeductResponse response = new DeductResponse();
        response.setAmount(deductDto.getAmount());
        response.setPlayerId(deductDto.getPlayerId());
        response.setTransactionId(deductDto.getTransactionId());
        response.setPromoCode(deductDto.getPromoCode());
        return response;
    }

}
