package com.dragon.order.service.impl;

import com.dragon.order.client.ProductClient;
import com.dragon.order.dataobject.OrderDetail;
import com.dragon.order.dataobject.OrderMaster;
import com.dragon.order.dataobject.ProductInfo;
import com.dragon.order.dto.CartDTO;
import com.dragon.order.dto.OrderDTO;
import com.dragon.order.enums.OrderStatusEnum;
import com.dragon.order.enums.PayStatusEnum;
import com.dragon.order.repository.OrderDetailRepository;
import com.dragon.order.repository.OrderMasterRepository;
import com.dragon.order.service.OrderService;
import com.dragon.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        /**
         * 1. TODO 查询商品信息（调用商品服务）
         * 2. TODO 计算总价
         * 3. TODO 扣库存（调用商品服务）
         * 4. 订单入库
         */
        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 计算总价
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo: productInfoList) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    // 单价 * 数量
                    orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 扣库存（调用商品服务）
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);


        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
