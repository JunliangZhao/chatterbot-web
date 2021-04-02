package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.common.lang.Result;
import com.example.entity.Record;
import com.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author directorfan
 * @since 2020-07-29
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;
    //获取用户聊天记录
    @GetMapping("/{userAccount}")
    public Result getRecord(@PathVariable("userAccount") String userAccount){
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        List<Record> list = recordService.getBaseMapper().selectList(queryWrapper);
        if(list.size() > 0)
            return Result.success(200,"用户聊天消息查询成功",list);
        else
            return Result.fail(400,"未查询到用户聊天消息");
    }
    @GetMapping
    public Result getAllRecord(){
        List<Record> list = recordService.getBaseMapper().selectList(null);
        if(list.size() > 0)
            return Result.success(200,"所有用户聊天条数查询成功",list.size());
        else
            return Result.fail(400,"未查询到用户聊天消息");
    }
    //增加用户聊天记录
    @PostMapping
    public Result recordIncrement(@RequestBody Record record){
        int b = recordService.getBaseMapper().insert(record);
        if(b == 1)
            return Result.success(200,"用户聊天消息添加成功",null);
        else
            return Result.fail(400,"用户聊天消息添加失败");
    }

}
