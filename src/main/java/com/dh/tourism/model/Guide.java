package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author duhan
 * @title: Guide
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1623:36
 */
@Data
@TableName("tr_guide")
public class Guide {
    @TableId
    private Integer id;

    private String acount;//账号

    private String guideName;

    private String password;

    private Integer age;

    private String sex;

    private String phone;

    private String salary;

    private String address;

    private Integer statu;

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间
}
