import { createRouter, createWebHashHistory } from 'vue-router'
import Task from '../views/Task.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Task
  },
  {
    path: '/node',
    name: 'Node',
    component: () => import(/* webpackChunkName: "about" */ '../views/Node.vue')
  },
  {
    path: '/resource',
    name: 'Resource',
    component: () => import(/* webpackChunkName: "about" */ '../views/Resource.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
