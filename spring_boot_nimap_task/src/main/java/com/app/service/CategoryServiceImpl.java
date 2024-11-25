package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.CategoryRequestDTO;
import com.app.dto.CategoryResponseDTO;
import com.app.entity.Category;
import com.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CategoryResponseDTO> getAllCategories() {
		List<Category> categories = categoryRepo.findAll();
        List<CategoryResponseDTO> responseList = new ArrayList<>();
        for (Category category : categories) {
            responseList.add(modelMapper.map(category, CategoryResponseDTO.class));
        }
        return responseList;
		
	}

	@Override
	public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequest) {
		Category category = modelMapper.map(categoryRequest, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDTO.class);
	}

	@Override
	public CategoryResponseDTO getCategoryById(Long id) {
		Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return modelMapper.map(category, CategoryResponseDTO.class);
	}
	
	@Override
	public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequest) {
		Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setName(categoryRequest.getName());
        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
	}

	@Override
	public void deleteCategory(Long id) {
		 if (!categoryRepo.existsById(id)) {
	            throw new ResourceNotFoundException("Category not found with id: " + id);
	        }
	        categoryRepo.deleteById(id);
		
	}
	
	

}
