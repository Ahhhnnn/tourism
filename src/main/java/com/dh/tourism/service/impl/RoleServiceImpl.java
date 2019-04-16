package com.dh.tourism.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dh.tourism.dao.RoleMapper;
import com.dh.tourism.model.Role;
import com.dh.tourism.service.RoleService;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
