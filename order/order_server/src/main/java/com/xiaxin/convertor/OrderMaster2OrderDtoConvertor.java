package com.xiaxin.convertor;

import com.xiaxin.domain.OrderMaster;
import com.xiaxin.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaxin on 2018/2/20.
 */
public class OrderMaster2OrderDtoConvertor {
    public static OrderDto convertor(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convertor(List<OrderMaster> orderMasterList) {
        List<OrderDto> orderDtoList = orderMasterList.stream().map(e -> convertor(e)).collect(Collectors.toList());
        return orderDtoList;
    }

}
