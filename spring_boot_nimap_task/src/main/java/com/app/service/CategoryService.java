package com.app.service;

import java.util.List;

import com.app.dto.CategoryRequestDTO;
import com.app.dto.CategoryResponseDTO;
import com.app.entity.Category;

public interface CategoryService {
	
	 public List<CategoryResponseDTO> getAllCategories();
	
	 public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequest);
	
	public CategoryResponseDTO getCategoryById(Long id);
	 
	public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequest) ;
	 
	public void deleteCategory(Long id);

}
