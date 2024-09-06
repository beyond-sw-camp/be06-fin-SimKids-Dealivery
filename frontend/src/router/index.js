import LoginComponent from "@/components/user/LoginComponent.vue";
import UserSignupComponent from "@/components/user/UserSignupComponent.vue";
import AuthPage from "@/pages/common/AuthPage.vue";
import MainPage from "@/pages/common/MainPage.vue";
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

    {
      path: "/auth",
      component: AuthPage,
      children: [
        {path: "login", component: LoginComponent, meta: { requiresAuth: false } },
        {path: "user/signup", component: UserSignupComponent, meta: { requiresAuth: false } },
        {path: "", redirect: "/auth/login", meta: { requiresAuth: false } },
      ]
    }
  ]
});

export default router;
