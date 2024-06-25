import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'

import AdminEbook from "@/views/admin/admin-ebook.vue";
import AdminCategory from "@/views/admin/admin-category.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },{
    path: '/admin/ebook',  //访问的路径
    name: 'AdminEbook',
    component: AdminEbook    //文件所在地址
  },{
    path: '/admin/category',  //访问的路径
    name: 'AdminCategory',
    component: AdminCategory    //文件所在地址
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
