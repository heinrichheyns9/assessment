package org.wuphf.assessment.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LastTenDto {

    private long playerId;
    private String password;
    private List<DBTransactionDto> topTen;

}
