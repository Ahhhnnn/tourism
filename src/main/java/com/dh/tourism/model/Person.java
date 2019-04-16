package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: People
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1523:13
 */
@Data
@TableName("tr_person")
public class Person {
    @TableId
    private Integer personId;//员工id

    private String personName;//员工姓名

    private String sex;//性别

    private String phone;//手机号

    private String password;//密码

    private String email;//邮箱

    private String birthday;//生日

    private Integer statu;     //状态

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间
}
