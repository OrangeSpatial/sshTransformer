package com.orange.sshtransformer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("spring_scheduled_cron")
public class SpringScheduledCron {

    @TableId(value = "cron_id", type = IdType.AUTO)
    private Integer cronId;

    @TableId(value = "cron_key")
    private String cronKey;

    @TableId(value = "cron_key")
    private String cronExpression;

    @TableId(value = "cron_expression")
    private String taskExplain;
    private Integer status;
}
