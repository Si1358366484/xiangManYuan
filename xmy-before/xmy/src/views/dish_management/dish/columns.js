// 菜品管理表格列配置
export const tableColumns = [
  {
    prop: 'dishName',
    label: '菜品名称',
    width: 180
  },
  {
    prop: 'categoryId',
    label: '菜品分类',
    width: 140
  },
  {
    prop: 'dishPrice',
    label: '售价',
    width: 120,
    type: 'price'
  },
  {
    prop: 'dbstatus',
    label: '售卖状态',
    width: 140,
    type: 'status'
  },
  {
    prop: 'updateTime',
    label: '最后操作时间',
    type: 'datetime'
  }
]
