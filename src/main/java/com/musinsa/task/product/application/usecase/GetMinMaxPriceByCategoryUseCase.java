package com.musinsa.task.product.application.usecase;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.product.application.dto.PriceInfo;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.domain.model.ProductCollection;
import com.musinsa.task.product.domain.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMinMaxPriceByCategoryUseCase {
	private final ProductService productService;

	public PriceInfo execute(String categoryName) {
		Category category = Category.fromString(categoryName);

		ProductCollection productCollection = productService.getProductsByCategory(category);

		Map.Entry<Product, Product> minMaxPriceProducts = productCollection.findMinMaxPriceProductsByCategory(category);

		return new PriceInfo(
			categoryName,
			minMaxPriceProducts.getKey().brand().name(),
			minMaxPriceProducts.getKey().price().value(),
			minMaxPriceProducts.getValue().brand().name(),
			minMaxPriceProducts.getValue().price().value()
		);
	}
}
