package org.wuphf.assessment.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponse {

    private long transactionId;
    private long playerId;
    private double amount;

}
