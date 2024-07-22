package com.musinsa.task.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.musinsa.task.domain.repository.ProductRepository;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.infrastructure.persistence.ProductEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
	private final ProductJpaRepository productJpaRepository;

	@Override
	public List<Product> findAllByCategoryIn(List<Category> categories) {
		List<ProductEntity> productEntities = productJpaRepository.findAllByCategoryIn(categories);
		return productEntities.stream()
			.map(ProductEntity::toDomain)
			.collect(Collectors.toList());
	}

	@Override
	public List<Product> findAll() {
		List<ProductEntity> productEntities = productJpaRepository.findAll();
		return productEntities.stream()
			.map(ProductEntity::toDomain)
			.collect(Collectors.toList());
	}

	@Override
	public List<Product> findAllByCategory(Category category) {
		List<ProductEntity> productEntities = productJpaRepository.findAllByCategory(category);
		return productEntities.stream()
			.map(ProductEntity::toDomain)
			.collect(Collectors.toList());
	}
}
