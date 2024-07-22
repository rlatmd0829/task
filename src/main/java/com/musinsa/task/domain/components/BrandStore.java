package com.musinsa.task.domain.components;

import org.springframework.stereotype.Service;

import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandStore {
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
