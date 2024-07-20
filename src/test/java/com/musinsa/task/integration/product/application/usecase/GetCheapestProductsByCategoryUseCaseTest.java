package com.musinsa.task.integration.product.application.usecase;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.product.application.usecase.GetCheapestProductsByCategoryUseCase;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;
import com.musinsa.task.product.infrastructure.repository.ProductJpaRepository;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GetCheapestProductsByCategoryUseCaseTest {

	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Autowired
	private GetCheapestProductsByCategoryUseCase getCheapestProductsByCategoryUseCase;

	@BeforeEach
	void setUp() {
		productJpaRepository.deleteAll();

		productJpaRepository.save(new ProductEntity(1L, new BrandEntity(1L, "A"), Category.상의, new PriceEmbeddable(100)));
		productJpaRepository.save(new ProductEntity(2L, new BrandEntity(2L, "B"), Category.가방, new PriceEmbeddable(200)));
		productJpaRepository.save(new ProductEntity(3L, new BrandEntity(1L, "A"), Category.상의, new PriceEmbeddable(50)));
		productJpaRepository.save(new ProductEntity(4L, new BrandEntity(3L, "C"), Category.가방, new PriceEmbeddable(150)));
	}

	@Test
	@DisplayName("카테고리별 최저가 상품 찾기 통합 테스트")
	void testFindCheapestProductsByCategory() {
		// When
		Map<Category, Product> cheapestProducts = getCheapestProductsByCategoryUseCase.execute();

		// Then
		assertThat(cheapestProducts).hasSize(2);
		assertThat(cheapestProducts.get(Category.상의).price().value()).isEqualTo(50);
		assertThat(cheapestProducts.get(Category.가방).price().value()).isEqualTo(150);
	}
}
