package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.entity.Category;
import com.app.entity.Product;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productRepo.findAll();
        List<ProductResponseDTO> responseList = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDTO responseDTO = modelMapper.map(product, ProductResponseDTO.class);
            responseDTO.setCategoryName(product.getCategory().getName());
            responseList.add(responseDTO);
        }
        return responseList;
	}

	@Override
	public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
		 Category category = categoryRepo.findById(productRequest.getCategoryId())
		            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequest.getCategoryId()));

		    Product product = new Product();
		    product.setProductName(productRequest.getName());
		    product.setPrice(productRequest.getPrice());
		    product.setCategory(category); // Set the category object
		    
		    Product savedProduct = productRepo.save(product);
		    // Map to DTO and set category name
		    ProductResponseDTO responseDTO = modelMapper.map(savedProduct, ProductResponseDTO.class);
		    responseDTO.setCategoryName(category.getName());
		    return responseDTO;
	}

	@Override
	public ProductResponseDTO getProductById(Long id) {
		Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        ProductResponseDTO responseDTO = modelMapper.map(product, ProductResponseDTO.class);
        responseDTO.setCategoryName(product.getCategory().getName());
        return responseDTO;
	}
	
	@Override
	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequest) {
		 Product product = productRepo.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

		    product.setProductName(productRequest.getName());
		    product.setPrice(productRequest.getPrice());

		    if (productRequest.getCategoryId() != null) {
		        Category category = categoryRepo.findById(productRequest.getCategoryId())
		                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequest.getCategoryId()));
		        product.setCategory(category);
		    }
		    Product updatedProduct = productRepo.save(product);

		    ProductResponseDTO responseDTO = modelMapper.map(updatedProduct, ProductResponseDTO.class);
		    responseDTO.setCategoryName(product.getCategory().getName());
		    return responseDTO;
	}

	@Override
	public void deleteProduct(Long id) {
		if (!productRepo.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepo.deleteById(id);
		
	}

}
