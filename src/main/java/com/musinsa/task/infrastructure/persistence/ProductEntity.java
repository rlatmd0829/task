package com.musinsa.task.infrastructure.persistence;

import java.util.ArrayList;

import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.domain.model.Category;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;

	@Enumerated(EnumType.STRING)
	private Category category;

	@Embedded
	private PriceEmbeddable price;

	public static ProductEntity create(BrandEntity brand, Category category, PriceEmbeddable price) {
		return ProductEntity.builder()
			.brand(brand)
			.category(category)
			.price(price)
			.build();
	}

	public static Product toDomain(ProductEntity productEntity) {
		return new Product(
			productEntity.getId(),
			new Brand(productEntity.getBrand().getId(), productEntity.getBrand().getName(), new ArrayList<>()),
			productEntity.getCategory(),
			productEntity.getPrice().toDomain()
		);
	}

	public static ProductEntity toEntity(Product product) {
		return ProductEntity.builder()
			.category(product.category())
			.price(PriceEmbeddable.toEmbeddable(product.price()))
			.build();
	}

	public void updateBrand(BrandEntity brand) {
		this.brand = brand;
	}
}
