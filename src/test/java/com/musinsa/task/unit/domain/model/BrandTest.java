package com.musinsa.task.unit.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Price;
import com.musinsa.task.domain.model.Product;

class BrandTest {

	@Test
	void testSetProducts() {
		// Given
		Brand brand = new Brand(1L, "TestBrand");
		List<Product> newProducts = List.of(
			new Product(1L, brand, Category.상의, new Price(100)),
			new Product(2L, brand, Category.바지, new Price(200))
		);

		// When
		brand.setProducts(newProducts);

		// Then
		assertEquals(2, brand.products().size());
		assertEquals(newProducts, brand.products());
	}

	@Test
	void testGetTotalPrice() {
		// Given
		Brand brand = new Brand(1L, "TestBrand");
		List<Product> products = List.of(
			new Product(1L, brand, Category.상의, new Price(100)),
			new Product(2L, brand, Category.바지, new Price(200))
		);
		brand.setProducts(products);

		// When
		int totalPrice = brand.getTotalPrice();

		// Then
		assertEquals(300, totalPrice);
	}
}
