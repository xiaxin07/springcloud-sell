package com.xiaxin.vo;

import lombok.Data;

/**
 * Created by xiaxin on 2018/10/30.
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;
}
