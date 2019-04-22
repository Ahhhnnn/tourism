package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.CarMapper;
import com.dh.tourism.dao.HotalMapper;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.Hotal;
import com.dh.tourism.service.CarService;
import com.dh.tourism.service.HotalService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * @author duhan
 * @title: UserServiceImpl
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1423:14
 */
@Service
public class HotalServiceImpl extends ServiceImpl<HotalMapper,Hotal> implements HotalService{

    @Override
    public Hotal queryById(Integer hotalId) {
        EntityWrapper<Hotal> hotalEntityWrapper=new EntityWrapper<Hotal>();
        hotalEntityWrapper.eq("id",hotalId);
        List<Hotal> hotalList=baseMapper.selectList(hotalEntityWrapper);
        return hotalList.get(0);
    }
}
