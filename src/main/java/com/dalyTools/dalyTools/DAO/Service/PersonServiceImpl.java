package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private PersonRepository personRepository;
    @Override
    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person deleteByName(String name) {
        return personRepository.deleteByName(name);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
