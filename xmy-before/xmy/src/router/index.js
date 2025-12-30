import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/home/index.vue'
import Bill from '../views/bill/index.vue'
import DishManagement from '../views/dish_management/dish/index.vue'

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
  },
  {
    path: '/dish_management',
    name: 'DishManagement',
    component: DishManagement
  }
  // 后续可以在这里添加更多路由
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
