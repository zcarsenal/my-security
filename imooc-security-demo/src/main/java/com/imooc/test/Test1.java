package com.imooc.test;

public class Test1 {

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      try {
        System.out.println(i);
        int a = i / 0;
      } finally {

      }
    }
  }
}
