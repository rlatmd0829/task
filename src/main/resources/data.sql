-- 브랜드 데이터 삽입
INSERT INTO brands (name) VALUES ('A');
INSERT INTO brands (name) VALUES ('B');
INSERT INTO brands (name) VALUES ('C');
INSERT INTO brands (name) VALUES ('D');
INSERT INTO brands (name) VALUES ('E');
INSERT INTO brands (name) VALUES ('F');
INSERT INTO brands (name) VALUES ('G');
INSERT INTO brands (name) VALUES ('H');
INSERT INTO brands (name) VALUES ('I');

-- 상품 데이터 삽입
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '상의', 11200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '아우터', 5500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '바지', 4200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '스니커즈', 9000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '가방', 2000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '모자', 1700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '양말', 1800);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='A'), '액세서리', 2300);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '상의', 10500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '아우터', 5900);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '바지', 3800);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '스니커즈', 9100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '가방', 2100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '모자', 2000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '양말', 2000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='B'), '액세서리', 2200);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '상의', 10000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '아우터', 6200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '바지', 3300);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '스니커즈', 9200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '가방', 2200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '모자', 1900);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '양말', 2200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='C'), '액세서리', 2100);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '상의', 10100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '아우터', 5100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '바지', 3000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '스니커즈', 9500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '가방', 2500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '모자', 1500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '양말', 2400);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='D'), '액세서리', 2000);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '상의', 10700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '아우터', 5000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '바지', 3800);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '스니커즈', 9900);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '가방', 2300);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '모자', 1800);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '양말', 2100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='E'), '액세서리', 2100);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '상의', 11200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '아우터', 7200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '바지', 4000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '스니커즈', 9300);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '가방', 2100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '모자', 1600);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '양말', 2300);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='F'), '액세서리', 1900);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '상의', 10500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '아우터', 5800);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '바지', 3900);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '스니커즈', 9000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '가방', 2200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '모자', 1700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '양말', 2100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='G'), '액세서리', 2000);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '상의', 10800);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '아우터', 6300);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '바지', 3100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '스니커즈', 9700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '가방', 2100);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '모자', 1600);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '양말', 2000);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='H'), '액세서리', 2000);

INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '상의', 11400);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '아우터', 6700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '바지', 3200);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '스니커즈', 9500);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '가방', 2400);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '모자', 1700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '양말', 1700);
INSERT INTO products (brand_id, category, price) VALUES ((SELECT id FROM brands WHERE name='I'), '액세서리', 2400);
