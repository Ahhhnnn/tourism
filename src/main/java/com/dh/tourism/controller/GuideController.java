package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Guide;
import com.dh.tourism.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duhan
 * @title: GuideController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1623:40
 */
@RestController
@RequestMapping("/guide")
public class GuideController {

    private static Integer count=2;
    @Autowired
    private GuideService guideService;

    @RequestMapping("/query")
    public PageResult<Guide> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<Guide> guidePage = new Page<>(page, limit);
        EntityWrapper<Guide> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            if(searchKey.equals("guide_name")||searchKey.equals("phone")){
                wrapper.like(searchKey,searchValue);
            }else {
                wrapper.eq(searchKey, searchValue);
            }
        }
        wrapper.orderBy("create_time", true);
        guideService.selectPage(guidePage, wrapper);
        List<Guide> count=guideService.selectList(wrapper);
        List<Guide> userList = guidePage.getRecords();
        return new PageResult<>(userList, count.size());
    }

    @RequestMapping("/insert")
    public PageResult insertGuide(Guide guide){

        guide.setId(count);
        count++;
        if(guideService.insert(guide)){
            return new PageResult("添加成功",200);
        }
        return new PageResult("添加失败",400);
    }

    @RequestMapping("/update")
    public PageResult updateGuide(Guide guide){

        if(guideService.updateById(guide)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{guideId}")
    public PageResult delteGuide(@PathVariable("guideId") Integer guideId){

        if(guideService.deleteById(guideId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }
    @PutMapping("/statu")
    public JsonResult updatestatu(Integer id, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        Guide guide=new Guide();
        guide.setStatu(statu);
        guide.setId(id);
        if (guideService.updateById(guide)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 查询所有状态正常的导游
     * @return
     */
    @RequestMapping("/queryAllStatu")
    public PageResult<Guide> queryAllStatu(){
        EntityWrapper entity=new EntityWrapper();
        entity.eq("statu",0);
        List<Guide> guides=guideService.selectList(entity);
        return new PageResult<>(200,"查询成功",guides.size(),guides);
    }
}
