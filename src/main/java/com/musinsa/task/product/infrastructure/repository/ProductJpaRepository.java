package com.musinsa.task.product.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findByCategory(Category category);
	List<ProductEntity> findAllByCategoryIn(List<Category> categories);
}
