package com.nt.authorService;

import java.util.List;
import java.util.Optional;

import com.nt.entity.Author;

public interface AuthorManagementService  {
	
	public String registerAuthor(Author author);
	public List<Author> getAuthors();
	public Author getAuthorById (int aid);

}
