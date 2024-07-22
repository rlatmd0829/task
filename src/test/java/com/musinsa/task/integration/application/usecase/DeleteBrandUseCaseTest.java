package com.musinsa.task.integration.application.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.application.usecase.DeleteBrandUseCase;
import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.musinsa.task.infrastructure.repository.BrandJpaRepository;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DeleteBrandUseCaseTest {

	@Autowired
	private DeleteBrandUseCase deleteBrandUseCase;

	@Autowired
	private BrandJpaRepository brandJpaRepository;

	private BrandEntity savedBrand;

	@BeforeEach
	void setUp() {
		savedBrand = brandJpaRepository.save(BrandEntity.create("Test Brand"));
	}

	@Test
	void deleteBrand() {
		deleteBrandUseCase.execute(savedBrand.getId());

		assertThat(brandJpaRepository.findById(savedBrand.getId())).isEmpty();
	}

	@Test
	void deleteNonExistentBrand() {
		Long nonExistentId = 999L;

		assertThatThrownBy(() -> deleteBrandUseCase.execute(nonExistentId))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.BRAND_NOT_FOUND.getMessage());
	}
}
