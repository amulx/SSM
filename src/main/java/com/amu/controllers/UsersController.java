package com.amu.controllers;

import com.amu.Entities.UserInfo;
import com.amu.mappers.UserMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    // GET /users 获取用户列表信息
    // GET /users/123 获取用户详细信息
    // POST /users 新增用户
    // PUT /users 修改用户
    // DELETE /users 删除用户
    // 使用方式：Content-Type ==》application/json
    @Autowired
    UserMapper userMapper;

    @GetMapping("{id:\\d+}")
    public UserInfo getUsersDetaila(@PathVariable("id") int userid)  {
        return userMapper.getUserDetail(userid);
    }

    @GetMapping("")
    public List<UserInfo> getUserList(){
        return userMapper.getUserList();
    }

    @PostMapping(value = "",consumes = "application/json")
    public String addUser(@RequestBody UserInfo userInfo){
        userMapper.addUser(userInfo);
        return userInfo.toString();
    }

    @Delete("")
    public int deleteUser(@PathVariable("id") int userid){
        return userMapper.deleteUser(userid);
    }

    @Update("")
    public String updateUser(@RequestBody UserInfo userInfo){
        userMapper.updateUser(userInfo);
        return userInfo.toString();
    }


}
