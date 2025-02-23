package com.nt.OrderRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.OrderEntity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	
	public List<OrderEntity> findOrdersByUserId(Integer userId);
	
	public Optional<OrderEntity> findByOrderId(Integer orderId);

	public OrderEntity findByRazorpayOrderId(String razorPayOrderId);

}
