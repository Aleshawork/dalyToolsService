package com.dalyTools.dalyTools.Securityty;


import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Service.PersonServiceOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDatailService implements UserDetailsService {

 private PersonServiceOptional personService;

    @Autowired
    public JwtUserDatailService(PersonServiceOptional personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Person person= personService.findByUserName(name).orElseThrow(()->new UsernameNotFoundException("User with given username doesn't exist."));

        return  JwtUserFactory.createPerson(person);


    }
}
