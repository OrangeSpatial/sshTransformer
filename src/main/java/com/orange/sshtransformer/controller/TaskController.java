package com.orange.sshtransformer.controller;

import com.jcraft.jsch.JSchException;
import com.orange.sshtransformer.entity.Task;
import com.orange.sshtransformer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/action/{id}")
    public String actionTask(@PathVariable int id){
        Boolean aBoolean = false;
        try {
            aBoolean = taskService.actionTask(id);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        if (aBoolean) return "执行成功！";
        return "执行失败！";
    }

    @GetMapping("/all")
    public List<Task> getAll(){
        List<Task> allTasks = taskService.getAllTasks();
        return allTasks;
    }

    @PostMapping("/add")
    public Boolean addTask(@RequestBody Task task){
        boolean b = taskService.createTask(task);
        return b;
    }

    @GetMapping("/del/{id}")
    public Boolean delTask(@PathVariable int id){
        boolean delete = taskService.delete(id);
        return delete;
    }
}
