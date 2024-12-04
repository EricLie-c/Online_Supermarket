import Vue from 'vue'
import VueRouter from 'vue-router'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login/LoginView.vue')
  },
  {
    path: '/merchant',
    name: 'merchant',
    component: () => import('../views/Merchant/MerchantView.vue')
  },
  {
    path: '/guest',
    name: 'guest',
    component:() => import("../views/Guest/GuestView.vue")
  },
  {
    path: '/statisticalForm',
    name: 'statisticalForm',
    component: () => import("../views/Merchant/statisticalForm.vue")
  },
  {
    path: '/orderManagement',
    redirect: '/merchant'

  },
  {
    path: '/guestManagement',
    name: 'guestManagement',
    component: () => import("../views/Merchant/guestManagement.vue")
  },
  {
    path: '/guestLog',
    name: 'guestLog',
    component: () => import("../views/Merchant/guestLog.vue")
  }
  
]

const router = new VueRouter({
  routes
})

export default router
