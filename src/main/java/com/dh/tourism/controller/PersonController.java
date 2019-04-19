package com.dh.tourism.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.Person;
import com.dh.tourism.model.Team;
import com.dh.tourism.service.PersonService;
import com.dh.tourism.service.TeamService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    private static Integer count=2;
    @Autowired
    private PersonService personService;
    @Autowired
    private TeamService teamService;
    
    @RequestMapping("/insert")
    public PageResult insertPerson(Person person){

        person.setPersonId(count);
        count++;
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
     * 查询指定id的游客
     * @param personIds
     * @return
     */
    @RequestMapping("queryByPersonIds")
    public PageResult queryByPersonIds(Integer page, Integer limit,String personIds){
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<Person> personPage = new Page<>(page, limit);
        EntityWrapper<Person> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_time", true);
        List<Integer> personIdsInt=new ArrayList<>();
        List<String> personIdsString= Arrays.asList(personIds.split(","));
        for(String personId:personIdsString){
            personIdsInt.add(Integer.valueOf(personId));
        }
        wrapper.in("person_id",personIdsInt);
        personService.selectPage(personPage, wrapper);
        List<Person> count=personService.selectList(wrapper);
        List<Person> userList = personPage.getRecords();
        return new PageResult<>(userList, count.size());
    }

    /**
     * 查询所有状态为0 的游客
     * @return
     */
    @RequestMapping("/queryAllStatu")
    public PageResult<Person> queryAll(){
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("statu",0);
        List<Person> personList =personService.selectList(entityWrapper);
        return new PageResult<>(200,"查询游客成功",personList.size(),personList);
    }

    @RequestMapping("queryNotRepact")
    public PageResult<Person> queryNotRepact(){
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("statu",0);
        //查询所有状态正常的旅游
        List<Person> personList =personService.selectList(entityWrapper);
        //查询所有状态正常的团队
        List<Team> teamList=teamService.selectList(entityWrapper);
        List<String> teamPersonIds=new ArrayList<>();//团队里的所有游客ids
        for (Team team:teamList){
            teamPersonIds.add(team.getPersonIds());
        }
        List<Integer> teamPersonIdsInt=new ArrayList<>();
        for(String personId:teamPersonIds){
            for(String personid:personId.split(",")){
                teamPersonIdsInt.add(Integer.valueOf(personid));
            }
        }
        List<Person> finalPersonList=new ArrayList<>();
        finalPersonList.addAll(personList);
        for(Person person:personList){

            if(teamPersonIdsInt.contains(person.getPersonId())){
                finalPersonList.remove(person);
            }
        }
        return new PageResult<>(200,"查询成功",finalPersonList.size(),finalPersonList);
    }

}
