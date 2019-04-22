package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Guide;
import com.dh.tourism.model.Scenic;
import com.dh.tourism.model.ScenicOrder;
import com.dh.tourism.model.Team;
import com.dh.tourism.service.GuideService;
import com.dh.tourism.service.ScenicOrderService;
import com.dh.tourism.service.ScenicService;
import com.dh.tourism.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duhan
 * @title: ScenicOrderController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1923:24
 */
@RestController
@RequestMapping("/scenicOrder")
public class ScenicOrderController {

    private static Integer count=1;
    @Autowired
    private ScenicOrderService scenicOrderService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private ScenicService scenicService;

    @RequestMapping("insert")
    public PageResult insert(ScenicOrder scenicOrder){
        scenicOrder.setId(count);
        count++;
        if(scenicOrder.getTeamId()==null){
            return new PageResult("请选择团队",400);
        }

        Integer guideId=scenicOrder.getGuideId();
        Guide guide=guideService.getGuideById(guideId);
        scenicOrder.setGuideName(guide.getGuideName());
        //计算订单总价
        Integer teamId=scenicOrder.getTeamId();
        //设置团队名
        Team team=teamService.queryById(teamId);
        scenicOrder.setTeamName(team.getTeamName());
        Integer personNum=teamService.queryPersonNumById(teamId);
        //查询景点的单价
        Scenic scenic=scenicService.queryById(scenicOrder.getScenicId());
        Double price=(Double.valueOf(scenic.getPrice())*personNum);
        scenicOrder.setPrice(String.valueOf(price));
        scenicOrder.setScenicName(scenic.getName());
        if(!scenicOrderService.insert(scenicOrder)){
            return new PageResult("预订失败",400);
        }
        return new PageResult("预订成功",200);
    }

    @RequestMapping("/update")
    public PageResult updatePerson(ScenicOrder scenicOrder){

        if(scenicOrderService.updateById(scenicOrder)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{scenicOrderId}")
    public PageResult deltePerson(@PathVariable("scenicOrderId") Integer scenicOrderId){

        if(scenicOrderService.deleteById(scenicOrderId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }

    @PutMapping("/statu")
    public JsonResult updatestatu(Integer scenicOrderId, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        ScenicOrder scenicOrder=new ScenicOrder();
        scenicOrder.setStatu(statu);
        scenicOrder.setId(scenicOrderId);
        if (scenicOrderService.updateById(scenicOrder)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @RequestMapping("/query")
    public PageResult<ScenicOrder> list(Integer page, Integer limit, String searchKey, String searchValue,Integer guideId) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<ScenicOrder> scenicOrderPage = new Page<>(page, limit);
        EntityWrapper<ScenicOrder> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            wrapper.eq(searchKey, searchValue);
        }
        wrapper.orderBy("create_time", true);
        if(guideId!=null){
            wrapper.eq("guide_id",guideId);
        }
        scenicOrderService.selectPage(scenicOrderPage, wrapper);
        List<ScenicOrder> count=scenicOrderService.selectList(wrapper);
        List<ScenicOrder> scenicOrderList = scenicOrderPage.getRecords();
        return new PageResult<>(scenicOrderList, count.size());
    }
}
