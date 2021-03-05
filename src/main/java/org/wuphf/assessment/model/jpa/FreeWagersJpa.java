package org.wuphf.assessment.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "FREEWAG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class FreeWagersJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private long uid;

    @Column(name = "playerId")
    private long playerId;

    @Column(name = "freewagers")
    private long freewagers;

}
