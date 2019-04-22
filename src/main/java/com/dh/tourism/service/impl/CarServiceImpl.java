package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.CarMapper;
import com.dh.tourism.dao.UserMapper;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.User;
import com.dh.tourism.service.CarService;
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
public class CarServiceImpl extends ServiceImpl<CarMapper,Car> implements CarService{

    @Override
    public Car queryById(Integer carId) {
        EntityWrapper<Car> carEntityWrapper=new EntityWrapper<>();
        carEntityWrapper.eq("id",carId);
        List<Car> carList=baseMapper.selectList(carEntityWrapper);
        return carList.get(0);
    }
}
