package com.xiaxin.service;

import com.xiaxin.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by xiaxin on 2018/2/20.
 */
public interface OrderService {

    /**
     * 创建订单
     */
    OrderDto create(OrderDto orderDto);




}
