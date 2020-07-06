package com.dragon.product.service;

import com.dragon.product.commmon.DecreaseStockInput;
import com.dragon.product.commmon.ProductInfoOutput;
import com.dragon.product.dto.CartDTO;
import com.dragon.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询参数列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOS);
}
