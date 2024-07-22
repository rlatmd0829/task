package com.musinsa.task.presentation.dto.request;

import java.util.List;

public record BrandCreateRequest(String name, List<ProductCreateRequest> products) {
}
