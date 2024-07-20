package com.musinsa.task.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Brand {
	private Long id;
	private String name;

	public static Brand create(Long id, String name) {
		return Brand.builder()
			.id(id)
			.name(name)
			.build();
	}
}
