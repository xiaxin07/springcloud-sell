package com.xiaxin.service.impl;

import com.netflix.discovery.converters.Auto;
import com.xiaxin.domain.OrderDetail;
import com.xiaxin.domain.OrderMaster;
import com.xiaxin.domain.ProductInfo;
import com.xiaxin.dto.CartDto;
import com.xiaxin.dto.OrderDto;
import com.xiaxin.enums.OrderStatusEnums;
import com.xiaxin.enums.PayStatusEnums;
import com.xiaxin.enums.ResultEnums;
import com.xiaxin.exception.SellException;
import com.xiaxin.product.client.ProductClient;
import com.xiaxin.product.common.DecreaseStockInput;
import com.xiaxin.product.common.ProductInfoOutput;
import com.xiaxin.repository.OrderDetailRepository;
import com.xiaxin.repository.OrderMasterRepository;
import com.xiaxin.service.OrderService;
import com.xiaxin.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;


    /**
     * 创建订单
     *
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.getUniqueKey();

        //查询商品信息(调用商品服务)
        List<String> productIdList = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDto.getOrderDetailList()) {
            for (ProductInfoOutput productInfo: productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDto.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDto;

    }


}
