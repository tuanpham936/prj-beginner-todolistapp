package com.projects.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    private final TaskRestController taskRestController;

    public PageController(TaskRestController taskRestController) {
        this.taskRestController = taskRestController;
    }

    @GetMapping("/")
    public String getHomePage(@RequestAttribute(required = false) String activeTab, Model page) {
        page.addAttribute("activeTab", "todo");
        page.addAttribute("todo", taskRestController.getAllToDo());
        page.addAttribute("done", taskRestController.getAllDoneTasks());
        page.addAttribute("cancel", taskRestController.getAllCancelTasks());
        return "home.html";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = true) String activeTab, @RequestParam(required = true) String keyword, Model page) {
        page.addAttribute("activeTab", activeTab);
        return "home.html";
    }
}
