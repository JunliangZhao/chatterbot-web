package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.Bot;
import com.example.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author directorfan
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/bot")
public class BotController {
    @Autowired
    BotService botService;

    //    查询机器人信息
    @GetMapping("/rankList")
    public Result getRankList() {
        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("botScore");
        List<Bot> list = botService.getBaseMapper().selectList(queryWrapper);
        if (list.size() > 0)
            return Result.success(200,"读取列表成功",list);
        else
            return Result.fail(400,"读取列表失败");
    }

}