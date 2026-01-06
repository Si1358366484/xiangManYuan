<template>
  <div class="dish-management">
    <!-- 搜索区域 -->
    <div class="search-section">
      <div class="search-item">
        <label class="search-label">菜品名称</label>
        <el-input
          v-model="queryParams.dishName"
          placeholder="请输入菜品名称"
          class="search-input"
          clearable
          @keyup.enter="handleSearch"
        />
      </div>
      <div class="search-item">
        <label class="search-label">菜品分类</label>
        <el-select
          v-model="queryParams.categoryValue"
          placeholder="请选择菜品分类"
          class="search-input"
          clearable
        >
          <el-option
            v-for="(categoryName, categoryValue) in categoryDict"
            :key="categoryValue"
            :label="categoryName"
            :value="categoryValue.toString()"
          />
        </el-select>
      </div>
      <div class="search-buttons">
        <el-button type="primary" @click="handleSearch">搜索</el-button>
        <el-button type="default" @click="handleReset">重置</el-button>
      </div>
    </div>
    
    <!-- 按钮操作区域 -->
    <div class="button-group">
      <el-button type="primary" plain @click="handleAddClick">
        <el-icon><Plus /></el-icon> 新增
      </el-button>
      <el-button type="success" plain :disabled="multipleSelection.length !== 1" @click="handleEditClick">
        <el-icon><Edit /></el-icon> 修改
      </el-button>
      <el-button type="danger" plain :disabled="multipleSelection.length === 0" @click="handleDelete">
        <el-icon><Delete /></el-icon> {{ multipleSelection.length > 1 ? '批量删除' : '删除' }}
      </el-button>
      <el-button type="warning" plain :disabled="multipleSelection.length !== 1" @click="handleStatusChange">
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
          v-for="column in tableColumns"
          :key="column.prop"
          :prop="column.prop"
          :label="column.label"
          :min-width="column.minWidth"
        >
          <template #default="scope">
            <!-- 价格类型 -->
            <template v-if="column.type === 'price'">
              ¥{{ scope.row[column.prop] }}
            </template>
            <!-- 状态类型 -->
            <template v-else-if="column.type === 'status'">
              <el-tag
                :type="scope.row[column.prop] === 1 ? 'success' : 'danger'"
                size="small"
              >
                {{ scope.row[column.prop] === 1 ? '启售' : '停售' }}
              </el-tag>
            </template>
            <!-- 日期时间类型 -->
            <template v-else-if="column.type === 'datetime'">
              {{ scope.row[column.prop] || scope.row.createTime }}
            </template>
            <!-- 分类类型 -->
            <template v-else-if="column.type === 'category'">
              {{ getDictValue(categoryDict, scope.row[column.prop]) }}
            </template>
            <!-- 默认类型 -->
            <template v-else>
              {{ scope.row[column.prop] }}
            </template>
          </template>
        </el-table-column>
      </el-table>
      
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

  <!-- 新增/修改菜品弹窗 -->
  <el-dialog
    v-model="dialogVisible"
    :title="dialogMode === 'add' ? '新增菜品' : '修改菜品'"
    width="500px"
    :before-close="handleClose"
  >
    <el-form
      :model="formData"
      label-position="left"
      label-width="100px"
    >
      <el-form-item
        v-for="column in formColumns"
        :key="column.prop"
        :label="column.label"
        :required="column.form?.required"
      >
        <!-- 输入框类型 -->
        <el-input
          v-if="column.form?.type === 'input'"
          v-model="formData[column.prop]"
          :placeholder="column.form?.placeholder"
          :maxlength="column.form?.maxlength"
          show-word-limit
          style="width: 100%"
        />
        
        <!-- 下拉选择类型 -->
        <el-select
          v-else-if="column.form?.type === 'select'"
          v-model="formData[column.prop]"
          :placeholder="column.form?.placeholder"
          style="width: 100%"
        >
          <!-- 菜品分类选项 -->
        <template v-if="column.type === 'category'">
          <el-option
            v-for="(categoryName, categoryValue) in categoryDict"
            :key="categoryValue"
            :label="categoryName"
            :value="categoryValue.toString()"
          />
        </template>
        </el-select>
        
        <!-- 单选按钮类型 -->
        <el-radio-group
          v-else-if="column.form?.type === 'radio'"
          v-model="formData[column.prop]"
        >
          <el-radio
            v-for="option in column.form?.options"
            :key="option.value"
            :label="option.value"
          >
            {{ option.label }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <div style="display: flex; justify-content: flex-end; gap: 10px; width: 100%; padding: 10px 20px 20px;">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleSave">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { Plus, Edit, Delete, RefreshRight } from '@element-plus/icons-vue'
import { getDishList, getDishCategoryList, deleteDish, updateDish, addDish } from '@/api/dish'
import { ElMessage, ElMessageBox } from 'element-plus'
import { tableColumns } from './columns'
import { createDict, getDictValue } from '@/utils/dict'

// 响应式数据
// 表格数据
const tableData = ref([])
// 查询参数（包含分页参数）
const queryParams = ref({
  pageNum: 1,
  pageSize: 7
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
// 菜品分类数据
const categoryData = ref([])
// 菜品分类字典
const categoryDict = ref({})

// 新增弹窗相关数据
const dialogVisible = ref(false)
// 弹窗模式：add - 新增，edit - 修改
const dialogMode = ref('add')
// 表单数据
const formData = ref({
  dishName: '',
  categoryValue: '',
  dishPrice: '',
  salesStatus: 1
})

// 计算属性：过滤出非隐藏的表单字段
const formColumns = computed(() => {
  return tableColumns.filter(column => column.form?.type !== 'hidden')
})

// 获取菜品分类数据
const getCategoryList = async () => {
  try {
    const response = await getDishCategoryList()
    // 从 response.data 中获取分类数组
    categoryData.value = response.data
    // 创建分类字典，使用 categoryValue 作为键字段
    categoryDict.value = createDict(categoryData.value, 'categoryValue', 'categoryName')
  } catch (error) {
    ElMessage.error('获取菜品分类失败: ' + error)
    categoryData.value = []
    categoryDict.value = {}
  }
}

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
  // 收集搜索参数
  const searchParams = {}
  Object.keys(queryParams.value).forEach(key => {
    const value = queryParams.value[key]
    // 过滤掉undefined、空字符串、null的值
    if (value !== undefined && value !== '' && value !== null) {
      searchParams[key] = value
    }
  })
  
  // 重置页码为1，保留原有的分页大小
  queryParams.value = {
    pageNum: 1,
    pageSize: queryParams.value.pageSize,
    ...searchParams // 合并搜索参数
  }
  
  getList() // 重新获取列表数据
}

// 重置方法
const handleReset = () => {
  // 重置查询参数
  queryParams.value = {
    pageNum: 1,
    pageSize: queryParams.value.pageSize
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

// 删除处理函数
const handleDelete = async () => {
  // 构建确认消息
  const isBatch = multipleSelection.value.length > 1
  const confirmMessage = isBatch 
    ? `确定要删除选中的 ${multipleSelection.value.length} 个菜品吗？`
    : `确定要删除菜品【${multipleSelection.value[0].dishName}】吗？`
  try {
    // 显示确认对话框
    await ElMessageBox.confirm(confirmMessage, '删除确认', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'error'
    })
    // 构建删除ID数组
    const ids = multipleSelection.value.map(item => item.id)
    
    // 调用删除API
    await deleteDish(ids)
    
    // 刷新列表
    getList()
    
    // 显示成功消息
    ElMessage.success(isBatch ? '批量删除成功' : '删除成功')
  } catch (error) {
    // 如果是用户取消操作，不显示错误消息
    if (error !== 'cancel') {
      ElMessage.error('删除失败: ' + error)
    }
  }
}

// 状态切换处理函数
const handleStatusChange = async () => {
  try {
    // 获取当前选中的菜品
    const selectedDish = multipleSelection.value[0]
    // 切换状态
    const newStatus = selectedDish.salesStatus === 1 ? 0 : 1
    // 构建更新数据
    const updateData = {
      id: selectedDish.id,
      salesStatus: newStatus
    }
    // 调用更新API
    await updateDish(updateData)
    // 刷新列表
    getList()
    // 显示成功消息
    ElMessage.success('状态切换成功')
  } catch (error) {
    ElMessage.error('状态切换失败: ' + error)
  }
}

// 打开新增弹窗
const handleAddClick = () => {
  // 设置弹窗模式为新增
  dialogMode.value = 'add'
  // 重置表单数据
  formData.value = {
    dishName: '',
    categoryValue: '',
    dishPrice: '',
    salesStatus: 1
  }
  // 显示弹窗
  dialogVisible.value = true
}

// 打开修改弹窗
const handleEditClick = () => {
  // 获取当前选中的菜品
  const selectedDish = multipleSelection.value[0]
  if (selectedDish) {
    // 设置弹窗模式为修改
    dialogMode.value = 'edit'
    // 填充表单数据
    formData.value = {
      ...selectedDish,
      dishPrice: selectedDish.dishPrice.toString(),
      categoryValue: selectedDish.categoryValue.toString()
    }
    // 显示弹窗
    dialogVisible.value = true
  }
}

// 关闭弹窗
const handleClose = () => {
  // 隐藏弹窗
  dialogVisible.value = false
}

// 保存菜品（新增或修改）
const handleSave = async () => {
  try {
    // 准备提交数据，将dishPrice转换为数字
    const submitData = {
      ...formData.value,
      dishPrice: Number(formData.value.dishPrice),
      dbstatus: 1
    }
    
    // 根据弹窗模式调用不同的API
    if (dialogMode.value === 'add') {
      // 新增时添加dbstatus字段
      await addDish(submitData)
      ElMessage.success('新增菜品成功')
    } else {
      // 修改时调用更新API
      await updateDish(submitData)
      ElMessage.success('修改菜品成功')
    }
    
    // 关闭弹窗
    dialogVisible.value = false
    // 刷新列表
    getList()
  } catch (error) {
    ElMessage.error(dialogMode.value === 'add' ? '新增菜品失败: ' + error : '修改菜品失败: ' + error)
  }
}



// 页面挂载时获取数据
onMounted(async () => {
  // 先获取菜品分类数据
  await getCategoryList()
  // 再获取菜品列表数据
  getList()
})
</script>

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