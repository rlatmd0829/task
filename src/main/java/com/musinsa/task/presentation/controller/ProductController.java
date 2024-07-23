package com.musinsa.task.presentation.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.application.usecase.GetCheapestBrandProductsUseCase;
import com.musinsa.task.common.exception.ApiResponse;
import com.musinsa.task.presentation.dto.response.TotalCategoryPriceResponse;
import com.musinsa.task.presentation.mapper.ProductMapper;
import com.musinsa.task.application.dto.PriceInfo;
import com.musinsa.task.application.usecase.GetCheapestProductsByCategoryUseCase;
import com.musinsa.task.application.usecase.GetMinMaxPriceByCategoryUseCase;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.presentation.dto.response.CheapestBrandResponse;
import com.musinsa.task.presentation.dto.response.PriceInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "상품 API")
public class ProductController {
	private final GetCheapestProductsByCategoryUseCase getCheapestProductsByCategoryUseCase;
	private final GetCheapestBrandProductsUseCase getCheapestBrandProductsUseCase;
	private final GetMinMaxPriceByCategoryUseCase getMinMaxPriceByCategoryUseCase;

	@GetMapping("/cheapest-by-category")
	@Operation(summary = "카테고리별 가장 싼 상품 조회", description = "카테고리별로 가장 싼 상품을 가져옵니다.", tags = {"Product API"})
	public ResponseEntity<ApiResponse<TotalCategoryPriceResponse>> getCheapestProduct() {
		Map<Category, Product> categoryProductMap = getCheapestProductsByCategoryUseCase.execute();
		TotalCategoryPriceResponse response = ProductMapper.toTotalPriceResponse(categoryProductMap);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	@GetMapping("/cheapest-brand")
	@Operation(summary = "가장 싼 브랜드 조회", description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드 조회", tags = {"Product API"})
	public ResponseEntity<ApiResponse<CheapestBrandResponse>> getCheapestBrand() {
		Brand cheapestBrand = getCheapestBrandProductsUseCase.execute();
		CheapestBrandResponse response = ProductMapper.toCheapestBrandResponse(cheapestBrand);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	@GetMapping("/price-range")
	@Operation(summary = "카테고리 이름으로 최저, 최고 가격 상품 조회", description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회", tags = {"Product API"})
	public ResponseEntity<ApiResponse<PriceInfoResponse>> getPriceRangeByCategory(@RequestParam String category) {
		PriceInfo priceInfo = getMinMaxPriceByCategoryUseCase.execute(category);
		PriceInfoResponse response = ProductMapper.toPriceInfoResponse(priceInfo);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}
}
