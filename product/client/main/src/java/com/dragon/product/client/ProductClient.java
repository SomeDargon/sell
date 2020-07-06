package com.dragon.product.client;

import com.dragon.product.commmon.DecreaseStockInput;
import com.dragon.product.commmon.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient
public interface ProductClient {
    /**
     * 获取商品列表（给订单服务用的)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) ;
}
