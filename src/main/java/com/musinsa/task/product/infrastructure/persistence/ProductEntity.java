package com.musinsa.task.product.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "products")
@Getter
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String brand; // enum으로?

	@Enumerated(EnumType.STRING)
	private String category; // 엔티티로? 관리하면 좋은점ㅇ이 이싼?
	private int price; // VO로?
}
