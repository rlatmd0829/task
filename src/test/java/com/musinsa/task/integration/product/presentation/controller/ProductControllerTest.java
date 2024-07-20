package com.musinsa.task.integration.product.presentation.controller;

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

import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;
import com.musinsa.task.product.infrastructure.repository.ProductJpaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductJpaRepository productJpaRepository;

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
	void testGetCheapestProduct() throws Exception {
		mockMvc.perform(get("/api/products/cheapest")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.categoryPrices", hasSize(2)))
			.andExpect(jsonPath("$.categoryPrices[0].category", is("상의")))
			.andExpect(jsonPath("$.categoryPrices[0].price", is(50)))
			.andExpect(jsonPath("$.categoryPrices[1].category", is("가방")))
			.andExpect(jsonPath("$.categoryPrices[1].price", is(150)))
			.andExpect(jsonPath("$.total", is(200)));
	}
}
