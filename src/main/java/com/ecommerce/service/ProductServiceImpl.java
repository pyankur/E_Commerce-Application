package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.request.createProductRequest;

@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Product createProduct(createProductRequest req) {
		Category topLevel=categoryRepo.findByName(req.getTopLevelCateogory());
		if(topLevel==null) {
			Category topLevelCategory=new Category();
			topLevelCategory.setName(req.getTopLevelCateogory());
			topLevelCategory.setLevel(1);
			categoryRepo.save(topLevelCategory);
		}
		Category secondLevel=categoryRepo.findByNameAndParent(req.getSecondLevelCateogory(),topLevel.getName());
		if(secondLevel==null) {
			Category secondLevelCategory=new Category();
			secondLevelCategory.setName(req.getSecondLevelCateogory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);
			categoryRepo.save(secondLevelCategory);
			
		}
		Category thirdLevel=categoryRepo.findByNameAndParent(req.getThirdLevelCateogory(),secondLevel.getName());
		if(thirdLevel==null) {
			Category thirdLevelCategory=new Category();
			thirdLevelCategory.setName(req.getThirdLevelCateogory());
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevelCategory.setLevel(3);
			categoryRepo.save(thirdLevelCategory);
		}
		Product product =new Product();
		product.setTitle(req.getTitle());
		product.setBrand(req.getBrand());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPresent(req.getDiscountPresent());
		product.setImageUrl(req.getImageUrl());
		product.setPrice(req.getPrice());
		product.setQuantity(req.getQuantity());
		product.setSizes(req.getSizes());
		product.setCategory(thirdLevel);
		Product savedProduct=productRepo.save(product);
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product=findProductById(productId);
		product.getSizes().clear();
		productRepo.delete(product);
		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product=findProductById(productId);
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		return productRepo.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> option=productRepo.findById(id);
		if(option.isPresent()) {
			return option.get();
		}
		throw new ProductException("Product not found with id--"+id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		List<Product> products=productRepo.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		if(!colors.isEmpty()) {
			products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}
		if(stock!=null) {
			if(stock.equals("in_stock")) {
				products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
			}else if(stock.equals("out_of_stock")) {
				products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
			}
		}
		int startIndex=(int)pageable.getOffset();
		int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());
		List<Product> pageContent=products.subList(startIndex, endIndex);
		Page<Product> filteredProducts=new PageImpl<>(pageContent,pageable,products.size());
		return filteredProducts;
	}

}
