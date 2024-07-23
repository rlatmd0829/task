package com.musinsa.task.application.usecase;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.domain.components.BrandReader;
import com.musinsa.task.domain.model.Brand;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllBrandsUseCase {
	private final BrandReader brandReader;

	public List<Brand> execute() {
		return brandReader.getAllBrands();
	}
}
