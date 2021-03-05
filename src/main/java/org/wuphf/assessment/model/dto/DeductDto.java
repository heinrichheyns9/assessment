package org.wuphf.assessment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeductDto extends AbstractTransactionDto {

    private long freeWagerUid;

}
