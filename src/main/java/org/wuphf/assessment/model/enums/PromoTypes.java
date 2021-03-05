package org.wuphf.assessment.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PromoTypes {
    PAPEER("paper", 5),
    NONE("", 0);

    private String code;
    private long amount;

    public static long getAmountForCode(String code) {
        return Arrays.stream(values()).filter(z -> z.getCode().equals(code)).findFirst().orElse(NONE).getAmount();
    }

}
