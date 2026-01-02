// 菜品管理表格列配置
export const tableColumns = [
  {
    prop: 'dishName',
    label: '菜品名称',
    minWidth: 150
  },
  {
    prop: 'categoryId',
    label: '菜品分类',
    minWidth: 120,
    type: 'category'
  },
  {
    prop: 'dishPrice',
    label: '售价',
    minWidth: 100,
    type: 'price'
  },
  {
    prop: 'dbstatus',
    label: '售卖状态',
    minWidth: 120,
    type: 'status'
  },
  {
    prop: 'updateTime',
    label: '最后操作时间',
    minWidth: 180,
    type: 'datetime'
  }
]
