package com.dalyTools.dalyTools.DAO.Service;

import com.dalyTools.dalyTools.DAO.Repository.TaskRepository;
import com.dalyTools.dalyTools.DAO.dto.AllTaskDto;
import com.dalyTools.dalyTools.DAO.dto.StatusDTO;
import com.dalyTools.dalyTools.DAO.dto.WeekTaskDto;
import com.dalyTools.dalyTools.Securityty.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.*;

@Service
public class TaskService implements TaskRepository {

    private String SELECT_ALL_TASK = "select task,priority from day_task where fr_nom = (select id from date_task where fr_id=( select id from person where username=?) and date=?);";
    private String SELECT_TASK_BY_WEEK = "select date_task.date, day_task.task from day_task left join date_task on date_task.id=day_task.fr_nom where date_task.date between ? and ? and date_task.fr_id = (select id from person where username=?) order by date_task.date, day_task.priority;";
    private String INSER_TASK = "";

    private String userName;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public ResponseEntity<AllTaskDto> getAllTask(Date date) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtuser = (JwtUser) authentication.getPrincipal();
       userName = jwtuser.getUsername();

        //  вытаскиваем данные из SecurityContextHolder
        HashMap<Integer, String> mapOfTask = jdbcTemplate.query(
                SELECT_ALL_TASK,
                new Object[]{userName, date},
                (ResultSet rs) -> {
                    HashMap<Integer, String> map = new HashMap<>();
                    while (rs.next()) {
                        map.put(rs.getInt("priority"), rs.getString("task"));
                    }
                    return map;
                }
        );
        return ResponseEntity.ok(new AllTaskDto(
                date,
                mapOfTask
        ));
    }

    @Override
    public ResponseEntity<StatusDTO> setNewTask(Date date, String task) {

        return null;
    }


    // задачи идут по порядку приоритетности по полю priority
    @Override
    public ResponseEntity<WeekTaskDto> getTaskByWeek(String firstDate, String lastDate) {
        Date startDate = Date.valueOf(firstDate);
        Date endDate =Date.valueOf(lastDate);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtuser = (JwtUser) authentication.getPrincipal();
        userName = jwtuser.getUsername();

        HashMap<Date, ArrayList<String>> weekmap = jdbcTemplate.query(
                SELECT_TASK_BY_WEEK,
                new Object[]{startDate, endDate, userName},
                (ResultSet rs) -> {
                    HashMap<Date, ArrayList<String>> map = new HashMap<>();
                    while (rs.next()) {
                        if(map.containsKey(rs.getDate("date"))){
                            map.get(rs.getDate("date")).add(rs.getString("task"));
                        }
                        else {
                            ArrayList<String> list = new ArrayList<>();
                            list.add(rs.getString("task"));
                            map.put(rs.getDate("date"),list);

                        }
                    }
                    return map;
                }
        );
        WeekTaskDto weekTaskDto = new WeekTaskDto();
        weekTaskDto.setWeekmap(weekmap);


        return ResponseEntity.ok(weekTaskDto);
    }
}

