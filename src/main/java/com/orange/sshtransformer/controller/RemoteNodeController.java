package com.orange.sshtransformer.controller;

import com.orange.sshtransformer.dao.RemoteNodeRepository;
import com.orange.sshtransformer.entity.RemoteNode;
import com.orange.sshtransformer.service.RemoteNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/node")
public class RemoteNodeController {

    @Autowired
    private RemoteNodeService remoteNodeService;

    @GetMapping("/all")
    public List<RemoteNode> getAll(){
        List<RemoteNode> all = remoteNodeService.getAll();
        return all;
    }

    @PostMapping("/add")
    public boolean addNode(@RequestBody RemoteNode node){
        boolean add = remoteNodeService.add(node);
        return add;
    }

    @GetMapping("/del/{id}")
    public boolean delNode(@PathVariable int id){
        boolean delete = remoteNodeService.delete(id);
        return delete;
    }

    @PostMapping("/update")
    public boolean updateNode(@RequestBody RemoteNode node){
        boolean update = remoteNodeService.update(node);
        return update;
    }

}
