package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.PersonMapper;
import com.dh.tourism.dao.UserMapper;
import com.dh.tourism.model.Person;
import com.dh.tourism.model.User;
import com.dh.tourism.service.PersonService;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper,Person> implements PersonService{
    @Override
    public List<Person> queryAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public Person getByUsername(String username) {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("username",username);
        List<Person> persons=baseMapper.selectList(entityWrapper);
        return persons.get(0);
    }
}
