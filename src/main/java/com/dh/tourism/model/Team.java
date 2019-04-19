package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: Team
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1816:11
 */
@Data
@TableName("tr_team")
public class Team {
    @TableId
    private Integer id;

    private String teamName;//团队名称

    private Integer guideId;

    private String guideName;

    private String personIds;

    private String personNames;

    private Integer statu;

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间
}
