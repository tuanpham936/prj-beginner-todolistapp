package com.projects.todolist.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.projects.todolist.models.Task;
import com.projects.todolist.repositories.TasksRepository;
import com.projects.todolist.services.TaskServices;

import java.util.ArrayList;
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

    private List<Task> todo = new ArrayList<>();
    private List<Task> doneTasks = new ArrayList<>();
    private List<Task> cancelTasks = new ArrayList<>();
    
    public TaskRestController(TaskServices taskServices, TasksRepository tasksRepository) {
        this.taskServices = taskServices;
        this.tasksRepository = tasksRepository;

        Task t1 = new Task();
        t1.setTask("Task 1");
        t1.setDate("2025-01-01");
        Task t2 = new Task();
        t2.setTask("Task 2");
        t2.setDate("2025-01-02");
        Task t3 = new Task();
        t3.setTask("Task 3");
        t3.setDate("2025-01-03");

        todo.addAll(List.of(t1, t2, t3));
    }

    @GetMapping("/search/{list}/{name}")
    public List<Task> getTasksByName(@PathVariable String list, @PathVariable String name) {
        return null;
    }

    @DeleteMapping("/clear/{tab}")
    public void clearTab(@PathVariable String tab) {
        if (tab.equals("todo")) {
            todo.clear();
        }
        else if (tab.equals("done")) {
            doneTasks.clear();
        }
        else if (tab.equals("cancel")) {
            cancelTasks.clear();
        }
    }
    
    @GetMapping("/todo")
    public List<Task> getAllToDo() {
        return tasksRepository.findAllTaskInToDo();
    }

    @PostMapping("/todo/add")
    public Task addToDo(@RequestBody Task task) {
        Task t = new Task();
        t.setTask(task.getTask());
        t.setDate(task.getDate());
        todo.add(t);
        return t;
    }
    
    @PutMapping("/todo/update")
    public ResponseEntity<Task> updateToDo(@RequestBody Task task) {
        boolean isExist = false;
        for (Task t : todo) {
            if (t.getId().equals(task.getId())) {
                isExist = true;
                t.setTask(task.getTask());
                t.setDate(task.getDate());
            }
        }
        return isExist ? ResponseEntity.status(201).body(task) : ResponseEntity.status(200).body(addToDo(task));
    }

    @DeleteMapping("/todo/done/{id}")
    public ResponseEntity<Task> doneToDo(@PathVariable String id) {
        int removeIndex = -1;
        for (Task task : todo) {
            if (task.getId().equals(id)) {
                removeIndex = todo.indexOf(task);
                doneTasks.add(task);
            }
        }
        if (removeIndex != -1) todo.remove(removeIndex);

        return removeIndex != -1 ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/todo/cancel/{id}")
    public ResponseEntity<Task> cancelToDo(@PathVariable String id) {
        int removeIndex = -1;
        for (Task task : todo) {
            if (task.getId().equals(id)) {
                removeIndex = todo.indexOf(task);
                cancelTasks.add(task);
            }
        }
        if (removeIndex != -1) todo.remove(removeIndex);

        return removeIndex != -1 ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/done")
    public List<Task> getAllDoneTasks() {
        return tasksRepository.findAllTaskInDone();
    }
    
    @DeleteMapping("/done/rollback/{id}")
    public ResponseEntity<Task> rollbackDone(@PathVariable String id) {
        int removeIndex = -1;
        for (Task task : doneTasks) {
            if (task.getId().equals(id)) {
                removeIndex = doneTasks.indexOf(task);
                todo.add(task);
            }
        }
        if (removeIndex != -1) doneTasks.remove(removeIndex);

        return removeIndex != -1 ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/cancel")
    public List<Task> getAllCancelTasks() {
        return tasksRepository.findAllTaskInCancel();
    }
    
    @DeleteMapping("/cancel/rollback/{id}")
    public ResponseEntity<Task> rollbackCancel(@PathVariable String id) {
        int removeIndex = -1;
        for (Task task : cancelTasks) {
            if (task.getId().equals(id)) {
                removeIndex = cancelTasks.indexOf(task);
                todo.add(task);
            }
        }
        if (removeIndex != -1) cancelTasks.remove(removeIndex);

        return removeIndex != -1 ? ResponseEntity.ok().body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
