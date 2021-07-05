package com.dalyTools.dalyTools.DAO.Entity;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(generator = "increment")
    private long id;


    @Column(name = "first_name")
    private String name;

    @Column(name = "second_name")
    private String sername;

    @Column(name = "username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "data_creation_code")
    private LocalDateTime dataCreationCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


    public Person() {
    }
}