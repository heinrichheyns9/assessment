package org.wuphf.assessment.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionTypes {

    DEDUCTION("DED"), DEPOSIT("DEP");

    String key;

}
