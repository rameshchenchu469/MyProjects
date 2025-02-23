package com.nt.orderDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.nt.entity.BookEntity;
import com.nt.userEntity.UserEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
	
	private Integer orderId;
	private Long contactNo;
	private LocalDate bookingDate;
	private LocalTime bookingTime;
	private Double orderAmount;
	private String deliveryAdderss;
	private DeliveryLocation deliveryLocation;
	private String paymentType;
	private List<Integer> bookId;
	private Integer userId;
	private UserEntity user;
	private List<BookEntity> books;
	private String razorpayOrderId;
	private String orderStatus;

}
