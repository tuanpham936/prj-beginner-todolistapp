package com.projects.todolist.models;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    private String id;
    private String task;
    private String exp;
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
    public String getExp() {
        return exp;
    }
    public void setExp(String date) {
        this.exp = date;
    }
}
