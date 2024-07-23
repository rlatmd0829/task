package com.musinsa.task.unit.domain.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.task.domain.components.BrandReader;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.repository.BrandRepository;

@ExtendWith(MockitoExtension.class)
class BrandReaderTest {

	@Mock
	private BrandRepository brandRepository;

	@InjectMocks
	private BrandReader brandReader;

	private List<Brand> brandList;

	@BeforeEach
	void setUp() {
		Brand brand1 = new Brand(1L, "Brand1", List.of());
		Brand brand2 = new Brand(2L, "Brand2", List.of());
		brandList = List.of(brand1, brand2);
	}

	@Test
	void testGetAllBrands() {
		// Given
		when(brandRepository.findAll()).thenReturn(brandList);

		// When
		List<Brand> result = brandReader.getAllBrands();

		// Then
		assertEquals(brandList, result);
	}
}
