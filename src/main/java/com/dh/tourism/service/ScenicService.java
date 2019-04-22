package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.Scenic;
import com.dh.tourism.model.User;

import java.util.List;


public interface ScenicService extends IService<Scenic>{
    List<Scenic> queryAll();
    void insertOne(Scenic scenic);
    Scenic queryById(Integer id);
}
