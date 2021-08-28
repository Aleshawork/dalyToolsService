package com.dalyTools.dalyTools.Controller;

import com.dalyTools.dalyTools.DAO.Service.TaskService;
import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import com.dalyTools.dalyTools.DAO.dto.TaskDto;
import com.dalyTools.dalyTools.DAO.dto.WeekTaskDto;
import com.dalyTools.dalyTools.DAO.payload.IntervalTaskPayloadDTO;
import com.dalyTools.dalyTools.DAO.payload.TaskPayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("api/person/task")
public class TasksController {

    @Autowired
    private TaskService taskService;



    @PostMapping("/getall")
    AllTaskDto getAllTaskByDate(@RequestBody TaskPayloadDTO taskPayloadDTO, Authentication authentication){
        Date date=  Date.valueOf(taskPayloadDTO.getDate());
        return  taskService.getAllTask(date, authentication.getName());
    }

    @PostMapping("/getweek")
    WeekTaskDto getTaskByWeek(@RequestBody IntervalTaskPayloadDTO intervalTaskPayloadDTO,Authentication authentication ){

        return taskService.getTaskByWeek(
                intervalTaskPayloadDTO.getFirstDate()
                ,intervalTaskPayloadDTO.getLastDate()
                ,authentication.getName()
                );
    }

    @PostMapping("/addone")
    void addNewTask(@RequestBody TaskDto taskDto,Authentication authentication){
        taskService.addTask(
                taskDto.getDate()
                ,taskDto.getPriority()
                ,taskDto.getTask()
                , authentication.getName());
    }





}
