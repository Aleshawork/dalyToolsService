package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Entity.task.DateTask;
import com.dalyTools.dalyTools.DAO.Entity.task.DayTask;
import com.dalyTools.dalyTools.DAO.Repository.PersonRepository;
import com.dalyTools.dalyTools.DAO.Repository.taskRepo.DateTaskRepository;
import com.dalyTools.dalyTools.DAO.Repository.taskRepo.DayTaskRepository;
import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import com.dalyTools.dalyTools.DAO.dto.WeekTaskDto;
import com.dalyTools.dalyTools.Securityty.JwtUser;
import com.dalyTools.dalyTools.exceptions.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final DateTaskRepository dateTaskRepository;
    private final DayTaskRepository dayTaskRepository;
    private final PersonRepository personRepository;


    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    public TaskService(DateTaskRepository dateTaskRepository, DayTaskRepository dayTaskRepository, PersonRepository personRepository) {
        this.dateTaskRepository = dateTaskRepository;
        this.dayTaskRepository = dayTaskRepository;
        this.personRepository = personRepository;

    }


    @Transactional
    public AllTaskDto getAllTask(Date date,String userName) {


        Map<Integer, String> mapOfTask = new HashMap<>();
        Optional<Person> person = personRepository.findByUsername(userName);

        if (person.isPresent()) {
            Person p = person.get();
            Optional<DateTask> first = p.getDateTasks().stream()
                    .filter(el -> el.getDate().equals(date))
                    .findFirst();
            if (first.isPresent()) {
                DateTask dateTask = first.get();
                mapOfTask = dateTask.getDayTasks().stream()
                        .collect(Collectors.toMap(DayTask::getPriority, DayTask::getTask));
            }

        } else {
            String msg = String.format("Person with name: %s in not found", userName);
            log.warn(msg);
            throw new ApiRequestException(msg);
        }

        return new AllTaskDto(
                date,
                mapOfTask
        );
    }


    // задачи в List идут по порядку приоритетности по полю priority
    @Transactional
    public WeekTaskDto getTaskByWeek(String firstDate, String lastDate,String userName) {
        Date startDate = Date.valueOf(firstDate);
        Date endDate = Date.valueOf(lastDate);


        Optional<Person> byUserName = personRepository.findByUsername(userName);
        if (byUserName.isPresent()) {
            Person person = byUserName.get();
            List<DateTask> collect = person.getDateTasks().stream()
                    .filter(el -> {
                                return el.getDate().getTime() >= startDate.getTime() && el.getDate().getTime() <= endDate.getTime();
                            }
                    ).collect(Collectors.toList());
            Map<Date, List<String>> collect1 = new HashMap<>();
            if (collect.size() > 0) {
                collect1 = collect.stream()
                        .collect(Collectors.toMap(
                                DateTask::getDate
                                , el -> el.getDayTasks().stream()
                                        .sorted(Comparator.comparingInt(DayTask::getPriority))
                                        .map(a -> a.getTask())
                                        .collect(Collectors.toList())
                        ));

            }
            return new WeekTaskDto(collect1);

        } else {
            String msg = String.format("Person with name: %s in not found", userName);
            log.warn(msg);
            throw new ApiRequestException(msg);
        }

    }


    @Transactional
    public void addTask(String date, int priority, String task,String userName) {
        Date dateOfTask = Date.valueOf(date);


        Optional<Person> byUserName = personRepository.findByUsername(userName);
        if (byUserName.isPresent()) {

            Person person = byUserName.get();
            Optional<DateTask> dateTask = dateTaskRepository.findByPersonIdAndDate(person.getId(), dateOfTask);
            if (dateTask.isPresent()) {
                DateTask newDateTask = dateTask.get();
                newDateTask.setCountOfTask(newDateTask.getCountOfTask() + 1);
                DayTask dayTask = new DayTask(newDateTask, task, priority);
                dayTaskRepository.save(dayTask);

            } else {
                // у человека не было ни одного task на эту дату
                DateTask addDateTask = new DateTask(person, dateOfTask, 1);
                DayTask dayTask = new DayTask(addDateTask, task, priority);
                dayTaskRepository.save(dayTask);
            }

        }

    }
}




