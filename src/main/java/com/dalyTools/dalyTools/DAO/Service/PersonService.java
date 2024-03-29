package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import com.dalyTools.dalyTools.DAO.Repository.RoleRepository;
import com.dalyTools.dalyTools.DAO.dto.PersonDto;
import com.dalyTools.dalyTools.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PersonService  {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @Transactional
    public Person registerNewPerson(PersonDto personDto){
        Person registerPerson = new Person();
        registerPerson.setName(personDto.getName());
        registerPerson.setSername(personDto.getSername());
        registerPerson.setUsername(personDto.getUsername());
        registerPerson.setEmail(personDto.getEmail());
        registerPerson.setActivationCode(UUID.randomUUID().toString());
        registerPerson.setDataCreationCode(LocalDateTime.now());

        registerPerson.setRole(roleRepository.findByName(personDto.getRole()).get());
        String encodedPassword = bCryptPasswordEncoder.encode(personDto.getPassword());
        registerPerson.setPassword(encodedPassword);
        Person person = personRepository.save(registerPerson);
        logger.info("Register new user,  name:{}  userName:{}",personDto.getName(),personDto.getUsername());

        //TODO: send email message

       return person ;


    }

    @Transactional
    public void activateUser(String encodedUserActivationCode) throws ApiException {

        Person activatedPerson = personRepository.findByActivationCode(encodedUserActivationCode).get();

        activatedPerson.setActivationCode(null);

        personRepository.save(activatedPerson);

    }

}
