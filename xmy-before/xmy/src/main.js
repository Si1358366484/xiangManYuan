import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 导入Element Plus中文语言包
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

createApp(App).use(router).use(ElementPlus, { locale: zhCn }).mount('#app')
