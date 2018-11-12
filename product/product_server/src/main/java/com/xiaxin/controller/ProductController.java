package com.xiaxin.controller;

import com.xiaxin.domain.ProductCategory;
import com.xiaxin.domain.ProductInfo;
import com.xiaxin.dto.CartDto;
import com.xiaxin.service.ProductCategoryService;
import com.xiaxin.service.ProductService;
import com.xiaxin.utils.ResultVoUtil;
import com.xiaxin.vo.ProductInfoVo;
import com.xiaxin.vo.ProductVo;
import com.xiaxin.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaxin on 2018/10/30.
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有在架商品
     * 获取类目type列表
     * 查询类目
     * 构造数据
     *
     * @return
     */
    @GetMapping("list")
    public ResultVo<ProductVo> list() {

        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                ProductInfoVo productInfoVo = new ProductInfoVo();
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);

        }

        return ResultVoUtil.success(productVoList);
    }

    /**
     * 查询商品列表（供订单使用）
     *
     * @param productIdList
     * @return
     */
    @PostMapping("listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        List<ProductInfo> list = productService.findList(productIdList);
        return list;
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDto> cartDtoList) {
        productService.decreaseStock(cartDtoList);
    }
}

