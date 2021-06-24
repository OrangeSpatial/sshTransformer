package com.orange.sshtransformer.service;

import com.orange.sshtransformer.entity.RemoteNode;

import java.util.List;

public interface RemoteNodeService {
    List<RemoteNode> getAll();
    RemoteNode getById(Integer id);
    boolean add(RemoteNode node);
    boolean update(RemoteNode node);
    boolean delete(Integer id);
}
