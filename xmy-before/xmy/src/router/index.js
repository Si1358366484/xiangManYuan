import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/home/index.vue'
import Bill from '../views/bill/index.vue'
// 导入菜品管理相关组件
import DishManagement from '../views/dish_management/index.vue'
import Dish from '../views/dish_management/dish/index.vue'
import DishCategory from '../views/dish_management/dishCategory/index.vue'

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
    component: DishManagement,
    redirect: '/dish_management/dish',
    children: [
      {
        path: 'dish',
        name: 'Dish',
        component: Dish,
        meta: { title: '菜品' }
      },
      {
        path: 'dishCategory',
        name: 'DishCategory',
        component: DishCategory,
        meta: { title: '菜品口味' }
      }
    ]
  }
  // 后续可以在这里添加更多路由
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
