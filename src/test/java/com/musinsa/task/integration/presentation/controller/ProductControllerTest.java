package com.musinsa.task.integration.presentation.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.musinsa.task.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.infrastructure.persistence.ProductEntity;
import com.musinsa.task.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.infrastructure.repository.ProductJpaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

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
	@DisplayName("카테고리별 최저가 상품 찾기 통합 테스트")
	void testGetCheapestProduct() throws Exception {
		mockMvc.perform(get("/api/products/cheapest")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.categoryPrices", hasSize(8)))
			.andExpect(jsonPath("$.data.categoryPrices[0].category", is("상의")))
			.andExpect(jsonPath("$.data.categoryPrices[0].price", is(10500)))
			.andExpect(jsonPath("$.data.categoryPrices[0].brand", is("B")))
			.andExpect(jsonPath("$.data.categoryPrices[1].category", is("아우터")))
			.andExpect(jsonPath("$.data.categoryPrices[1].price", is(5500)))
			.andExpect(jsonPath("$.data.categoryPrices[1].brand", is("A")))
			.andExpect(jsonPath("$.data.categoryPrices[2].category", is("바지")))
			.andExpect(jsonPath("$.data.categoryPrices[2].price", is(3800)))
			.andExpect(jsonPath("$.data.categoryPrices[2].brand", is("B")))
			.andExpect(jsonPath("$.data.categoryPrices[3].category", is("스니커즈")))
			.andExpect(jsonPath("$.data.categoryPrices[3].price", is(9000)))
			.andExpect(jsonPath("$.data.categoryPrices[3].brand", is("A")))
			.andExpect(jsonPath("$.data.categoryPrices[4].category", is("가방")))
			.andExpect(jsonPath("$.data.categoryPrices[4].price", is(2000)))
			.andExpect(jsonPath("$.data.categoryPrices[4].brand", is("A")))
			.andExpect(jsonPath("$.data.categoryPrices[5].category", is("모자")))
			.andExpect(jsonPath("$.data.categoryPrices[5].price", is(1700)))
			.andExpect(jsonPath("$.data.categoryPrices[5].brand", is("A")))
			.andExpect(jsonPath("$.data.categoryPrices[6].category", is("양말")))
			.andExpect(jsonPath("$.data.categoryPrices[6].price", is(1800)))
			.andExpect(jsonPath("$.data.categoryPrices[6].brand", is("A")))
			.andExpect(jsonPath("$.data.categoryPrices[7].category", is("액세서리")))
			.andExpect(jsonPath("$.data.categoryPrices[7].price", is(2200)))
			.andExpect(jsonPath("$.data.categoryPrices[7].brand", is("B")))
			.andExpect(jsonPath("$.data.total", is(36500)));
	}

	@Test
	@DisplayName("단일 브랜드로 모든 카테고리 상품 최저가 조회 테스트")
	void testGetCheapestBrand() throws Exception {
		mockMvc.perform(get("/api/products/cheapest-brand")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.cheapest.brand", is("B")))
			.andExpect(jsonPath("$.data.cheapest.categories", hasSize(8)))
			.andExpect(jsonPath("$.data.cheapest.categories[0].category", is("상의")))
			.andExpect(jsonPath("$.data.cheapest.categories[0].price", is(10500)))
			.andExpect(jsonPath("$.data.cheapest.categories[1].category", is("아우터")))
			.andExpect(jsonPath("$.data.cheapest.categories[1].price", is(5900)))
			.andExpect(jsonPath("$.data.cheapest.categories[2].category", is("바지")))
			.andExpect(jsonPath("$.data.cheapest.categories[2].price", is(3800)))
			.andExpect(jsonPath("$.data.cheapest.categories[3].category", is("스니커즈")))
			.andExpect(jsonPath("$.data.cheapest.categories[3].price", is(9100)))
			.andExpect(jsonPath("$.data.cheapest.categories[4].category", is("가방")))
			.andExpect(jsonPath("$.data.cheapest.categories[4].price", is(2100)))
			.andExpect(jsonPath("$.data.cheapest.categories[5].category", is("모자")))
			.andExpect(jsonPath("$.data.cheapest.categories[5].price", is(2000)))
			.andExpect(jsonPath("$.data.cheapest.categories[6].category", is("양말")))
			.andExpect(jsonPath("$.data.cheapest.categories[6].price", is(2000)))
			.andExpect(jsonPath("$.data.cheapest.categories[7].category", is("액세서리")))
			.andExpect(jsonPath("$.data.cheapest.categories[7].price", is(2200)))
			.andExpect(jsonPath("$.data.cheapest.total", is(37600)));
	}

	@Test
	@DisplayName("카테고리별 최저가 및 최고가 상품 정보 조회 테스트")
	void testGetCategoryPriceRange() throws Exception {
		mockMvc.perform(get("/api/products/price-range")
				.param("category", "상의")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.category", is("상의")))
			.andExpect(jsonPath("$.data.minPrice.brand", is("B")))
			.andExpect(jsonPath("$.data.minPrice.price", is(10500)))
			.andExpect(jsonPath("$.data.maxPrice.brand", is("A")))
			.andExpect(jsonPath("$.data.maxPrice.price", is(11200)));
	}

	@Test
	@DisplayName("카테고리가 없는 경우 예외 발생 테스트")
	void testGetCategoryPriceRangeCategoryNotFound() throws Exception {
		mockMvc.perform(get("/api/products/price-range")
				.param("category", "장난감")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is(ErrorCode.INVALID_TYPE_VALUE.getMessage())));
	}
}
