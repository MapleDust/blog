// router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Home from "./components/Home.vue";
import Tools from "./components/Tools.vue";

const routes = [
    {
        path: "/",
        name: "home",
        component: Home,
        meta: { title: "主页" }
    },
    {
        path: "/tools",
        name: "tools",
        component: Tools,
        meta: { title: "工具" }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = "fcidd blog | " + to.meta.title;
    }
    next();
});

export default router;