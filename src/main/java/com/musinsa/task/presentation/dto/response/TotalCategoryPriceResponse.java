package com.musinsa.task.presentation.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리별 총 가격 정보를 포함하는 응답")
public record TotalCategoryPriceResponse(
	@Schema(description = "카테고리별 가격 목록") List<CategoryPrice> categoryPrices,
	@Schema(description = "총 가격") int total) {

	@Schema(description = "카테고리별 가격 상세 정보")
	public record CategoryPrice(
		@Schema(description = "카테고리 이름") String category,
		@Schema(description = "브랜드 이름") String brand,
		@Schema(description = "가격") int price) {
	}
}
