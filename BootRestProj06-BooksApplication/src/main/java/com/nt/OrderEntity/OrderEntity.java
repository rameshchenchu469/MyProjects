package com.nt.OrderEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.nt.entity.BookEntity;
import com.nt.userEntity.UserEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="books_orders_table")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	private LocalDate bookingDate;
	private LocalTime bookingTime;
	private Double orderAmount;
	private String deliveryAddress;
	private String deliveryLocation;
	private String paymentType;
	private Long contactNo;
	private String razorpayOrderId;
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	   @ManyToMany
	    @JoinTable(
	        name = "order_books",
	        joinColumns = @JoinColumn(name = "order_id"),
	        inverseJoinColumns = @JoinColumn(name = "book_id")
	    )
	    private List<BookEntity> books;
	   
	   
}
