package com.dh.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.Team;


public interface TeamService extends IService<Team>{

    Integer queryPersonNumById(Integer teamId);

    Team queryById(Integer teamId);
}
