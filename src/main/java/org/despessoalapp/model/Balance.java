package org.despessoalapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data public class Balance extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_balance")
    @SequenceGenerator(name = "seq_balance", sequenceName = "seq_balance", allocationSize = 1)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Bank bank;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private User user;

    @NotBlank
    private Double debit;

    @NotBlank
    private Double credit;

    public Balance(Balance bal) {
        this.id = null;
        this.bank = bal.getBank();
        this.user = bal.getUser();
        this.debit = bal.getDebit();
        this.credit = bal.getCredit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance)) return false;
        if (!super.equals(o)) return false;
        Balance balance = (Balance) o;
        return Objects.equals(getId(), balance.getId()) &&
                Objects.equals(getBank(), balance.getBank()) &&
                Objects.equals(getUser(), balance.getUser()) &&
                Objects.equals(getDebit(), balance.getDebit()) &&
                Objects.equals(getCredit(), balance.getCredit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getBank(), getUser(), getDebit(), getCredit());
    }
}
