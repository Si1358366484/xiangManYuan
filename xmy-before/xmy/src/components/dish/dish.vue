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
import { ref } from 'vue'

// 导航列表
const navList = ref(['全部', '大厅', '包间', '阳台'])
// 当前选中的导航项
const currentNav = ref('全部')
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

/* 顶部导航样式 - 与Layout.vue保持一致 */
.top-navigation {
  width: 100%;
  background-color: #2a2a2a;
  padding: 2px 2px;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}

/* 导航按钮样式 - 与Layout.vue保持一致 */
.nav-button {
  padding: 8px 20px;
  background-color: transparent;
  border: none;
  color: #ffffff;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  border-radius: 4px;
  transition: all 0.3s ease;
  white-space: nowrap;
}

.nav-button:hover {
  background-color: rgba(255, 255, 255, 0.1);
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
  background-color: white;
  overflow: auto;
}

.dish-content h3 {
  margin: 0 0 20px 0;
  color: #333;
}
</style>