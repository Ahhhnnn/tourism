package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.UserMapper;
import com.dh.tourism.model.User;
import com.dh.tourism.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duhan
 * @title: UserServiceImpl
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1423:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
    @Override
    public List<User> queryAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public User getByUsername(String username) {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("username",username);
        List<User> users=baseMapper.selectList(entityWrapper);
        if(users.size()==0){
            return null;
        }else {
            return users.get(0);
        }
    }
}
