package com.orange.sshtransformer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("remote_node")
public class RemoteNode {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String host;
    private Integer port;
    @TableField("system_type")
    private Integer systemType; // 0 win, 1 linux
    @TableField("node_name")
    private String nodeName;
    @TableField("user")
    private String user;
    @TableField("password")
    private String password;
    @TableField("remote_path")
    private String remotePath;
}
