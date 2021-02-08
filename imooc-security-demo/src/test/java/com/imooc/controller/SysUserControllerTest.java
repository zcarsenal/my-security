package com.imooc.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserControllerTest {

  @Autowired private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void set() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void listAllSysUser() throws Exception {
    String ret =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/sysUser")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .param("username", "admin")
                    .param("age", "11")
                    .param("ageTo", "22")
                    .param("size", "11")
                    .param("page", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            // github 搜索 jsonPath  -> java 可以找到该表达式的意义
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
            .andReturn()
            .getResponse()
            .getContentAsString();
    System.out.println(ret);
  }

  @Test
  public void whenGetSysUserInfoSuccess() throws Exception {
    String ret =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/sysUser/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("admin"))
            .andReturn()
            .getResponse()
            .getContentAsString();
    System.out.println(ret);
  }

  @Test
  public void whenGetSysUserInfoFail() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/sysUser/a").contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void whenUploadSuccess() throws Exception {
    String ret =
        mockMvc
            .perform(
                MockMvcRequestBuilders.fileUpload("/sysUser/file")
                    .file(
                        new MockMultipartFile(
                            "file",
                            "text.txt",
                            MediaType.MULTIPART_FORM_DATA_VALUE,
                            "hello".getBytes())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    System.out.println(ret);
    ;
  }
}
