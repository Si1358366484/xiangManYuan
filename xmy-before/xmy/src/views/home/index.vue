<template>
  <!-- 卡座页 -->
  <div class="home-content">
    <!-- 网格布局 -->
    <div class="grid-container">
      <div 
        v-for="(item, index) in boothList" 
        :key="index"
        :class="['grid-item', item.boothStatus]"
        @click="changeBoothStatus(item)"
      >
        <!-- 空闲状态展示 -->
        <div v-if="item.boothStatus !== '0'" class="empty-content">
          <div class="item-name">{{ item.boothName }}</div>
          <div class="item-message">
            <div class="item-capacity-wrapper">
              <img src="@/assets/images/home/people.png" alt="人数" class="item-capacity-icon">
              <span class="item-capacity">{{ item.boothCapacity }}人</span>
            </div>
          </div>
        </div>
        
        <!-- 占用状态展示 -->
        <div v-if="item.boothStatus === '0'" class="occupied-content">
          <div class="item-name">{{ item.boothName }}</div>
          <div class="item-money">￥{{ item.boothPrice }}</div>
          <div class="item-message">
            <div class="item-time-wrapper">
              <img src="@/assets/images/home/time.png" alt="时间" class="item-capacity-icon">
              <span class="item-time">{{ item.boothTime }}分钟</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 状态统计栏 -->
    <div class="status-bar">
      <div class="status-item">
        <span class="status-icon status-icon-all"></span>
        <span class="status-label">全部</span>
        <span class="status-count">({{ boothStatusCount.total }})</span>
      </div>
      <div class="status-item">
        <span class="status-icon status-icon-free"></span>
        <span class="status-label">空桌台</span>
        <span class="status-count">({{ boothStatusCount.freeCount }})</span>
      </div>
      <div class="status-item">
        <span class="status-icon status-icon-occupy"></span>
        <span class="status-label">待结账</span>
        <span class="status-count">({{ boothStatusCount.occupyCount }})</span>
      </div>
      <div class="status-item">
        <span class="status-icon status-icon-reserve"></span>
        <span class="status-label">已预结</span>
        <span class="status-count">({{ boothStatusCount.reserveCount }})</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, inject, watch } from 'vue'
import { useRouter } from 'vue-router'
import { getTableList, updateBoothInfo } from '@/api/home'
import { ElMessage, ElMessageBox } from 'element-plus'

// 从父组件 Layout 接收卡座类型、搜索文本和搜索关键词
const boothType = inject('boothType', ref('全部'))
const searchKeyword = inject('searchKeyword', ref(''))

// 初始化路由
const router = useRouter()

// 桌台列表（原始数据）
const boothList = ref([]);

// 状态统计
const boothStatusCount = ref({
  total: 0,
  freeCount: 0,
  occupyCount: 0,
  reserveCount: 0
});

// 处理列表数据更新
const handleListData = (res) => {
  boothList.value = res.data.boothList || [];
  boothStatusCount.value = res.data.boothStatusCount || {
    total: 0,
    freeCount: 0,
    occupyCount: 0,
    reserveCount: 0
  };
};

//创建搜索函数，接收搜索关键词和卡座类型，调用getTableList接口，更新boothList数据
const searchBooth = (searchKeyword, boothType) => {
  // 卡座类型映射：前端名称 -> 后端数字编码
  const typeMapping = {
    '大厅': 1,
    '包间': 2,
    '阳台': 3
  }
  // 当前筛选参数
  const currentFilterParams = boothType === '全部' ? {} : { boothType: typeMapping[boothType] };
  // 构建搜索参数
  const searchText = searchKeyword ? { boothName: searchKeyword } : {};
  // 合并参数
  const params = { ...currentFilterParams, ...searchText };
  getTableList(params).then(res => {
    handleListData(res);
  }).catch(error => {
    console.error('搜索失败:', error);
    ElMessage.error('搜索失败，请稍后重试');
  })
}

onMounted(() => {
  searchBooth(searchKeyword.value, boothType.value)
})

// 监听 boothType 和 searchKeyword 变化
watch([boothType, searchKeyword], () => {
  searchBooth(searchKeyword.value, boothType.value)
})

// 改变卡座状态
const changeBoothStatus = async (item) => {
  if (item.boothStatus === '0') {
    router.push('/bill')
    return
  }
  // 开台确认弹窗语句
  const confirmMessage = `确定要为【${item.boothName}】执行开台操作吗？`
  try {
    // 使用 Element Plus 的确认对话框
    await ElMessageBox.confirm(confirmMessage, '操作确认', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
      customClass: 'booth-operation-dialog'
    })
    // 构建参数：包含卡座ID和新的状态（开台：设置为占用状态'0'）
    const params = { 
      id: item.id,
      boothStatus: '0' 
    }
    await updateBoothInfo(params)
    searchBooth(searchKeyword.value, boothType.value)
    ElMessage.success('操作成功')
  } catch (error) {
    if (error === 'cancel') {
      // 用户取消操作
      ElMessage.info('已取消操作')
    } else {
      // 其他错误
      ElMessage.error('操作失败: ' + error)
    }
  }
}
</script>

<style scoped>
.home-content {
  width: 100%;
  height: 100%;
  overflow-y: auto;
  position: relative;
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
  aspect-ratio: 1.8;
  border-radius: 4px;
  padding: 8px 8px 0 8px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  min-width: 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
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
  position: relative;
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

/* 价格 - 左上角，名称下方 */
.item-money {
  font-size: 14px;
  color: #333333;
  line-height: 1.2;
  margin-top: 4px;
}

.grid-item[class*=" 0"] .item-money,
.grid-item.occupied .item-money {
  color: #ffffff;
}

/* 时间信息容器 - 右下角 */
.item-time-wrapper {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 14px;
  color: #333333;
  justify-content: flex-end;
}

.grid-item[class*=" 0"] .item-time-wrapper,
.grid-item.occupied .item-time-wrapper {
  color: #333333;
}

/* 时间文字 */
.item-time {
  font-size: 14px;
  font-weight: 500;
  line-height: 1;
  color: #333333;
}

.grid-item[class*=" 0"] .item-time,
.grid-item.occupied .item-time {
  color: #ffffff;
}

/* 信息容器 - 占据下半部分 */
.item-message {
  margin-top: auto;
  padding: 8px;
  background-color: #e6e6e6;
  border-radius: 0 0 4px 4px;
  width: calc(100% + 18px);
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-left: -8px;
  margin-right: -8px;
  margin-bottom: 0;
  padding-left: 8px;
  padding-right: 8px;
  padding-bottom: 9px;
}

.grid-item[class*=" 0"] .item-message,
.grid-item.occupied .item-message {
  background-color: rgba(0, 0, 0, 0.05);
}

/* 容量信息容器 */
.item-capacity-wrapper {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 14px;
  color: #333333;
}

.grid-item[class*=" 0"] .item-capacity-wrapper,
.grid-item.occupied .item-capacity-wrapper {
  color: #333333;
}

/* 容量图标 */
.item-capacity-icon {
  width: 16px;
  height: 16px;
  object-fit: contain;
  display: inline-block;
  flex-shrink: 0;
}

/* 容量文字 */
.item-capacity {
  font-size: 14px;
  font-weight: 500;
  line-height: 1;
  color: #333333;
}

.grid-item[class*=" 0"] .item-capacity,
.grid-item.occupied .item-capacity {
  color: #333333;
}

/* 状态统计栏 */
.status-bar {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 24px;
  padding: 12px 24px;
  flex-wrap: wrap;
  background-color: #ffffff;
  border-radius: 4px;
  max-width: fit-content;
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 100;
}

.status-item {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  color: #333333;
}

.status-icon {
  width: 12px;
  height: 12px;
  display: inline-block;
  border-radius: 2px;
  flex-shrink: 0;
}

.status-icon-all {
  background-color: #000000;
}

.status-icon-free {
  background-color: #ffffff;
  border: 1px solid #e0e0e0;
}

.status-icon-occupy {
  background-color: #dc3545;
}

.status-icon-reserve {
  background-color: #ff9800;
}

.status-label {
  font-weight: 500;
}

.status-count {
  color: #666666;
}
</style>

