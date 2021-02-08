package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统用户
 *
 * @author zhouchao
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "系统用户")
public class SysUser implements Serializable {

  public interface SysUserSimpleView {}
  ;

  public interface SysUserDetailView extends SysUserSimpleView {}
  ;

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "用户id")
  @TableId(value = "user_id", type = IdType.AUTO)
  private Long userId;

  @ApiModelProperty(value = "所属机构")
  private Long orgId;

  @ApiModelProperty(value = "用户名")
  @JsonView(SysUserSimpleView.class)
  private String username;

  @ApiModelProperty(value = "密码")
  @JsonView(SysUserDetailView.class)
  private String password;

  @ApiModelProperty(value = "姓名(昵称)")
  private String nickname;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "手机号")
  private String mobile;

  @ApiModelProperty(value = "状态 0:禁用，1:正常")
  private Integer status;

  @ApiModelProperty(value = "头像上传 0:未上传 1:上传")
  private Integer avatarStatus;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "创建用户id")
  private Long userIdCreate;

  @ApiModelProperty(value = "创建时间")
  private Date gmtCreate;

  @ApiModelProperty(value = "修改时间")
  private Date gmtModified;

  @ApiModelProperty(value = "是否修改过初始密码")
  private Integer isModifyPwd;

  private Integer id;
}
