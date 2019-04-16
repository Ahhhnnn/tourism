package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: Scenic
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1515:10
 */
@Data
@TableName("tr_scenic")
public class Scenic {
    @TableId
    private Integer id;

    private String name;

    private String picture;//图片路径

    private String price;

    private String type;

    private String place;

    private Integer statu;//状态

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间
}
