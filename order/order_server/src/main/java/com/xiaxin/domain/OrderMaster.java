package com.xiaxin.domain;

import com.xiaxin.enums.OrderStatusEnums;
import com.xiaxin.enums.PayStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiaxin on 2018/2/20.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus= OrderStatusEnums.NEW.getCode();

    private Integer payStatus= PayStatusEnums.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
