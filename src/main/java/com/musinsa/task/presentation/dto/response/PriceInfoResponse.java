package com.musinsa.task.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리별 최저 및 최고 가격 정보를 포함하는 응답")
public record PriceInfoResponse(
	@Schema(description = "카테고리 이름") String category,
	@Schema(description = "최저 가격 정보") PriceDetail minPrice,
	@Schema(description = "최고 가격 정보") PriceDetail maxPrice) {

	@Schema(description = "가격 상세 정보")
	public record PriceDetail(
		@Schema(description = "브랜드 이름") String brand,
		@Schema(description = "가격") int price) {
	}
}
