package com.xiaxin.service.impl;

import com.xiaxin.domain.ProductInfo;
import com.xiaxin.dto.CartDto;
import com.xiaxin.enums.ProductStatusEnums;
import com.xiaxin.enums.ResultEnums;
import com.xiaxin.exception.ProductException;
import com.xiaxin.repository.ProductInfoRepository;
import com.xiaxin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by xiaxin on 2018/2/19.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;


    @Override
    public ProductInfo findOne(String productId) {
//        repository.findById(productId);
        return null;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList);
    }

    /**
     * 加库存
     * @param cartDtoList
     */
//    @Override
//    @Transactional
//    public void increaceStock(List<CartDto> cartDtoList) {
//        for (CartDto cartDto : cartDtoList) {
//            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
//            if (productInfo == null) {
//                throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
//            }
//            productInfo.setProductStock(productInfo.getProductStock() + cartDto.getProductQuantity());
//            repository.save(productInfo);
//        }
//
//    }

    /**
     * 减库存
     * @param cartDtoList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
//            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            Optional<ProductInfo> productInfoOptional = repository.findById(cartDto.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnums.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

//    @Override
//    public ProductInfo onsale(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if (productId == null) {
//            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
//        }
//        if (productInfo.getProductStatus().equals(ProductStatusEnums.UP)) {
//            throw new ProductException(ResultEnums.PRODUCT_STATUS_ERROR);
//        }
//        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());
//
//        return repository.save(productInfo);
//    }

//    @Override
//    public ProductInfo offSale(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if (productId == null) {
//            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
//        }
//        if (productInfo.getProductStatus().equals(ProductStatusEnums.DOWN)) {
//            throw new ProductException(ResultEnums.PRODUCT_STATUS_ERROR);
//        }
//        productInfo.setProductStatus(ProductStatusEnums.DOWN.getCode());
//
//        return repository.save(productInfo);
//    }
}
