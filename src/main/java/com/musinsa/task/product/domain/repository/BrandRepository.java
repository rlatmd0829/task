package com.musinsa.task.product.domain.repository;

import java.util.Optional;

import com.musinsa.task.product.domain.model.Brand;

public interface BrandRepository {
	void save(Brand brand);
	void update(Brand brand);
}
