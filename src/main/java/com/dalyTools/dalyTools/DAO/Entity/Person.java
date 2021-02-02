package com.dalyTools.dalyTools.DAO.Entity;




import javax.persistence.*;


@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String name;

    @Column(name = "sername")
    private String sername;

    @Column(name="email")
    private String email;

    public Person(int id, String name, String sername, String email) {
        this.id = id;
        this.name = name;
        this.sername = sername;
        this.email = email;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id+'-'+name+'-'+sername+'-'+email;
    }
}