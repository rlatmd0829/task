package com.musinsa.task.product.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.task.product.infrastructure.persistence.BrandEntity;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {
}
