package com.xiaxin.enums;

import lombok.Getter;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Getter
public enum OrderStatusEnums {


    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
