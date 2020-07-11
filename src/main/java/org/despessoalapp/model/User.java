package org.despessoalapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.despessoalapp.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data public class User extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1)
    private Integer id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    @Column(updatable = false)
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Balance> balances;

    public User(UserDto dto) {
        this.fullName = dto.getFullName();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getFullName(), user.getFullName()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getTransactions(), user.getTransactions()) &&
                Objects.equals(getBalances(), user.getBalances());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getFullName(), getPassword(), getEmail(), getTransactions(), getBalances());
    }
}
