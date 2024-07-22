package com.musinsa.task.application.usecase;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.domain.model.ProductCollection;
import com.musinsa.task.domain.components.ProductReader;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCheapestProductsByCategoryUseCase {
	private final ProductReader productReader;

	public Map<Category, Product> execute() {
		Category[] categories = Category.values();
		ProductCollection productCollection = productReader.getProductsByCategories(categories);
		return productCollection.findCheapestProductsByCategory();
	}
}
