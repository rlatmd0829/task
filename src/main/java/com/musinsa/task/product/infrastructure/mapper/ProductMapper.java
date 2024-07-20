package com.musinsa.task.product.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

@Component
public class ProductMapper {

	// public Product toDomain(ProductEntity productEntity) {
	// 	return Product.create(
	// 		productEntity.getId(),
	// 		Brand.create(productEntity.getBrand().getId(), productEntity.getBrand().getName()),
	// 		productEntity.getCategory(),
	// 		productEntity.getPrice().toDomain()
	// 	);
	// }
	//
	// public ProductEntity toEntity(Product product) {
	// 	return ProductEntity.create(
	// 		BrandEntity.create(product.getBrand().getName()),
	// 		product.getCategory(),
	// 		PriceEmbeddable.create(product.getPrice().getValue())
	// 	);
	// }
}
