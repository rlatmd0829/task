package com.musinsa.task.product.domain.model;

import java.util.ArrayList;
import java.util.List;

public record Brand(Long id, String name, List<Product> products) {
	public Brand(Long id, String name) {
		this(id, name, new ArrayList<>());
	}

	public void setProducts(List<Product> products) {
		this.products.addAll(products);
	}

	public int getTotalPrice() {
		return products.stream().mapToInt(product -> product.price().value()).sum();
	}
}
