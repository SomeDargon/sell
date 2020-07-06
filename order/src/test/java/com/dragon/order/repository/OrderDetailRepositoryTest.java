package com.dragon.order.repository;

import com.dragon.order.OrderApplicationTests;
import com.dragon.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

   @Autowired
   private OrderDetailRepository orderDetailRepository;

   @Test
   public void testSave() {
      OrderDetail orderDetail = new OrderDetail();
      orderDetail.setDetailId("123456");
      orderDetail.setOrderId("123456");
      orderDetail.setProductId("http://www");
      orderDetail.setProductId("2312312312313");
      orderDetail.setProductName("皮蛋粥");
      orderDetail.setProductPrice(new BigDecimal(0.1));
      orderDetail.setProductQuantity(12);
      OrderDetail result = orderDetailRepository.save(orderDetail);
      Assert.assertTrue(orderDetail != null);
   }

}
