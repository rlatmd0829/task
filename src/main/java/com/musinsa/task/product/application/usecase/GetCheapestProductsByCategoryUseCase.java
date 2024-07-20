package com.musinsa.task.product.application.usecase;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.domain.model.ProductCollection;
import com.musinsa.task.product.domain.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCheapestProductsByCategoryUseCase {
	private final ProductService productService;

	public Map<Category, Product> execute() {
		Category[] categories = Category.values();
		ProductCollection productCollection = productService.getProductsByCategories(categories);
		return productCollection.findCheapestProductsByCategory();
	}
}
