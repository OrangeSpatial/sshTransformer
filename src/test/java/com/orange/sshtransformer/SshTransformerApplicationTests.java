package com.orange.sshtransformer;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.orange.sshtransformer.dao.FileRepository;
import com.orange.sshtransformer.dao.RemoteNodeRepository;
import com.orange.sshtransformer.dao.TaskRepository;
import com.orange.sshtransformer.entity.OFile;
import com.orange.sshtransformer.entity.RemoteNode;
import com.orange.sshtransformer.entity.Task;
import com.orange.sshtransformer.utils.Sftp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class SshTransformerApplicationTests {

    @Autowired
    private Sftp sftp;


    @Test
    void contextLoads() throws JSchException {
        sftp.setHost("192.168.10.104");
        sftp.setUsername("root");
        sftp.setPassword("docker");
        System.out.println(sftp.getUsername());

//        Session session = sftp.getSession();
//        session.connect();
//        if (session.isConnected()) {
//            log.info("Host({}) connected.",sftp.getHost());
//        }
//        session.disconnect();
    }

    @Test
    void testCommand() throws JSchException {
        sftp.setHost("192.168.10.104");
        sftp.setUsername("root");
        sftp.setPassword("docker");
        Session session = sftp.getSession();
        session.connect();
        sftp.remoteExecute(session, "pwd");
//        sftp.scpTo("F:/code/sshTransformer/data/test.txt", session, "/root/sftp");
        List<String> list = sftp.remoteExecute(session, "ls /root/sftp");
        for(String fileName: list) {
            System.out.println(fileName);
            sftp.scpFrom(session, "/root/sftp/"+fileName,"F:/code/sshTransformer/data", 0);
        }
        session.disconnect();
    }


    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testFile(){
        List<OFile> oFiles = fileRepository.selectList(null);
        System.out.println("文件");
        oFiles.forEach(oFile -> System.out.println(oFile));

        List<Task> tasks = taskRepository.selectList(new QueryWrapper<Task>().eq("status", 1));
        tasks.forEach(task -> {
            RemoteNode remoteNode = remoteNodeRepository.selectById(task.getNodeId());
            System.out.println(remoteNode);
            sftp.setHost(remoteNode.getHost());
            sftp.setUsername(remoteNode.getUser());
            sftp.setPassword(remoteNode.getPassword());
            Session session = null;
            try {
                session = sftp.getSession();
                session.connect();
                sftp.remoteExecute(session, "pwd");
//        sftp.scpTo("F:/code/sshTransformer/data/test.txt", session, "/root/sftp");
                List<String> list = sftp.remoteExecute(session, "dir /b " + remoteNode.getRemotePath());
                for(String fileName: list) {
                    System.out.println(fileName);
                    sftp.scpFrom(session, remoteNode.getRemotePath()+"\\"+fileName,task.getLocalPath(), remoteNode.getSystemType());
                }
                session.disconnect();
            } catch (JSchException e) {
                e.printStackTrace();
            }
        });

    }

}
