package com.musinsa.task.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.task.infrastructure.persistence.BrandEntity;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long>, BrandJpaRepositoryCustom {
}
