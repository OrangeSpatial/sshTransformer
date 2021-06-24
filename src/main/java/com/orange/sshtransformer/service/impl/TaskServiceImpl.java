package com.orange.sshtransformer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.orange.sshtransformer.dao.FileRepository;
import com.orange.sshtransformer.dao.RemoteNodeRepository;
import com.orange.sshtransformer.dao.TaskRepository;
import com.orange.sshtransformer.entity.OFile;
import com.orange.sshtransformer.entity.RemoteNode;
import com.orange.sshtransformer.entity.Task;
import com.orange.sshtransformer.service.TaskService;
import com.orange.sshtransformer.utils.Sftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    private Sftp sftp;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public boolean createTask(Task task) {
        task.setCreateTime(new Date());
        task.setStatus(0);
        int insert = taskRepository.insert(task);
        if (insert>0)return true;
        return false;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.selectList(null);
        return tasks;
    }

    @Override
    public Boolean actionTask(Integer id) throws JSchException {
        // 获取连接信息
        Task task = taskRepository.selectById(id);
        task.setStatus(1);
        int insert = taskRepository.updateById(task);
        logger.info("执行的任务状态：{}，任务：{}", task.getStatus(), task.getId());
        if (insert>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        int i = taskRepository.deleteById(id);
        return i>0;
    }
    private int i;

    /**
     * 2小时一次
     */
    @Scheduled(cron = "*/30 * * * * ?")
//    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void execute() {
        logger.info("thread id:{},Task execute times:{}", Thread.currentThread().getId(), ++i);
        // 查询所有可执行任务
        List<Task> tasks = taskRepository.selectList(new QueryWrapper<Task>().eq("status", 1));
        tasks.forEach(task -> {
//            查询node
            RemoteNode remoteNode = remoteNodeRepository.selectById(task.getNodeId());
            System.out.println(remoteNode);
            sftp.setHost(remoteNode.getHost());
            sftp.setUsername(remoteNode.getUser());
            sftp.setPassword(remoteNode.getPassword());
            Session session = null;
            try {
                session = sftp.getSession();
                session.connect();
//                sftp.remoteExecute(session, "pwd");
//        sftp.scpTo("F:/code/sshTransformer/data/test.txt", session, "/root/sftp");
                List<String> list = null;
                if (remoteNode.getSystemType()==0){
                    list = sftp.remoteExecute(session, "dir /b " + remoteNode.getRemotePath());
                } else {
                    list = sftp.remoteExecute(session, "ls " + remoteNode.getRemotePath());
                }

                for(String fileName: list) {
                    System.out.println(fileName);
                    long size = sftp.scpFrom(session, remoteNode.getRemotePath() + File.separator + fileName, task.getLocalPath(), remoteNode.getSystemType());
                    if (size>0) {
                        OFile oFile = new OFile();
                        oFile.setName(fileName);
                        oFile.setTaskId(task.getId());
                        oFile.setPath(task.getLocalPath());
                        oFile.setSize(size);
                        oFile.setTransferTime(new Date());
                        fileRepository.insert(oFile);
                    }
                }
                session.disconnect();
            } catch (JSchException e) {
                e.printStackTrace();
            }
        });
    }
}
