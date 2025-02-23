package com.nt.OrderService;

import java.util.List;
import java.util.Map;

import com.nt.OrderEntity.OrderEntity;
import com.nt.orderDTO.OrderDTO;
import com.razorpay.RazorpayException;

public interface IOrderService {
	
	public OrderDTO bookOrder (OrderDTO orderDTO) throws RazorpayException;
	
	public List<OrderEntity> getOrdersByUserId(int UserId);
	
	public OrderEntity getOrdersByOrderId(int orderId);
	
	public OrderEntity updateOrderStatus(String razorpayOrderId);

}
