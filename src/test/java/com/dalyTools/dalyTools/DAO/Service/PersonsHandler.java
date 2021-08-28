package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Entity.Role;
import com.dalyTools.dalyTools.DAO.Entity.task.DateTask;
import com.dalyTools.dalyTools.DAO.Entity.task.DayTask;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PersonsHandler {
    public static Person getPerson(){
        List<DateTask> dateTaskList = new ArrayList<>();
        Person person = new Person(
                12L,"Egor","Voronov","EGOR","voronov_egor@mail.ru","password",null,null,
                new Role(12L,"ROLE_ADMIN")
                ,dateTaskList
        );


        DateTask dateTask1 = new DateTask(person, Date.valueOf("2021-08-28"),2);
        DateTask dateTask2 = new DateTask(person, Date.valueOf("2021-08-30"),2);

        //DateTask1
        DayTask dayTask1_1 = new DayTask(dateTask1,"Погулять с собакой",1);
        DayTask dayTask1_2 = new DayTask(dateTask1,"Сходить в магазин",2);

        //DateTask2
        DayTask dayTask2_1 = new DayTask(dateTask2,"Соревнования",2);
        DayTask dayTask2_2 = new DayTask(dateTask2,"День рождение брата",1);


        List<DayTask> dayTaskList1 = new ArrayList<>();
        dayTaskList1.add(dayTask1_1);
        dayTaskList1.add(dayTask1_2);

        List<DayTask> dayTaskList2 = new ArrayList<>();
        dayTaskList2.add(dayTask2_1);
        dayTaskList2.add(dayTask2_2);

        dateTask1.setDayTasks(dayTaskList1);
        dateTask2.setDayTasks(dayTaskList2);

        dateTaskList.add(dateTask1);
        dateTaskList.add(dateTask2);

        return person;

    }

}
