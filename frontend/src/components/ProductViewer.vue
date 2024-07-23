<template>
  <div id="product-viewer">
    <h1>상품 조회</h1>
    <section>
      <h2>카테고리별 가장 싼 상품 조회</h2>
      <button @click="fetchCheapestProducts" class="btn btn-primary">조회</button>
      <ul class="product-list">
        <li v-for="product in cheapestProducts" :key="product.category" class="product-item">
          <strong>{{ product.category }}:</strong> {{ product.brand }} - {{ product.price }}
        </li>
      </ul>
    </section>
    <section>
      <h2>가장 싼 브랜드 조회</h2>
      <button @click="fetchCheapestBrand" class="btn btn-primary">조회</button>
      <div v-if="cheapestBrand" class="brand-info">
        <p><strong>Brand:</strong> {{ cheapestBrand.cheapest.brand }}</p>
        <p><strong>Total:</strong> {{ cheapestBrand.cheapest.total }}</p>
        <ul class="category-list">
          <li v-for="category in cheapestBrand.cheapest.categories" :key="category.category" class="category-item">
            <strong>{{ category.category }}:</strong> {{ category.price }}
          </li>
        </ul>
      </div>
    </section>
    <section>
      <h2>카테고리별 가격 범위 조회</h2>
      <select v-model="selectedCategory" class="input-field">
        <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
      </select>
      <button @click="fetchPriceRange" class="btn btn-primary">조회</button>
      <div v-if="priceRange" class="price-info">
        <p><strong>Category:</strong> {{ priceRange.category }}</p>
        <div>
          <p><strong>Min Price:</strong></p>
          <ul>
            <li><strong>Brand:</strong> {{ priceRange.minPrice.brand }}</li>
            <li><strong>Price:</strong> {{ priceRange.minPrice.price }}</li>
          </ul>
        </div>
        <div>
          <p><strong>Max Price:</strong></p>
          <ul>
            <li><strong>Brand:</strong> {{ priceRange.maxPrice.brand }}</li>
            <li><strong>Price:</strong> {{ priceRange.maxPrice.price }}</li>
          </ul>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cheapestProducts: [],
      cheapestBrand: null,
      selectedCategory: '상의',
      priceRange: null,
      categories: ['상의', '아우터', '바지', '스니커즈', '가방', '모자', '양말', '액세서리'],
    };
  },
  methods: {
    async fetchCheapestProducts() {
      try {
        const response = await this.$axios.get('/api/products/cheapest-by-category');
        this.cheapestProducts = response.data.data.categoryPrices;
      } catch (error) {
        console.error('Error fetching cheapest products:', error);
      }
    },
    async fetchCheapestBrand() {
      try {
        const response = await this.$axios.get('/api/products/cheapest-brand');
        console.log(response.data); // 응답 데이터 구조 확인
        this.cheapestBrand = response.data.data;
      } catch (error) {
        console.error('Error fetching cheapest brand:', error);
      }
    },
    async fetchPriceRange() {
      try {
        if (!this.selectedCategory) {
          alert('Please select a category');
          return;
        }
        const response = await this.$axios.get(`/api/products/price-range?category=${this.selectedCategory}`);
        this.priceRange = response.data.data;
      } catch (error) {
        console.error('Error fetching price range:', error);
      }
    },
  },
};
</script>

<style scoped>
#product-viewer {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

section {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.btn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin: 10px 0;
}

.btn:hover {
  background-color: #0056b3;
}

.product-list, .category-list {
  list-style-type: none;
  padding: 0;
}

.product-item, .category-item {
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 10px;
  margin: 10px 0;
  border-radius: 5px;
}

.brand-info, .price-info {
  text-align: left;
  margin-top: 20px;
}

.input-field {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: calc(100% - 22px);
  margin-bottom: 10px;
}

h1 {
  font-size: 2em;
  margin-bottom: 20px;
}

h2 {
  font-size: 1.5em;
  margin-bottom: 10px;
}

.price-info > div {
  margin-top: 10px;
}

.price-info ul {
  list-style-type: none;
  padding: 0;
}

.price-info li {
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 5px;
  margin: 5px 0;
  border-radius: 5px;
}
</style>
