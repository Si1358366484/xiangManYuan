<template>
    <div class="bill-container">
        <!-- 左侧区域 -->
        <div class="left-area">
            区域1
        </div>
        <!-- 右侧区域 -->
        <div class="right-area">
            <DishComponent />
        </div>
    </div>
</template>

<script setup>
// 导入dish组件
import DishComponent from '@/components/dish/dish.vue'
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
    background-color: white;
    border-radius: 0 8px 8px 0;
    margin-right: 3px;
    padding: 20px;
    box-sizing: border-box;
}

.right-area {
    width: 70%;
    background-color: #2A2A2A;
    border-radius: 8px 0 0 8px;
    margin-left: 3px;
    box-sizing: border-box;
}
</style>