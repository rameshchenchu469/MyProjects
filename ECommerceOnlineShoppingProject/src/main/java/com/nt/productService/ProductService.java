package com.nt.productService;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nt.UserEntity.Product;
import com.nt.dto.ProductDTO;
import com.nt.exception.ProductException;

public interface ProductService {
	
	public Product createProduct(ProductDTO productDTO);
	
	public String deleteProduct(Long ProductId)throws ProductException;
	
	public Product updateProduct(Long productId,Product product)throws ProductException;
	
	public Product findProductById(Long ProductId)throws ProductException;
	
	public Page<Product> getAllProducts(String category,List<String> colors,List<String> sizes,Integer minPrice,
			Integer maxPrice,Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pafeSized);
	
	public List<Product> findProductByCategory(String category);
	
}
