<template>
  <div class="dish-management">
    <!-- 搜索区域 -->
    <div class="search-section">
      <div class="search-item">
        <label class="search-label">菜品分类名称</label>
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入菜品分类名称"
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
      <el-button type="primary" plain @click="handleAddClick">
        <el-icon><Plus /></el-icon> 新增
      </el-button>
      <el-button type="success" plain :disabled="multipleSelection.length !== 1" @click="handleEditClick">
        <el-icon><Edit /></el-icon> 修改
      </el-button>
      <el-button type="danger" plain :disabled="multipleSelection.length === 0" @click="handleDeleteClick">
        <el-icon><Delete /></el-icon> 删除
      </el-button>
      
    </div>
    
    <!-- 表格区域 -->
    <div class="table-section">
      <el-table
        ref="dishTable"
        v-loading="loading"
        :data="tableData"
        border
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <!-- 复选框 -->
        <el-table-column
          type="selection"
          width="55"
          align="center"
        />
        <!-- 表格列渲染 -->
        <el-table-column
          v-for="column in tableColumns"
          :key="column.prop"
          :prop="column.prop"
          :label="column.label"
          :min-width="column.minWidth"
          align="center"
        >
          <!-- 序号类型 -->
          <template #default="scope" v-if="column.type === 'index'">
            {{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}
          </template>
          <!-- 日期时间类型 -->
          <template #default="scope" v-else-if="column.type === 'datetime'">
            {{ scope.row[column.prop] || scope.row.createTime }}
          </template>
          <!-- 默认类型 -->
          <template #default="scope" v-else>
            {{ scope.row[column.prop] }}
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

  <!-- 新增/修改菜品分类弹窗 -->
  <el-dialog
    v-model="dialogVisible"
    :title="dialogMode === 'add' ? '新增菜品分类' : '修改菜品分类'"
    width="500px"
    :before-close="handleClose"
  >
    <el-form
      :model="formData"
      :rules="formRules"
      ref="formRef"
      label-width="100px"
      class="dialog-form"
    >
      <el-form-item
        v-for="column in formColumns"
        :key="column.prop"
        :label="column.label"
        :prop="column.prop"
      >
        <!-- 输入框类型 -->
        <el-input
          v-if="column.form.type === 'input'"
          v-model="formData[column.prop]"
          :placeholder="column.form.placeholder"
          clearable
        />
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
import { getDishCategoryPageList, addDishCategory, updateDishCategory, deleteDishCategory } from '@/api/dish'
import { ElMessage, ElMessageBox } from 'element-plus'
import { tableColumns } from './columns'

// 响应式数据
// 表格数据
const tableData = ref([])
// 查询参数（包含分页参数）
const queryParams = ref({
  pageNum: 1,
  pageSize: 7,
  categoryName: ''
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

// 新增弹窗相关数据
const dialogVisible = ref(false)
// 弹窗模式：add - 新增，edit - 修改
const dialogMode = ref('add')
// 表单数据
const formData = ref({
  categoryName: '',
  categoryValue: ''
})
// 表单引用
const formRef = ref(null)
// 表单验证规则
const formRules = ref({})

// 计算属性：过滤出非隐藏的表单字段
const formColumns = computed(() => {
  return tableColumns.filter(column => column.form?.type !== 'hidden')
})

// 获取菜品分类列表数据
const getList = async () => {
  loading.value = true
  try {
    // 调用分页查询 API
    const response = await getDishCategoryPageList(queryParams.value)
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
  queryParams.value.pageNum = 1
  getList()
}

// 重置方法
const handleReset = () => {
  queryParams.value = {
    pageNum: 1,
    pageSize: 7,
    categoryName: ''
  }
  getList()
}

// 分页大小变化处理
const handleSizeChange = (size) => {
  queryParams.value.pageSize = size
  queryParams.value.pageNum = 1
  getList()
}

// 页码变化处理
const handleCurrentChange = (current) => {
  queryParams.value.pageNum = current
  getList()
}



// 处理表格选择变化
const handleSelectionChange = (selection) => {
  multipleSelection.value = selection
  selectedDish.value = selection.length === 1 ? selection[0] : null
}

// 新增按钮点击事件
const handleAddClick = () => {
  dialogMode.value = 'add'
  formData.value = {
    categoryName: '',
    categoryValue: ''
  }
  dialogVisible.value = true
}

// 修改按钮点击事件
const handleEditClick = () => {
  dialogMode.value = 'edit'
  formData.value = { ...multipleSelection.value[0] }
  dialogVisible.value = true
}

// 删除按钮点击事件
const handleDeleteClick = () => {
  ElMessageBox.confirm('确定要删除选中的菜品分类吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      // 调用删除API
      await deleteDishCategory(multipleSelection.value.map(item => item.id))
      ElMessage.success('删除成功')
      getList()
    } catch (error) {
      ElMessage.error('删除失败: ' + error)
    }
  }).catch(() => {
    // 取消删除
  })
}

// 保存按钮点击事件
const handleSave = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        // 调用新增或修改API
        if (dialogMode.value === 'add') {
          await addDishCategory(formData.value)
        } else {
          await updateDishCategory(formData.value)
        }
        ElMessage.success(dialogMode.value === 'add' ? '新增成功' : '修改成功')
        dialogVisible.value = false
        getList()
      } catch (error) {
        ElMessage.error(dialogMode.value === 'add' ? '新增失败: ' + error : '修改失败: ' + error)
      }
    }
  })
}

// 关闭弹窗事件
const handleClose = () => {
  dialogVisible.value = false
  if (formRef.value) {
    formRef.value.resetFields()
  }
}

// 页面挂载时获取数据
onMounted(async () => {
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

/* 弹窗表单样式 */
.dialog-form {
  padding: 16px 0;
}
</style>