package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: Car
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1622:09
 */
@Data
@TableName("tr_car")
public class Car {
    @TableId
    private Integer id;

    private String carName;

    private String carType;

    private String price;

    private Integer peopleNum;//限载人数

    private Integer carSum;

    private Integer carNum;

    private Integer carOut;

    private Integer statu;

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间

}
