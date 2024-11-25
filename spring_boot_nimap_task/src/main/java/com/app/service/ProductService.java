package com.app.service;

import java.util.List;

import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.entity.Product;

public interface ProductService {
	public List<ProductResponseDTO> getAllProducts();
	
	 public ProductResponseDTO createProduct(ProductRequestDTO productRequest);
	
	public ProductResponseDTO getProductById(Long id);
	
	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequest) ;
 
	public void deleteProduct(Long id);

}
