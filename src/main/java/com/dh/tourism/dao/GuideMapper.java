package com.dh.tourism.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.Guide;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuideMapper extends BaseMapper<Guide> {

}
