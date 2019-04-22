package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.CarOrderMapper;
import com.dh.tourism.dao.ScenicOrderMapper;
import com.dh.tourism.model.CarOrder;
import com.dh.tourism.model.ScenicOrder;
import com.dh.tourism.service.CarOrderService;
import com.dh.tourism.service.ScenicOrderService;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.EncTicketPart;

import java.util.List;

/**
 * @author duhan
 * @title: UserServiceImpl
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1423:14
 */
@Service
public class CarOrderServiceImpl extends ServiceImpl<CarOrderMapper,CarOrder> implements CarOrderService{

    @Override
    public CarOrder queryById(Integer carOrderId) {
        EntityWrapper<CarOrder> carOrderEntityWrapper=new EntityWrapper<CarOrder>();
        carOrderEntityWrapper.eq("id",carOrderId);
        List<CarOrder> carOrderList=baseMapper.selectList(carOrderEntityWrapper);

        return carOrderList.get(0);
    }
}
