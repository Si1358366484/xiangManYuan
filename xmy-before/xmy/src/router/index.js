import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/home/index.vue'
import Bill from '../views/bill/index.vue'

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/bill',
    name: 'Bill',
    component: Bill
  }
  // 后续可以在这里添加员工管理、优惠管理等路由
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
