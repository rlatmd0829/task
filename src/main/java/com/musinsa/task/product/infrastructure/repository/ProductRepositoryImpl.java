package com.musinsa.task.product.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.musinsa.task.product.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
	private final ProductJpaRepository productJpaRepository;


}
