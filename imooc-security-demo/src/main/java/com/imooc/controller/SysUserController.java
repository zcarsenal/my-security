package com.imooc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.FileInfoDTO;
import com.imooc.dto.SysUserCondition;
import com.imooc.entity.SysUser;
import com.imooc.service.SysUserService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 系统用户 前端控制器
 *
 * @author zhouchao
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

  @Autowired private SysUserService sysUserService;

  @GetMapping("me")
  public Object me(@AuthenticationPrincipal UserDetails userDetails) {
    return userDetails;
  }

  @GetMapping
  @JsonView(SysUser.SysUserSimpleView.class)
  public List<SysUser> listAllSysUser(
      SysUserCondition condition,
      @PageableDefault(page = 1, size = 20, sort = "username,asc") Pageable pageable) {
    System.out.println(
        ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
    System.out.println(
        ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
    return sysUserService.list();
  }

  @GetMapping("/{id:\\d+}")
  @JsonView(SysUser.SysUserDetailView.class)
  public SysUser getInfoById(@PathVariable Long id) {
    return sysUserService.getById(id);
  }

  @PostMapping("/file")
  public FileInfoDTO upload(MultipartFile file) throws IOException {
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getName());
    System.out.println(file.getSize());
    File localFile = new File(System.currentTimeMillis() + ".txt");
    file.transferTo(localFile);
    return new FileInfoDTO(localFile.getAbsolutePath());
  }

  @GetMapping("/download/{id}")
  public void download(@PathVariable String id, HttpServletResponse response) {
    try (FileInputStream fs = new FileInputStream(new File(id + ".txt"));
        OutputStream outputStream = response.getOutputStream(); ) {
      response.setContentType("application/x-download");
      response.addHeader("Content-Disposition", "attachment;filename=text.txt");
      IOUtils.copy(fs, outputStream);
      outputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
