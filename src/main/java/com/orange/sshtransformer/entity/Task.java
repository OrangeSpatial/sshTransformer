package com.orange.sshtransformer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("task")
public class Task {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;

//    private RemoteNode node;
    @TableField("node_id")
    private String nodeId;
    @TableField("local_path")
    private String localPath;
    @TableField("create_time")
    private Date createTime;
    @TableField("status")
    private Integer status; // 0刚创建，1可执行，2正在执行，3停止，4删除

}
