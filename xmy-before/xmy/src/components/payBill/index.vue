<template>
  <div class="pay-bill-container">
    <!-- 左侧区域 -->
    <div class="pay-left-area">
        <div class="pay-left-top">
            <div class="discount-section">
                <h3 class="discount-title">选择优惠</h3>
                <div class="discount-options">
                    <button class="discount-btn">订单打折</button>
                    <button class="discount-btn">订单减免</button>
                    <button class="discount-btn active">免单</button>
                </div>
            </div>
        </div>
        <div class="pay-left-bottom">
            <div class="discount-section">
                <h3 class="discount-title">选择支付方式</h3>
            </div>
        </div>
    </div>
    <!-- 右侧区域 -->
    <div class="pay-right-area">区域B</div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.pay-bill-container {
    display: flex;
    width: 100%;
    height: 100%;
    overflow: hidden;
}

.pay-left-area {
    width: 50%;
    background-color: white;
    border-right: 1px solid black;
    border-radius: 8px 0 0 8px;
    padding: 20px;
    box-sizing: border-box;
}

.pay-right-area {
    width: 50%;
    background-color: white;
    padding: 20px;
    box-sizing: border-box;
}

.discount-section {
    margin-bottom: 20px;
}

.discount-title {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    color: #333;
}

.discount-options {
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
}

.discount-btn {
    flex: 1;
    min-width: 120px;
    padding: 12px 20px;
    background-color: #f5f5f5;
    border: 1px solid #ddd;
    border-radius: 8px;
    font-size: 14px;
    color: #666;
    cursor: pointer;
    transition: all 0.2s ease;
}

.discount-btn:hover {
    background-color: #e8e8e8;
    border-color: #ccc;
}

.discount-btn.active {
    background-color: #e8f0fe;
    border-color: #2196f3;
    color: #1976d2;
    font-weight: bold;
}
</style>