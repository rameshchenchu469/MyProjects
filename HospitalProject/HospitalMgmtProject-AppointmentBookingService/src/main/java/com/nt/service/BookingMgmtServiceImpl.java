package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.BookingDTO;
import com.nt.entity.Booking;
import com.nt.repository.BookingRepository;

@Service
public class BookingMgmtServiceImpl implements BookingMgmtService {
	
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public BookingDTO createBooking(BookingDTO bookingDTO) {
		
		Booking booking = new Booking();
		booking.setBookingDate(bookingDTO.getBookingDate());
		booking.setDepartment(bookingDTO.getDepartment());
		booking.setPatientId(bookingDTO.getPatientId());
		booking.setHospitalId(bookingDTO.getHospitalId());
		booking.setDoctorId(bookingDTO.getDoctorId());
		
		Booking saveBooking = bookingRepo.save(booking);
		
		BookingDTO dto = convertEntityToDTO(saveBooking);
		
		return dto;
	}

	private BookingDTO convertEntityToDTO(Booking booking) {
		
		BookingDTO dto = new BookingDTO();
		dto.setBookingDate(booking.getBookingDate());
		dto.setBookingId(booking.getBookingId());
		dto.setDepartment(booking.getDepartment());
		dto.setDoctorId(booking.getDoctorId());
		dto.setPatientId(booking.getPatientId());
		dto.setHospitalId(booking.getHospitalId());
		
		return dto;
	}

	@Override
	public List<Booking> getBookings() {	
		return bookingRepo.findAll();
	}

	@Override
	public String removeBookingById(Integer bookingId) {
		@SuppressWarnings("deprecation")
		Long id = bookingRepo.getById(bookingId).getBookingId();
		
		if(id != null) {
			 bookingRepo.deleteById(bookingId);
			 return "booking deleted succesfully!";
		}
		
		return "booking id not found for deletion";
	}

}
