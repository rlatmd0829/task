package com.musinsa.task.product.presentation.dto;

import java.util.List;

public record BrandResponse(String brand, List<BrandCategoryResponse> categories, int total) {}
