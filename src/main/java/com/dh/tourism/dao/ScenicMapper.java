package com.dh.tourism.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dh.tourism.model.Scenic;
import com.dh.tourism.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScenicMapper extends BaseMapper<Scenic> {

    void insertOne(Scenic scenic);
}
