package com.musinsa.task.product.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.repository.BrandRepository;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {
	private final BrandJpaRepository brandJpaRepository;

	@Override
	public void save(Brand brand) {
		brandJpaRepository.save(BrandEntity.toEntity(brand));
	}

	@Override
	public void update(Brand brand) {
		BrandEntity existingBrandEntity = brandJpaRepository.findById(brand.id())
			.orElseThrow(() -> new CustomException(ErrorCode.BRAND_NOT_FOUND));

		existingBrandEntity.updateName(brand.name());

		List<ProductEntity> productEntities = brand.products().stream()
			.map(ProductEntity::toEntity)
			.collect(Collectors.toList());

		existingBrandEntity.updateProducts(productEntities);

		brandJpaRepository.save(existingBrandEntity);
	}

	@Override
	public void delete(Long id) {
		BrandEntity existingBrandEntity = brandJpaRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.BRAND_NOT_FOUND));

		brandJpaRepository.delete(existingBrandEntity);
	}
}
