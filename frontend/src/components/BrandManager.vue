<template>
  <div id="brand-manager">
    <h1>브랜드 및 상품 기능</h1>
    <section>
      <h2>브랜드 및 상품 추가</h2>
      <input v-model="newBrand.name" placeholder="Enter brand name" class="input-field" />
      <div v-for="(product, index) in newBrand.products" :key="index" class="product-input">
        <label>Product {{ index + 1 }}:</label>
        <select v-model="product.categoryName" class="input-field">
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
        <input v-model="product.price" placeholder="Enter product price" class="input-field" type="number" />
        <button @click="removeProduct(index)" class="btn btn-danger">Remove Product</button>
      </div>
      <button @click="addProduct" class="btn btn-secondary add-product">Add Product</button>
      <button @click="addBrand" class="btn btn-primary">Add Brand</button>
    </section>
    <section>
      <h2>브랜드 및 상품 업데이트</h2>
      <input v-model="updateBrandId" placeholder="Enter brand ID to update" class="input-field" />
      <input v-model="updateBrand.name" placeholder="Enter new brand name" class="input-field" />
      <div v-for="(product, index) in updateBrand.products" :key="index" class="product-input">
        <label>Product {{ index + 1 }}:</label>
        <select v-model="product.categoryName" class="input-field">
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
        <input v-model="product.price" placeholder="Enter product price" class="input-field" type="number" />
        <button @click="removeUpdateProduct(index)" class="btn btn-danger">Remove Product</button>
      </div>
      <button @click="addUpdateProduct" class="btn btn-secondary add-product">Add Product</button>
      <button @click="updateBrandData" class="btn btn-primary">Update Brand</button>
    </section>
    <section>
      <h2>브랜드 삭제</h2>
      <input v-model="deleteBrandId" placeholder="Enter brand ID to delete" class="input-field" />
      <button @click="deleteBrand" class="btn btn-primary">Delete Brand</button>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newBrand: {
        name: '',
        products: [
          { categoryName: '상의', price: '' },
        ],
      },
      updateBrandId: '',
      updateBrand: {
        name: '',
        products: [
          { categoryName: '상의', price: '' },
        ],
      },
      deleteBrandId: '',
      categories: ['상의', '아우터', '바지', '스니커즈', '가방', '모자', '양말', '액세서리'], // 정해진 카테고리 목록
    };
  },
  methods: {
    addProduct() {
      this.newBrand.products.push({ categoryName: '상의', price: '' });
    },
    addUpdateProduct() {
      this.updateBrand.products.push({ categoryName: '상의', price: '' });
    },
    removeProduct(index) {
      this.newBrand.products.splice(index, 1);
    },
    removeUpdateProduct(index) {
      this.updateBrand.products.splice(index, 1);
    },
    async addBrand() {
      try {
        const response = await this.$axios.post('/api/brands', this.newBrand);
        console.log(response.data);
        alert('Brand added successfully!');
      } catch (error) {
        console.error('Error adding brand:', error);
        alert('Failed to add brand.');
      }
    },
    async updateBrandData() {
      try {
        const response = await this.$axios.put(`/api/brands/${this.updateBrandId}`, this.updateBrand);
        console.log(response.data);
        alert('Brand updated successfully!');
      } catch (error) {
        console.error('Error updating brand:', error);
        alert('Failed to update brand.');
      }
    },
    async deleteBrand() {
      try {
        const response = await this.$axios.delete(`/api/brands/${this.deleteBrandId}`);
        console.log(response.data);
        alert('Brand deleted successfully!');
      } catch (error) {
        console.error('Error deleting brand:', error);
        alert('Failed to delete brand.');
      }
    },
  },
};
</script>

<style scoped>
#brand-manager {
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

.product-input {
  margin-bottom: 10px;
}

.btn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin: 10px 5px;
}

.btn-danger {
  background-color: #dc3545;
}

.btn:hover {
  background-color: #0056b3;
}

.btn-danger:hover {
  background-color: #c82333;
}

.input-field {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: calc(100% - 22px);
  margin-bottom: 10px;
}

.add-product {
  background-color: #28a745;
}

.add-product:hover {
  background-color: #218838;
}
</style>
