package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: HotalOrder
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/2218:23
 */
@Data
@TableName("tr_hotal_order")
public class HotalOrder {
    @TableId
    private Integer id;

    private Integer teamId;

    private String teamName;

    private Integer guideId;

    private String guideName;

    private Integer hotalId;

    private String hotalName;

    private String roomName;

    private Integer roomNum;

    private String startTime;

    private String endTime;

    private String price;

    private Integer statu;

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间

}
