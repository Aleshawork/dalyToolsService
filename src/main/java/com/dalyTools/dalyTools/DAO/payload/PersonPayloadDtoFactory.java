package com.dalyTools.dalyTools.DAO.payload;

import com.dalyTools.dalyTools.DAO.Entity.Person;

public class PersonPayloadDtoFactory {
//TODO: можно через instanceof определять roleDto
    public static PersonPayLoadDto createPersonPayLodDto(Person person){
        return new PersonPayLoadDto(person);

    }
}
