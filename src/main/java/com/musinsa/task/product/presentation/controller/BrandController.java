package com.musinsa.task.product.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.common.exception.ApiResponse;
import com.musinsa.task.product.application.usecase.AddBrandUseCase;
import com.musinsa.task.product.application.usecase.UpdateBrandUseCase;
import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.presentation.dto.request.BrandCreateRequest;
import com.musinsa.task.product.presentation.mapper.BrandMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
	private final AddBrandUseCase addBrandUseCase;
	private final UpdateBrandUseCase updateBrandUseCase;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> addBrand(@RequestBody BrandCreateRequest brandCreateRequest) {
		addBrandUseCase.execute(BrandMapper.toDomain(brandCreateRequest));
		return ResponseEntity.ok(new ApiResponse<>());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> updateBrand(@PathVariable Long id, @RequestBody BrandCreateRequest brandCreateRequest) {
		Brand brand = BrandMapper.toDomain(id, brandCreateRequest);
		updateBrandUseCase.execute(brand);
		return ResponseEntity.ok(new ApiResponse<>());
	}
}
