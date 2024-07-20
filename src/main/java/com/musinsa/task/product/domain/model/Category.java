package com.musinsa.task.product.domain.model;

public enum Category {
	TOPS("상의"),
	OUTER("아우터"),
	PANTS("바지"),
	SNEAKERS("스니커즈"),
	BAGS("가방"),
	HATS("모자"),
	SOCKS("양말"),
	ACCESSORIES("액세서리");

	private final String displayName;

	Category(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Category fromDisplayName(String displayName) {
		for (Category category : Category.values()) {
			if (category.getDisplayName().equals(displayName)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Unknown display name: " + displayName);
	}
}
