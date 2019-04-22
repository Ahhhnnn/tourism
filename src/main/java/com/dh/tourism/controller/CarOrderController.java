package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Guide;
import com.dh.tourism.model.Car;
import com.dh.tourism.model.CarOrder;
import com.dh.tourism.model.Team;
import com.dh.tourism.service.GuideService;
import com.dh.tourism.service.CarOrderService;
import com.dh.tourism.service.CarService;
import com.dh.tourism.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duhan
 * @title: CarOrderController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1923:24
 */
@RestController
@RequestMapping("/carOrder")
public class CarOrderController {

    private static Integer count=1;
    @Autowired
    private CarOrderService carOrderService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private CarService carService;

    @RequestMapping("insert")
    public PageResult insert(CarOrder carOrder){
        carOrder.setId(count);
        count++;
        if(carOrder.getTeamId()==null){
            return new PageResult("请选择团队",400);
        }
        Integer guideId=carOrder.getGuideId();
        Guide guide=guideService.getGuideById(guideId);
        carOrder.setGuideName(guide.getGuideName());
        //计算订单总价
        Integer teamId=carOrder.getTeamId();
        //设置团队名
        Team team=teamService.queryById(teamId);
        carOrder.setTeamName(team.getTeamName());
        Integer personNum=teamService.queryPersonNumById(teamId);
        Car car=carService.queryById(carOrder.getCarId());
        carOrder.setCarName(car.getCarName());
        carOrder.setCarType(car.getCarType());
        //更改该种汽车剩余数量
        Integer leaseCarNum=carOrder.getCarNum();//该订单租车数量
        Double price=(Double.valueOf(car.getPrice())*leaseCarNum);
        carOrder.setPrice(String.valueOf(price));
        car.setCarNum(car.getCarNum()-leaseCarNum);
        car.setCarOut(car.getCarOut()+leaseCarNum);
        carService.updateById(car);
        if(!carOrderService.insert(carOrder)){
            return new PageResult("预订失败",400);
        }
        return new PageResult("预订成功",200);
    }

    @RequestMapping("/update")
    public PageResult updatePerson(CarOrder carOrder){

        Integer teamId=carOrder.getTeamId();
        //设置团队名
        Team team=teamService.queryById(teamId);
        carOrder.setTeamName(team.getTeamName());
        Car car=carService.queryById(carOrder.getCarId());
        Integer leaseCarNum=carOrder.getCarNum();//该订单租车数量
        Double price=(Double.valueOf(car.getPrice())*leaseCarNum);
        carOrder.setPrice(String.valueOf(price));
        car.setCarNum(car.getCarNum()-leaseCarNum);
        car.setCarOut(car.getCarOut()+leaseCarNum);
        carService.updateById(car);
        if(carOrderService.updateById(carOrder)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{carOrderId}")
    public PageResult deltePerson(@PathVariable("carOrderId") Integer carOrderId){

        CarOrder carOrder=carOrderService.queryById(carOrderId);
        Car car=carService.queryById(carOrder.getCarId());
        Integer leaseCarNum=carOrder.getCarNum();//该订单租车数量
        //删除订单后，要修改该种汽车剩余数量
        car.setCarNum(car.getCarNum()+leaseCarNum);
        car.setCarOut(car.getCarOut()-leaseCarNum);
        carService.updateById(car);
        if(carOrderService.deleteById(carOrderId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }

    @PutMapping("/statu")
    public JsonResult updatestatu(Integer carOrderId, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        CarOrder carOrder=new CarOrder();
        carOrder.setStatu(statu);
        carOrder.setId(carOrderId);
        if (carOrderService.updateById(carOrder)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @RequestMapping("/query")
    public PageResult<CarOrder> list(Integer page, Integer limit, String searchKey, String searchValue,Integer guideId) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<CarOrder> carOrderPage = new Page<>(page, limit);
        EntityWrapper<CarOrder> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            wrapper.eq(searchKey, searchValue);
        }
        wrapper.orderBy("create_time", true);
        if(guideId!=null){
            wrapper.eq("guide_id",guideId);
        }
        carOrderService.selectPage(carOrderPage, wrapper);
        List<CarOrder> count=carOrderService.selectList(wrapper);
        List<CarOrder> carOrderList = carOrderPage.getRecords();
        return new PageResult<>(carOrderList, count.size());
    }
}
