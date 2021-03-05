package org.wuphf.assessment.model.mapper;

import org.wuphf.assessment.api.response.TransactionData;
import org.wuphf.assessment.model.dto.DBTransactionDto;
import org.wuphf.assessment.model.jpa.TransactionJpa;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    private static DBTransactionDto buildDBTransactionDto(TransactionJpa jpa) {
        DBTransactionDto dto = new DBTransactionDto();
        dto.setTransactionId(jpa.getTransactionId());
        dto.setAmount(jpa.getAmount());
        dto.setType(jpa.getType());
        dto.setUid(jpa.getUid());
        dto.setPlayerId(jpa.getPlayerId());
        dto.setFreewagerUid(jpa.getFreeWagerUid());

        return dto;
    }

    public static List<DBTransactionDto> buildDBTransactionDtos(List<TransactionJpa> jpa) {
        return jpa.stream().map(TransactionMapper::buildDBTransactionDto).limit(10).collect(Collectors.toList());
    }

    private static TransactionData buildTransactionData(DBTransactionDto dto) {
        TransactionData data = new TransactionData();
        data.setTransactionId(dto.getTransactionId());
        data.setAmount(dto.getAmount());
        data.setType(dto.getType());
        data.setUid(dto.getUid());
        data.setPlayerId(dto.getPlayerId());
        data.setFreewagerUid(dto.getFreewagerUid());

        return data;
    }

    public static List<TransactionData> buildTransactionDatas(List<DBTransactionDto> dtos) {
        return dtos.stream().map(TransactionMapper::buildTransactionData).collect(Collectors.toList());
    }

}
