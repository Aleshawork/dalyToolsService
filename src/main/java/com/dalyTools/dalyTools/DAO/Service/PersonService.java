package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import com.dalyTools.dalyTools.DAO.Repository.RoleRepository;
import com.dalyTools.dalyTools.DAO.dto.PersonDto;
import com.dalyTools.dalyTools.exceptions.NotFoundException;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }


    public Person add(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findByUserName(String userName){return personRepository.findByUsername(userName);}


    public Person deleteByName(String name) {
        return personRepository.deleteByName(name);
    }


    public List<Person> findAll() {
        return personRepository.findAll();
    }


    public Person registerNewPerson(PersonDto personDto){
        Person registerPerson = new Person();
        registerPerson.setName(personDto.getName());
        registerPerson.setSername(personDto.getSername());
        registerPerson.setUsername(personDto.getUsername());
        registerPerson.setEmail(personDto.getEmail());
        registerPerson.setActivationCode(UUID.randomUUID().toString());
        registerPerson.setDataCreationCode(LocalDateTime.now());
        registerPerson.setRole(roleRepository.findByName("ROLE_PERSON").orElseThrow(() -> { throw new NoSuchElementException("No such role found.");
        }));
        String encodedPassword = bCryptPasswordEncoder.encode(personDto.getPassword());
        registerPerson.setPassword(encodedPassword);
        //TODO: send email message


       return personRepository.save(registerPerson);


    }

    public void activateUser(String encodedUserActivationCode) {

        Person activatedPerson = personRepository.findByActivationCode(encodedUserActivationCode).orElseThrow(
                () -> { throw new NotFoundException(/*"Activation code not found"*/);}
        );

        activatedPerson.setActivationCode(null);

        personRepository.save(activatedPerson);

    }

}
