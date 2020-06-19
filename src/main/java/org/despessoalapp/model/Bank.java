package org.despessoalapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.despessoalapp.dto.BankDto;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class Bank extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bank")
    @SequenceGenerator(name = "seq_bank", sequenceName = "seq_bank", allocationSize = 1)
    private Integer id;

    private String name;

    private String description;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Balance> balances;

    public Bank(BankDto entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
