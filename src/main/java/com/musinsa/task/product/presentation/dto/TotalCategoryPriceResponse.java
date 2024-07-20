package com.musinsa.task.product.presentation.dto;

import java.util.List;

public record TotalCategoryPriceResponse(List<CategoryPriceResponse> categoryPrices, int total) {
}
