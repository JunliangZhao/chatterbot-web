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
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "userID", type = IdType.AUTO)
    private Integer userID;

    /**
     * 用户姓名/昵称
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户登录的帐号（不同用户账号不可相同，但该项不是主键）
     */
    @TableField("userAccount")
    private String userAccount;

    /**
     * 用户登录密码
     */
    @TableField("userPwd")
    private String userPwd;

    /**
     * 用户类型（系统使用者false、管理员true等）
     */
    @TableField("userType")
    private Boolean userType;

    /**
     * 性别
     */
    @TableField("userSex")
    private String userSex;

    /**
     * 备注
     */
    @TableField("userNote")
    private String userNote;

    /**
     * 账户是否可用
     */
    @TableField("userAvailable")
    private Boolean userAvailable;

    /**
     * 用户年龄（注册时用户可选填写，默认值为0）
     */
    @TableField("userAge")
    private Integer userAge;


}
