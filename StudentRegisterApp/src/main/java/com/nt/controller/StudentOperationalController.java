package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.StudentDTO;
import com.nt.entity.Student;
import com.nt.service.IStudentMgmtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/student")
@CrossOrigin(origins="http://localhost:3000")
public class StudentOperationalController {

	@Autowired
	private IStudentMgmtService studentService;
	
	@PostMapping("/register")
	public ResponseEntity<String> enrollStudent(@RequestBody StudentDTO studentDTO) {
		
		log.info("Entered student controller register api ");
		
		try {
			
			String response = studentService.registerStudent(studentDTO);
			return new ResponseEntity<String>(response,HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Getting error while register student",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> fetchAllStudents(){
		try {
			List<Student> studentList = studentService.getAllStudents();
			return new ResponseEntity<List<Student>>(studentList,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("students List fetching failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
