package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: ScenicOrder
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1914:00
 */
@Data
@TableName("tr_scenic_order")
public class ScenicOrder {
    @TableId
    private Integer id;

    private Integer scenicId;

    private Integer teamId;

    private Integer guideId;

    private String guideName;

    private String startTime;//出发时间

    private String endTime; //归回日期

    private Integer statu;//状态

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间

}
