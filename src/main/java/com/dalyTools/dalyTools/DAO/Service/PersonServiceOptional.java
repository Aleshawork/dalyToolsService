package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceOptional {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceOptional(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findByUserName(String name){
        return personRepository.findByUsername(name);
    }
}
