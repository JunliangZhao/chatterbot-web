package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    UserService userService;

    //用户查询，用于在设置界面显示用户个人信息
    @GetMapping("/{userAccount}")
    public Result userQuery(@PathVariable("userAccount") String userAccount){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        List<User> list = userService.getBaseMapper().selectList(queryWrapper);
        if(list.size() > 0)
            return Result.success(200,"查询到用户",list.get(0));
        else
            return Result.fail(400,"未查询到用户");
    }

    //用户修改，用于用户修改个人信息
    @PutMapping()
    public Result UserModify(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",user.getUserAccount());
        int b = userService.getBaseMapper().update(user,queryWrapper);
        if(b == 1)
            return Result.success(200,"用户信息修改成功",null);
        else
            return Result.fail(400,"用户信息修改失败");
    }
}
