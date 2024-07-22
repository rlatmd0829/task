package com.musinsa.task.integration.application.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.application.usecase.AddBrandUseCase;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Price;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.musinsa.task.infrastructure.persistence.ProductEntity;
import com.musinsa.task.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.infrastructure.repository.ProductJpaRepository;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AddBrandUseCaseTest {

	@Autowired
	private AddBrandUseCase addBrandUseCase;

	@Autowired
	private BrandJpaRepository brandJpaRepository;

	@Autowired
	private ProductJpaRepository productJpaRepository;

	@BeforeEach
	void setUp() {
		brandJpaRepository.deleteAll();
		productJpaRepository.deleteAll();
	}

	@Test
	@DisplayName("새로운 브랜드와 상품을 추가하는 통합 테스트")
	void testAddBrand() {
		// Given
		Brand brand = new Brand(
			null,
			"NewBrand",
			List.of(
				new Product(null, null, Category.상의, new Price(15000)),
				new Product(null, null, Category.바지, new Price(20000))
			)
		);

		// When
		addBrandUseCase.execute(brand);

		// Then
		BrandEntity savedBrand = brandJpaRepository.findAll().get(0);
		assertEquals("NewBrand", savedBrand.getName());
		assertEquals(2, savedBrand.getProducts().size());

		List<ProductEntity> savedProducts = productJpaRepository.findAll();
		assertEquals(2, savedProducts.size());
		assertEquals(Category.상의, savedProducts.get(0).getCategory());
		assertEquals(15000, savedProducts.get(0).getPrice().getValue());
		assertEquals(Category.바지, savedProducts.get(1).getCategory());
		assertEquals(20000, savedProducts.get(1).getPrice().getValue());
	}
}
