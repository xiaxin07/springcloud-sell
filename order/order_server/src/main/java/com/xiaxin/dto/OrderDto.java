package com.xiaxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaxin.domain.OrderDetail;
import com.xiaxin.enums.OrderStatusEnums;
import com.xiaxin.enums.PayStatusEnums;
import lombok.Data;
import org.apache.commons.lang.enums.EnumUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)/**不给前端返回null*/
public class OrderDto {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetailList /*= new ArrayList<>()*/;

    //对象转成Json格式会忽略掉此方法
}
