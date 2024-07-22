package com.musinsa.task.unit.product.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.repository.BrandRepository;
import com.musinsa.task.product.domain.service.BrandService;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;

	@InjectMocks
	private BrandService brandService;

	@Test
	@DisplayName("새로운 브랜드를 추가하는 테스트")
	void testAddBrand() {
		// Given
		Brand brand = new Brand(null, "TestBrand", List.of());

		// When
		brandService.addBrand(brand);

		// Then
		verify(brandRepository, times(1)).save(brand);
	}

	@Test
	@DisplayName("기존 브랜드를 업데이트하는 테스트")
	void testUpdateBrand() {
		// Given
		Brand brand = new Brand(1L, "UpdatedBrand", List.of());

		// When
		brandService.updateBrand(brand);

		// Then
		verify(brandRepository, times(1)).update(brand);
	}

	@Test
	@DisplayName("존재하지 않는 브랜드를 업데이트하려고 할 때 예외 발생 테스트")
	void testUpdateNonExistingBrand() {
		// Given
		Brand brand = new Brand(1L, "UpdatedBrand", List.of());
		doThrow(new CustomException(ErrorCode.BRAND_NOT_FOUND))
			.when(brandRepository).update(brand);

		// When & Then
		assertThatThrownBy(() -> brandService.updateBrand(brand))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.BRAND_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("브랜드를 삭제하는 테스트")
	void testDeleteBrand() {
		// Given
		Long brandId = 1L;

		// When
		brandService.deleteBrand(brandId);

		// Then
		verify(brandRepository, times(1)).delete(brandId);
	}

	@Test
	@DisplayName("존재하지 않는 브랜드를 삭제하려고 할 때 예외 발생 테스트")
	void testDeleteNonExistingBrand() {
		// Given
		Long brandId = 999L;
		doThrow(new CustomException(ErrorCode.BRAND_NOT_FOUND))
			.when(brandRepository).delete(brandId);

		// When & Then
		assertThatThrownBy(() -> brandService.deleteBrand(brandId))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.BRAND_NOT_FOUND.getMessage());
	}
}
