package com.musinsa.task.integration.application.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.application.usecase.UpdateBrandUseCase;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.musinsa.task.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.infrastructure.persistence.ProductEntity;
import com.musinsa.task.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.infrastructure.repository.ProductJpaRepository;
import com.musinsa.task.presentation.dto.request.BrandCreateRequest;
import com.musinsa.task.presentation.dto.request.ProductCreateRequest;
import com.musinsa.task.presentation.mapper.BrandMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UpdateBrandUseCaseTest {

	@Autowired
	private BrandJpaRepository brandJpaRepository;

	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Autowired
	private UpdateBrandUseCase updateBrandUseCase;

	@BeforeEach
	void setUp() {
		productJpaRepository.deleteAll();
		brandJpaRepository.deleteAll();

		BrandEntity brandEntity = brandJpaRepository.save(BrandEntity.create("ExistingBrand"));
		productJpaRepository.save(ProductEntity.create(brandEntity, Category.상의, new PriceEmbeddable(1000)));
		productJpaRepository.save(ProductEntity.create(brandEntity, Category.바지, new PriceEmbeddable(2000)));
	}

	@Test
	@DisplayName("브랜드와 상품을 업데이트하는 통합 테스트")
	void testUpdateBrand() throws Exception {
		// Given
		Long existingBrandId = brandJpaRepository.findAll().get(0).getId();
		BrandCreateRequest brandCreateRequest = new BrandCreateRequest(
			"UpdatedBrand",
			List.of(
				new ProductCreateRequest("상의", 1500),
				new ProductCreateRequest("바지", 2500)
			)
		);

		Brand updatedBrand = BrandMapper.toDomain(existingBrandId, brandCreateRequest);

		// When
		updateBrandUseCase.execute(updatedBrand);

		// Then
		BrandEntity updatedBrandEntity = brandJpaRepository.findById(existingBrandId)
			.orElseThrow(() -> new CustomException(ErrorCode.BRAND_NOT_FOUND));

		assertEquals("UpdatedBrand", updatedBrandEntity.getName());
		assertEquals(2, updatedBrandEntity.getProducts().size());

		List<ProductEntity> updatedProducts = updatedBrandEntity.getProducts();
		assertEquals(Category.상의, updatedProducts.get(0).getCategory());
		assertEquals(1500, updatedProducts.get(0).getPrice().getValue());
		assertEquals(Category.바지, updatedProducts.get(1).getCategory());
		assertEquals(2500, updatedProducts.get(1).getPrice().getValue());
	}

	@Test
	@DisplayName("존재하지 않는 브랜드 업데이트 시 예외 발생 테스트")
	void testUpdateNonExistingBrand() {
		// Given
		BrandCreateRequest brandCreateRequest = new BrandCreateRequest(
			"NonExistingBrand",
			List.of(
				new ProductCreateRequest("상의", 1500),
				new ProductCreateRequest("바지", 2500)
			)
		);

		Brand nonExistingBrand = BrandMapper.toDomain(999L, brandCreateRequest);

		// When & Then
		assertThatThrownBy(() -> updateBrandUseCase.execute(nonExistingBrand))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.BRAND_NOT_FOUND.getMessage());
	}
}
