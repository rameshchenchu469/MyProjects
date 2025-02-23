package com.nt.dto;

import java.time.LocalDateTime;

import com.nt.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

	private Long bookingId;
	private LocalDateTime bookingDate;
//	private Status status;
	private Long patientId;
	private Long doctorId;
	private Integer hospitalId;
	private String department;
}
