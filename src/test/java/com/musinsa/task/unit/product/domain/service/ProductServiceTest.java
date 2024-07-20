package com.musinsa.task.unit.product.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Price;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.domain.model.ProductCollection;
import com.musinsa.task.product.domain.repository.ProductRepository;
import com.musinsa.task.product.domain.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	@DisplayName("카테고리별 제품 가져오기 테스트")
	void testGetProductsByCategories() {
		// Given
		Category[] categories = {Category.상의, Category.가방};
		Product product1 = new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100));
		Product product2 = new Product(2L, new Brand(2L, "B"), Category.가방, new Price(200));
		List<Product> mockProducts = List.of(product1, product2);

		when(productRepository.findAllByCategoryIn(List.of(categories))).thenReturn(mockProducts);

		// When
		ProductCollection productCollection = productService.getProductsByCategories(categories);

		// Then
		assertEquals(2, productCollection.products().size());
		assertEquals(mockProducts, productCollection.products());
	}
}
