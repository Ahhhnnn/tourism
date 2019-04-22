package com.dh.tourism.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.CarMapper;
import com.dh.tourism.dao.GuideMapper;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.Guide;
import com.dh.tourism.service.CarService;
import com.dh.tourism.service.GuideService;
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
public class GuideServiceImpl extends ServiceImpl<GuideMapper,Guide> implements GuideService{

    @Override
    public Guide getGuideByAcount(String acount) {
        EntityWrapper<Guide> entityWrapper=new EntityWrapper<Guide>();
        entityWrapper.eq("acount",acount);
        List<Guide> guides= baseMapper.selectList(entityWrapper);
        if(guides.size()==0){
            return null;
        }else {
            return guides.get(0);
        }

    }

    @Override
    public Guide getGuideById(Integer id) {
        EntityWrapper<Guide> entityWrapper=new EntityWrapper<Guide>();
        entityWrapper.eq("id",id);
        List<Guide> guideList=baseMapper.selectList(entityWrapper);
        if(CollectionUtils.isEmpty(guideList)){
            return null;
        }
        return guideList.get(0);
    }
}
