package com.musinsa.task.product.presentation.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.product.application.usecase.GetCheapestProductsByCategoryUseCase;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.presentation.dto.TotalPriceResponse;
import com.musinsa.task.product.presentation.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	private final GetCheapestProductsByCategoryUseCase getCheapestProductsByCategoryUseCase;

	@GetMapping("/cheapest")
	public ResponseEntity<TotalPriceResponse> getCheapestProduct() {
		Map<Category, Product> categoryProductMap = getCheapestProductsByCategoryUseCase.execute();
		TotalPriceResponse response = ProductMapper.toTotalPriceResponse(categoryProductMap);
		return ResponseEntity.ok(response);
	}
}
