package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Entity.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoTestData {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    public static Optional<Person> aPerson(){

        return Optional.of(
                new Person(3,"Alexey","Borisov","ALEXEY",
                        "borisov_alexey@mail.ru",
                        bCryptPasswordEncoder.encode("password"),
                        " ",
                        LocalDateTime.now(),
                        new Role(
                                123,"USER"
                        ))
        );
    }

    public static List<Person> aListOfPerson(){
        List<Person> list = new ArrayList<>();
        list.add(new Person(3,"Alexey","Borisov","ALEXEY",
                "borisov_alexey@mail.ru",
                bCryptPasswordEncoder.encode("password"),
                " ",
                LocalDateTime.now(),
                new Role(
                        1,"USER"
                )));
        list.add(new Person(5,"Irina","Buzova","IRINA_228",
                "irina_buzova@mail.ru",
                bCryptPasswordEncoder.encode("password"),
                " ",
                LocalDateTime.now(),
                new Role(
                        1,"USER"
                )));

        return list;
    }
}
