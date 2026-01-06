// 菜品口味管理表格列配置
export const tableColumns = [
  {
    prop: 'index',
    label: '序号',
    minWidth: 80,
    type: 'index',
    form: {
      type: 'hidden'
    }
  },
  {
    prop: 'categoryName',
    label: '菜品分类名称',
    minWidth: 150,
    form: {
      type: 'input',
      placeholder: '请输入菜品分类名称'
    }
  },
  {
    prop: 'categoryValue',
    label: '菜品值',
    minWidth: 100,
    form: {
      type: 'input',
      placeholder: '请输入菜品值'
    }
  },
  {
    prop: 'updateTime',
    label: '最后操作时间',
    minWidth: 180,
    type: 'datetime',
    form: {
      type: 'hidden'
    }
  }
]