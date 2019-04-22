package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.CarOrder;
import com.dh.tourism.model.ScenicOrder;


public interface CarOrderService extends IService<CarOrder>{

    CarOrder queryById(Integer carOrderId);
}
