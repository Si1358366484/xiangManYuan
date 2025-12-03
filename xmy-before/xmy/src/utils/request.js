import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

// 创建axios实例
const service = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 从localStorage获取loginUser并提取token
    let loginUser = JSON.parse(localStorage.getItem('loginUser'))
    if (loginUser) {
      config.headers.token = loginUser.token
    }
    return config
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    // 成功回调
    return response.data
  },
  (error) => {
    // 失败回调
    if (error.response && error.response.status === 401) {
      // 如果响应的状态码为401, 则路由到登录页面
      ElMessage.error('登录失效, 请重新登录')
      router.push('/login')
    } else {
      ElMessage.error('接口访问异常')
    }
    return Promise.reject(error) // 返回错误信息
  }
)

// 导出原始的axios实例，以便特殊需求使用
export default service
