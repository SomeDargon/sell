package com.dragon.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 */
@Data
@Entity
public class OrderDetail {

    @Id
   private String detailId;

   /** 订单id. */
   private String orderId;

   /** 商品id. */
   private String productId;

   /** 商品名称. */
   private String productName;

   /** 商品单价. */
   private BigDecimal productPrice;

   /** 商品小图. */
   private Integer productQuantity;
}
