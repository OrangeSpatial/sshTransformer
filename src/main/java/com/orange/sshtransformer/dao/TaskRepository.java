package com.orange.sshtransformer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.sshtransformer.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskRepository extends BaseMapper<Task> {
}
