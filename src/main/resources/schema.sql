CREATE TABLE brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      brand_id BIGINT,
      category VARCHAR(255),
      price INTEGER,
      CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id)
);
