package com.musinsa.task.unit.product.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Price;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

class BrandEntityTest {

	private BrandEntity brandEntity;
	private ProductEntity product1;
	private ProductEntity product2;

	@BeforeEach
	void setUp() {
		brandEntity = BrandEntity.create("Test Brand");

		product1 = ProductEntity.create(brandEntity, Category.상의, new PriceEmbeddable(1000));
		product2 = ProductEntity.create(brandEntity, Category.바지, new PriceEmbeddable(2000));

		brandEntity.addProduct(product1);
		brandEntity.addProduct(product2);
	}

	@Test
	void testCreateBrandEntity() {
		BrandEntity brand = BrandEntity.create("New Brand");

		assertThat(brand.getName()).isEqualTo("New Brand");
		assertThat(brand.getProducts()).isEmpty();
	}

	@Test
	void testToEntity() {
		Brand brand = new Brand(null, "Brand", List.of(new Product(null, null, Category.상의, new Price(1000)), new Product(null, null, Category.바지, new Price(2000))));

		BrandEntity entity = BrandEntity.toEntity(brand);

		assertThat(entity.getName()).isEqualTo("Brand");
		assertThat(entity.getProducts()).hasSize(2);
		assertThat(entity.getProducts().get(0).getCategory()).isEqualTo(Category.상의);
		assertThat(entity.getProducts().get(0).getPrice().getValue()).isEqualTo(1000);
		assertThat(entity.getProducts().get(1).getCategory()).isEqualTo(Category.바지);
		assertThat(entity.getProducts().get(1).getPrice().getValue()).isEqualTo(2000);
	}

	@Test
	void testToDomain() {
		Brand domain = BrandEntity.toDomain(brandEntity);

		assertThat(domain.name()).isEqualTo("Test Brand");
		assertThat(domain.products()).hasSize(2);
		assertThat(domain.products().get(0).category()).isEqualTo(Category.상의);
		assertThat(domain.products().get(0).price().value()).isEqualTo(1000);
		assertThat(domain.products().get(1).category()).isEqualTo(Category.바지);
		assertThat(domain.products().get(1).price().value()).isEqualTo(2000);
	}

	@Test
	void testUpdateName() {
		brandEntity.updateName("Updated Brand");

		assertThat(brandEntity.getName()).isEqualTo("Updated Brand");
	}

	@Test
	void testUpdateProducts() {
		ProductEntity product3 = ProductEntity.create(brandEntity, Category.스니커즈, new PriceEmbeddable(3000));

		List<ProductEntity> newProducts = new ArrayList<>();
		newProducts.add(product3);

		brandEntity.updateProducts(newProducts);

		assertThat(brandEntity.getProducts()).hasSize(1);
		assertThat(brandEntity.getProducts().get(0).getCategory()).isEqualTo(Category.스니커즈);
		assertThat(brandEntity.getProducts().get(0).getPrice().getValue()).isEqualTo(3000);
	}

	@Test
	void testAddProduct() {
		ProductEntity product3 = ProductEntity.create(brandEntity, Category.스니커즈, new PriceEmbeddable(3000));

		brandEntity.addProduct(product3);

		assertThat(brandEntity.getProducts()).hasSize(3);
		assertThat(brandEntity.getProducts().get(2).getCategory()).isEqualTo(Category.스니커즈);
		assertThat(brandEntity.getProducts().get(2).getPrice().getValue()).isEqualTo(3000);
	}
}
