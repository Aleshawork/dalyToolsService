package com.dalyTools.dalyTools.DAO.Repository;

import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import com.dalyTools.dalyTools.DAO.dto.StatusDTO;
import com.dalyTools.dalyTools.DAO.dto.WeekTaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.Map;

public interface TaskRepository {

    ResponseEntity<AllTaskDto> getAllTask(Date date);
    ResponseEntity<WeekTaskDto> getTaskByWeek(String firstDate, String lastDate);
    ResponseEntity<HttpStatus> addTask(String date,int priority,String task);


    void addStartTask(Date date, String username);
}
