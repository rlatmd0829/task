# musinsa task

## 설명

- data.sql, schema.sql 파일을 사용해 초기 데이터를 삽입합니다.
- 복잡한 쿼리 대신 애플리케이션단에서 처리하여 데이터를 조회합니다.
  - 테스트 코드를 작성하여 비즈니스 로직을 검증합니다.
- jpaEntity와 domain model을 분리하여 jpa 의존성을 분리하였습니다.
- Repository 인터페이스와 이를 구현한 JPARepository를 분리하여 JPA 의존성을 도메인 모델에서 분리했습니다.
- Presentation 레이어에서 dto 변환을 수행해 의존성 방향을 일관성 있게 유지하였습니다.
- frontend 화면을 구현하여 확인해볼 수 있습니다. 


### 패키지 구조

클린 아키텍처를 적용하여 도메인 모델에 변경이 최대한 영향을 주지 않도록 설계하였습니다.

- Presentation (controller, dto)
- Application (usecase)
- domain (components, model, repository)
- infra (entity, repositoryImpl, jpa, queryDSL)


## 개발 환경
### Backend
* Spring Boot 3.2.8
* Java 17
* Gradle
* h2
* JPA, Querydsl
* Swagger
* Junit5, AssertJ, Mockito

### Frontend
* Vue3
* JavaScript


## 구현 내용

### 구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
- url : `GET /api/products/cheapest-by-category`
- swagger : http://localhost:8080/swagger-ui/index.html#/Product%20API/getCheapestProduct

### 구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- url : `GET /api/products/cheapest-brand`
- swagger : http://localhost:8080/swagger-ui/index.html#/Product%20API/getCheapestBrand

### 구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
- url : `GET /api/products/price-range`
- swagger : http://localhost:8080/swagger-ui/index.html#/Product%20API/getPriceRangeByCategory

### 구현 4-1) 브랜드 및 상품을 추가하는 API
- url : `POST /api/brands`
- swagger : http://localhost:8080/swagger-ui/index.html#/Brand%20API/addBrand

### 구현 4-2) 브랜드 및 상품을 업데이트하는 API
- url : `PUT /api/brands/{id}`
- swagger : http://localhost:8080/swagger-ui/index.html#/Brand%20API/updateBrand

### 구현 4-3) 브랜드 및 상품을 삭제하는 API
- url : `DELETE /api/brands/{id}`
- swagger : http://localhost:8080/swagger-ui/index.html#/Brand%20API/deleteBrand

### 구현 4-4) 브랜드 및 상품 전체조회 API
- url : `GET /api/brands`
- swagger : http://localhost:8080/swagger-ui/index.html#/Brand%20API/getAllBrands



## 실행 방법
### Backend

#### 빌드
```shell
./gradlew build
```

#### 프로젝트 실행
```shell
./gradlew bootRun
```

- h2 DB
  - http://localhost:8080/h2-console
  - jdbc url : `jdbc:h2:mem:testdb`
  - id : `sa`
  - pw : 

- swagger
  - http://localhost:8080/swagger-ui.html

#### 테스트 실행
```shell
./gradlew test
```

![image](https://github.com/user-attachments/assets/0a6834b4-d264-4e81-b1d1-2708b9775048)

> `build/reports/tests/test/index.html` 파일을 브라우저로 열어 테스트 결과를 확인할 수 있습니다.


### Frontend

#### 프로젝트 실행
```shell
cd /frontend
yarn install
yarn serve
```

#### 화면

![image](https://github.com/user-attachments/assets/a60f20b1-a711-4eae-820f-bd4c328e70cb)

![image](https://github.com/user-attachments/assets/7a8aae71-6964-4945-939e-40e114e81722)

* http://localhost:8081


