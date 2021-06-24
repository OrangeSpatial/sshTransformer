import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { setupAntd } from './antd'
import axios from 'axios'

const service = axios.create({
  // timeout: 5000, // 请求超时时间
  baseURL: 'http://localhost:8080/'
})

const app = createApp(App)
app.config.globalProperties.$http = service
setupAntd(app)
app.use(router)
app.mount('#app')
