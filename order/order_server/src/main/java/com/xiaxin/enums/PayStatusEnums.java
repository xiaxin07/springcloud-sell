package com.xiaxin.enums;

import lombok.Getter;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Getter
public enum PayStatusEnums{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
