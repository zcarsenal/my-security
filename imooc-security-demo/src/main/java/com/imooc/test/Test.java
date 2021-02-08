package com.imooc.test;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

  public static void main(String[] args) {
    UserDetails build =
        User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("user")
            .build();
    System.out.println(build.getPassword());

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode("myPassword");
    System.out.println(result);


  }
}
