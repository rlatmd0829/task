package com.musinsa.task.infrastructure.repository;

import java.util.List;

import com.musinsa.task.infrastructure.persistence.BrandEntity;

public interface BrandJpaRepositoryCustom {
	List<BrandEntity> getAllBrands();
}
