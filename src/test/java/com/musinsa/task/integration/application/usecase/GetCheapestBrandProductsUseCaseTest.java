package com.musinsa.task.integration.application.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.application.usecase.GetCheapestBrandProductsUseCase;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.musinsa.task.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.infrastructure.persistence.ProductEntity;
import com.musinsa.task.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.infrastructure.repository.ProductJpaRepository;

@SpringBootTest
@Transactional
class GetCheapestBrandProductsUseCaseTest {
	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Autowired
	private BrandJpaRepository brandJpaRepository;

	@Autowired
	private GetCheapestBrandProductsUseCase getCheapestBrandProductsUseCase;

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
		productJpaRepository.save(ProductEntity.create(brandA, Category.액세서리, new PriceEmbeddable(2300)));

		productJpaRepository.save(ProductEntity.create(brandB, Category.상의, new PriceEmbeddable(10500)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.아우터, new PriceEmbeddable(5900)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.바지, new PriceEmbeddable(3800)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.스니커즈, new PriceEmbeddable(9100)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.가방, new PriceEmbeddable(2100)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.모자, new PriceEmbeddable(2000)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.양말, new PriceEmbeddable(2000)));
		productJpaRepository.save(ProductEntity.create(brandB, Category.액세서리, new PriceEmbeddable(2200)));
	}

	@Test
	@DisplayName("단일 브랜드로 모든 카테고리 상품 최저가 조회 테스트")
	void testFindCheapestBrand() {
		// When
		Brand cheapestBrand = getCheapestBrandProductsUseCase.execute();

		// Then
		assertThat(cheapestBrand).isNotNull();
		assertThat(cheapestBrand.name()).isEqualTo("B");
		assertThat(cheapestBrand.getTotalPrice()).isEqualTo(37600);
	}
}
