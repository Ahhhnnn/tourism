package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Hotal;
import com.dh.tourism.model.HotalOrder;
import com.dh.tourism.model.Guide;
import com.dh.tourism.model.Team;
import com.dh.tourism.service.HotalOrderService;
import com.dh.tourism.service.HotalService;
import com.dh.tourism.service.GuideService;
import com.dh.tourism.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duhan
 * @title: HotalOrderController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1923:24
 */
@RestController
@RequestMapping("/hotalOrder")
public class HotalOrderController {

    private static Integer count=1;
    @Autowired
    private HotalOrderService hotalOrderService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private HotalService hotalService;

    @RequestMapping("insert")
    public PageResult insert(HotalOrder hotalOrder){
        hotalOrder.setId(count);
        count++;
        if(hotalOrder.getTeamId()==null){
            return new PageResult("请选择团队",400);
        }
        Integer guideId=hotalOrder.getGuideId();
        Guide guide=guideService.getGuideById(guideId);
        hotalOrder.setGuideName(guide.getGuideName());
        //计算订单总价
        Integer teamId=hotalOrder.getTeamId();
        //设置团队名
        Team team=teamService.queryById(teamId);
        hotalOrder.setTeamName(team.getTeamName());

        Integer roomNum=hotalOrder.getRoomNum();
        Hotal hotal=hotalService.queryById(hotalOrder.getHotalId());
        hotalOrder.setHotalName(hotal.getHotalName());
        hotalOrder.setRoomName(hotalOrder.getRoomName());
        String roomprice="";
        if(hotal.getRoom1().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom1Price();
            hotal.setRoom1Num(hotal.getRoom1Num()-hotalOrder.getRoomNum());
        }else if(hotal.getRoom2().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom2Price();
            hotal.setRoom2Num(hotal.getRoom2Num()-hotalOrder.getRoomNum());
        }else if(hotal.getRoom3().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom3Price();
            hotal.setRoom3Num(hotal.getRoom3Num()-hotalOrder.getRoomNum());
        }
        else if(hotal.getRoom4().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom4Price();
            hotal.setRoom4Num(hotal.getRoom4Num()-hotalOrder.getRoomNum());
        }
        Double price=(Double.valueOf(roomprice)*roomNum);

        hotalOrder.setPrice(String.valueOf(price));

        hotalService.updateById(hotal);
        if(!hotalOrderService.insert(hotalOrder)){
            return new PageResult("预订失败",400);
        }
        return new PageResult("预订成功",200);
    }

    @RequestMapping("/update")
    public PageResult updatePerson(HotalOrder hotalOrder){

        Integer teamId=hotalOrder.getTeamId();
        //设置团队名
        Team team=teamService.queryById(teamId);
        hotalOrder.setTeamName(team.getTeamName());
        Integer roomNum=hotalOrder.getRoomNum();
        Hotal hotal=hotalService.queryById(hotalOrder.getHotalId());
        String roomprice="";
        if(hotal.getRoom1().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom1Price();
            hotal.setRoom1Num(hotal.getRoom1Num()-hotalOrder.getRoomNum());
        }else if(hotal.getRoom2().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom2Price();
            hotal.setRoom2Num(hotal.getRoom2Num()-hotalOrder.getRoomNum());
        }else if(hotal.getRoom3().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom3Price();
            hotal.setRoom3Num(hotal.getRoom3Num()-hotalOrder.getRoomNum());
        }
        else if(hotal.getRoom4().equals(hotalOrder.getRoomName())){
            roomprice=hotal.getRoom4Price();
            hotal.setRoom4Num(hotal.getRoom4Num()-hotalOrder.getRoomNum());
        }
        Double price=(Double.valueOf(roomprice)*roomNum);

        hotalOrder.setPrice(String.valueOf(price));

        hotalService.updateById(hotal);
        if(hotalOrderService.updateById(hotalOrder)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{hotalOrderId}")
    public PageResult deltePerson(@PathVariable("hotalOrderId") Integer hotalOrderId){

        HotalOrder hotalOrder=hotalOrderService.queryById(hotalOrderId);
        Hotal hotal=hotalService.queryById(hotalOrder.getHotalId());
        if(hotal.getRoom1().equals(hotalOrder.getRoomName())){
            hotal.setRoom1Num(hotal.getRoom1Num()+hotalOrder.getRoomNum());
        }else if(hotal.getRoom2().equals(hotalOrder.getRoomName())){
            hotal.setRoom2Num(hotal.getRoom2Num()+hotalOrder.getRoomNum());
        }else if(hotal.getRoom3().equals(hotalOrder.getRoomName())){
            hotal.setRoom3Num(hotal.getRoom3Num()+hotalOrder.getRoomNum());
        }
        else if(hotal.getRoom4().equals(hotalOrder.getRoomName())){
            hotal.setRoom4Num(hotal.getRoom4Num()+hotalOrder.getRoomNum());
        }
        hotalService.updateById(hotal);
        if(hotalOrderService.deleteById(hotalOrderId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }

    @PutMapping("/statu")
    public JsonResult updatestatu(Integer hotalOrderId, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        HotalOrder hotalOrder=new HotalOrder();
        hotalOrder.setStatu(statu);
        hotalOrder.setId(hotalOrderId);
        if (hotalOrderService.updateById(hotalOrder)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @RequestMapping("/query")
    public PageResult<HotalOrder> list(Integer page, Integer limit, String searchKey, String searchValue,Integer guideId) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<HotalOrder> hotalOrderPage = new Page<>(page, limit);
        EntityWrapper<HotalOrder> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            wrapper.eq(searchKey, searchValue);
        }
        wrapper.orderBy("create_time", true);
        if(guideId!=null){
            wrapper.eq("guide_id",guideId);
        }
        hotalOrderService.selectPage(hotalOrderPage, wrapper);
        List<HotalOrder> count=hotalOrderService.selectList(wrapper);
        List<HotalOrder> hotalOrderList = hotalOrderPage.getRecords();
        return new PageResult<>(hotalOrderList, count.size());
    }
}
