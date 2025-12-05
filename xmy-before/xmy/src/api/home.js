import request from '@/utils/request'

// 获取桌台列表信息
export const getTableList = (query) => {
  return request({
    url: '/xmy/booth/list',
    method: 'get',
    params: query
  })
}
// 修改卡座信息
export const updateBoothInfo = (query) => {
  return request({
    url: '/xmy/booth',
    method: 'put',
    params: query
  })
}