package com.dalyTools.dalyTools.Controller;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Service.PersonService;
import com.dalyTools.dalyTools.DAO.dto.PersonDto;
import com.dalyTools.dalyTools.DAO.payload.PersonPayLoadDto;
import com.dalyTools.dalyTools.DAO.payload.PersonPayloadDtoFactory;
import com.dalyTools.dalyTools.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("api/signup")
public class PersonSignUPController {

    private PersonService personService;

    @Autowired
    public PersonSignUPController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/user")
    ResponseEntity<PersonPayLoadDto> signUpPerson(@RequestBody PersonDto personDto){

        if(personService.findByUserName(personDto.getUsername()).isPresent()){

            throw new NotFoundException("A user with this name already exists");
        }
        Person registerPerson=personService.registerNewPerson(personDto);

        return ResponseEntity.ok(PersonPayloadDtoFactory.createPersonPayLodDto(registerPerson));

    }



}
