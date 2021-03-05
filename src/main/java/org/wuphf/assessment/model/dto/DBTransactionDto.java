package org.wuphf.assessment.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DBTransactionDto {

    private long uid;
    private long freewagerUid;
    private long transactionId;
    private long playerId;
    private String type;
    private double amount;

}
