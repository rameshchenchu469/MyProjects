package com.nt.authorService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Author;
import com.nt.entity.BookEntity;
import com.nt.repo.AuthorRepository;
import com.nt.repo.BookRepository;

@Service
public class AuthorManagementServiceImpl implements AuthorManagementService {
	
	@Autowired
	private AuthorRepository  authRepo;

	@Override
	public String registerAuthor(Author author) {
		int authorId = authRepo.save(author).getAuthorId();
		return "author Registered successfully with id value"+authorId;
	}

	@Override
	public List<Author> getAuthors() {
		List<Author> authorsList = authRepo.findAll();
		return authorsList;
	}

	@Override
	public Author getAuthorById(int aid) {
		
		Optional<Author> optionalAuthor = authRepo.findById(aid);
		Author author = optionalAuthor.orElseThrow(()->new IllegalArgumentException("AuthoId not found for fetching the deatils"));
		return author;
		
	}

}
