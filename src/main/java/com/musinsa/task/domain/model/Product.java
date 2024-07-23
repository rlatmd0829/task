package com.musinsa.task.domain.model;

public record Product(Long id, Brand brand, Category category, Price price) {

	@Override
	public String toString() {
		return "Product{" +
			"id=" + id +
			", category=" + category +
			", price=" + price +
			'}';
	}
}
