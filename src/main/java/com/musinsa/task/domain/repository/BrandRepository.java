package com.musinsa.task.domain.repository;

import com.musinsa.task.domain.model.Brand;

public interface BrandRepository {
	void save(Brand brand);

	void update(Brand brand);

	void delete(Long id);
}
