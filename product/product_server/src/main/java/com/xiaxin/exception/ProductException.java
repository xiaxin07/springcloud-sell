package com.xiaxin.exception;

import com.xiaxin.enums.ResultEnums;
import lombok.Getter;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Getter
public class ProductException extends RuntimeException{


    private Integer code;

    public ProductException(ResultEnums resultEnums) {
        super(resultEnums.getMessage());
        this.code = resultEnums.getCode();
    }

    public ProductException(Integer code, String message) {
        super(message);
        this.code=code;
    }
}
