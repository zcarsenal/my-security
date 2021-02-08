package com.imooc.service.impl;

import com.imooc.entity.SysUser;
import com.imooc.dao.SysUserDao;
import com.imooc.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zhouchao
 * @since 2021-01-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}
