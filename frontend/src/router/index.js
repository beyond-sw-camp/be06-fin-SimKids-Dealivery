import MainPage from "@/pages/MainPage.vue";
import CompanyBoardPage from "../pages/company/board/CompanyBoardPage.vue";
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
          component: CompanyBoardPage,
          meta: { requiresAuth: false },
        },
      ],
    },
  ],
});

export default router;
