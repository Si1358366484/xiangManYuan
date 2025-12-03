import request from '@/utils/request'

// 获取桌台列表信息
export const getTableList = () => {
  return request({
    url: '/xmy/booth/list',
    method: 'get'
  })
}