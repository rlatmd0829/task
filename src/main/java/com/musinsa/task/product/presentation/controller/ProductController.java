package com.musinsa.task.product.presentation.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.common.exception.ApiResponse;
import com.musinsa.task.product.application.dto.PriceInfo;
import com.musinsa.task.product.application.usecase.GetCheapestBrandProductsUseCase;
import com.musinsa.task.product.application.usecase.GetCheapestProductsByCategoryUseCase;
import com.musinsa.task.product.application.usecase.GetMinMaxPriceByCategoryUseCase;
import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.presentation.dto.response.CheapestBrandResponse;
import com.musinsa.task.product.presentation.dto.response.PriceInfoResponse;
import com.musinsa.task.product.presentation.dto.response.TotalCategoryPriceResponse;
import com.musinsa.task.product.presentation.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	private final GetCheapestProductsByCategoryUseCase getCheapestProductsByCategoryUseCase;
	private final GetCheapestBrandProductsUseCase getCheapestBrandProductsUseCase;
	private final GetMinMaxPriceByCategoryUseCase getMinMaxPriceByCategoryUseCase;

	@GetMapping("/cheapest")
	public ResponseEntity<ApiResponse<TotalCategoryPriceResponse>> getCheapestProduct() {
		Map<Category, Product> categoryProductMap = getCheapestProductsByCategoryUseCase.execute();
		TotalCategoryPriceResponse response = ProductMapper.toTotalPriceResponse(categoryProductMap);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	@GetMapping("/cheapest-brand")
	public ResponseEntity<ApiResponse<CheapestBrandResponse>> getCheapestBrand() {
		Brand cheapestBrand = getCheapestBrandProductsUseCase.execute();
		CheapestBrandResponse response = ProductMapper.toCheapestBrandResponse(cheapestBrand);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	@GetMapping("/price-range")
	public ResponseEntity<ApiResponse<PriceInfoResponse>> getPriceRangeByCategory(@RequestParam String category) {
		PriceInfo priceInfo = getMinMaxPriceByCategoryUseCase.execute(category);
		PriceInfoResponse response = ProductMapper.toPriceInfoResponse(priceInfo);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}
}
