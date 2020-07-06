package com.dragon.product.service.impl;

import com.dragon.product.commmon.DecreaseStockInput;
import com.dragon.product.commmon.ProductInfoOutput;
import com.dragon.product.dto.CartDTO;
import com.dragon.product.dataobject.ProductInfo;
import com.dragon.product.enums.ProductStatusEnum;
import com.dragon.product.enums.ResultEnum;
import com.dragon.product.exception.ProductException;
import com.dragon.product.repository.ProductInfoRepository;
import com.dragon.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputs) {
            Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(decreaseStockInput.getProductId());
            // 判断商品是否存在
            if (!optionalProductInfo.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = optionalProductInfo.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            // 库存是否足够
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }


}
