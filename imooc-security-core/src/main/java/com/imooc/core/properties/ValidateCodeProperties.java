package com.imooc.core.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {

  private int size = 6;

  private int expiredIn = 60;
}
