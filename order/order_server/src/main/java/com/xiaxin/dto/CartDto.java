package com.xiaxin.dto;

import lombok.Data;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
