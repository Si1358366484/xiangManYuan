import request from '@/utils/request'

// 获取菜品分类列表
export const getDishCategoryList = () => {
  return request({
    url: '/xmy/dishCategory/list',
    method: 'get'
  })
}

// 获取菜品分类列表（分页）
export const getDishCategoryPageList = (params) => {
  return request({
    url: '/xmy/dishCategory/pageList',
    method: 'get',
    params
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

// 删除菜品分类
export function deleteDishCategory(ids) {
  const idString = Array.isArray(ids) ? ids.join(',') : ids
  return request({
    url: "/xmy/dishCategory/" + idString,
    method: "delete",
  });
}

// 更新菜品分类
export const updateDishCategory = (data) => {
  return request({
    url: '/xmy/dishCategory',
    method: 'put',
    data: data
  })
}

// 新增菜品分类
export const addDishCategory = (data) => {
  return request({
    url: '/xmy/dishCategory',
    method: 'post',
    data: data
  })
}

// 删除菜品
export function deleteDish(ids) {
  const idString = Array.isArray(ids) ? ids.join(',') : ids
  return request({
    url: "/xmy/dish/" + idString,
    method: "delete",
  });
}

// 更新菜品
export const updateDish = (data) => {
  return request({
    url: '/xmy/dish',
    method: 'put',
    data: data
  })
}

// 新增菜品
export const addDish = (data) => {
  return request({
    url: '/xmy/dish',
    method: 'post',
    data: data
  })
}
