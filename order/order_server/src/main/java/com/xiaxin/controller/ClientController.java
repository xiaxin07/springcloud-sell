//package com.xiaxin.controller;
//
//import com.netflix.discovery.converters.Auto;
//import com.xiaxin.config.RestTemplateConfig;
//import com.xiaxin.domain.ProductInfo;
//import com.xiaxin.dto.CartDto;
//import com.xiaxin.product.client.ProductClient;
//import com.xiaxin.product.common.DecreaseStockInput;
//import feign.Feign;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by xiaxin on 2018/10/31.
// * 应用间通信
// */
//@RestController
//@Slf4j
//public class ClientController {
//
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//    //    @Autowired
////    private RestTemplate restTemplate;
//    @Autowired
//    private ProductClient productClient;
//
//    @RequestMapping("/getProductMsg")
//    public String getProductMsg() {
//        //第一种方式 RestTemplate url写死的，不灵活
////        RestTemplate restTemplate = new RestTemplate();
////        String msg = restTemplate.getForObject("http://localhost:8080/product/msg", String.class);
//
//        //第二种方式 利用注入LoadBalancerClient 获取host、port
////        RestTemplate restTemplate = new RestTemplate();
////        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");//参数为服务的名称
////
////        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
////        String msg = restTemplate.getForObject(url, String.class);
//
//        //第三种方式 使用@LoadBalanced注解 在RestTemplate的url中使用应用名字
////        String msg = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//
//        String msg = /*productClient.getProductMsg();*/"";
//        return msg;
//
//    }
//
//
//    @GetMapping("/getProductList")
//    public String getProductList(/*@RequestBody List<String> productIdList*/) {
//        List<com.xiaxin.product.common.ProductInfoOutput> productInfoOutputList = productClient.listForOrder(Arrays.asList("361116", "521316", "521507"));
//        log.info("prouctInfoList={}", productInfoOutputList);
//        return "ok";
//    }
//
//    @GetMapping("/productDecreaseStock")
//    public String decreaseProductList() {
//        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("361116", 1)));
//        return "ok";
//    }
//}
//
