package com.dh.tourism.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Scenic;

import com.dh.tourism.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    private static Integer count=8;
    @RequestMapping("/query")
    public PageResult<Scenic> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        com.baomidou.mybatisplus.plugins.Page<Scenic> scenicPage = new com.baomidou.mybatisplus.plugins.Page<>(page, limit);
        EntityWrapper<Scenic> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            if(searchKey.equals("name")){
                wrapper.like(searchKey,searchValue);
            }else {
                wrapper.eq(searchKey, searchValue);
            }
        }
        wrapper.orderBy("create_time", true);
        scenicService.selectPage(scenicPage, wrapper);
        List<Scenic> count=scenicService.selectList(wrapper);
        List<Scenic> scenicList = scenicPage.getRecords();
        return new PageResult<>(scenicList, count.size());
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

    /**
     * 通过id查询景点详细信息
     * @param id
     * @return
     */
    @RequestMapping("queryById")
    public PageResult<Scenic> queryById(Integer id){
        EntityWrapper<Scenic> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("id",id);
        List<Scenic> scenics=scenicService.selectList(entityWrapper);

        return new PageResult<>(200,"查询成功",scenics.size(),scenics);
    }
    @RequestMapping("insert")
    public PageResult insert(Scenic scenic){
        scenic.setId(count);
        count++;
       scenicService.insert(scenic);
       return new PageResult("新增成功",200);
    }


    @RequestMapping("/update")
    public PageResult updateScenic(Scenic scenic){

        if(scenicService.updateById(scenic)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{scenicId}")
    public PageResult delteScenic(@PathVariable("scenicId") Integer scenicId){

        if(scenicService.deleteById(scenicId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }

    @PutMapping("/statu")
    public JsonResult updatestatu(Integer id, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        Scenic scenic=new Scenic();
        scenic.setStatu(statu);
        scenic.setId(id);
        if (scenicService.updateById(scenic)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }
}
