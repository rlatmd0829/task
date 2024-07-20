package com.musinsa.task.product.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.domain.repository.ProductRepository;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

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
}
