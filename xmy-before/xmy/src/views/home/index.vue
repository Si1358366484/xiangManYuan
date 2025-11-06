<template>
  <div class="home-content">
    <!-- 网格布局 -->
    <div class="grid-container">
      <div 
        v-for="(item, index) in gridItems" 
        :key="index"
        :class="['grid-item', item.status]"
      >
        <div class="item-name">{{ item.name }}</div>
        <div class="item-capacity">{{ item.capacity }}</div>
        <div v-if="item.status === 'occupied'" class="item-details">
          <div class="item-price">¥{{ item.price }}</div>
          <div class="item-people">{{ item.people }}</div>
          <div class="item-time">{{ item.time }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 当前选中的标签
const activeTab = ref('all')

// 标签选项
const tabs = [
  { label: '全部', value: 'all' },
  { label: '大厅', value: 'hall' },
  { label: '包间', value: 'private' },
  { label: '阳台', value: 'balcony' }
]

// 模拟数据 - 网格项
const allItems = ref([
  // 空桌台
  { name: '卡1', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡2', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡3', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡5', capacity: '4人', status: 'occupied', type: 'hall', price: 0, people: '3/4人', time: '31分钟' },
  { name: '卡6', capacity: '4人', status: 'occupied', type: 'hall', price: 0, people: '2/4人', time: '58分钟' },
  { name: '卡8', capacity: '4人', status: 'occupied', type: 'hall', price: 12, people: '4/4人', time: '26分钟' },
  { name: '卡9', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡10', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡11', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡12', capacity: '4人', status: 'occupied', type: 'hall', price: 20, people: '3/4人', time: '13分钟' },
  { name: '卡13', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡15', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡16', capacity: '4人', status: 'empty', type: 'hall' },
  { name: '卡18', capacity: '4人', status: 'empty', type: 'hall' },
  // 包间
  { name: '如意厅', capacity: '10人', status: 'empty', type: 'private' },
  { name: '锦绣厅', capacity: '10人', status: 'empty', type: 'private' },
  { name: '五福厅', capacity: '10人', status: 'empty', type: 'private' },
  { name: '吉祥厅', capacity: '10人', status: 'empty', type: 'private' },
  { name: '富贵厅', capacity: '10人', status: 'occupied', type: 'private', price: 0, people: '10人', time: '0分钟' },
  // 阳台
  { name: '外1', capacity: '4人', status: 'empty', type: 'balcony' },
  { name: '外2', capacity: '4人', status: 'empty', type: 'balcony' },
  { name: '外3', capacity: '4人', status: 'empty', type: 'balcony' },
  { name: '外5', capacity: '4人', status: 'empty', type: 'balcony' },
  { name: '外6', capacity: '4人', status: 'empty', type: 'balcony' }
])

// 根据选中的标签过滤数据
const gridItems = computed(() => {
  if (activeTab.value === 'all') {
    return allItems.value
  }
  return allItems.value.filter(item => {
    const typeMap = {
      'hall': 'hall',
      'private': 'private',
      'balcony': 'balcony'
    }
    return item.type === typeMap[activeTab.value]
  })
})
</script>

<style scoped>
.home-content {
  width: 100%;
  height: 100%;
}

/* 网格布局样式 */
.grid-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 16px;
  padding: 20px 0;
}

.grid-item {
  aspect-ratio: 1;
  border-radius: 8px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 2px solid transparent;
}

/* 空桌台样式 - 白色 */
.grid-item.empty {
  background-color: #ffffff;
  color: #1a1a1a;
  border-color: #e0e0e0;
}

.grid-item.empty:hover {
  border-color: #ffd700;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(255, 215, 0, 0.3);
}

/* 已占用桌台样式 - 红色 */
.grid-item.occupied {
  background-color: #dc3545;
  color: #ffffff;
  border-color: #c82333;
}

.grid-item.occupied:hover {
  border-color: #ff6b7a;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(220, 53, 69, 0.4);
}

.item-name {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 8px;
}

.item-capacity {
  font-size: 14px;
  opacity: 0.8;
  margin-bottom: auto;
}

.item-details {
  margin-top: 8px;
  padding-top: 8px;
  border-top: 1px solid rgba(255, 255, 255, 0.2);
  font-size: 12px;
}

.item-price {
  font-weight: 600;
  margin-bottom: 4px;
}

.item-people,
.item-time {
  opacity: 0.9;
  margin-bottom: 2px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .grid-container {
    grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
    gap: 12px;
  }
}

@media (min-width: 1920px) {
  .grid-container {
    grid-template-columns: repeat(5, 1fr);
  }
}
</style>

