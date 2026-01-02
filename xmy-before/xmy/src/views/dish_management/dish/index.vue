<template>
  <div class="dish-management">
    <!-- 搜索区域 -->
    <div class="search-section">
      <div class="search-item">
        <label class="search-label">菜品名称</label>
        <el-input
          v-model="queryParams.name"
          placeholder="请输入菜品名称"
          class="search-input"
          clearable
          @keyup.enter="handleSearch"
        />
      </div>
      <div class="search-buttons">
        <el-button type="primary" @click="handleSearch">搜索</el-button>
        <el-button type="default" @click="handleReset">重置</el-button>
      </div>
    </div>
    
    <!-- 按钮操作区域 -->
    <div class="button-group">
      <el-button type="primary" plain>
        <el-icon><Plus /></el-icon> 新增
      </el-button>
      <el-button type="success" plain>
        <el-icon><Edit /></el-icon> 修改
      </el-button>
      <el-button type="danger" plain>
        <el-icon><Delete /></el-icon> 删除
      </el-button>
      <el-button type="warning" plain>
        <el-icon><RefreshRight /></el-icon> 状态切换
      </el-button>
    </div>
    
    <!-- 表格区域 -->
    <div class="table-section">
      <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%"
        border
        fit
        ref="dishTable"
        @row-click="handleRowClick"
        @selection-change="handleSelectionChange"
        highlight-current-row
      >
        <el-table-column
          type="selection"
          width="55"
        />
        <el-table-column
          prop="dishName"
          label="菜品名称"
          width="180"
        />
        <!-- <el-table-column
          prop="categoryId"
          label="菜品分类"
          width="150"
        /> -->
        <el-table-column
          prop="dishPrice"
          label="售价"
          width="120"
          align="right"
        >
          <template #default="scope">
            ¥{{ scope.row.dishPrice }}
          </template>
        </el-table-column>
        <el-table-column
          prop="dbstatus"
          label="售卖状态"
          width="140"
        >
          <template #default="scope">
            <el-tag
              :type="scope.row.dbstatus === 1 ? 'success' : 'danger'"
              size="small"
            >
              {{ scope.row.dbstatus === 1 ? '启售' : '停售' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="updateTime"
          label="最后操作时间"
        >
          <template #default="scope">
            {{ scope.row.updateTime || scope.row.createTime }}
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 空数据提示 -->
      <el-empty
        v-if="!loading && tableData.length === 0"
        description="暂无数据"
        class="empty-tip"
      />
      
      <!-- 分页区域 -->
      <div class="pagination-section">
        <el-pagination
          v-model:current-page="queryParams.pageNum"
          v-model:page-size="queryParams.pageSize"
          :page-sizes="[2, 3, 5, 7]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
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
  padding: 8px 16px;
  background-color: #ffffff;
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
  display: flex;
  gap: 8px;
  padding: 12px 16px;
  background-color: #ffffff;
  border: 1px solid #e4e7ed;
  border-bottom: none;
}

/* 表格区域样式 */
.table-section {
  background-color: #ffffff;
  border: 1px solid #e4e7ed;
}

/* 空数据提示样式 */
.empty-tip {
  padding: 60px 0;
}

/* 分页区域样式 */
.pagination-section {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding: 12px 16px;
  background-color: #ffffff;
  border-top: 1px solid #e4e7ed;
}

/* 按钮样式调整 */
:deep(.el-button) {
  margin-right: 8px;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  vertical-align: middle;
}

:deep(.el-button span) {
  display: flex;
  align-items: center;
  justify-content: center;
}

:deep(.el-button .el-icon) {
  margin-right: 4px;
  vertical-align: middle;
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
  border: none;
}

:deep(.el-table__header-wrapper th) {
  background-color: #f0f2f5;
  font-weight: 500;
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
</style>

<script setup>
import { ref, onMounted } from 'vue'
import { Plus, Edit, Delete, RefreshRight } from '@element-plus/icons-vue'
import { getDishList } from '@/api/dish'
import { ElMessage } from 'element-plus'

// 响应式数据
// 表格数据
const tableData = ref([])
// 查询参数（包含分页参数）
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  name: ''
})
// 总记录数
const total = ref(0)
// 加载状态
const loading = ref(false)
// 表格引用
const dishTable = ref(null)
// 选择状态
const multipleSelection = ref([])
const selectedDish = ref(null)

// 获取菜品列表数据
const getList = async () => {
  loading.value = true
  try {
    // 调用分页查询 API
    const response = await getDishList(queryParams.value)
    // 直接使用后端返回的rows和total，与表格字段一一对应
    tableData.value = response.rows || []
    total.value = response.total || 0
  } catch (error) {
    ElMessage.error('获取数据失败: ' + error)
    tableData.value = []
    total.value = 0
  } finally {
    loading.value = false
  }
}

// 搜索方法
const handleSearch = () => {
  // 搜索时重置到第一页
  queryParams.value.pageNum = 1
  getList()
}

// 重置方法
const handleReset = () => {
  // 重置查询参数
  queryParams.value = {
    pageNum: 1,
    pageSize: queryParams.value.pageSize,
    name: ''
  }
  getList()
}

// 分页大小变化
const handleSizeChange = (val) => {
  queryParams.value.pageSize = val
  queryParams.value.pageNum = 1
  getList()
}

// 当前页码变化
const handleCurrentChange = (val) => {
  queryParams.value.pageNum = val
  getList()
}

// 行点击事件处理
const handleRowClick = (row) => {
  // 选中当前行
  dishTable.value.toggleRowSelection(row, true)
}

// 选择变化事件处理
const handleSelectionChange = (selection) => {
  multipleSelection.value = selection
  selectedDish.value = selection.length > 0 ? selection[selection.length - 1] : null
}

// 页面挂载时获取数据
onMounted(() => {
  getList()
})
</script>