<template>
  <div class="layout-container">
    <!-- 左侧菜单 -->
    <div class="sidebar" :class="{ collapsed: isMenuCollapsed }">
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
    <div class="content-wrapper">
      <!-- 顶部栏 -->
      <div class="top-bar">
        <div class="top-bar-left">
          <!-- 菜单切换按钮 -->
          <div class="menu-toggle" @click="toggleMenu">
            <img 
              :src="arrowsIcon" 
              alt="菜单切换" 
              class="toggle-icon"
              :class="{ rotated: !isMenuCollapsed }"
            />
            <span v-if="isMenuCollapsed" class="current-menu-label">{{ currentMenuLabel }}</span>
          </div>
          <div class="search-box">
            <input 
              type="text" 
              placeholder="搜索..." 
              class="search-input"
              v-model="searchText"
            />
            <span class="search-icon">🔍</span>
          </div>
        </div>
        <div class="top-bar-right">
          <div class="user-info">
            <img :src="logoIcon" alt="头像" class="avatar" />
            <span class="username">管理员</span>
          </div>
        </div>
      </div>
      
      <!-- 主内容区域 -->
      <div class="content-container">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import logoIcon from '@/assets/images/layout/icon.png'
import arrowsIcon from '@/assets/images/layout/arrows.jpg'

const route = useRoute()

// 搜索文本
const searchText = ref('')

// 菜单折叠状态
const isMenuCollapsed = ref(true)

// 菜单项配置
const menuItems = ref([
  { label: '首页', path: '/home' },
  { label: '员工管理', path: '/employees' },
  { label: '优惠管理', path: '/promotions' }
  // 后续可以添加更多菜单项
])

// 切换菜单显示/隐藏
const toggleMenu = () => {
  isMenuCollapsed.value = !isMenuCollapsed.value
}

// 获取当前选中的菜单项标签
const currentMenuLabel = computed(() => {
  const currentItem = menuItems.value.find(item => item.path === route.path)
  return currentItem ? currentItem.label : ""
})
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
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  flex-shrink: 0;
  height: 100vh;
  display: flex;
  flex-direction: column;
  transition: transform 0.3s ease, width 0.3s ease;
  overflow: hidden;
}

.sidebar.collapsed {
  transform: translateX(-100%);
  width: 0;
  padding: 0;
}

.menu-header {
  display: flex;
  align-items: center;
  gap: 12px;
  text-align: left;
  height: 50px;
  padding: 0 20px;
  margin: 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  box-sizing: border-box;
  flex-shrink: 0;
}

.logo {
  width: 32px;
  height: 32px;
  object-fit: cover;
  border-radius: 50%;
  flex-shrink: 0;
}

.menu-header h3 {
  color: #ffffff;
  font-size: 16px;
  font-weight: 600;
  margin: 0;
  white-space: nowrap;
}

.menu-items {
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 20px 0;
  overflow-y: auto;
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

/* 右侧内容包装器 */
.content-wrapper {
  flex: 1;
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: hidden;
}

/* 顶部栏样式 */
.top-bar {
  height: 50px;
  background-color: #2a2a2a;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  z-index: 100;
  flex-shrink: 0;
}

.top-bar-left {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 16px;
}

/* 菜单切换按钮 */
.menu-toggle {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  height: 32px;
  padding: 0 12px;
  background-color: #4a4a4a;
  border-radius: 0 16px 16px 0;
  cursor: pointer;
  transition: all 0.3s ease;
  white-space: nowrap;
  margin-left: -20px;
  min-width: 40px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.menu-toggle:hover {
  background-color: #5a5a5a;
}

.toggle-icon {
  width: 20px;
  height: 20px;
  display: inline-block;
  transition: transform 0.3s ease;
  object-fit: contain;
}

.toggle-icon.rotated {
  transform: rotate(180deg);
}

.current-menu-label {
  font-size: 14px;
  color: #ffffff;
  font-weight: 500;
}

.search-box {
  position: relative;
  width: 300px;
  max-width: 100%;
}

.search-input {
  width: 100%;
  height: 36px;
  padding: 0 40px 0 12px;
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 18px;
  font-size: 14px;
  outline: none;
  transition: all 0.3s ease;
  background-color: rgba(255, 255, 255, 0.1);
  color: #ffffff;
}

.search-input::placeholder {
  color: rgba(255, 255, 255, 0.5);
}

.search-input:focus {
  border-color: #ffd700;
  background-color: rgba(255, 255, 255, 0.15);
  box-shadow: 0 0 0 2px rgba(255, 215, 0, 0.2);
}

.search-icon {
  position: absolute;
  right: 12px;
  top: 50%;
  transform: translateY(-50%);
  color: rgba(255, 255, 255, 0.6);
  font-size: 16px;
  pointer-events: none;
}

.top-bar-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 10px;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 20px;
  transition: background-color 0.3s ease;
}

.user-info:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid rgba(255, 255, 255, 0.3);
}

.username {
  font-size: 14px;
  color: #ffffff;
  font-weight: 500;
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
  
  .top-bar {
    padding: 0 15px;
  }
  
  .search-box {
    width: 200px;
  }
  
  .username {
    display: none;
  }
}
</style>
