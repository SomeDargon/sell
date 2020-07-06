package com.dragon.product.service;

import com.dragon.product.ProductApplicationTests;
import com.dragon.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductCategoryServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
//        Assert.assertTrue(productCategories.size() >  0);
    }

}
