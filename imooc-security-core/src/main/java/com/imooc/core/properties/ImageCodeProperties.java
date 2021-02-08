package com.imooc.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties {

    private int width = 200;

    private int height = 30;

    private int size = 6;

    private int expiredIn = 60;
}
