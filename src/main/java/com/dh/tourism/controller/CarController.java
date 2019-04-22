package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.*;
import com.dh.tourism.service.CarService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duhan
 * @title: CarController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1622:14
 */
@RestController
@RequestMapping("/car")
public class CarController {
    private static Integer count=2;
    @Autowired
    private CarService carService;

    @RequestMapping("insert")
    public PageResult insert(Car car){
        car.setId(count);
        count++;
        car.setCarNum(car.getCarSum());
        if(!carService.insert(car)){
             return new PageResult("添加失败",400);
        }
        return new PageResult("添加成功",200);
    }

    @RequestMapping("/update")
    public PageResult updatePerson(Car car){

        car.setCarNum(car.getCarSum()-car.getCarOut());
        if(carService.updateById(car)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{carId}")
    public PageResult deltePerson(@PathVariable("carId") Integer carId){

        if(carService.deleteById(carId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }

    @PutMapping("/statu")
    public JsonResult updatestatu(Integer carId, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        Car car=new Car();
        car.setStatu(statu);
        car.setId(carId);
        if (carService.updateById(car)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @RequestMapping("/query")
    public PageResult<Car> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<Car> carPage = new Page<>(page, limit);
        EntityWrapper<Car> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            wrapper.eq(searchKey, searchValue);
        }
        wrapper.orderBy("create_time", true);
        carService.selectPage(carPage, wrapper);
        List<Car> count=carService.selectList(wrapper);
        List<Car> carList = carPage.getRecords();
        return new PageResult<>(carList, count.size());
    }

    /**
     * 查询所有的车辆信息，展示车辆信息用
     * @return
     */
    @RequestMapping("queryAll")
    public PageResult<Car> queryAll(){
        EntityWrapper<Car> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("statu",0);
        List<Car> carList=carService.selectList(entityWrapper);
        return  new PageResult<>(200,"查询成功",carList.size(),carList);
    }

    @RequestMapping("queryById")
    public PageResult<Car> queryById(Integer id){

        Car car=carService.queryById(id);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        return new PageResult<>(200,"查询成功",carList.size(),carList);
    }
}
