package com.xiaxin.enums;

import lombok.Getter;

/**
 * Created by xiaxin on 2018/2/19.
 */
@Getter
public enum ProductStatusEnums {
    UP(0, "上架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
