package org.despessoalapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.despessoalapp.dto.TransactionDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.NotFoundException;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data public class Transaction extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction")
    @SequenceGenerator(name = "seq_transaction", sequenceName = "seq_transaction", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Bank bank;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private User user;

    @NotBlank
    private String operation;

    @NotBlank
    private String description;

    @NotBlank
    private String debCred;

    @NotBlank
    private Double value;

    public Transaction(TransactionDto dto) {
        this.bank = (Bank) Bank.findByIdOptional(dto.getBankId(), LockModeType.NONE).
                orElseThrow(() -> new NotFoundException("Bank not found"));
        this.user = (User) User.findByIdOptional(dto.getUserId(), LockModeType.NONE).
                orElseThrow(() -> new NotFoundException("User not found"));
        this.operation = dto.getOperation();
        this.description = dto.getDescription();
        this.debCred = dto.getDebCred();
        this.value = dto.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        if (!super.equals(o)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getBank(), that.getBank()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getOperation(), that.getOperation()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDebCred(), that.getDebCred()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getBank(), getUser(), getOperation(), getDescription(), getDebCred(), getValue());
    }
}
