package com.nt.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Author_Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorId;
	private String AuthorName;
	private Integer age;
	private String dob;
	private String authorImage;
	private String gender;
	@OneToMany(targetEntity=BookEntity.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="authorId",referencedColumnName="authorId")
	private List<BookEntity> books;

}
