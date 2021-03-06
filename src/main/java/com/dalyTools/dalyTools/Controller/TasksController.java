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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping("api/person/task")
public class TasksController {

    @Autowired
    private TaskService taskService;



    @PostMapping("/getall")
    ResponseEntity<AllTaskDto> getAllTaskByDate(@RequestBody TaskPayloadDTO taskPayloadDTO){
        Date date=  Date.valueOf(taskPayloadDTO.getDate());
        return  taskService.getAllTask(date);
    }

    @PostMapping("/getweek")
    ResponseEntity<WeekTaskDto> getTaskByWeek(@RequestBody IntervalTaskPayloadDTO intervalTaskPayloadDTO ){

        return taskService.getTaskByWeek(intervalTaskPayloadDTO.getFirstDate()
                ,intervalTaskPayloadDTO.getLastDate());
    }

    @PostMapping("/addone")
    ResponseEntity<HttpStatus> addNewTask(@RequestBody TaskDto taskDto){
        return taskService.addTask(taskDto.getDate(),taskDto.getPriority(),taskDto.getTask());
    }





}
