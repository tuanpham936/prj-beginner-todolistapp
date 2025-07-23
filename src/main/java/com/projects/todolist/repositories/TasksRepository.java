package com.projects.todolist.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.projects.todolist.models.Task;

@Repository
public interface TasksRepository extends CrudRepository<Task, String> {
    @Query("SELECT * FROM todo")
    public List<Task> findAllTaskInToDo();

    @Query("SELECT * FROM done")
    public List<Task> findAllTaskInDone();
    
    @Query("SELECT * FROM cancel")
    public List<Task> findAllTaskInCancel();
}
