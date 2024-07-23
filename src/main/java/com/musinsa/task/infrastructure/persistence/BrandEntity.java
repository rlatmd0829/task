package com.musinsa.task.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import com.musinsa.task.domain.model.Brand;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands", indexes = {
	@Index(name = "idx_brand_name", columnList = "name")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductEntity> products = new ArrayList<>();

	public void addProduct(ProductEntity product) {
		products.add(product);
		product.updateBrand(this);
	}

	public static BrandEntity create(String name) {
		return BrandEntity.builder()
			.name(name)
			.build();
	}

	public static BrandEntity toEntity(Brand brand) {
		BrandEntity brandEntity = BrandEntity.builder()
			.name(brand.name())
			.build();
		brand.products().forEach(product -> brandEntity.addProduct(ProductEntity.toEntity(product)));
		return brandEntity;
	}

	public static Brand toDomain(BrandEntity brandEntity) {
		return new Brand(
			brandEntity.getId(),
			brandEntity.getName(),
			brandEntity.getProducts().stream()
				.map(ProductEntity::toDomain)
				.toList()
		);
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateProducts(List<ProductEntity> newProducts) {
		this.products.clear();
		newProducts.forEach(product -> {
			product.updateBrand(this);
			this.products.add(product);
		});
	}
}
