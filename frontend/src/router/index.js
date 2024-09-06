import MainPage from "@/pages/MainPage.vue";
import CompanyBoardPostPage from "../pages/company/board/CompanyBoardPostPage.vue";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      component: MainPage,
      children: [
        {
          path: "company",
          component: CompanyBoardPostPage,
          meta: { requiresAuth: false },
        },
      ],
    },
  ],
});

export default router;
