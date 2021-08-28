package com.dalyTools.dalyTools.DAO.Entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@Entity
@Table(name="day_task")
@NoArgsConstructor
public class DayTask {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="fr_nom")
    private DateTask dateTask;

    private String task;
    private int priority;


    public DayTask(DateTask dateTask, String task, int priority) {
        this.dateTask = dateTask;
        this.task = task;
        this.priority = priority;
    }
}
