package com.nt.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

	private Integer id;
	private String name;
	private Integer age;
	private String email;
	private String gender;
	private Long contact;
	private String Qualification;
	private LocalDate dob;
}
