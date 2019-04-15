package com.dh.tourism.controller;

import com.dh.tourism.model.User;
import com.dh.tourism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duhan
 * @title: UserController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1423:11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public List<User> query(){
        List<User> userList=userService.queryAll();
        return userList;
    }
}
