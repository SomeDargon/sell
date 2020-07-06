package com.dragon.product.repository;


import com.dragon.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfo = productInfoRepository.findByProductStatus(1);
        Assert.assertTrue(productInfo.size() > 0);
    }

    @Test
    public void findByProductIn() throws Exception{
        List<ProductInfo> productInfos = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022"));
        Assert.assertTrue(productInfos != null);
    }
}
