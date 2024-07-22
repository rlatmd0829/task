package com.musinsa.task.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.domain.components.ProductReader;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.ProductCollection;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCheapestBrandProductsUseCase {
	private final ProductReader productReader;

	public Brand execute() {
		ProductCollection productCollection = productReader.getAllProducts();
		return productCollection.findCheapestBrand();
	}
}
