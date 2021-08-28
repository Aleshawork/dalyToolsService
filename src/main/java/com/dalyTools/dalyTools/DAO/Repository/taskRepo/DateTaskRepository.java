package com.dalyTools.dalyTools.DAO.Repository.taskRepo;

import com.dalyTools.dalyTools.DAO.Entity.task.DateTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DateTaskRepository extends JpaRepository<DateTask,Long> {
    Optional<DateTask> findByPersonIdAndDate(Long id, Date date);

}
