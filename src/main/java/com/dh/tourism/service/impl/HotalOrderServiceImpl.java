package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.CarOrderMapper;
import com.dh.tourism.dao.HotalOrderMapper;
import com.dh.tourism.model.CarOrder;
import com.dh.tourism.model.HotalOrder;
import com.dh.tourism.service.CarOrderService;
import com.dh.tourism.service.HotalOrderService;
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
public class HotalOrderServiceImpl extends ServiceImpl<HotalOrderMapper,HotalOrder> implements HotalOrderService{

    @Override
    public HotalOrder queryById(Integer hotalOrderId) {
        EntityWrapper<HotalOrder> hotalOrderEntityWrapper=new EntityWrapper<HotalOrder>();
        hotalOrderEntityWrapper.eq("id",hotalOrderId);
        List<HotalOrder> hotalOrderList=baseMapper.selectList(hotalOrderEntityWrapper);

        return hotalOrderList.get(0);
    }
}
