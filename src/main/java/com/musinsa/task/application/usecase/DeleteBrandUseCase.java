package com.musinsa.task.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.task.domain.components.BrandStore;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteBrandUseCase {
	private final BrandStore brandStore;

	public void execute(Long id) {
		brandStore.deleteBrand(id);
	}
}
