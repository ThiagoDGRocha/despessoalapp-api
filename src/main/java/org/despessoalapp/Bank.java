package org.despessoalapp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data public class Bank extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bank")
    @SequenceGenerator(name = "seq_bank", sequenceName = "seq_bank", allocationSize = 1)
    public Integer id;
    private String name;
    private String description;

    @Override
    public boolean equals (Object o) {
        return this == o || getClass().isInstance(o) && getId() == ((Bank) o).getId();
    }

    @Override
    public int hashCode () {
        return Objects.hash(getId());
    }
}
