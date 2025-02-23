package com.nt.OrderService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nt.OrderEntity.OrderEntity;
import com.nt.OrderRepo.OrderRepository;
import com.nt.entity.BookEntity;
import com.nt.orderDTO.DeliveryLocation;
import com.nt.orderDTO.OrderDTO;
import com.nt.repo.BookRepository;
import com.nt.userEntity.UserEntity;
import com.nt.userRepo.IUserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;
	
	@Value("${razorpay.key.secret}")
	private String razorPaySecret;
	

	@Override
	public OrderDTO bookOrder(OrderDTO orderDTO) throws RazorpayException {
		
		JSONObject json = new JSONObject();
		
		json.put("amount", orderDTO.getOrderAmount()*100);
		json.put("currency", "INR");
		json.put("receipt", "ram@gmail.com");
		RazorpayClient client = new RazorpayClient(razorPayKey,razorPaySecret);
		Order razorPayOrder = client.orders.create(json);
		
		
		
		OrderEntity entity = new OrderEntity();
		int userId = orderDTO.getUserId();
		List<Integer> bookIds = orderDTO.getBookId();
		
		Optional<UserEntity> optUser = userRepo.findById(userId);
		
		if(!optUser.isPresent()) {
			throw new RuntimeException("user id not found");
		}
		UserEntity user = optUser.get();
		
		List<BookEntity> bookList = bookRepo.findAllById(bookIds);
		if(bookList.size() != orderDTO.getBookId().size()) {
			throw new RuntimeException ("one or more not found");
		}
		 
		
		entity.setBookingDate(orderDTO.getBookingDate());
		entity.setBookingTime(orderDTO.getBookingTime());
		entity.setBooks(bookList);
		entity.setDeliveryAddress(orderDTO.getDeliveryAdderss());
		entity.setDeliveryLocation(orderDTO.getDeliveryLocation().getName());
		entity.setOrderAmount(orderDTO.getOrderAmount());
		entity.setPaymentType(orderDTO.getPaymentType());
		entity.setContactNo(orderDTO.getContactNo());
		entity.setUser(user);
		entity.setRazorpayOrderId(razorPayOrder.get("id"));
		entity.setOrderStatus(razorPayOrder.get("status"));
		
		orderRepo.save(entity);
		
		return entityToDTO(entity);
	}
	
	public OrderDTO entityToDTO(OrderEntity entity) {
		
		DeliveryLocation location = new DeliveryLocation();
		location.setName(entity.getDeliveryLocation());
		
		OrderDTO dto = new OrderDTO();
		dto.setBookingDate(entity.getBookingDate());
		dto.setBookingTime(entity.getBookingTime());
		dto.setDeliveryAdderss(entity.getDeliveryAddress());
		dto.setDeliveryLocation(location);
		dto.setOrderAmount(entity.getOrderAmount());
		dto.setOrderId(entity.getOrderId());
		dto.setPaymentType(entity.getPaymentType());
		dto.setUserId(entity.getUser().getId());
		dto.setUser(entity.getUser());
	    dto.setBooks(entity.getBooks());
	    dto.setContactNo(entity.getContactNo());
	    dto.setRazorpayOrderId(entity.getRazorpayOrderId());
	    dto.setOrderStatus(entity.getOrderStatus());
		return dto;
	}

	@Override
	public List<OrderEntity> getOrdersByUserId(int userId) {
		
		List<OrderEntity> orders =orderRepo.findOrdersByUserId(userId);
		
		return orders;
		
	}

	@Override
	public OrderEntity getOrdersByOrderId(int orderId) {
		
		Optional<OrderEntity> opt = orderRepo.findByOrderId(orderId);
		
		if(opt.isPresent()) {
			 return  opt.get();
		}
		throw new RuntimeException("order id not found in the database for fetching the orders");
	}
	
public OrderEntity updateOrderStatus(String razorpayOrderId) {
		
		OrderEntity order = orderRepo.findByRazorpayOrderId(razorpayOrderId);
		
		order.setOrderStatus("PAYMENT_COMPLETED");
		
		OrderEntity updateOrder = orderRepo.save(order);

		return updateOrder;
	}
	
	
}
