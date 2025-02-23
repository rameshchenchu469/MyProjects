package com.nt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Books_Table")
@Entity
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String bookName;
	private String bookImageUrl;
	private String publishedYear;
	private Float price;
	@ManyToOne(targetEntity=Author.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="bookIdId" ,referencedColumnName="authorId")
	private Author author;

}
