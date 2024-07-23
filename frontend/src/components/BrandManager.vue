<template>
  <div id="brand-manager">
    <h1>브랜드 관리</h1>
    <section>
      <h2>
        브랜드 목록
        <button @click="openAddModal" class="btn btn-primary add-btn">브랜드 추가</button>
      </h2>
      <table class="brand-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>이름</th>
          <th>관리</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="brand in brands" :key="brand.id">
          <td>{{ brand.id }}</td>
          <td>{{ brand.name }}</td>
          <td>
            <button @click.stop="selectBrand(brand)" class="btn btn-info">상세 보기</button>
            <button @click.stop="editBrand(brand)" class="btn btn-secondary">수정</button>
            <button @click.stop="confirmDeleteBrand(brand.id)" class="btn btn-danger">삭제</button>
          </td>
        </tr>
        </tbody>
      </table>
    </section>

    <!-- Add Brand Modal -->
    <div v-if="isAddModalOpen" class="modal" @click.self="closeAddModal">
      <div class="modal-content">
        <span class="close" @click="closeAddModal">&times;</span>
        <h3>브랜드 및 상품 추가</h3>
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
      </div>
    </div>

    <!-- Update Brand Modal -->
    <div v-if="isUpdateModalOpen" class="modal" @click.self="closeUpdateModal">
      <div class="modal-content small-modal">
        <span class="close" @click="closeUpdateModal">&times;</span>
        <h3>브랜드 및 상품 업데이트</h3>
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
      </div>
    </div>

    <!-- Brand Detail Modal -->
    <div v-if="selectedBrand" class="modal" @click.self="selectedBrand = null">
      <div class="modal-content">
        <span class="close" @click="selectedBrand = null">&times;</span>
        <h3>{{ selectedBrand.name }}의 상품 목록</h3>
        <ul class="product-list">
          <li v-for="product in selectedBrand.products" :key="product.id" class="product-item">
            {{ product.categoryName }} - {{ product.price }}원
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      brands: [],
      selectedBrand: null,
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
      isAddModalOpen: false,
      isUpdateModalOpen: false,
      categories: ['상의', '아우터', '바지', '스니커즈', '가방', '모자', '양말', '액세서리'], // 정해진 카테고리 목록
    };
  },
  methods: {
    async fetchAllBrands() {
      try {
        const response = await this.$axios.get('/api/brands');
        this.brands = response.data.data;
        this.selectedBrand = null;
      } catch (error) {
        console.error('Error fetching brands:', error);
        alert('Failed to fetch brands.');
      }
    },
    selectBrand(brand) {
      this.selectedBrand = brand;
    },
    openAddModal() {
      this.isAddModalOpen = true;
    },
    closeAddModal() {
      this.isAddModalOpen = false;
      this.newBrand = {
        name: '',
        products: [
          { categoryName: '상의', price: '' },
        ],
      };
    },
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
        this.fetchAllBrands();
        this.closeAddModal();
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
        this.fetchAllBrands();
        this.closeUpdateModal();
      } catch (error) {
        console.error('Error updating brand:', error);
        alert('Failed to update brand.');
      }
    },
    confirmDeleteBrand(id) {
      if (confirm('Are you sure you want to delete this brand?')) {
        this.deleteBrand(id);
      }
    },
    async deleteBrand(id) {
      try {
        const response = await this.$axios.delete(`/api/brands/${id}`);
        console.log(response.data);
        alert('Brand deleted successfully!');
        this.fetchAllBrands();
      } catch (error) {
        console.error('Error deleting brand:', error);
        alert('Failed to delete brand.');
      }
    },
    editBrand(brand) {
      this.updateBrandId = brand.id;
      this.updateBrand = {
        name: brand.name,
        products: brand.products.map(product => ({
          categoryName: product.categoryName,
          price: product.price
        }))
      };
      this.isUpdateModalOpen = true;
    },
    closeUpdateModal() {
      this.isUpdateModalOpen = false;
      this.updateBrandId = '';
      this.updateBrand = { name: '', products: [{ categoryName: '상의', price: '' }] };
    }
  },
  created() {
    this.fetchAllBrands();
  }
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

.brand-table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
}

.brand-table th, .brand-table td {
  border: 1px solid #ddd;
  padding: 8px;
}

.brand-table th {
  background-color: #f2f2f2;
  text-align: left;
}

.brand-table tr:hover {
  background-color: #f1f1f1;
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

.btn-info {
  background-color: #17a2b8;
}

.btn-info:hover {
  background-color: #138496;
}

.input-field {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: calc(100% - 22px);
  margin-bottom: 10px;
}

.add-btn {
  float: right;
}

.add-product {
  background-color: #28a745;
}

.add-product:hover {
  background-color: #218838;
}

.product-list {
  list-style-type: none;
  padding: 0;
}

.product-item {
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 10px;
  margin: 5px 0;
  border-radius: 5px;
}

/* Modal styles */
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  border-radius: 10px;
  position: relative;
  max-height: 80%;
  overflow-y: auto;
}

.modal-content.small-modal {
  width: 40%;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
