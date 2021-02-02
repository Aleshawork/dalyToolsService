package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;

import java.util.List;

public interface PersonService {
    Person findByName(String name);

    Person findById(int id);

    Person add(Person person);

    Person deleteByName(String name);

    List<Person> findAll();
}
