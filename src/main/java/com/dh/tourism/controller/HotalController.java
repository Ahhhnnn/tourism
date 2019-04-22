package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Hotal;
import com.dh.tourism.service.HotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duhan
 * @title: HotalController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1622:14
 */
@RestController
@RequestMapping("/hotal")
public class HotalController {
    private static Integer count=1;
    @Autowired
    private HotalService hotalService;

    @RequestMapping("insert")
    public PageResult insert(Hotal hotal){
        hotal.setId(count);
        count++;
        if(!hotalService.insert(hotal)){
             return new PageResult("添加失败",400);
        }
        return new PageResult("添加成功",200);
    }

    @RequestMapping("/update")
    public PageResult updatePerson(Hotal hotal){

        if(hotalService.updateById(hotal)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{hotalId}")
    public PageResult deltePerson(@PathVariable("hotalId") Integer hotalId){

        if(hotalService.deleteById(hotalId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }

    @PutMapping("/statu")
    public JsonResult updatestatu(Integer hotalId, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        Hotal hotal=new Hotal();
        hotal.setStatu(statu);
        hotal.setId(hotalId);
        if (hotalService.updateById(hotal)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @RequestMapping("/query")
    public PageResult<Hotal> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<Hotal> hotalPage = new Page<>(page, limit);
        EntityWrapper<Hotal> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            if(searchKey.equals("hotal_name")||searchKey.equals("address")){
                wrapper.like(searchKey,searchValue);
            }else {
                wrapper.eq(searchKey, searchValue);
            }
        }
        wrapper.orderBy("create_time", true);
        hotalService.selectPage(hotalPage, wrapper);
        List<Hotal> count=hotalService.selectList(wrapper);
        List<Hotal> hotalList = hotalPage.getRecords();
        return new PageResult<>(hotalList, count.size());
    }

    @RequestMapping("queryById")
    public PageResult<Hotal> queryById(Integer id){
        EntityWrapper<Hotal> entityWrapper=new EntityWrapper<Hotal>();
        entityWrapper.eq("id",id);
        List<Hotal> hotalList=hotalService.selectList(entityWrapper);

        return new PageResult<>(200,"查询成功",hotalList.size(),hotalList);
    }

    @RequestMapping("queryAll")
    public PageResult<Hotal> queryAll(){
        EntityWrapper<Hotal> entityWrapper=new EntityWrapper<Hotal>();
        entityWrapper.eq("statu",0);
        List<Hotal> hotalList=hotalService.selectList(entityWrapper);

        return new PageResult<>(200,"查询成功",hotalList.size(),hotalList);
    }
}
