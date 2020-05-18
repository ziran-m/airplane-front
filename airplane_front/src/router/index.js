import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/home/HelloWorld'
import flightList from '@/components/home/flightList'
import select from '@/components/home/select'
import home from '@/components/home/home'
import orderList from '@/components/home/orderList'
import adminLogin from '@/components/admin/login'
import admin from '@/components/admin/admin'
import user from '@/components/admin/user'
import setting from '@/components/admin/setting'
import order from '@/components/admin/order'
import flight from '@/components/admin/flight'
import addflight from '@/components/admin/addFlight'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: home,
      children:[
        {
          path: '',
          name: 'HelloWorld',
          components: {
            below:HelloWorld,
          }
        },
        {
          path: 'select',
          name: 'select',
          components: {
            below:select
          }
        },
        {
          path: 'flightList',
          name: 'flightList',
          components: {
            below:flightList
          }
        },
        {
          path: 'order',
          name: 'orderList',
          components: {
            below:orderList
          }
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: adminLogin,
    },
    {
      path:'/admin',
      name:'admin',
      component:admin,
      children:[
        {
          path: 'user',
          name: 'user',
          components: {
            _below:user}
        },
        {
          path: 'order',
          name: 'order',
          components: {
            _below:order}
        },
        {
          path: '',
          name: 'flight',
          components: {
            _below:flight}
        },
        {
          path: 'setting',
          name: 'setting',
          components: {
            _below:setting}
        },
        {
          path: 'addflight',
          name: 'addflight',
          components: {
            _below:addflight}
        },
        ]
      
    }
  ]
})
