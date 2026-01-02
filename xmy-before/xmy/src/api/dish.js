import request from '@/utils/request'

// 获取菜品分类列表
export const getDishCategoryList = () => {
  return request({
    url: '/xmy/dishCategory/list',
    method: 'get'
  })
}

// 获取菜品列表
export const getDishList = (params) => {
  return request({
    url: '/xmy/dish/list',
    method: 'get',
    params
  })
}

// 获取所有菜品列表（专用）
export const getDishListAll = (params) => {
  return request({
    url: '/xmy/dish/list/all',
    method: 'get',
    params
  })
}
