package com.musinsa.task.application.usecase;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.application.dto.PriceInfo;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.domain.model.ProductCollection;
import com.musinsa.task.domain.components.ProductReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMinMaxPriceByCategoryUseCase {
	private final ProductReader productReader;

	public PriceInfo execute(String categoryName) {
		Category category = Category.fromString(categoryName);

		ProductCollection productCollection = productReader.getProductsByCategory(category);

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
