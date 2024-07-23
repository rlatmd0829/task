package com.musinsa.task.presentation.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "브랜드 요청 정보를 포함하는 레코드")
public record BrandRequest(
	@Schema(description = "브랜드 이름", example = "Nike")
	@NotBlank(message = "브랜드 이름은 필수입니다.")
	String name,
	@Schema(description = "상품 목록")
	@Valid
	List<ProductRequest> products
) {}
