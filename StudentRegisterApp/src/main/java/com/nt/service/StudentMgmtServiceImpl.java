package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.StudentDTO;
import com.nt.entity.Student;
import com.nt.repository.StudentRepository;

@Service
public class StudentMgmtServiceImpl implements IStudentMgmtService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public String registerStudent(StudentDTO studentDTO) {
		Student student = new Student();
		
		student.setAge(studentDTO.getAge());
		student.setContact(studentDTO.getContact());
		student.setDob(studentDTO.getDob());
		student.setEmail(studentDTO.getEmail());
		student.setGender(studentDTO.getGender());
		student.setName(studentDTO.getName());
		student.setQualification(studentDTO.getQualification());
		
		Student stu = studentRepo.save(student);
		
		return "student registered successfully with the id value:"+stu.getId();
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

}
