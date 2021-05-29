package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Repository.TaskRepository;
import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService implements TaskRepository {

    private String SELECT_ALL_TASK = "select  task,priority from day_task where fr_nom = (select id from date_task where fr_id =? and date=?);";
    private String INSERT_TASK = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<AllTaskDto> getAllTask(Integer fr_id,Date date) {


        HashMap<Integer,String>  mapOfTask = jdbcTemplate.query(
                SELECT_ALL_TASK,
                new Object[]{fr_id,date},
                (ResultSet rs) ->{

                    HashMap<Integer,String> map =new HashMap<>();
                    while(rs.next()){
                        map.put(rs.getInt("priority"),rs.getString("task"));
                    }
                    return map;
                }
        );

        return  ResponseEntity.ok(new AllTaskDto(
                date,
                mapOfTask

        ));
    }

    @Override
    public void setNewTask(Map<Integer, String> mapOfTask) {

    }
}
