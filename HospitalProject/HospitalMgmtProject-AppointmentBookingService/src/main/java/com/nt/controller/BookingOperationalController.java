package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.BookingDTO;
import com.nt.entity.Booking;
import com.nt.service.BookingMgmtService;

@RestController
@RequestMapping("/appointment")
public class BookingOperationalController {
	
	@Autowired
	private BookingMgmtService bookingService;

	@PostMapping("/save-booking")
	public BookingDTO saveBooking(@RequestBody BookingDTO bookingDTO) {
		BookingDTO booking = bookingService.createBooking(bookingDTO);
		return booking;
	}
	
	@GetMapping("/getAll-bookings")
	public List<Booking> getAllAppointments(){
		List<Booking> bookingsList = bookingService.getBookings();
		return bookingsList;
	}
	
	public String deleteBookingById(@PathVariable Integer bookingId) {
		
		String response = bookingService.removeBookingById(bookingId);
		return response;
		
	}
}
