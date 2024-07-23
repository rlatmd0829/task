package com.musinsa.task.integration.presentation.controller;

import static org.assertj.core.api.AssertionsForClassTypes.*;
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
import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.musinsa.task.infrastructure.persistence.PriceEmbeddable;
import com.musinsa.task.infrastructure.persistence.ProductEntity;
import com.musinsa.task.infrastructure.repository.BrandJpaRepository;
import com.musinsa.task.infrastructure.repository.ProductJpaRepository;
import com.musinsa.task.presentation.dto.request.BrandRequest;
import com.musinsa.task.presentation.dto.request.ProductRequest;

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
		BrandRequest brandRequest = new BrandRequest(
			"NewBrand",
			List.of(
				new ProductRequest("상의", 15000),
				new ProductRequest("바지", 20000)
			)
		);

		String brandJson = objectMapper.writeValueAsString(brandRequest);

		// When & Then
		mockMvc.perform(post("/api/brands")
				.contentType(MediaType.APPLICATION_JSON)
				.content(brandJson))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").doesNotExist());

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

	@Test
	@DisplayName("기존 브랜드와 상품을 업데이트하는 통합 테스트")
	void testUpdateBrand() throws Exception {
		// Given
		BrandEntity existingBrand = brandJpaRepository.save(BrandEntity.create("ExistingBrand"));
		productJpaRepository.save(ProductEntity.create(existingBrand, Category.상의, new PriceEmbeddable(1000)));
		productJpaRepository.save(ProductEntity.create(existingBrand, Category.바지, new PriceEmbeddable(2000)));

		Long existingBrandId = existingBrand.getId();

		BrandRequest brandRequest = new BrandRequest(
			"UpdatedBrand",
			List.of(
				new ProductRequest("상의", 1500),
				new ProductRequest("바지", 2500)
			)
		);

		String brandJson = objectMapper.writeValueAsString(brandRequest);

		// When & Then
		mockMvc.perform(put("/api/brands/{id}", existingBrandId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(brandJson))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").doesNotExist());

		BrandEntity updatedBrand = brandJpaRepository.findById(existingBrandId)
			.orElseThrow(() -> new CustomException(ErrorCode.BRAND_NOT_FOUND));

		assertEquals("UpdatedBrand", updatedBrand.getName());
		assertEquals(2, updatedBrand.getProducts().size());

		List<ProductEntity> updatedProducts = updatedBrand.getProducts();
		assertEquals(Category.상의, updatedProducts.get(0).getCategory());
		assertEquals(1500, updatedProducts.get(0).getPrice().getValue());
		assertEquals(Category.바지, updatedProducts.get(1).getCategory());
		assertEquals(2500, updatedProducts.get(1).getPrice().getValue());
	}

	@Test
	@DisplayName("존재하지 않는 브랜드 업데이트 시 예외 발생 테스트")
	void testUpdateNonExistingBrand() throws Exception {
		// Given
		BrandRequest brandRequest = new BrandRequest(
			"NonExistingBrand",
			List.of(
				new ProductRequest("상의", 1500),
				new ProductRequest("바지", 2500)
			)
		);

		String brandJson = objectMapper.writeValueAsString(brandRequest);

		// When & Then
		mockMvc.perform(put("/api/brands/{id}", 999L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(brandJson))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.message").value(ErrorCode.BRAND_NOT_FOUND.getMessage()));
	}

	@Test
	@DisplayName("브랜드 삭제 통합 테스트")
	void testDeleteBrand() throws Exception {
		// Given
		BrandEntity existingBrand = brandJpaRepository.save(BrandEntity.create("BrandToDelete"));
		Long existingBrandId = existingBrand.getId();

		// When & Then
		mockMvc.perform(delete("/api/brands/{id}", existingBrandId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").doesNotExist());

		assertThat(brandJpaRepository.findById(existingBrandId)).isEmpty();
	}

	@Test
	@DisplayName("존재하지 않는 브랜드 삭제 시 예외 발생 테스트")
	void testDeleteNonExistingBrand() throws Exception {
		// When & Then
		mockMvc.perform(delete("/api/brands/{id}", 999L)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.message").value(ErrorCode.BRAND_NOT_FOUND.getMessage()));
	}
}
