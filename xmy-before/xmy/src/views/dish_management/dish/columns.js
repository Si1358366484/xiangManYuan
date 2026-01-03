// 菜品管理表格列配置
export const tableColumns = [
  {
    prop: 'dishName',
    label: '菜品名称',
    minWidth: 150,
    form: {
      type: 'input',
      required: true,
      placeholder: '请输入菜品名称'
    }
  },
  {
    prop: 'categoryId',
    label: '菜品分类',
    minWidth: 120,
    type: 'category',
    form: {
      type: 'select',
      required: true,
      placeholder: '请选择菜品分类'
    }
  },
  {
    prop: 'dishPrice',
    label: '售价',
    minWidth: 100,
    type: 'price',
    form: {
      type: 'input',
      required: true,
      placeholder: '请输入菜品价格'
    }
  },
  {
    prop: 'salesStatus',
    label: '售卖状态',
    minWidth: 120,
    type: 'status',
    form: {
      type: 'radio',
      required: true,
      options: [
        { label: '启售', value: 1 },
        { label: '停售', value: 0 }
      ]
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
