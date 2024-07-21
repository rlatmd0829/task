package com.musinsa.task.product.infrastructure.persistence;

import com.musinsa.task.product.domain.model.Price;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PriceEmbeddable {

	@Column(name = "price")
	private int value;

	public static PriceEmbeddable create(int value) {
		return PriceEmbeddable.builder()
			.value(value)
			.build();
	}

	public Price toDomain() {
		return new Price(value);
	}

	public static PriceEmbeddable toEmbeddable(Price price) {
		return PriceEmbeddable.builder()
			.value(price.value())
			.build();
	}
}
