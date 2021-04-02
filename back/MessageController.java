package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author directorfan
 * @since 2020-07-31
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/{userAccount}")
    public Result getMessage(@PathVariable("userAccount") String userAccount){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        List<Message> list = messageService.getBaseMapper().selectList(queryWrapper);
        if(list.size() > 0)
            return Result.success(200,"查询用户关键词成功",list);
        else return Result.fail(400,"查询用户关键词失败");
    }

}
