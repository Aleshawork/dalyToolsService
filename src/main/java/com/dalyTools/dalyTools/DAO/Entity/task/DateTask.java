package com.dalyTools.dalyTools.DAO.Entity.task;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="date_task")
public class DateTask {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "fr_id")
    private Person person;

    private Date date;

    @Column(name = "kol_task")
    private int countOfTask;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "dateTask")
    private List<DayTask> dayTasks = new ArrayList<>();


    public DateTask(Person person, Date date, int countOfTask, List<DayTask> dayTasks) {
        this.person = person;
        this.date = date;
        this.countOfTask = countOfTask;
        this.dayTasks = dayTasks;
    }

    public DateTask(Person person, Date date, int countOfTask) {
        this.person = person;
        this.date = date;
        this.countOfTask = countOfTask;
    }
}
