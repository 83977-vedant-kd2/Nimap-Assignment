package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryRequestDTO;
import com.app.dto.CategoryResponseDTO;
import com.app.entity.Category;
import com.app.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	 	@GetMapping
	 	public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
	        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
	 	}

	 	@PostMapping
	    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequest) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
	        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequest) {
	        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, categoryRequest));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");
	    }
}
