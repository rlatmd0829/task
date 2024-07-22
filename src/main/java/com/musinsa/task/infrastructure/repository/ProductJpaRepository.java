package com.musinsa.task.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findAllByCategoryIn(List<Category> categories);
	List<ProductEntity> findAllByCategory(Category category);
}
