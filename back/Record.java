package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author directorfan
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_record")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 聊天记录编号
     */
    @TableId(value = "recordId", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 用户和机器人的聊天记录都放在一张表中,用来区分是用户发送还是机器人发送,用户为true,机器人为false
     */
    @TableField("messageType")
    private Boolean messageType;

    /**
     * 用户帐户,确定该记录属于谁
     */
    @TableField("userAccount")
    private String userAccount;

    /**
     * 用户消息发送的日期
     */
    @TableField("recordDate")
    private String recordDate;

    /**
     * 用户消息发送的准确时间
     */
    @TableField("recordTime")
    private String recordTime;

    /**
     * 用户一条消息的内容
     */
    private String record;


}
