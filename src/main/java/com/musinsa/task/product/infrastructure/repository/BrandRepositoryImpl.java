package com.musinsa.task.product.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.repository.BrandRepository;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {
	private final BrandJpaRepository brandJpaRepository;

	@Override
	public void save(Brand brand) {
		brandJpaRepository.save(BrandEntity.toEntity(brand));
	}
}
