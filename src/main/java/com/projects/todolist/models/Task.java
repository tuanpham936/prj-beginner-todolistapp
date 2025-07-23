package com.projects.todolist.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private String id;
    private String task;
    private String date;
    public Task() {
        id = UUID.randomUUID().toString();
    }
    public String getId() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
