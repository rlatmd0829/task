package com.musinsa.task.integration.product.application.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.product.application.dto.PriceInfo;
import com.musinsa.task.product.application.usecase.GetMinMaxPriceByCategoryUseCase;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;
import com.musinsa.task.product.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.product.infrastructure.repository.ProductJpaRepository;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GetMinMaxPriceByCategoryUseCaseTest {

	@Autowired
	private GetMinMaxPriceByCategoryUseCase getMinMaxPriceByCategoryUseCase;

	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Autowired
	private BrandJpaRepository brandJpaRepository;

	@BeforeEach
	void setUp() {
		productJpaRepository.deleteAll();
		brandJpaRepository.deleteAll();

		BrandEntity brandA = brandJpaRepository.save(BrandEntity.create("A"));
		BrandEntity brandB = brandJpaRepository.save(BrandEntity.create("B"));

		productJpaRepository.save(ProductEntity.create(brandA, Category.상의, new PriceEmbeddable(11200)));
		productJpaRepository.save(ProductEntity.create(brandA, Category.아우터, new PriceEmbeddable(5500)));
		productJpaRepository.save(ProductEntity.create(brandA, Category.바지, new PriceEmbeddable(4200)));
		productJpaRepository.save(ProductEntity.create(brandA, Category.스니커즈, new PriceEmbeddable(9000)));
		productJpaRepository.save(ProductEntity.create(brandA, Category.가방, new PriceEmbeddable(2000)));
		productJpaRepository.save(ProductEntity.create(brandA, Category.모자, new PriceEmbeddable(1700)));
		productJpaRepository.save(ProductEntity.create(brandA, Category.양말, new PriceEmbeddable(1800)));

		productJpaRepository.save(ProductEntity.create(brandB, Category.상의, new PriceEmbeddable(10500)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.아우터, new PriceEmbeddable(5900)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.바지, new PriceEmbeddable(3800)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.스니커즈, new PriceEmbeddable(9100)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.가방, new PriceEmbeddable(2100)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.모자, new PriceEmbeddable(2000)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.양말, new PriceEmbeddable(2000)));
	}

	@Test
	@DisplayName("카테고리 별 최저 및 최고 가격 조회 테스트")
	void testGetMinMaxPriceByCategory() {
		// Given
		String categoryName = "상의";

		// When
		PriceInfo priceInfo = getMinMaxPriceByCategoryUseCase.execute(categoryName);

		// Then
		assertEquals("상의", priceInfo.category());
		assertEquals("B", priceInfo.minPriceBrand());
		assertEquals(10500, priceInfo.minPrice());
		assertEquals("A", priceInfo.maxPriceBrand());
		assertEquals(11200, priceInfo.maxPrice());
	}

	@Test
	@DisplayName("카테고리가 없는 경우 예외 발생 테스트")
	void testCategoryNotFound() {
		// Given
		String categoryName = "장난감";

		// When & Then
		assertThatThrownBy(() -> getMinMaxPriceByCategoryUseCase.execute(categoryName))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_TYPE_VALUE.getMessage());
	}

	@Test
	@DisplayName("카테고리에 해당하는 제품이 없는 경우 예외 발생 테스트")
	void testNoProductsInCategory() {
		// Given
		String categoryName = "액세서리";

		// When & Then
		assertThatThrownBy(() -> getMinMaxPriceByCategoryUseCase.execute(categoryName))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.PRODUCT_NOT_FOUND.getMessage());
	}
}
