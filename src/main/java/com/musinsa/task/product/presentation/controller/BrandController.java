package com.musinsa.task.product.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.product.application.usecase.AddBrandUseCase;
import com.musinsa.task.product.presentation.dto.request.BrandCreateRequest;
import com.musinsa.task.product.presentation.mapper.BrandMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
	private final AddBrandUseCase addBrandUseCase;

	@PostMapping("/add")
	public ResponseEntity<Void> addBrand(@RequestBody BrandCreateRequest brandCreateRequest) {
		addBrandUseCase.execute(BrandMapper.toDomain(brandCreateRequest));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
