package com.projects.todolist.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.projects.todolist.models.Task;
import jakarta.transaction.Transactional;

@Repository
public interface TasksRepository extends CrudRepository<Task, String> {
    @Query(value = "SELECT * FROM todo", nativeQuery = true)
    public List<Task> findAllTaskInToDo();

    @Query(value = "SELECT * FROM done", nativeQuery = true)
    public List<Task> findAllTaskInDone();
    
    @Query(value = "SELECT * FROM cancel", nativeQuery = true)
    public List<Task> findAllTaskInCancel();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO todo VALUES (:id, :task, :Exp)", nativeQuery = true)
    public void saveTaskToDo(@Param("id") String id, @Param("task") String task, @Param("Exp") String date);

    @Query(value = "SELECT COUNT(*) > 0 FROM todo WHERE id = :id", nativeQuery = true)
    public boolean existInToDoById(@Param("id") String id);

    @Query(value = "SELECT COUNT(*) > 0 FROM done WHERE id = :id", nativeQuery = true)
    public boolean existInDoneById(@Param("id") String id);

    @Query(value = "SELECT COUNT(*) > 0 FROM cancel WHERE id = :id", nativeQuery = true)
    public boolean existInCancelById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE todo t SET t.task = :task, t.exp = :Exp WHERE t.id = :id", nativeQuery = true)
    public void updateTaskToDo(@Param("id") String id, @Param("task") String task, @Param("Exp") String date);

    @Query(value = "SELECT * FROM todo t WHERE t.id = :id", nativeQuery = true)
    public Task findTaskInToDoById(@Param("id") String id);

    @Query(value = "SELECT * FROM done t WHERE t.id = :id", nativeQuery = true)
    public Task findTaskInDoneById(@Param("id") String id);

    @Query(value = "SELECT * FROM cancel t WHERE t.id = :id", nativeQuery = true)
    public Task findTaskInCancelById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todo t WHERE t.id = :id", nativeQuery = true)
    public void deleteTaskToDo(@Param("id") String id);

    @Modifying
    @Query(value = "DELETE FROM done t WHERE t.id = :id", nativeQuery = true)
    public void deleteTaskDone(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cancel t WHERE t.id = :id", nativeQuery = true)
    public void deleteTaskCancel(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO done VALUES (:id, :task, :Exp)", nativeQuery = true)
    public void saveTaskDone(@Param("id") String id, @Param("task") String task, @Param("Exp") String date);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cancel VALUES (:id, :task, :Exp)", nativeQuery = true)
    public void saveTaskCancel(@Param("id") String id, @Param("task") String task, @Param("Exp") String date);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todo", nativeQuery = true)
    public void clearToDo();
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM done", nativeQuery = true)
    public void clearDone();
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cancel", nativeQuery = true)
    public void clearCancel();
}
