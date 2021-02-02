package com.dalyTools.dalyTools.DAO.Repository;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person deleteByName(String name);

    Person findByName(String name);
}
