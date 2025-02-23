package com.hunger.saviour.portal.service;

import com.hunger.saviour.portal.dto.OrderDTO;

public interface PaymentService {

   public  void processPayment(OrderDTO orderDTO);
}
