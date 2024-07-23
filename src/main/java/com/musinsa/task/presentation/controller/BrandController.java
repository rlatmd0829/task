package com.musinsa.task.presentation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.task.application.usecase.GetAllBrandsUseCase;
import com.musinsa.task.presentation.dto.response.ApiResponse;
import com.musinsa.task.application.usecase.AddBrandUseCase;
import com.musinsa.task.application.usecase.DeleteBrandUseCase;
import com.musinsa.task.application.usecase.UpdateBrandUseCase;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.presentation.dto.request.BrandRequest;
import com.musinsa.task.presentation.dto.response.BrandResponse;
import com.musinsa.task.presentation.utils.BrandMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "Brand API", description = "브랜드 API")
public class BrandController {
	private final AddBrandUseCase addBrandUseCase;
	private final UpdateBrandUseCase updateBrandUseCase;
	private final DeleteBrandUseCase deleteBrandUseCase;
	private final GetAllBrandsUseCase getAllBrandsUseCase;

	@PostMapping
	@Operation(summary = "새로운 브랜드 및 상품 추가", description = "새로운 브랜드 및 상품을 추가합니다.", tags = {"Brand API"})
	public ResponseEntity<ApiResponse<Void>> addBrand(@Valid @RequestBody BrandRequest brandRequest) {
		addBrandUseCase.execute(BrandMapper.toDomain(brandRequest));
		return ResponseEntity.ok(new ApiResponse<>());
	}

	@PutMapping("/{id}")
	@Operation(summary = "기존 브랜드 및 상품 업데이트", description = "기존 브랜드 및 상품을 업데이트합니다.", tags = {"Brand API"})
	public ResponseEntity<ApiResponse<Void>> updateBrand(@PathVariable Long id, @RequestBody BrandRequest brandRequest) {
		Brand brand = BrandMapper.toDomain(id, brandRequest);
		updateBrandUseCase.execute(brand);
		return ResponseEntity.ok(new ApiResponse<>());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "브랜드 삭제", description = "브랜드를 삭제합니다.", tags = {"Brand API"})
	public ResponseEntity<ApiResponse<Void>> deleteBrand(@PathVariable Long id) {
		deleteBrandUseCase.execute(id);
		return ResponseEntity.ok(new ApiResponse<>());
	}

	@GetMapping
	@Operation(summary = "브랜드 전체 조회", description = "모든 브랜드를 조회합니다.", tags = {"Brand API"})
	public ResponseEntity<ApiResponse<List<BrandResponse>>> getAllBrands() {
		List<Brand> brands = getAllBrandsUseCase.execute();
		List<BrandResponse> response = brands.stream()
			.map(BrandMapper::toResponse)
			.collect(Collectors.toList());
		return ResponseEntity.ok(new ApiResponse<>(response));
	}
}
