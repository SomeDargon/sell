package com.dragon.product.service.impl;


import com.dragon.product.ProductApplicationTests;
import com.dragon.product.commmon.DecreaseStockInput;
import com.dragon.product.commmon.ProductInfoOutput;
import com.dragon.product.dataobject.ProductInfo;
import com.dragon.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {
    @Autowired
    private ProductService productService;
    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertTrue(productInfos.size() > 0);
    }

    @Test
    public void findByProductList() throws Exception {
        List<ProductInfoOutput> productInfos = productService.findList(Arrays.asList("157875196366160022"));
        Assert.assertTrue(productInfos != null);
    }

    @Test
    public void decreaseStock()  throws Exception{
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022", 2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}
