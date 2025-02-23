package com.nt.orderController;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.OrderEntity.OrderEntity;
import com.nt.OrderService.IOrderService;
import com.nt.orderDTO.OrderDTO;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderOperationalController {

	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) throws RazorpayException {
		OrderDTO dto = orderService.bookOrder(orderDTO);
//		System.out.println(dto);t
		try {
		return new ResponseEntity<OrderDTO> (dto,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Order creation failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getOrdersByUser/{userId}")
//	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> fetchAllOrdersByUser(@PathVariable int userId){
		
		List<OrderEntity> orders=orderService.getOrdersByUserId(userId);
		
		try {
			return new ResponseEntity<List<OrderEntity>>(orders, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem while fetching orders",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getOrdersByOrderId/{orderId}")
	public ResponseEntity<?> fetchOrdersById(@PathVariable int orderId){
		
		OrderEntity ordersList = orderService.getOrdersByOrderId(orderId);
		try {
		return new ResponseEntity<OrderEntity> (ordersList,HttpStatus.OK);
		} catch(Exception e) {
			 e.printStackTrace();
			 return new ResponseEntity<String>("getting problem while fetching order details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/update-order-status")
	public String handlePaymentCallback(@RequestBody Map<String,String> respPayload) {
		System.out.println("response:"+respPayload);
		String razorpayOrderId = respPayload.get("orderId");
		orderService.updateOrderStatus(razorpayOrderId);
		return "order placed successfully";
	}
	
}
