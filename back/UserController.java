package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.common.lang.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author directorfan
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

   //用户列表查询，用于管理员管理使用
    @GetMapping()
    public Result userQuery(){
        List<User> list = userService.getBaseMapper().selectList(null);
        if(list.size() > 0)
            return Result.success(200,"查询到用户列表",list);
        else
            return Result.fail(400,"未查询到用户列表");
    }

    //用户信息删除，用于管理员注销账户
    @DeleteMapping("/{userList}")
    public Result userDelete(@PathVariable("userList") List<Integer> userList){
        boolean b = true;
        if(userList.size()>0) {
            for(int i = 0; i < userList.size();i++){
                if(! userService.removeById(userList.get(i)))
                    b = false;
            }
            if (b)
                return Result.success(200, "用户信息删除成功", null);
            else
                return Result.fail(400, "用户信息删除失败");
        }
        else return null;
    }
}
