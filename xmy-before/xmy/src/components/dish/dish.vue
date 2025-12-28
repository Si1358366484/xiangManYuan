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
      <!-- 网格布局 -->
      <div class="grid-container">
        <div 
          v-for="(item, index) in dishList" 
          :key="index"
          class="grid-item"
        >
          <!-- 菜品图片 -->
          <div class="dish-image">
            <div class="no-image">暂无图片</div>
          </div>
          <!-- 菜品名称 -->
          <div class="dish-name">{{ item.dishName }}</div>
          <!-- 菜品价格 -->
          <div class="dish-price">￥{{ item.dishPrice }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getDishCategoryList, getDishList } from '@/api/dish'
import { ElMessage } from 'element-plus'

// 导航列表
const navList = ref(['全部'])
// 当前选中的导航项
const currentNav = ref('全部')
// 菜品列表
const dishList = ref([])

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
    ElMessage.error('获取菜品分类失败，请稍后重试')
  }
}

// 获取菜品列表数据
const fetchDishList = async () => {
  try {
    const response = await getDishList()
    // 从response.data中获取菜品数组
    dishList.value = response.data || []
  } catch (error) {
    console.error('获取菜品列表失败:', error)
    ElMessage.error('获取菜品列表失败，请稍后重试')
  }
}

// 组件挂载时获取数据
onMounted(() => {
  fetchDishCategories()
  fetchDishList()
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
  background-color: #2A2A2A;
  overflow-y: auto;
  /* 隐藏滚动条 */
  scrollbar-width: none;
  -ms-overflow-style: none;
}

/* Chrome, Safari 和 Opera */
.dish-content::-webkit-scrollbar {
  display: none;
}

/* 网格布局样式 */
.grid-container {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  padding: 4px 0;
  gap: 4px;
  width: 100%;
  box-sizing: border-box;
  overflow: hidden;
}

/* 网格项样式 */
.grid-item {
  aspect-ratio: 1.8;
  background-color: #ffffff;
  border-radius: 4px;
  padding: 4px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  position: relative;
}

/* 菜品图片容器 */
.dish-image {
  width: 100%;
  flex-grow: 4;
  background-color: #f0f0f0;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 8px;
  overflow: hidden;
}

/* 暂无图片样式 */
.no-image {
  color: #999;
  font-size: 14px;
}

/* 菜品图片样式 */
.dish-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 菜品名称 */
.dish-name {
  font-size: 16px;
  font-weight: 500;
  color: #333;
  margin-bottom: 4px;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100%;
  flex-grow: 1;
  display: flex;
  align-items: center;
}

/* 菜品价格 */
.dish-price {
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
  background-color: #dc3545;
  padding: 4px 8px;
  border-radius: 4px;
  position: absolute;
  bottom: 8px;
  right: 8px;
  margin: 0;
}
</style>