<template>
  <div id="app">
    <h1>무신사 과제 테스트</h1>
    <div class="menu">
      <button @click="currentComponent = 'product-viewer'" class="btn btn-primary">상품 조회 기능</button>
      <button @click="currentComponent = 'brand-manager'" class="btn btn-primary">브랜드 및 상품 기능</button>
    </div>
    <component :is="currentComponent"></component>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentComponent: null
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1 {
  font-size: 2em;
  margin-bottom: 20px;
}

.menu {
  margin-bottom: 20px;
}

.btn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin: 10px;
}

.btn:hover {
  background-color: #0056b3;
}
</style>
