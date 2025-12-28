<template>
  <div class="dish-container">
    <!-- 顶部导航栏 -->
    <div class="top-navigation">
      <button 
        v-for="item in navList" 
        :key="item" 
        class="nav-button" 
        :class="{ active: currentNav === item }"
        @click="currentNav = item"
      >
        {{ item }}
      </button>
    </div>
    
    <!-- 内容区域 -->
    <div class="dish-content">
      <h3>当前选中: {{ currentNav }}</h3>
      <!-- 这里可以根据currentNav的值显示不同的内容 -->
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getDishCategoryList } from '@/api/dish'

// 导航列表
const navList = ref(['全部'])
// 当前选中的导航项
const currentNav = ref('全部')

// 获取菜品分类数据
const fetchDishCategories = async () => {
  try {
    const response = await getDishCategoryList()
    // 从response.data中获取分类数组，提取categoryName字段
    const categories = response.data || []
    const categoryNames = categories.map(item => item.categoryName)
    navList.value = ['全部', ...categoryNames]
  } catch (error) {
    console.error('获取菜品分类失败:', error)
  }
}

// 组件挂载时获取分类数据
onMounted(() => {
  fetchDishCategories()
})
</script>

<style scoped>
.dish-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #2A2A2A;
  border-radius: 8px 0 0 8px;
  overflow: hidden;
}

/* 顶部导航样式 */
.top-navigation {
  width: 100%;
  background-color: #ffffff;
  padding: 5px;
  display: flex;
  align-items: center;
  flex-shrink: 0;
  border-radius: 4px;
}

/* 导航按钮样式 */
.nav-button {
  padding: 8px 20px;
  background-color: #e0e0e0;
  border: none;
  color: #333333;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  border-radius: 4px;
  transition: all 0.3s ease;
  white-space: nowrap;
  margin-right: 4px;
}

.nav-button:hover {
  background-color: #d0d0d0;
}

.nav-button.active {
  background-color: #ffd700;
  color: #000000;
  font-weight: 600;
}

/* 内容区域样式 */
.dish-content {
  flex: 1;
  padding: 20px;
  background-color: #2A2A2A;
  overflow: auto;
}

.dish-content h3 {
  margin: 0 0 20px 0;
  color: #ffffff;
}
</style>