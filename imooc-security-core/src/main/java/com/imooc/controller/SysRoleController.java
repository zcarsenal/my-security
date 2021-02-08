package com.imooc.controller;

import com.imooc.entity.SysRole;
import com.imooc.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统角色 前端控制器
 *
 * @author zhouchao
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

  @Autowired private SysRoleService sysRoleService;

  @GetMapping
  public List<SysRole> listAllRole() {
    return sysRoleService.list();
  }
}
