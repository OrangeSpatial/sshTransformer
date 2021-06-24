package com.orange.sshtransformer.service.impl;

import com.orange.sshtransformer.dao.RemoteNodeRepository;
import com.orange.sshtransformer.entity.RemoteNode;
import com.orange.sshtransformer.service.RemoteNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteServiceImpl implements RemoteNodeService {

    @Autowired
    private RemoteNodeRepository remoteNodeRepository;

    @Override
    public List<RemoteNode> getAll() {
        List<RemoteNode> remoteNodes = remoteNodeRepository.selectList(null);
        return remoteNodes;
    }

    @Override
    public RemoteNode getById(Integer id) {
        RemoteNode remoteNode = remoteNodeRepository.selectById(id);
        return remoteNode;
    }

    @Override
    public boolean add(RemoteNode node) {
        int insert = remoteNodeRepository.insert(node);
        if (insert>0) return true;
        return false;
    }

    @Override
    public boolean update(RemoteNode node) {
        int insert = remoteNodeRepository.updateById(node);
        if (insert>0) return true;
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        int i = remoteNodeRepository.deleteById(id);
        if (i>0) return true;
        return false;
    }
}
