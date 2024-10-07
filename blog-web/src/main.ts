// main.ts
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueWechatTitle from 'vue-wechat-title'
import "element-plus/theme-chalk/el-notification.css";


const app = createApp(App)
app.use(VueWechatTitle)
app.use(router)
app.mount('#app')