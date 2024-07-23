package com.musinsa.task.presentation.dto.request;

import org.hibernate.validator.constraints.Range;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "상품 요청 정보를 포함하는 레코드")
public record ProductRequest(
	@Schema(description = "카테고리 이름", example = "상의")
	@NotBlank(message = "카테고리 이름은 필수입니다.")
	String categoryName,
	@Schema(description = "가격", example = "10000")
	@Range(min = 0, max = 9999999, message = "가격은 0 이상 9999999 이하의 값이어야 합니다.")
	@Digits(integer = 7, fraction = 0, message = "가격은 소수점 없이 7자리 이하의 정수여야 합니다.")
	int price
) {
}
