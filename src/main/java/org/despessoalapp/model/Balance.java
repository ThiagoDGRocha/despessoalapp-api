package org.despessoalapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@Data public class Balance extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_balance")
    @SequenceGenerator(name = "seq_balance", sequenceName = "seq_balance", allocationSize = 1)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Bank bank;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private User user;

    private Double debit;

    private Double credit;

    private ZonedDateTime creditDate;

}
