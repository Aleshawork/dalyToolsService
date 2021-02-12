package com.dalyTools.dalyTools.Securityty;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserFactory {

    public static JwtUser createPerson(Person person) {

        return new JwtUser(
                person.getUsername(),
                person.getPassword(),
                new SimpleGrantedAuthority(person.getRole().getName())
        );

    }
}
