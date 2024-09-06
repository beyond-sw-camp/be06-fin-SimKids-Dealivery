import MainPage from "@/pages/MainPage.vue";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
        path: "/",component: MainPage,
    },
    
  ]
});

export default router;