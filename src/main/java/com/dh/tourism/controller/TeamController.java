package com.dh.tourism.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dh.tourism.commom.JsonResult;
import com.dh.tourism.commom.PageResult;
import com.dh.tourism.model.*;
import com.dh.tourism.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duhan
 * @title: TeamController
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1816:23
 */
@RestController
@RequestMapping("/team")
public class TeamController {
    private static Integer count=2;

    @Autowired
    private TeamService teamService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ScenicOrderService scenicOrderService;

    @Autowired
    private CarOrderService carOrderService;
    @Autowired
    private HotalOrderService hotalOrderService;

    @RequestMapping("/query")
    public PageResult<Team> list(Integer page, Integer limit, String searchKey, String searchValue,Integer guideId) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<Team> teamPage = new Page<>(page, limit);
        EntityWrapper<Team> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            if(searchKey.equals("team_name")||searchKey.equals("guide_name")){
                wrapper.like(searchKey,searchValue);
            }else {
                wrapper.eq(searchKey, searchValue);
            }
        }
        if(guideId!=null){
            wrapper.eq("guide_id",guideId);
        }
        wrapper.orderBy("create_time", true);
        teamService.selectPage(teamPage, wrapper);
        List<Team> count=teamService.selectList(wrapper);
        List<Team> userList = teamPage.getRecords();
        return new PageResult<>(userList, count.size());
    }

    @RequestMapping("/insert")
    public PageResult insertTeam(Team team){

        team.setId(count);
        count++;
        setTeamGuideAndPersonName(team);
        if(teamService.insert(team)){
            return new PageResult("添加成功",200);
        }
        return new PageResult("添加失败",400);
    }

    @RequestMapping("/update")
    public PageResult updateTeam(Team team){

        setTeamGuideAndPersonName(team);
        if(teamService.updateById(team)){
            return new PageResult("修改成功",200);
        }
        return new PageResult("修改失败",400);
    }

    @RequestMapping("/delete/{teamId}")
    public PageResult delteTeam(@PathVariable("teamId") Integer teamId){

        if(teamService.deleteById(teamId)){
            return new PageResult("删除成功",200);
        }
        return new PageResult("删除失败",400);
    }
    @PutMapping("/statu")
    public JsonResult updatestatu(Integer id, Integer statu) {
        if (statu == null || (statu != 0 && statu != 1)) {
            return JsonResult.error("statu值需要在[0,1]中");
        }
        Team team=new Team();
        team.setStatu(statu);
        team.setId(id);
        if (teamService.updateById(team)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 查询去重后的 团队
     * @return
     */
    @RequestMapping("queryNotRepact")
    public PageResult<Team> queryNotRepact(Integer guideId){
        EntityWrapper<ScenicOrder> scenicOrderWrapper=new EntityWrapper<ScenicOrder>();
        List<ScenicOrder> scenicOrders=scenicOrderService.selectList(scenicOrderWrapper);
        List<Integer> existTeamIds=scenicOrders.stream().map(ScenicOrder->ScenicOrder.getTeamId()).collect(Collectors.toList());
        List<Team> finalTeam=new ArrayList<>();

        EntityWrapper<Team> teamWrapper=new EntityWrapper<>();
        teamWrapper.eq("statu",0);
        teamWrapper.eq("guide_id",guideId);
        List<Team> teamList=teamService.selectList(teamWrapper);
        finalTeam.addAll(teamList);
        for(Team team:teamList){
            if(existTeamIds.contains(team.getId())){
                finalTeam.remove(team);
            }
        }
        return new PageResult<Team>(200,"查询成功",finalTeam.size(),finalTeam);
    }

    /**
     * 查询去重后的团队列表，租车界面使用
     * @param guideId
     * @return
     */
    @RequestMapping("queryNotRepactForCar")
    public PageResult<Team> queryNotRepactForCar(Integer guideId){
        EntityWrapper<CarOrder> CarOrderWrapper=new EntityWrapper<CarOrder>();
        List<CarOrder> CarOrders= carOrderService.selectList(CarOrderWrapper);
        List<Integer> existTeamIds=CarOrders.stream().map(CarOrder->CarOrder.getTeamId()).collect(Collectors.toList());
        List<Team> finalTeam=new ArrayList<>();

        EntityWrapper<Team> teamWrapper=new EntityWrapper<>();
        teamWrapper.eq("statu",0);
        teamWrapper.eq("guide_id",guideId);
        List<Team> teamList=teamService.selectList(teamWrapper);
        finalTeam.addAll(teamList);
        for(Team team:teamList){
            if(existTeamIds.contains(team.getId())){
                finalTeam.remove(team);
            }
        }
        return new PageResult<Team>(200,"查询成功",finalTeam.size(),finalTeam);
    }

    /**
     * 查询去重后的团队列表，租车界面使用
     * @param guideId
     * @return
     */
    @RequestMapping("queryNotRepactForHotal")
    public PageResult<Team> queryNotRepactForHotal(Integer guideId){
        EntityWrapper<HotalOrder> HotalOrderWrapper=new EntityWrapper<HotalOrder>();
        List<HotalOrder> HotalOrders= hotalOrderService.selectList(HotalOrderWrapper);
        List<Integer> existTeamIds=HotalOrders.stream().map(hotalOrder->hotalOrder.getTeamId()).collect(Collectors.toList());
        List<Team> finalTeam=new ArrayList<>();

        EntityWrapper<Team> teamWrapper=new EntityWrapper<>();
        teamWrapper.eq("statu",0);
        teamWrapper.eq("guide_id",guideId);
        List<Team> teamList=teamService.selectList(teamWrapper);
        finalTeam.addAll(teamList);
        for(Team team:teamList){
            if(existTeamIds.contains(team.getId())){
                finalTeam.remove(team);
            }
        }
        return new PageResult<Team>(200,"查询成功",finalTeam.size(),finalTeam);
    }


    @RequestMapping("queryAllTeamByGuideId")
    public PageResult<Team> queryAllTeamByGuideId(Integer guideId){
        EntityWrapper<Team> teamEntityWrapper=new EntityWrapper<>();
        teamEntityWrapper.eq("guide_id",guideId);
        List<Team> teamList=teamService.selectList(teamEntityWrapper);
        return  new PageResult<>(200,"查询成功",teamList.size(),teamList);
    }

    public void setTeamGuideAndPersonName(Team team){
        Integer guideId=team.getGuideId();
        String personIds=team.getPersonIds();
        List<Integer> guideIdsInt=new ArrayList<>();
        List<Integer> personIdsInt=new ArrayList<>();

        guideIdsInt.add(guideId);

        for(String personid:personIds.split(",")){
            personIdsInt.add(Integer.valueOf(personid));
        }
        EntityWrapper<Guide> guideEntity=new EntityWrapper<>();
        guideEntity.in("id",guideIdsInt);
        EntityWrapper<Person> personEntity=new EntityWrapper<>();
        personEntity.in("person_id",personIdsInt);
        List<Guide> guideList=guideService.selectList(guideEntity);
        List<Person> personList=personService.selectList(personEntity);
        StringBuilder guidename=new StringBuilder("");
        StringBuilder personName=new StringBuilder("");
        for (Guide guide:guideList){
            guidename.append(guide.getGuideName()+" ");
        }
        for(Person person:personList){
            personName.append(person.getPersonName()+" ");
        }
        team.setGuideName(guidename.toString());
        team.setPersonNames(personName.toString());
    }
    
}
