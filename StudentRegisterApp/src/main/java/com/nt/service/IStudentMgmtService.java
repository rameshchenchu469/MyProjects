package com.nt.service;

import java.util.List;

import com.nt.dto.StudentDTO;
import com.nt.entity.Student;

public interface IStudentMgmtService {

	public String registerStudent(StudentDTO studentDTO);
	
	public List<Student> getAllStudents();
}
