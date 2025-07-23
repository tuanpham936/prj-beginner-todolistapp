package com.projects.todolist.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.projects.todolist.models.Task;
import com.projects.todolist.repositories.TasksRepository;
import com.projects.todolist.utils.Pair;

@Service
@Transactional
public class TaskServices {
    private final TasksRepository tasksRepository;

    public TaskServices(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task addToDo(Task task) {
        Task newTask = new Task();
        newTask.setTask(task.getTask());
        newTask.setExp(task.getExp());
        tasksRepository.saveTaskToDo(newTask.getId(), newTask.getTask(), newTask.getExp());
        return newTask;
    }

    public Pair<Boolean, Task> updateToDo(Task task) {
        if (task.getId() == null || task.getId().isEmpty() || !tasksRepository.existInToDoById(task.getId())) return new Pair<>(true, addToDo(task));
        tasksRepository.updateTaskToDo(task.getId(), task.getTask(), task.getExp());
        return new Pair<>(false, task);
    }

    public boolean transferTaskFromToDoToDone(String id) {
        if (!tasksRepository.existInToDoById(id)) return false;

        Task t = tasksRepository.findTaskInToDoById(id);

        tasksRepository.deleteTaskToDo(id);
        tasksRepository.saveTaskDone(id, t.getTask(), t.getExp());

        return true;
    }

    public boolean transferTaskFromToDoToCancel(String id) {
        if (!tasksRepository.existInToDoById(id)) return false;

        Task t = tasksRepository.findTaskInToDoById(id);

        tasksRepository.deleteTaskToDo(id);
        tasksRepository.saveTaskCancel(id, t.getTask(), t.getExp());

        return true;
    }

    public boolean transferTaskFromDoneToToDo(String id) {
        if (!tasksRepository.existInDoneById(id)) return false;

        Task t = tasksRepository.findTaskInDoneById(id);

        tasksRepository.deleteTaskDone(id);
        tasksRepository.saveTaskToDo(id, t.getTask(), t.getExp());

        return true;
    }

    public boolean transferTaskFromCancelToToDo(String id) {
        if (!tasksRepository.existInCancelById(id)) return false;

        Task t = tasksRepository.findTaskInCancelById(id);

        tasksRepository.deleteTaskCancel(id);
        tasksRepository.saveTaskToDo(id, t.getTask(), t.getExp());

        return true;
    }
}
