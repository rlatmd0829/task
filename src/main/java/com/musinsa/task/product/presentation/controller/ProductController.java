package com.musinsa.task.product.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.product.application.usecase.ProductUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductUsecase productUsecase;

	@GetMapping("/cheapest")
	public void getCheapestProduct() {
		productUsecase.getCheapestProduct();
	}
}
