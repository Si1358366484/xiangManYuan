import request from '@/utils/request'

// 获取菜品分类列表
export const getDishCategoryList = () => {
  return request({
    url: '/xmy/dishCategory/list',
    method: 'get'
  })
}
