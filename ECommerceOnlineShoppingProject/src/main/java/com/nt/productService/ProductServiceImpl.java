package com.nt.productService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.UserEntity.Category;
import com.nt.UserEntity.Product;
import com.nt.categoryRepository.CategoryRepository;
import com.nt.dto.ProductDTO;
import com.nt.exception.ProductException;
import com.nt.productRepositoty.ProductRepository;
import com.nt.userService.UserService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private UserService userService;

	@Override
	public Product createProduct(ProductDTO productDTO) {
		Category topLevel = categoryRepo.findByName(productDTO.getTopLevelCategory());
		if(topLevel == null) {
			Category top = new Category();
			top.setName(productDTO.getTopLevelCategory());
			top.setLevel(1);
			topLevel = categoryRepo.save(top);
		}
		
		Category secondLevel = categoryRepo.findByNameAndParent(productDTO.getSecondLevelCategory(),topLevel.getName());
		if(secondLevel == null) {
			Category second = new Category();
			second.setName(productDTO.getSecondLevelCategory());
			second.setParentCategory(topLevel);
			second.setLevel(2);
			
			secondLevel = categoryRepo.save(second);
		}
		
		Category thirdLevel = categoryRepo.findByNameAndParent(productDTO.getThirdLevelCategory(),secondLevel.getName());
		if(thirdLevel == null) {
			Category third = new Category();
			third.setName(productDTO.getThirdLevelCategory());
			third.setParentCategory(secondLevel);
			third.setLevel(3);
			
			thirdLevel = categoryRepo.save(third);
		}
		
		Product product = new Product();
		product.setProductTitle(productDTO.getTitle());
		product.setBrand(productDTO.getBrand());
		product.setColor(productDTO.getColor());
		product.setDescription(productDTO.getDescription());
		product.setDiscountPercent(productDTO.getDiscountPercent());
		product.setCreatedAt(LocalDateTime.now());
		product.setDiscountPrice(productDTO.getDiscountPrice());
		product.setImageUrl(productDTO.getImageUrl());
		product.setQuantity(productDTO.getQuantity());
		product.setPrice(productDTO.getPrice());
		product.setSizes(productDTO.getSize());
		product.setCategory(thirdLevel);
		
		Product saveProduct = productRepo.save(product);
		
		return saveProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepo.delete(product);
		
		return "Product deleted successfully";
	}

	

	@Override
	public Product updateProduct(Long productId, Product product) throws ProductException {
		Product prod = findProductById(productId);
		
		if(product.getQuantity()!= 0) {
			prod.setQuantity(product.getQuantity());
		}
		
		return productRepo.save(prod);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		
		Optional<Product> opt = productRepo.findById(productId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
			throw new ProductException("Product Not Fount with Id"+productId);
	}
	
	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		
		Pageable page = PageRequest.of(pageNumber,pageSize);
		
		List<Product> products = productRepo.filterProduct(category, minPrice, maxPrice, minDiscount, sort);
		
		if(!colors.isEmpty()) {
			products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
						.collect(Collectors.toList());
		}
		
		if(stock != null) {
			if(stock.equals("in_Stock")){
				
				products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
			}
			else if(stock.equals("Out Of Stock")) {
				products = products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
			}
		}
		
		int startIndex = (int) page.getOffset();
		int endIndex = Math.min(startIndex+page.getPageSize(),products.size());
		
		
		List<Product> pageContent = products.subList(endIndex, endIndex);
		
		Page<Product> filterProductsPage = new PageImpl<>(pageContent,page,products.size());
		return null;
	}

	

}
