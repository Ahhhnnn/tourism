package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Person;
import com.dh.tourism.service.PersonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @RequestMapping("/insert")
    public PageResult insertPerson(Person person){

        if(personService.insert(person)){
            return new PageResult("添加成功",200);
        }
        return new PageResult("添加失败",400);
    }

    @RequestMapping("/update")
    public PageResult updatePerson(Person person){

        if(personService.updateById(person)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{personId}")
    public PageResult deltePerson(@PathVariable("personId") Integer personId){

        if(personService.deleteById(personId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }
    @ApiOperation(value = "查询所有员工", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "limit", value = "每页多少条", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "searchKey", value = "筛选条件字段", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "searchValue", value = "筛选条件关键字", dataType = "String", paramType = "form"),
    })
    @RequestMapping("/query")
    public PageResult<Person> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<Person> userPage = new Page<>(page, limit);
        EntityWrapper<Person> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            if(searchKey.equals("person_name")||searchKey.equals("phone")){
                wrapper.like(searchKey,searchValue);
            }else {
                wrapper.eq(searchKey, searchValue);
            }
        }
        wrapper.orderBy("create_time", true);
        personService.selectPage(userPage, wrapper);
        List<Person> count=personService.selectList(wrapper);
        List<Person> userList = userPage.getRecords();
        return new PageResult<>(userList, count.size());
    }

    @ApiOperation(value = "修改员工状态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "员工Id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "statu", value = "状态：0正常，1冻结", required = true, dataType = "Integer", paramType = "form"),
    })
    @PutMapping("/statu")
    public JsonResult updatestatu(Integer personId, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        Person person=new Person();
        person.setStatu(statu);
        person.setPersonId(personId);
        if (personService.updateById(person)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 查询所有状态为0 的员工
     * @return
     */
    @RequestMapping("/queryAll")
    public PageResult<Person> queryAll(){
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("statu",0);
        List<Person> personList =personService.selectList(entityWrapper);
        return new PageResult<>(0,"查询员工成功",personList.size(),personList);
    }

}
