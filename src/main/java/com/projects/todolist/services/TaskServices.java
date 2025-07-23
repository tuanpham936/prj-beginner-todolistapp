package com.projects.todolist.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.todolist.repositories.TasksRepository;

@Service
@Transactional
public class TaskServices {
    private final TasksRepository tasksRepository;

    public TaskServices(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    
}
