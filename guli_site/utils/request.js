import axios from 'axios'
import { Message } from 'element-ui'
import cookie from 'js-cookie'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:9110',
  timeout: 12000 // 请求超时时间
})

// http request 拦截器
service.interceptors.request.use(
  config => {
    // 如果cookie中 包含guli_token
    // 则发送后端api请求的时候携带token
    var token = cookie.get("jwt_token") 
    if(token) {
      config.headers['token'] = token
    }

    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    /**
       * code为非20000是抛错 可结合自己业务进行修改
       */
    const res = response.data

    if (res.code == 20000) {
      return response.data
    } else if (res.code == 23004) { // 获取用户信息失败
      //  清楚cookie中的token
      cookie.set("jwt_token",'',{domain:'localhost'})
    } else if (res.code === 25000) { // 支付中
      return response.data // 不显示错误信息
    } else if(res.code === 28004) { // 鉴权失败
      window.location.href = '/login'
      return
    } else {
      Message({
        message: res.message || 'error',
        type: 'error',
        duration: 5 * 1000
      })
    }
  },
  error => {
    console.log('err：' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
