package com.musinsa.task.domain.repository;

import java.util.List;

import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;

public interface ProductRepository {
	List<Product> findAllByCategoryIn(List<Category> categories);
	List<Product> findAll();
	List<Product> findAllByCategory(Category category);
}
