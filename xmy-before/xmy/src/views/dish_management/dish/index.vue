<template>
  <div class="dish-management">
    <!-- 搜索区域 -->
    <div class="search-section">
      <div class="search-item">
        <label class="search-label">菜品名称</label>
        <el-input
          placeholder="请输入菜品名称"
          class="search-input"
        />
      </div>
      <div class="search-buttons">
        <el-button type="primary">搜索</el-button>
        <el-button type="default">重置</el-button>
      </div>
    </div>
    
    <!-- 按钮操作区域 -->
    <div class="button-group">
      <el-button type="primary" plain>
        <i class="el-icon-plus"></i> 新增
      </el-button>
      <el-button type="success" plain>
        <i class="el-icon-edit"></i> 修改
      </el-button>
      <el-button type="danger" plain>
        <i class="el-icon-delete"></i> 删除
      </el-button>
    </div>
    
    <!-- 表格区域 -->
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      fit
      header-cell-class-name="table-header"
      row-class-name="table-row"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        prop="name"
        label="菜品名称"
        width="180"
      />
      <el-table-column
        prop="category"
        label="菜品分类"
        width="150"
      />
      <el-table-column
        prop="price"
        label="售价"
        width="120"
        align="right"
      >
        <template #default="scope">
          ¥{{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="售卖状态"
        width="140"
      >
        <template #default="scope">
          <el-tag
            :type="scope.row.status === '启售' ? 'success' : 'danger'"
            size="small"
          >
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="lastOperationTime"
        label="最后操作时间"
      />
    </el-table>
  </div>
</template>

<style scoped>
.dish-management {
  padding: 16px;
  background-color: #ffffff;
  min-height: 100%;
  box-sizing: border-box;
}

/* 搜索区域样式 */
.search-section {
  display: flex;
  align-items: center;
  margin-bottom: 0;
  padding: 8px 16px;
  background-color: #ffffff;
  border-radius: 0;
  border: 1px solid #e4e7ed;
  border-bottom: none;
}

.search-item {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.search-label {
  font-size: 14px;
  color: #303133;
  margin-right: 8px;
  white-space: nowrap;
  font-weight: bold;
}

.search-input {
  width: 200px;
  border-radius: 4px;
}

.search-buttons {
  display: flex;
  gap: 10px;
}

/* 按钮操作区域 */
.button-group {
  margin-bottom: 0;
  display: flex;
  gap: 8px;
  padding: 12px 16px;
  background-color: #ffffff;
  border: 1px solid #e4e7ed;
  border-bottom: none;
}

/* 按钮样式调整 */
:deep(.el-button) {
  margin-right: 8px;
  border-radius: 4px;
}

:deep(.el-button--primary.is-plain) {
  color: #409eff;
  border-color: #d9ecff;
  background: #ecf5ff;
}

:deep(.el-button--success.is-plain) {
  color: #67c23a;
  border-color: #e1f3d8;
  background: #f0f9eb;
}

:deep(.el-button--danger.is-plain) {
  color: #f56c6c;
  border-color: #fbc4c4;
  background: #fef0f0;
}

/* 表格样式调整 */
:deep(.el-table) {
  border-radius: 0;
  overflow: hidden;
  border: 1px solid #e4e7ed;
}

:deep(.el-table__header-wrapper .el-table__header) {
  background-color: #f0f2f5;
}

:deep(.el-table__header-wrapper th) {
  background-color: #f0f2f5;
  font-weight: bold;
  color: #303133;
  text-align: center;
  border-bottom: 1px solid #e4e7ed;
}

:deep(.el-table__body-wrapper td) {
  text-align: center;
  border-bottom: 1px solid #ebeef5;
}

:deep(.el-table__body-wrapper tr:hover > td) {
  background-color: #f5f7fa;
}

:deep(.el-table__row:nth-child(even)) {
  background-color: #fafafa;
}

:deep(.el-table__row:nth-child(odd)) {
  background-color: #ffffff;
}
</style>

<script setup>
// 模拟数据
const tableData = [
  {
    name: '测试菜品',
    category: '辣味烤鱼',
    price: 38,
    status: '停售',
    lastOperationTime: '2022-09-20 19:11'
  },
  {
    name: '平锅草鱼汤',
    category: '汤类',
    price: 6,
    status: '启售',
    lastOperationTime: '2022-06-10 10:55'
  },
  {
    name: '鸡蛋汤',
    category: '汤类',
    price: 4,
    status: '启售',
    lastOperationTime: '2022-06-10 10:54'
  },
  {
    name: '烤鱼2斤',
    category: '辣味烤鱼',
    price: 72,
    status: '启售',
    lastOperationTime: '2022-06-10 10:43'
  },
  {
    name: '江团鱼2斤',
    category: '辣味烤鱼',
    price: 119,
    status: '启售',
    lastOperationTime: '2022-06-10 10:42'
  },
  {
    name: '草鱼鱼2斤',
    category: '辣味烤鱼',
    price: 68,
    status: '启售',
    lastOperationTime: '2022-06-10 10:41'
  },
  {
    name: '馋嘴牛蛙',
    category: '辣味牛蛙',
    price: 68,
    status: '启售',
    lastOperationTime: '2022-06-10 10:37'
  },
  {
    name: '香辣牛蛙',
    category: '辣味牛蛙',
    price: 88,
    status: '启售',
    lastOperationTime: '2022-06-10 10:35'
  }
];
</script>