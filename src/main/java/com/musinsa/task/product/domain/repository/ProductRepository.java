package com.musinsa.task.product.domain.repository;

import java.util.List;

import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;

public interface ProductRepository {
	List<Product> findAllByCategoryIn(List<Category> categories);
	List<Product> findAll();
	List<Product> findAllByCategory(Category category);
}
