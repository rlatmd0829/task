package com.musinsa.task.infrastructure.mapper;

import org.springframework.stereotype.Component;

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
