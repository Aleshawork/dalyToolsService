package com.dalyTools.dalyTools.DAO.payload;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonPayLoadDto {
    private String name;
    private String sername;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String role;

    PersonPayLoadDto(Person person){
        this.name= person.getName();;
        this.sername=person.getSername();
        this.username=person.getUsername();
        this.email=person.getEmail();
        this.password=person.getPassword();
        this.role=person.getRole().getName();
    }
}
