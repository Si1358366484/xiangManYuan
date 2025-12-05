import request from '@/utils/request'

// 获取桌台列表信息
export const getTableList = (query) => {
  return request({
    url: '/xmy/booth/list',
    method: 'get',
    params: query
  })
}