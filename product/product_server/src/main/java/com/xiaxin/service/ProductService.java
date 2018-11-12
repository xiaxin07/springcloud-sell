package com.xiaxin.service;

import com.xiaxin.domain.ProductInfo;
import com.xiaxin.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by xiaxin on 2018/2/19.
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    List<ProductInfo> findList(List<String> productIdList);


    //加库存

//    void increaceStock(List<CartDto> cartDtoList);
//    //减库存

    void decreaseStock(List<CartDto> cartDtoList);

//    ProductInfo onsale(String productId);
//
//    ProductInfo offSale(String productId);


}
