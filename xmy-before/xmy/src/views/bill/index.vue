<template>
    <div class="bill-container">
        <!-- 左侧区域 -->
        <div class="left-area">
            区域1
        </div>
        
        <!-- 右侧区域（包含中间和右侧） -->
        <div class="right-area">
            区域2
        </div>
    </div>
</template>

<script setup>

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
    background-color: white;
    border-radius: 8px 0 0 8px;
    margin-left: 3px;
    padding: 20px;
    box-sizing: border-box;
}
</style>