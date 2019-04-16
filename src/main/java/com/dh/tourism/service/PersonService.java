package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.Person;
import com.dh.tourism.model.User;

import java.util.List;


public interface PersonService extends IService<Person>{
    List<Person> queryAll();
    Person getByUsername(String personName);
}
