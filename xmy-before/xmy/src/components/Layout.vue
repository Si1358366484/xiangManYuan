<template>
  <div class="layout-container">
    <!-- 左侧菜单 -->
    <div class="sidebar">
      <div class="menu-header">
        <img :src="logoIcon" alt="Logo" class="logo" />
        <h3>香满园火锅店</h3>
      </div>
      <div class="menu-items">
        <router-link 
          v-for="item in menuItems" 
          :key="item.path"
          :to="item.path"
          :class="['menu-item', { active: $route.path === item.path }]"
        >
          {{ item.label }}
        </router-link>
      </div>
    </div>

    <!-- 右侧内容容器 -->
    <div class="content-container">
      <router-view />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import logoIcon from '@/assets/images/icon.png'

const route = useRoute()

// 菜单项配置
const menuItems = ref([
  { label: '桌台管理', path: '/home' },
  { label: '员工管理', path: '/employees' },
  { label: '优惠管理', path: '/promotions' }
  // 后续可以添加更多菜单项
])
</script>

<style scoped>
.layout-container {
  width: 100%;
  height: 100vh;
  background-color: transparent;
  display: flex;
}

/* 左侧菜单样式 */
.sidebar {
  width: 200px;
  background-color: #2a2a2a;
  border-radius: 0;
  padding: 20px;
  margin: 0;
  box-sizing: border-box;
  flex-shrink: 0;
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.menu-header {
  display: flex;
  align-items: center;
  gap: 12px;
  text-align: left;
  margin-bottom: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.logo {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 50%;
  flex-shrink: 0;
}

.menu-header h3 {
  color: #ffffff;
  font-size: 18px;
  font-weight: 600;
  margin: 0;
  white-space: nowrap;
}

.menu-items {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.menu-item {
  padding: 12px 20px;
  text-align: left;
  color: #ffffff;
  font-size: 16px;
  font-weight: 500;
  border-radius: 0;
  transition: all 0.3s ease;
  background-color: transparent;
  margin-bottom: 0;
  text-decoration: none;
  display: block;
  width: 100%;
}

.menu-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.menu-item.active {
  background-color: #3a3a3a;
  color: #ffffff;
  font-weight: 600;
}

/* 右侧内容容器样式 */
.content-container {
  flex: 1;
  padding: 20px;
  box-sizing: border-box;
  overflow: auto;
  background-color: transparent;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .layout-container {
    flex-direction: column;
  }
  
  .sidebar {
    width: 100%;
    height: auto;
    margin: 0;
    border-radius: 0;
    padding: 15px;
  }
  
  .menu-items {
    flex-direction: row;
    overflow-x: auto;
    gap: 8px;
    flex: none;
  }
  
  .menu-item {
    margin-bottom: 0;
    white-space: nowrap;
  }
}
</style>
