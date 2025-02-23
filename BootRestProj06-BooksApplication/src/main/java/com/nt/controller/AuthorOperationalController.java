package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.authorService.AuthorManagementService;
import com.nt.entity.Author;
import com.nt.entity.BookEntity;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/authors")
public class AuthorOperationalController{
	@Autowired
	private AuthorManagementService authorService;
	
	
	@GetMapping("/welcome")
	public String welcomeUser() {
		return "welcome to the book Application";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> saveBook(@RequestBody Author author) {
		
		try {
		String resultMsg = authorService.registerAuthor(author);
		return new ResponseEntity<String>(resultMsg.toString(),HttpStatus.CREATED);
	}
		catch(Exception e) {
			return new ResponseEntity<String>("problem in Author registartion",HttpStatus.INTERNAL_SERVER_ERROR);
		}
     }
	
	
	@GetMapping("/getAuthors")
	public ResponseEntity<?> FetchAllAuthors(){
		try {
			List<Author> authorsList = authorService.getAuthors();
			return new ResponseEntity<List<Author>>(authorsList,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("problem in fetching authors list",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAuthorById/{authorId}")
	public ResponseEntity<?> fetchAuthorById(@PathVariable int authorId){
		try {
			
			Author author = authorService.getAuthorById(authorId);
			return new ResponseEntity<Author>(author,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String> ("The other Id not found for fetching the details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
