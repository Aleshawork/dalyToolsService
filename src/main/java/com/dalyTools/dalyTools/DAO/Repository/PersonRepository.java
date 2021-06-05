package com.dalyTools.dalyTools.DAO.Repository;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person deleteByName(String name);
    Optional<Person> findByActivationCode(String username);

    Optional<Person> findByUsername(String userName);



}
