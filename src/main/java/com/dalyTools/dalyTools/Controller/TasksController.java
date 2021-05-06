package com.dalyTools.dalyTools.Controller;

import com.dalyTools.dalyTools.DAO.Service.TaskService;
import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import com.dalyTools.dalyTools.DAO.payload.DatePayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
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


    // todo: change String date -> sql.Date
    @PostMapping("/getall")
    ResponseEntity<AllTaskDto> getAllTaskByDate(@RequestBody DatePayloadDto datePayloadDto){
        Date date=  Date.valueOf(datePayloadDto.getDate());
        return  taskService.getAllTask(date);

    }


}
