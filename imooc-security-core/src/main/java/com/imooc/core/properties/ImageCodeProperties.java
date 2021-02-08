package com.imooc.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties extends ValidateCodeProperties{

    public ImageCodeProperties() {
        setSize(4);
    }

    private int width = 200;

    private int height = 30;
}
