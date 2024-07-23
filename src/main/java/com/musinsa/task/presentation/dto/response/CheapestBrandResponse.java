package com.musinsa.task.presentation.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "가장 저렴한 브랜드 정보를 포함하는 응답")
public record CheapestBrandResponse(
	@Schema(description = "가장 저렴한 브랜드의 상세 정보") CheapestBrandDetail cheapest) {

	@Schema(description = "카테고리별 가격 정보를 포함한 브랜드의 상세 정보")
	public record CheapestBrandDetail(
		@Schema(description = "브랜드 이름") String brand,
		@Schema(description = "각 카테고리와 해당 가격 목록") List<CategoryPriceDetail> categories,
		@Schema(description = "모든 카테고리의 총 가격") int total) {
	}

	@Schema(description = "특정 카테고리에 대한 가격 정보")
	public record CategoryPriceDetail(
		@Schema(description = "카테고리 이름") String category,
		@Schema(description = "카테고리별 가격") int price) {
	}
}
