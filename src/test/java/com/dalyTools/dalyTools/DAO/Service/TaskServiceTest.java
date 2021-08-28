package com.dalyTools.dalyTools.DAO.Service;
import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import com.dalyTools.dalyTools.DAO.dto.WeekTaskDto;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private PersonRepository testPersonRepository;



    @Autowired
    @InjectMocks
    private TaskService testTaskService;


    @Test
    void getAllTask() {
        when(testPersonRepository.findByUsername("EGOR")).thenReturn(
                Optional.of(PersonsHandler.getPerson())
        );

        Map<Integer,String> map= new HashMap<>();
        map.put(1,"Погулять с собакой");
        map.put(2,"Сходить в магазин");

        AllTaskDto canTake = new AllTaskDto(
                Date.valueOf("2021-08-28")
                ,map
        );

        AllTaskDto allTask = testTaskService.getAllTask(Date.valueOf("2021-08-28"),"EGOR");

        Assert.assertEquals(allTask,canTake);

    }

    @Test
    void getTaskByWeek() {

        when(testPersonRepository.findByUsername("EGOR")).thenReturn(
                Optional.of(PersonsHandler.getPerson())
        );

        Map<Date, List<String>> mapOfTasks = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("Погулять с собакой");
        list1.add("Сходить в магазин");

        list2.add("День рождение брата");
        list2.add("Соревнования");

        mapOfTasks.put(Date.valueOf("2021-08-28"),list1);
        mapOfTasks.put(Date.valueOf("2021-08-30"),list2);

        Assert.assertEquals(
                testTaskService.getTaskByWeek("2021-08-22","2021-08-30","EGOR")
                ,new WeekTaskDto(mapOfTasks));

    }


}