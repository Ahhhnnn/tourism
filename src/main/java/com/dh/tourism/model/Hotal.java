package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: Hotal
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1823:28
 */
@Data
@TableName("tr_hotal")
public class Hotal {
    @TableId
    private Integer id;

    private String hotalPicture;

    private String hotalName;

    private String grade;//酒店等级

    private String address;

    private String phone;

    private String introduce;

    private String room1;

    private Integer room1Num;

    private String room1Price;

    private String room2;

    private Integer room2Num;

    private String room2Price;

    private String room3;

    private Integer room3Num;

    private String room3Price;

    private String room4;

    private Integer room4Num;

    private String room4Price;

    private String minPrice;

    private Integer statu;//状态

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间
}
