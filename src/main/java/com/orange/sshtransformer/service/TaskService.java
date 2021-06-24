package com.orange.sshtransformer.service;

import com.jcraft.jsch.JSchException;
import com.orange.sshtransformer.entity.Task;

import java.util.List;

public interface TaskService {

    boolean createTask(Task task);
    List<Task> getAllTasks();
    Boolean actionTask(Integer id) throws JSchException;
    boolean delete(Integer id);
}
