package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.CarOrder;
import com.dh.tourism.model.Hotal;
import com.dh.tourism.model.HotalOrder;


public interface HotalOrderService extends IService<HotalOrder>{

    HotalOrder queryById(Integer hotalOrderId);
}
