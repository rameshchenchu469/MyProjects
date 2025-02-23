package com.nt.service;

import java.util.List;


import com.nt.dto.BookingDTO;
import com.nt.entity.Booking;

public interface BookingMgmtService {
	
	public BookingDTO createBooking(BookingDTO bookingDTO);

	public List<Booking> getBookings();

	public String removeBookingById(Integer bookingId);

}
