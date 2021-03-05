package org.wuphf.assessment.model.mapper;

import org.wuphf.assessment.api.request.LastTenRequest;
import org.wuphf.assessment.api.response.LastTenResponse;
import org.wuphf.assessment.model.dto.LastTenDto;

public class LastTenMapper {

    public static LastTenDto buildLastTenDto(LastTenRequest request) {
        LastTenDto dto = new LastTenDto();
        dto.setPlayerId(request.getPlayerId());
        dto.setPassword(request.getPassword());
        return dto;
    }

    public static LastTenResponse buildLastTenResponse(LastTenDto dto) {
        LastTenResponse response = new LastTenResponse();
        response.setTransactions(TransactionMapper.buildTransactionDatas(dto.getTopTen()));
        return response;
    }

}
