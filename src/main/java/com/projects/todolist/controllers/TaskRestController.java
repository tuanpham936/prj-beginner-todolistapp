package com.projects.todolist.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.projects.todolist.models.Task;
import com.projects.todolist.repositories.TasksRepository;
import com.projects.todolist.services.TaskServices;
import com.projects.todolist.utils.Pair;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TaskRestController {
    private final TaskServices taskServices;
    private final TasksRepository tasksRepository;
    
    public TaskRestController(TaskServices taskServices, TasksRepository tasksRepository) {
        this.taskServices = taskServices;
        this.tasksRepository = tasksRepository;
    }

    @GetMapping("/search/{list}/{name}")
    public List<Task> getTasksByName(@PathVariable String list, @PathVariable String name) {
        return null;
    }

    @DeleteMapping("/clear/{tab}")
    public void clearTab(@PathVariable String tab) {
        if (tab.equals("todo")) {
            tasksRepository.clearToDo();
        }
        else if (tab.equals("done")) {
            tasksRepository.clearDone();
        }
        else if (tab.equals("cancel")) {
            tasksRepository.clearCancel();
        }
    }
    
    @GetMapping("/todo")
    public List<Task> getAllToDo() {
        return tasksRepository.findAllTaskInToDo();
    }

    @PostMapping("/todo/add")
    public Task addToDo(@RequestBody Task task) {
        return taskServices.addToDo(task);
    }
    
    @PutMapping("/todo/update")
    public ResponseEntity<Task> updateToDo(@RequestBody Task task) {
        Pair<Boolean, Task> t = taskServices.updateToDo(task);
        return t.getKey() ? ResponseEntity.status(201).body(t.getValue()) : ResponseEntity.status(200).body(t.getValue());
    }

    @DeleteMapping("/todo/done/{id}")
    public ResponseEntity<Task> doneToDo(@PathVariable String id) {
        return taskServices.transferTaskFromToDoToDone(id) ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/todo/cancel/{id}")
    public ResponseEntity<Task> cancelToDo(@PathVariable String id) {
        return taskServices.transferTaskFromToDoToCancel(id) ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/done")
    public List<Task> getAllDoneTasks() {
        return tasksRepository.findAllTaskInDone();
    }
    
    @DeleteMapping("/done/rollback/{id}")
    public ResponseEntity<Task> rollbackDone(@PathVariable String id) {
        return taskServices.transferTaskFromDoneToToDo(id) ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/cancel")
    public List<Task> getAllCancelTasks() {
        return tasksRepository.findAllTaskInCancel();
    }
    
    @DeleteMapping("/cancel/rollback/{id}")
    public ResponseEntity<Task> rollbackCancel(@PathVariable String id) {
        return taskServices.transferTaskFromCancelToToDo(id) ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
