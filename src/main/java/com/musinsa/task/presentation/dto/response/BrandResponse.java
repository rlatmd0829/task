package com.musinsa.task.presentation.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "브랜드 응답 정보를 포함하는 레코드")
public record BrandResponse(
	@Schema(description = "브랜드 ID", example = "1") Long id,
	@Schema(description = "브랜드 이름", example = "Nike") String name,
	@Schema(description = "상품 목록") List<ProductResponse> products) {

	@Schema(description = "상품 응답 정보를 포함하는 레코드")
	public record ProductResponse(
		@Schema(description = "상품 ID", example = "1") Long id,
		@Schema(description = "카테고리 이름", example = "상의") String categoryName,
		@Schema(description = "가격", example = "10000") int price) {
	}
}
