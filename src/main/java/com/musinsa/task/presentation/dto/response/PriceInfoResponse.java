package com.musinsa.task.presentation.dto.response;

public record PriceInfoResponse(String category, PriceDetail minPrice, PriceDetail maxPrice) {
	public record PriceDetail(String brand, int price) {
	}
}
