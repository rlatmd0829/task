const path = require('path');

module.exports = {
  outputDir: path.resolve(__dirname, '../src/main/resources/static'),
  devServer: {
    port: 8081,  // 개발 서버 포트 설정
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // Spring Boot 서버 주소
        changeOrigin: true,
      },
    },
  },
};
