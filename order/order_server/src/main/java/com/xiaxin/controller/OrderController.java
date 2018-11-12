package com.xiaxin.controller;

import com.netflix.discovery.converters.Auto;
import com.xiaxin.convertor.OrderForm2OrderDto;
import com.xiaxin.dto.OrderDto;
import com.xiaxin.enums.ResultEnums;
import com.xiaxin.exception.SellException;
import com.xiaxin.form.OrderForm;
import com.xiaxin.service.OrderService;
import com.xiaxin.utils.ResultVoUtil;
import com.xiaxin.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaxin on 2018/10/31.
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 1.参数校验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     *
     * @return
     */
    @PostMapping("/create")
    public ResultVo create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnums.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

//        orderForm->orderdto
        OrderDto orderDto = OrderForm2OrderDto.convert(orderForm);
        orderDto  = orderService.create(orderDto);

        if (StringUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new SellException(ResultEnums.CART_EMPTY);
        }
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDto.getOrderId());
        return ResultVoUtil.success(map);
    }

}
