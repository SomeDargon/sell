package com.dragon.order.controller;

import com.dragon.order.VO.ResultVO;
import com.dragon.order.VO.ResultVOUtil;
import com.dragon.order.converter.OrderForm2OrderDTOConverter;
import com.dragon.order.dto.OrderDTO;
import com.dragon.order.enums.ResultEnum;
import com.dragon.order.exception.OrderException;
import com.dragon.order.form.OrderForm;
import com.dragon.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService  orderService;

    /**
     * 1. 参数效验
     * 2. 查询商品信息（调用商品服务）
     * 3. 计算总价
     * 4. 扣库存（调用商品服务）
     * 5. 订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                           BindingResult bindingResult){
        // 查询商品信息（调用商品服务）
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        // orderFrom -> orderDto
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO reuslt = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", reuslt.getOrderId());
        return ResultVOUtil.success(map);
    }

}
