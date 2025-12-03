import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 目标地址
        secure: false, // 如果是https接口，需要配置这个参数
        changeOrigin: true, // 是否改变源
        rewrite: (path) => path.replace(/^\/api/, ''), // 重写路径
      }
    }
  }
})
