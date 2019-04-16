package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.User;

import java.util.List;


public interface UserService extends IService<User>{
    List<User> queryAll();
    User getByUsername(String username);
}
