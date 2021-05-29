package com.dalyTools.dalyTools.DAO.Repository;

import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.Map;

public interface TaskRepository {

    ResponseEntity<AllTaskDto> getAllTask(Integer fr_id,Date date);
    void setNewTask(Map<Integer,String> mapOfTask);

}
