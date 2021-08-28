package com.dalyTools.dalyTools.DAO.Repository.taskRepo;

import com.dalyTools.dalyTools.DAO.Entity.task.DayTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface DayTaskRepository extends  JpaRepository<DayTask,Long> {

}
