package com.musinsa.task.domain.repository;

import java.util.List;

import com.musinsa.task.domain.model.Brand;

public interface BrandRepository {
	void save(Brand brand);

	void update(Brand brand);

	void delete(Long id);

	List<Brand> findAll();
}
