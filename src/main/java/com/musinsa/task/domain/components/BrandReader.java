package com.musinsa.task.domain.components;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandReader {
	private final BrandRepository brandRepository;

	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}
}
