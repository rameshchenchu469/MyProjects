package com.nt.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ECommerce_Product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;
	
	private String productTitle;
	
	private String color;
	
	private String description;
	
	private String brand;
	
	private double price;
	
	private Double discountPrice;
	
	private Double discountPercent;
	
	private int numRatings;
	
	private String imageUrl;
	
	@Embedded
	@ElementCollection
	private Set<Size> sizes = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	private LocalDateTime createdAt;
	
	private int quantity;
	

	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Rating> Ratings = new ArrayList<>();
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Review> reviews = new ArrayList<>();

	
}
