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
 * @since 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，设为自增长
     */
    @TableId(value = "userID", type = IdType.AUTO)
    private Integer userID;

    /**
     * 用户账户，用户的唯一标识
     */
    @TableField("userAccount")
    private String userAccount;

    /**
     * 用户关键词
     */
    private String userword;

    /**
     * 关键词频率
     */
    private Integer fwords;


}
