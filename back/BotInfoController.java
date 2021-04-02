package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.Bot;
import com.example.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/botInfo")
public class BotInfoController {

    @Autowired
    BotService botService;
    //获取机器人信息显示在聊天界面左侧
    @GetMapping("/{botAccount}")
    public Result getBotInfo(@PathVariable("botAccount") String botAccount){
        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("botAccount",botAccount);
        List<Bot> list =botService.getBaseMapper().selectList(queryWrapper);
        if(list.size() > 0)
            return Result.success(200,"获取机器人信息成功",list.get(0));
        else return Result.fail(400,"获取机器人信息失败");
    }

    //修改机器人信息
    @PutMapping()
    public Result modifyBotInfo(@RequestBody Bot bot){
        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("botAccount",bot.getBotAccount());
        int b = botService.getBaseMapper().update(bot,queryWrapper);
        if(b == 1)
            return Result.success(200,"机器人信息修改成功",null);
        else return Result.fail(400,"机器人信息修改失败");
    }
}
