package com.musinsa.task.presentation.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.musinsa.task.presentation.dto.request.BrandCreateRequest;
import com.musinsa.task.presentation.dto.request.ProductCreateRequest;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Price;
import com.musinsa.task.domain.model.Product;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandMapper {
	public Brand toDomain(BrandCreateRequest brandCreateRequest) {
		List<Product> products = brandCreateRequest.products().stream()
			.map(BrandMapper::toDomain)
			.collect(Collectors.toList());
		return new Brand(null, brandCreateRequest.name(), products);
	}

	public Brand toDomain(Long id, BrandCreateRequest brandCreateRequest) {
		List<Product> products = brandCreateRequest.products().stream()
			.map(BrandMapper::toDomain)
			.collect(Collectors.toList());
		return new Brand(id, brandCreateRequest.name(), products);
	}

	private Product toDomain(ProductCreateRequest productCreateRequest) {
		Category category = Category.fromString(productCreateRequest.categoryName());
		Price price = new Price(productCreateRequest.price());
		return new Product(null, null, category, price); // Brand는 나중에 설정됨
	}
}
