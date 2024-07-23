import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';
import BrandManager from './components/BrandManager.vue';
import ProductViewer from './components/ProductViewer.vue';

const app = createApp(App);

// Axios를 글로벌 프로퍼티로 추가
app.config.globalProperties.$axios = axios;

// 컴포넌트 등록
app.component('brand-manager', BrandManager);
app.component('product-viewer', ProductViewer);

app.mount('#app');
