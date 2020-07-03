package com.dragon.product.service.impl;

import com.dragon.product.dataobject.ProductInfo;
import com.dragon.product.enums.ProductStatusEnum;
import com.dragon.product.repository.ProductInfoRepository;
import com.dragon.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
