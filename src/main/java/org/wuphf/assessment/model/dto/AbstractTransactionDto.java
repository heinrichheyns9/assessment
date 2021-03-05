package org.wuphf.assessment.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractTransactionDto {

    private long transactionId;
    private long playerId;
    private double amount;
    private String promoCode;

}
