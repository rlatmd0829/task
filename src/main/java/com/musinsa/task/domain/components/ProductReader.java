package com.musinsa.task.domain.components;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.domain.model.ProductCollection;
import com.musinsa.task.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReader {
	private final ProductRepository productRepository;

	public ProductCollection getProductsByCategories(Category[] categories) {
		List<Product> products = productRepository.findAllByCategoryIn(List.of(categories));
		return new ProductCollection(products);
	}

	public ProductCollection getAllProducts() {
		List<Product> products = productRepository.findAll();
		return new ProductCollection(products);
	}

	public ProductCollection getProductsByCategory(Category category) {
		List<Product> products = productRepository.findAllByCategory(category);
		return new ProductCollection(products);
	}
}
