package com.musinsa.task.infrastructure.repository;

import java.util.List;

import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepositoryCustom {
	List<ProductEntity> getAllProductsByCategoryIn(List<Category> categories);
	List<ProductEntity> getAllProducts();
	List<ProductEntity> getAllProductsByCategory(Category category);
}
