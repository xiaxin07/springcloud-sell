package com.xiaxin.service;

import com.xiaxin.domain.ProductCategory;

import java.util.List;

/**
 * Created by xiaxin on 2018/2/19.
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
