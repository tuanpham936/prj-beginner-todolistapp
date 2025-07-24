package com.projects.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
@Controller
public class PageController {
    private final TaskRestController taskRestController;

    public PageController(TaskRestController taskRestController) {
        this.taskRestController = taskRestController;
    }

    @GetMapping("/")
    public String getHomePage(@RequestAttribute(required = false) String activeTab, Model page) {
        page.addAttribute("activeTab", activeTab == null ? "todo" : activeTab);
        page.addAttribute("todo", taskRestController.getAllToDo());
        page.addAttribute("done", taskRestController.getAllDoneTasks());
        page.addAttribute("cancel", taskRestController.getAllCancelTasks());
        return "home.html";
    }
}
