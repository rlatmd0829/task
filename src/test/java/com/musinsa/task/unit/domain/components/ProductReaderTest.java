package com.musinsa.task.unit.domain.components;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Price;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.domain.model.ProductCollection;
import com.musinsa.task.domain.repository.ProductRepository;
import com.musinsa.task.domain.components.ProductReader;

@ExtendWith(MockitoExtension.class)
class ProductReaderTest {
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductReader productReader;

	@Test
	@DisplayName("카테고리별 제품 가져오기 테스트")
	void testGetProductsByCategories() {
		// Given
		Category[] categories = {Category.상의, Category.가방};
		List<Product> mockProducts = List.of(
			new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100)),
			new Product(2L, new Brand(2L, "B"), Category.가방, new Price(200))
		);
		when(productRepository.findAllByCategoryIn(List.of(categories))).thenReturn(mockProducts);

		// When
		ProductCollection productCollection = productReader.getProductsByCategories(categories);

		// Then
		assertThat(productCollection).isNotNull();
		assertThat(productCollection.products()).hasSize(2);
		assertThat(productCollection.products()).containsAll(mockProducts);
	}

	@Test
	@DisplayName("전체 상품 조회 테스트")
	void testGetAllProducts() {
		// Given
		List<Product> mockProducts = List.of(
			new Product(1L, new Brand(1L, "A"), Category.상의, new Price(100)),
			new Product(2L, new Brand(2L, "B"), Category.가방, new Price(200))
		);
		when(productRepository.findAll()).thenReturn(mockProducts);

		// When
		ProductCollection productCollection = productReader.getAllProducts();

		// Then
		assertThat(productCollection).isNotNull();
		assertThat(productCollection.products()).hasSize(2);
		assertThat(productCollection.products()).containsAll(mockProducts);
	}
}
