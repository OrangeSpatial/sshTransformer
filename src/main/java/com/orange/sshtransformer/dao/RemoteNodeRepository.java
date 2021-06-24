package com.orange.sshtransformer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.sshtransformer.entity.RemoteNode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RemoteNodeRepository extends BaseMapper<RemoteNode> {
}
