package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.CarMapper;
import com.dh.tourism.dao.TeamMapper;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.Team;
import com.dh.tourism.service.CarService;
import com.dh.tourism.service.TeamService;
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
public class TeamServiceImpl extends ServiceImpl<TeamMapper,Team> implements TeamService{

    @Override
    public Integer queryPersonNumById(Integer teamId) {
        EntityWrapper<Team> teamEntityWrapper=new EntityWrapper<Team>();
        teamEntityWrapper.eq("id",teamId);
        List<Team> teamList=baseMapper.selectList(teamEntityWrapper);
        Team team=teamList.get(0);
        String personIds=team.getPersonIds();
        return personIds.split(",").length;
    }

    @Override
    public Team queryById(Integer teamId) {
        EntityWrapper<Team> entityWrapper=new EntityWrapper<Team>();
        entityWrapper.eq("id",teamId);
        List<Team> teamList=baseMapper.selectList(entityWrapper);
        return teamList.get(0);
    }
}
