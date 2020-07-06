package com.dragon.product.controller;


import com.dragon.product.commmon.DecreaseStockInput;
import com.dragon.product.commmon.ProductInfoOutput;
import com.dragon.product.dto.CartDTO;
import com.dragon.product.VO.ProductInfoVO;
import com.dragon.product.VO.ProductVO;
import com.dragon.product.VO.ResultVO;
import com.dragon.product.dataobject.ProductCategory;
import com.dragon.product.dataobject.ProductInfo;
import com.dragon.product.service.ProductCategoryService;
import com.dragon.product.service.ProductService;
import com.dragon.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 1. 获取所有在架商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public ResultVO list(){
        // 1. 获取所有在架商品
        List<ProductInfo> productInfos = productService.findUpAll();
        // 2. 获取类目type列表
        List<Integer> categoryTypeList = productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        // 3. 查询类目
        List<ProductCategory> categories = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        // 4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo: productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOS);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }



}
