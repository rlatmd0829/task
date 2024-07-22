package com.musinsa.task.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.components.BrandStore;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateBrandUseCase {
	private final BrandStore brandStore;

	public void execute(Brand brand) {
		brandStore.updateBrand(brand);
	}
}
