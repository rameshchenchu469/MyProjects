package com.nt.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
	
	private Long id;
    private LocalDateTime appointmentDate;
    private Long doctorId; // reference to Doctor ID
}
