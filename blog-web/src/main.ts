// main.ts
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueWechatTitle from 'vue-wechat-title'


const app = createApp(App)
app.use(VueWechatTitle)
app.use(router)
app.mount('#app')