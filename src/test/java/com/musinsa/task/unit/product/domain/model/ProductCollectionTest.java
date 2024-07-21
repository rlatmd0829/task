package com.musinsa.task.unit.product.domain.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Price;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.domain.model.ProductCollection;

class ProductCollectionTest {

	@Test
	@DisplayName("카테고리별 최저가 상품 찾기 테스트")
	void testFindCheapestProductsByCategory() {
		// Given
		Product product1 = new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100));
		Product product2 = new Product(2L, new Brand(2L, "B"), Category.가방, new Price(200));
		Product product3 = new Product(3L, new Brand(1L, "A"), Category.가방, new Price(50));
		List<Product> productList = List.of(product1, product2, product3);

		ProductCollection productCollection = new ProductCollection(productList);

		// When
		Map<Category, Product> cheapestProducts = productCollection.findCheapestProductsByCategory();

		// Then
		assertEquals(2, cheapestProducts.size());
		assertEquals(product1, cheapestProducts.get(Category.상의));
		assertEquals(product3, cheapestProducts.get(Category.가방));
	}

	@Test
	@DisplayName("카테고리별 상품 그룹화 테스트")
	void testGroupByCategory() {
		// Given
		Product product1 = new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100));
		Product product2 = new Product(2L, new Brand(2L, "B"), Category.가방, new Price(200));
		Product product3 = new Product(3L, new Brand(1L, "A"), Category.가방, new Price(50));
		List<Product> productList = List.of(product1, product2, product3);

		ProductCollection productCollection = new ProductCollection(productList);

		// When
		Map<Category, List<Product>> groupedProducts = productCollection.groupByCategory();

		// Then
		assertEquals(2, groupedProducts.size());
		assertEquals(List.of(product1), groupedProducts.get(Category.상의));
		assertEquals(List.of(product2, product3), groupedProducts.get(Category.가방));
	}

	@Test
	@DisplayName("최저가 상품 찾기 테스트")
	void testFindCheapestProduct() {
		// Given
		Product product1 = new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100));
		Product product2 = new Product(2L, new Brand(1L, "B"), Category.상의, new Price(50));
		List<Product> productList = List.of(product1, product2);

		ProductCollection productCollection = new ProductCollection(productList);

		// When
		Product cheapestProduct = productCollection.findCheapestProduct(productList);

		// Then
		assertEquals(product2, cheapestProduct);
	}

	@Test
	@DisplayName("빈 리스트에서 최저가 상품 찾기 예외 테스트")
	void testFindCheapestProduct_EmptyList() {
		// Given
		List<Product> productList = List.of();

		ProductCollection productCollection = new ProductCollection(productList);

		// When & Then
		assertThatThrownBy(() -> productCollection.findCheapestProduct(productList))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.PRODUCT_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("최저가 브랜드 찾기 테스트")
	void testFindCheapestBrand() {
		// Given
		Product product1 = new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100));
		Product product2 = new Product(2L, new Brand(1L, "A"), Category.가방, new Price(50));
		Product product3 = new Product(3L, new Brand(2L, "B"), Category.가방, new Price(200));
		List<Product> productList = List.of(product1, product2, product3);

		ProductCollection productCollection = new ProductCollection(productList);

		// When
		Brand cheapestBrand = productCollection.findCheapestBrand();

		// Then
		assertEquals("A", cheapestBrand.name());
		assertEquals(150, cheapestBrand.getTotalPrice());
	}
}
