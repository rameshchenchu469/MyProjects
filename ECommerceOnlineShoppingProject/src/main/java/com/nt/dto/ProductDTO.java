package com.nt.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.nt.UserEntity.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private String title;
	
	private String description;
	
	private Integer price;
	
	private Double discountPrice;
	
	private Double discountPercent;
	
	private int quantity;
	
	private String color;
	
	private String brand;
	
	private Set<Size> size = new HashSet<>();
	
	private String imageUrl;
	
	private String topLevelCategory;
	
	private String secondLevelCategory;
	
	private String thirdLevelCategory;

	private LocalDateTime createdAt;
}
