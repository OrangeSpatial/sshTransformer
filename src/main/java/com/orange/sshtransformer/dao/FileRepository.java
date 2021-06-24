package com.orange.sshtransformer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.sshtransformer.entity.OFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRepository extends BaseMapper<OFile> {
}
