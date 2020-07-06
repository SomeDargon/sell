package com.dragon.order.controller;

import com.dragon.order.client.ProductClient;
import com.dragon.order.dataobject.ProductInfo;
import com.dragon.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ProductClient productClient;
//    @Autowired
//    private RestTemplate restTemplate;
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 1. 第一种方式 (直接使用restTemplate)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//        log.info("response={}", response);
//        return response;

        // 第二种方式 (利用loadBalancerClient, 通过应用名获取url，然后在使用restTemplate)
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        log.info("host:" + serviceInstance.getHost() + ": " + serviceInstance.getPort());
//        String url = String.format("http://%s%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
//        String response = "" ;//restTemplate.getForObject(url, String.class);
        // 第三种方式
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("response={}", response);
        String response = productClient.productMsg();
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfo> productInfos =  productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("response={}", productInfos);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String ProductDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new CartDTO("157875196366160022", 5)));
        return "ok";
    }
}
