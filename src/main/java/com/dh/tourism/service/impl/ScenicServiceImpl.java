package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.ScenicMapper;
import com.dh.tourism.dao.UserMapper;
import com.dh.tourism.model.Scenic;
import com.dh.tourism.model.User;
import com.dh.tourism.service.ScenicService;
import com.dh.tourism.service.UserService;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author duhan
 * @title: UserServiceImpl
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1423:14
 */
@Service
public class ScenicServiceImpl extends ServiceImpl<ScenicMapper,Scenic> implements ScenicService{
    @Override
    public List<Scenic> queryAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public void insertOne(Scenic scenic) {
         baseMapper.insertOne(scenic);
    }

    @Override
    public Scenic queryById(Integer id) {
        EntityWrapper<Scenic> scenicEntityWrapper=new EntityWrapper<Scenic>();
        List<Scenic> scenicList=baseMapper.selectList(scenicEntityWrapper);
        if (CollectionUtils.isEmpty(scenicList)) {
            return null;
        }
        return scenicList.get(0);
        }

    }



