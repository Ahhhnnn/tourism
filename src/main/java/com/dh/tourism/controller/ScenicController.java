package com.dh.tourism.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Scenic;
import com.dh.tourism.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/**
 * @author duhan
 * @title: ScenicController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1515:22
 */
@RestController
@RequestMapping("/scenic")
public class ScenicController {
    @Autowired
    private ScenicService scenicService;

    private static Integer count=5;
    @RequestMapping("query")
    public PageResult<Scenic> query(){
        List<Scenic> scenics= scenicService.queryAll();
        return new PageResult<>(0,"查询成功",scenics.size(),scenics);
    }

    /**
     * 查询推荐旅游景点
     * @return
     */
    @RequestMapping("queryRecommend")
    public PageResult<Scenic> queryRecommend(){
        EntityWrapper<Scenic> entityWrapper=new EntityWrapper<Scenic>();
        entityWrapper.eq("type","recommend");
        List<Scenic> recommendScenicList=scenicService.selectList(entityWrapper);
        return new PageResult<>(200,"查询成功",recommendScenicList.size(),recommendScenicList);
    }

    /**
     * 查询国内旅游景点
     * @return
     */
    @RequestMapping("queryDomestic")
    public PageResult<Scenic> queryDomestic(){
        EntityWrapper<Scenic> entityWrapper=new EntityWrapper<Scenic>();
        entityWrapper.eq("type","domestic");
        List<Scenic> recommendScenicList=scenicService.selectList(entityWrapper);
        return new PageResult<>(200,"查询成功",recommendScenicList.size(),recommendScenicList);
    }

    /**
     * 查询境外旅游 景点
     * @return
     */
    @RequestMapping("queryAbroad")
    public PageResult<Scenic> queryAbroad(){
        EntityWrapper<Scenic> entityWrapper=new EntityWrapper<Scenic>();
        entityWrapper.eq("type","abroad");
        List<Scenic> recommendScenicList=scenicService.selectList(entityWrapper);
        return new PageResult<>(200,"查询成功",recommendScenicList.size(),recommendScenicList);
    }

    @RequestMapping("insert")
    public PageResult insert(Scenic scenic){
        scenic.setPicture("qingcheng.png");
        scenic.setId(count);
        count++;
       scenicService.insert(scenic);
       return new PageResult("新增成功",200);
    }
}
