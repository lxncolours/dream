import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import filter from './filter/filter'

Vue.use(ElementUI)

Vue.config.productionTip = false

Vue.prototype.$ajax = axios

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true
/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log('请求：', config)
  const token = Tool.getLoginUser().token
  if (Tool.isNotEmpty(token)) {
    config.headers.token = token
  }
  return config
})

axios.interceptors.response.use(function (response) {
  console.log('返回结果：', response)
  return response
}, error => {
  console.log('返回拦截：', error.response)
  const response = error.response
  return response
})

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
})

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    return item.meta.loginRequire
  })) {
    const loginUser = Tool.getLoginUser()
    if (Tool.isEmpty(loginUser)) {
      next('/login')
    } else {
      next()
    }
  } else {
    next()
  }
})

new Vue({
  router,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')
