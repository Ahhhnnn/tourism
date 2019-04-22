package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: CarOrder
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/2215:04
 */
@Data
@TableName("tr_car_order")
public class CarOrder {
    @TableId
    private Integer id;

    private Integer teamId;

    private String teamName;

    private Integer guideId;

    private String guideName;

    private Integer carId;

    private String carName;

    private String carType;

    private Integer carNum;

    private String startTime;

    private String endTime;

    private String price;

    private Integer statu;

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间

}
