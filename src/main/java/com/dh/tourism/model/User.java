package com.dh.tourism.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author duhan
 * @title: User
 * @projectName tourism
 * @description: TODO
 * @date 2019/4/1423:13
 */
@Data
@TableName("sys_user")
public class User {
    private Integer id;

    private String userName;
}
