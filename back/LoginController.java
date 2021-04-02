package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.common.lang.Result;
import com.example.entity.Bot;
import com.example.entity.Record;
import com.example.entity.User;
import com.example.service.BotService;
import com.example.service.RecordService;
import com.example.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    BotService botService;
    @Autowired
    RecordService recordService;
    //用户登录
    @GetMapping("/{userAccount}/{userPwd}")
    public Result login(@PathVariable("userAccount") String userAccount, @PathVariable("userPwd") String userPwd){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount).eq("userPwd",userPwd);
        List<User> list = userService.getBaseMapper().selectList(queryWrapper);
        if (list.size() > 0)
            return Result.success(200,"用户查询成功",list.get(0));
        else
            return Result.fail(400,"用户查询失败");
    }

    //用户注册
    @PostMapping()
    public Result UserIncrement(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",user.getUserAccount());
        List<User> list = userService.getBaseMapper().selectList(queryWrapper);
        if(list.size() != 0)
            return Result.fail(401,"用户名已注册");
        int b = userService.getBaseMapper().insert(user);
        if(b == 1)
           return Result.success(200,"用户注册成功",null);
        else
            return Result.fail(400,"用户注册失败");
    }

    //机器人注册
    @PostMapping("/botIncrement")
    public Result BotIncrement(@RequestBody Bot bot){
        int b = botService.getBaseMapper().insert(bot);
        if(b == 1) {
            Record record = new Record();
            record.setMessageType(false);
            record.setRecord("我们已经是好友了，快来和Ellie聊天吧");
            record.setUserAccount(bot.getBotAccount());
            record.setRecordDate("");
            record.setRecordTime("");
            recordService.getBaseMapper().insert(record);
            return Result.success(200, "机器人注册成功", null);
        }
        else return Result.fail(400,"机器人注册失败");
    }
}
