package com.xiaxin.convertor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xiaxin.domain.OrderDetail;
import com.xiaxin.dto.OrderDto;
import com.xiaxin.enums.ResultEnums;
import com.xiaxin.exception.SellException;
import com.xiaxin.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaxin on 2018/2/25.
 */
@Slf4j
public class OrderForm2OrderDto {
    public static OrderDto convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        //将json字符串转化为javaBean
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("[对象转换]错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnums.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }


}
