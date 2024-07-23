package com.musinsa.task.integration.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.task.application.usecase.GetAllBrandsUseCase;
import com.musinsa.task.domain.components.BrandReader;
import com.musinsa.task.domain.model.Brand;

@ExtendWith(MockitoExtension.class)
class GetAllBrandsUseCaseTest {

	@Mock
	private BrandReader brandReader;

	@InjectMocks
	private GetAllBrandsUseCase getAllBrandsUseCase;

	@Test
	void testExecute() {
		// Given
		Brand brand1 = new Brand(1L, "Brand1", List.of());
		Brand brand2 = new Brand(2L, "Brand2", List.of());
		List<Brand> expectedBrands = List.of(brand1, brand2);

		// 브랜드 리더가 모든 브랜드를 반환하도록 목 설정
		when(brandReader.getAllBrands()).thenReturn(expectedBrands);

		// When
		List<Brand> actualBrands = getAllBrandsUseCase.execute();

		// Then
		assertEquals(expectedBrands, actualBrands);
	}
}
