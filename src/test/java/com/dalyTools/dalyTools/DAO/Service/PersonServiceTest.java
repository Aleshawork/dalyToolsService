package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Test
    void findById() {
        assertNotNull(personService);
        when(personService.findById(3)).thenReturn(DaoTestData.aPerson());
        assertEquals("ALEXEY",personService.findById(3).get().getUsername());
    }


    @Test
    void findAll() {
        when(personService.findAll()).thenReturn(DaoTestData.aListOfPerson());
        assertEquals(2,personService.findAll().size());
    }
}