package com.dalyTools.dalyTools.DAO.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }
}
