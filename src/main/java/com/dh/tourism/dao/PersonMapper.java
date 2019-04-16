package com.dh.tourism.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dh.tourism.model.Person;
import com.dh.tourism.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
