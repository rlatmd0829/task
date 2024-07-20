package com.musinsa.task.product.presentation.dto;

import java.util.List;

public record TotalPriceResponse(List<CategoryPriceResponse> categoryPrices, int total) {
}
