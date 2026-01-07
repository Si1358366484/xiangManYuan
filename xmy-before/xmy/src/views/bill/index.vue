<template>
    <div class="bill-container">
        <!-- 左侧区域 -->
        <div class="left-area">
            <div class="left-area-left">
                <div class="left-area-left-top">订单详情</div>
                <div class="left-area-left-bottom">
                    <!-- 统计信息 -->
                    <div style="display: flex; flex-direction: column; align-items: flex-end;">
                        <span>共 3 项， 15 份</span>
                        <span style="font-size: 24px; font-weight: bold;">¥128</span>
                        <span style="font-size: 12px; text-decoration: line-through; color: #999;">¥133</span>
                    </div>
                    <!-- 操作按钮 -->
                    <div>
                        <!-- 当显示加菜视图时，显示下单和下单并结账按钮 -->
                        <template v-if="currentView === 'dish'">
                            <button @click="handleOrder">下单</button>
                            <button @click="handlePayBill">下单并结账</button>
                        </template>
                        <!-- 当显示结账视图时，显示加菜按钮 -->
                        <template v-else>
                            <button class="add-dish-btn" @click="handleAddDish">加菜</button>
                        </template>
                    </div>
                </div>
            </div>
            <div class="left-area-right">
                <h3>按钮区域</h3>
            </div>
        </div>
        <!-- 右侧区域 -->
        <div class="right-area">
            <DishComponent v-if="currentView === 'dish'" />
            <PayBillComponent v-if="currentView === 'pay'" />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
// 导入dish组件
import DishComponent from '@/components/dish/dish.vue'
import PayBillComponent from '@/components/payBill/index.vue'

// 当前显示的视图类型：'dish'表示加菜视图，'pay'表示结账视图
const currentView = ref('dish')

// 处理按钮点击事件
const handleAddDish = () => {
  currentView.value = 'dish'
}

const handlePayBill = () => {
  currentView.value = 'pay'
}

const handleOrder = () => {
  // 下单逻辑
  console.log('下单')
}
</script>

<style scoped>

.bill-container {
    display: flex;
    width: 100%;
    height: 100%;
    overflow: hidden;
    background-color: #2A2A2A;
}

.left-area {
    width: 30%;
    border-radius: 0 8px 8px 0;
    background-color: white;
    display: flex;
    overflow: hidden;
}

/* 左侧内部区域 - 垂直排列 */
.left-area-left {
    flex: 1;
    display: flex;
    flex-direction: column;
    overflow: hidden;
}

/* 订单详情区域 - 自适应高度 */
.left-area-left-top {
    flex: 1;
    overflow-y: auto;
    padding: 10px;
}

/* 结算区域 - 底部固定 */
.left-area-left-bottom {
    padding: 5px 10px 20px 10px;
    background-color: #f5f5f5;
    border-top: 1px solid #eee;
}

/* 结算区域内的div样式 */
.left-area-left-bottom > div {
    margin-bottom: 5px;
    font-size: 14px;
    color: #666;
}

/* 结算区域内的最后一个div（按钮容器） */
.left-area-left-bottom > div:last-child {
    display: flex;
    gap: 10px;
    margin-bottom: 0;
}

/* 按钮样式 */
.left-area-left-bottom button {
    flex: 1;
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    background-color: #fff;
    color: #333;
}

/* 右侧按钮区域 */
.left-area-right {
    padding: 15px;
    background-color: #f5f5f5;
    border-top: 1px solid #eee;
}

/* 右侧区域 */
.right-area {
    width: 70%;
    background-color: #2A2A2A;
    border-radius: 8px 0 0 8px;
    margin-left: 3px;
    box-sizing: border-box;
}
</style>