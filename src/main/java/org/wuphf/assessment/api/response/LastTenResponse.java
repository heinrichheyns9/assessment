package org.wuphf.assessment.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LastTenResponse {

    private List<TransactionData> transactions;

}
