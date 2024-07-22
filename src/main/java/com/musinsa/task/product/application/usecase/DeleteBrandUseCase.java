package com.musinsa.task.product.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.product.domain.service.BrandService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteBrandUseCase {
	private final BrandService brandService;

	public void execute(Long id) {
		brandService.deleteBrand(id);
	}
}
