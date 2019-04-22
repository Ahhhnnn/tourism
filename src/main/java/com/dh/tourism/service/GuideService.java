package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.Guide;


public interface GuideService extends IService<Guide>{

    Guide getGuideByAcount(String name);

    Guide getGuideById(Integer id);
}
