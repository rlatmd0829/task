package com.musinsa.task.product.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.ProductCollection;
import com.musinsa.task.product.domain.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCheapestBrandProductsUseCase {
	private final ProductService productService;

	public Brand execute() {
		ProductCollection productCollection = productService.getAllProducts();
		return productCollection.findCheapestBrand();
	}
}
