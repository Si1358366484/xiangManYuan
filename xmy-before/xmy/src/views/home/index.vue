<template>
  <div class="home-content">
    <!-- 网格布局 -->
    <div class="grid-container">
      <div 
        v-for="(item, index) in boothList" 
        :key="index"
        :class="['grid-item', item.boothStatus]"
      >
        <div class="item-name">{{ item.boothName }}</div>
        <div class="item-capacity">{{ item.boothCapacity }}</div>
        <!-- <div v-if="item.status === 'occupied'" class="item-details">
          <div class="item-price">¥{{ item.price }}</div>
          <div class="item-people">{{ item.people }}</div>
          <div class="item-time">{{ item.time }}</div>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getTableList } from '@/api/home'

// 桌台列表
const boothList = ref([]);

onMounted(() => {
  getTableList().then(res => {
    boothList.value = res.data;
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

