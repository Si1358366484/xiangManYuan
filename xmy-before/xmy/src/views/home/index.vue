<template>
  <div class="home-content">
    <!-- 网格布局 -->
    <div class="grid-container">
      <div 
        v-for="(item, index) in boothList" 
        :key="index"
        :class="['grid-item', item.boothStatus]"
      >
        <!-- 空闲状态展示 -->
        <div v-if="item.boothStatus !== '0'" class="empty-content">
          <div class="item-name">{{ item.boothName }}</div>
          <div class="item-capacity-wrapper">
            <span class="item-capacity">{{ item.boothCapacity }}人</span>
          </div>
        </div>
        
        <!-- 占用状态展示 -->
        <div v-if="item.boothStatus === '0'" class="occupied-content">
          <div class="item-name">{{ item.boothName }}</div>
          <div class="item-capacity-wrapper">
            <span class="item-capacity">{{ item.boothCapacity }}人</span>
          </div>
          <div class="item-details">
            <div class="item-price">¥{{ item.boothPrice }}</div>
            <div class="item-people">{{ item.boothPeople }}</div>
            <div class="item-time">{{ item.boothTime }}</div>
          </div>
        </div>
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
  overflow-y: auto;
}

/* 网格布局样式 - 一行7个 */
.grid-container {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 4px;
  padding: 4px;
  width: 100%;
  box-sizing: border-box;
}

/* 网格项基础样式 */
.grid-item {
  aspect-ratio: 1.7;
  border-radius: 4px;
  padding: 8px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  min-width: 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* 空闲状态 - 白色背景（默认样式） */
.grid-item {
  background-color: #ffffff;
  border: 2px solid #e0e0e0;
  color: #333333;
}

.grid-item:hover {
  border-color: #4a90e2;
  box-shadow: 0 4px 12px rgba(74, 144, 226, 0.3);
}

/* 占用状态 - 红色背景（boothStatus为'0'时） */
.grid-item[class*=" 0"],
.grid-item.occupied {
  background-color: #dc3545;
  border: 2px solid #c82333;
  color: #ffffff;
}

.grid-item[class*=" 0"]:hover,
.grid-item.occupied:hover {
  border-color: #ff6b7a;
  box-shadow: 0 4px 12px rgba(220, 53, 69, 0.4);
}

/* 内容容器 */
.empty-content,
.occupied-content {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* 卡座名称 - 左上角 */
.item-name {
  font-size: 20px;
  font-weight: 600;
  color: #333333;
  line-height: 1.2;
  margin: 0;
  align-self: flex-start;
}

.grid-item[class*=" 0"] .item-name,
.grid-item.occupied .item-name {
  color: #ffffff;
}

/* 容量信息容器 - 左下角 */
.item-capacity-wrapper {
  display: flex;
  align-items: center;
  gap: 6px;
  margin-top: auto;
  align-self: flex-start;
  color: #000000;
  font-size: 14px;
}

.grid-item[class*=" 0"] .item-capacity-wrapper,
.grid-item.occupied .item-capacity-wrapper {
  color: rgba(255, 255, 255, 0.9);
}

/* 容量文字 */
.item-capacity {
  font-size: 14px;
  font-weight: 500;
  line-height: 1;
}

/* 占用状态详情 */
.item-details {
  margin-top: 12px;
  padding-top: 12px;
  border-top: 1px solid rgba(255, 255, 255, 0.3);
  font-size: 12px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.item-price {
  font-weight: 600;
  font-size: 14px;
}

.item-people,
.item-time {
  opacity: 0.9;
  font-size: 12px;
}
</style>

