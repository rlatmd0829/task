package com.musinsa.task.product.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.service.BrandService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class AddBrandUseCase {
	private final BrandService brandService;

	public void execute(Brand brand) {
		brandService.addBrand(brand);
	}
}
