package org.wuphf.assessment.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class TransactionJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private long uid;

    @Column(name = "transactionId")
    private long transactionId;

    @Column(name = "playerId")
    private long playerId;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private double amount;

    @Column(name = "freeWagerUid")
    private long freeWagerUid;

}
