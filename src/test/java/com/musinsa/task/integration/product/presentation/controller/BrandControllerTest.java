package com.musinsa.task.integration.product.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.infrastructure.persistence.BrandEntity;
import com.musinsa.task.product.infrastructure.persistence.ProductEntity;
import com.musinsa.task.product.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.product.infrastructure.repository.ProductJpaRepository;
import com.musinsa.task.product.presentation.dto.request.BrandCreateRequest;
import com.musinsa.task.product.presentation.dto.request.ProductCreateRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BrandControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

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
	void testAddBrand() throws Exception {
		// Given
		BrandCreateRequest brandCreateRequest = new BrandCreateRequest(
			"NewBrand",
			List.of(
				new ProductCreateRequest("상의", 15000),
				new ProductCreateRequest("바지", 20000)
			)
		);

		String brandJson = objectMapper.writeValueAsString(brandCreateRequest);

		// When & Then
		mockMvc.perform(post("/api/brands/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(brandJson))
			.andExpect(status().isCreated());

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
