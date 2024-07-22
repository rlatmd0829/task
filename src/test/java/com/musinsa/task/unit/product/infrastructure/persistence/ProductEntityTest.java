package com.musinsa.task.unit.product.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Price;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

class ProductEntityTest {

	private BrandEntity brandEntity;
	private ProductEntity productEntity;

	@BeforeEach
	void setUp() {
		brandEntity = BrandEntity.create("Test Brand");
		productEntity = ProductEntity.create(brandEntity, Category.상의, new PriceEmbeddable(1000));
	}

	@Test
	void testCreateProductEntity() {
		ProductEntity product = ProductEntity.create(brandEntity, Category.바지, new PriceEmbeddable(2000));

		assertThat(product.getBrand()).isEqualTo(brandEntity);
		assertThat(product.getCategory()).isEqualTo(Category.바지);
		assertThat(product.getPrice().getValue()).isEqualTo(2000);
	}

	@Test
	void testToDomain() {
		Product product = ProductEntity.toDomain(productEntity);

		assertThat(product.id()).isEqualTo(productEntity.getId());
		assertThat(product.category()).isEqualTo(productEntity.getCategory());
		assertThat(product.price().value()).isEqualTo(productEntity.getPrice().getValue());
		assertThat(product.brand().name()).isEqualTo(productEntity.getBrand().getName());
	}

	@Test
	void testToEntity() {
		Product product = new Product(null, new Brand(brandEntity.getId(), brandEntity.getName(), new ArrayList<>()), Category.스니커즈, new Price(3000));
		ProductEntity entity = ProductEntity.toEntity(product);

		assertThat(entity.getCategory()).isEqualTo(Category.스니커즈);
		assertThat(entity.getPrice().getValue()).isEqualTo(3000);
	}

	@Test
	void testUpdateBrand() {
		BrandEntity newBrand = BrandEntity.create("New Brand");

		productEntity.updateBrand(newBrand);

		assertThat(productEntity.getBrand()).isEqualTo(newBrand);
	}
}
