// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store/store'
import Axios from 'axios'
import Qs from 'qs'
import VueCookies from 'vue-cookies'


Axios.interceptors.request.use(config => {
  // console.log(config)
  const token = localStorage.getItem('token')
  if (token) {
    // 将获取到的token设置给header中的Authorization
    config.headers.token = token
  }
  return config
})


Vue.use(VueCookies)
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.$http=Axios
Vue.prototype.qs = Qs;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
