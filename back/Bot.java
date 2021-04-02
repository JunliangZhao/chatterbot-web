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
@TableName("t_bot")
public class Bot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "botID", type = IdType.AUTO)
    private Integer botID;

    /**
     * 机器人姓名/昵称
     */
    @TableField("botName")
    private String botName;

    /**
     * 机器人性别（用MALE、FEMALE、AGENDER、BIGENDER、TRANS等等表示）
     */
    @TableField("botSex")
    private String botSex;

    /**
     * 机器人性取向
     */
    @TableField("botSexualOri")
    private String botSexualOri;

    /**
     * 机器人得分
     */
    @TableField("botScore")
    private Integer botScore;

    /**
     * 备注
     */
    @TableField("botNote")
    private String botNote;

    /**
     * 机器人主人名字（对应用户管理数据表——username）
     */
    @TableField("botMaster")
    private String botMaster;

    /**
     * 机器人主人的账户
     */
    @TableField("botAccount")
    private String botAccount;


}
