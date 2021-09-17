import Vue from 'vue'
import VueRouter from 'vue-router'
import Admin from '../views/admin.vue'
import Login from '../views/login.vue'
import Welcome from '../views/admin/welcome.vue'
import User from '../views/admin/user.vue'
import Customer from '../views/admin/customer.vue'
import Category from '../views/admin/category.vue'
import Goods from '../views/admin/goods.vue'
import OrderDet from '../views/admin/orderDet.vue'
import Order from '../views/admin/order.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: '/login'
  },
  {
    path: '',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin,
    meta: {
      loginRequire: true
    },
    children: [
      {
        path: 'welcome',
        component: Welcome
      },
      {
        path: 'user',
        component: User
      },
      {
        path: 'customer',
        component: Customer
      },
      {
        path: 'category',
        component: Category
      },
      {
        path: 'goods',
        component: Goods
      },
      {
        path: 'addOrder',
        component: OrderDet
      },
      {
        path: 'order',
        component: Order
      }
    ]
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
