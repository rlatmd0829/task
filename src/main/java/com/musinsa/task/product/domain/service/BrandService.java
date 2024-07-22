package com.musinsa.task.product.domain.service;

import org.springframework.stereotype.Service;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
	private final BrandRepository brandRepository;

	public void addBrand(Brand brand) {
		brandRepository.save(brand);
	}

	public void updateBrand(Brand brand) {
		brandRepository.update(brand);
	}

	public void deleteBrand(Long id) {
		brandRepository.delete(id);
	}
}
