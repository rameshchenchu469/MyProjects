package com.nt.emailController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.emailService.EmailServie;
import com.nt.orderDTO.DeliveryLocation;
import com.nt.orderDTO.OrderDTO;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {
	
	@Autowired
	private EmailServie emailService;

	@PostMapping("/sendData")
	public ResponseEntity<String> sendOrderDataToEmail(@RequestBody OrderDTO orderDTO,@RequestParam String toEmail){

		try {
			String orderData = generateEmailContent(orderDTO);
		 emailService.sendData(toEmail,"OrderData",orderData);
		return new ResponseEntity<String>("order data send to customer email successfully",HttpStatus.OK);
	}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem while sending order data to email",HttpStatus.INTERNAL_SERVER_ERROR);
		}
}

	private String generateEmailContent(OrderDTO orderDTO) {
		
//		DeliveryLocation location = new DeliveryLocation();
//		location.setName(orderDTO.getDeliveryLocation().getName());
		
//		return "<h1>Order Deatils<h1>:"
//				+ "<p>OrderId:"+orderDTO.getOrderId()+"</p>"
//				+"<p>orderBooks:"+orderDTO.getBooks()+"</p>"
//				+"<p>contactNo:"+orderDTO.getContactNo()+"</p>"
//				+"<p>orderAmount:"+orderDTO.getOrderAmount()+"</p>"
//				+"<p>razorpayOrderId:"+orderDTO.getRazorpayOrderId()+"</p>"
//				+"<p>delivery Address:"+orderDTO.getDeliveryAdderss()+"</p>"
////				+"<p>deliveryLocation:"+location+"</p>"
//				+"<p>UserDetails:"+orderDTO.getUser()+"</p>"
//				+"<p>Thanks for youe shopping</p>";
		
		return "<h1>Order Deatils<h1>:"
		+ "OrderId:"+orderDTO.getOrderId()
		+"orderBooks:"+orderDTO.getBooks()
		+"orderDate:"+orderDTO.getBookingDate()
		+"orderTime:"+orderDTO.getBookingTime()
		+"contactNo:"+orderDTO.getContactNo()
		+"orderAmount:"+orderDTO.getOrderAmount()
		+"razorpayOrderId:"+orderDTO.getRazorpayOrderId()
		+"delivery Address:"+orderDTO.getDeliveryAdderss()
//		+"<p>deliveryLocation:"+location+"</p>"
		+"<p>UserDetails:"+orderDTO.getUser()+"</p>"
		+"<p>Thanks for your shopping</p>";
	}
}
