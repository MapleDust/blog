import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";
import Home from "./App.vue"
import Login from "./components/Login.vue"

const routes: Array<RouteRecordRaw> = [
    {
        path: "/",
        name: "home",
        component: Home,
        meta:{
            title: "主页",
        }
    },
    {
        path: "/login",
        name: "Login",
        component: Login,
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})
router.beforeEach((to) => {
    if(to.meta.title){
        document.title="fcidd blog | "+to.meta.title
    }
})

export default router;