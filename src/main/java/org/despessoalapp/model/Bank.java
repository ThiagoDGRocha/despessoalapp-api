package org.despessoalapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.despessoalapp.dto.BankDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data public class Bank extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bank")
    @SequenceGenerator(name = "seq_bank", sequenceName = "seq_bank", allocationSize = 1)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private LocalDate paymentDay;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Balance> balances;

    public Bank(BankDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.paymentDay = dto.getPaymentDay();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;
        if (!super.equals(o)) return false;
        Bank bank = (Bank) o;
        return Objects.equals(getId(), bank.getId()) &&
                Objects.equals(getName(), bank.getName()) &&
                Objects.equals(getDescription(), bank.getDescription()) &&
                Objects.equals(getPaymentDay(), bank.getPaymentDay()) &&
                Objects.equals(getTransactions(), bank.getTransactions()) &&
                Objects.equals(getBalances(), bank.getBalances());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getDescription(), getPaymentDay(), getTransactions(), getBalances());
    }
}
