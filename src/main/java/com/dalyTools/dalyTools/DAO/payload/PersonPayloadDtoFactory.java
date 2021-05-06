package com.dalyTools.dalyTools.DAO.payload;

import com.dalyTools.dalyTools.DAO.Entity.Person;

public class PersonPayloadDtoFactory {
    public static PersonPayLoadDto createPersonPayLodDto(Person person){
        return new PersonPayLoadDto(person);

    }
}
