import LoginComponent from "@/components/user/LoginComponent.vue";
import UserSignupComponent from "@/components/user/UserSignupComponent.vue";
import AuthPage from "@/pages/common/AuthPage.vue";
import MainPage from "@/pages/common/MainPage.vue";
import MyPage from "@/pages/user/MyPage.vue";
import CompanyBoardPostPage from "../pages/company/board/CompanyBoardPostPage.vue";
import OrdersPage from "@/pages/user/orders/OrdersPage.vue";
import { createRouter, createWebHistory } from "vue-router";
import CompanySignupComponent from "@/components/company/CompanySignupComponent.vue";
import InvalidUrlComponent from "@/components/user/InvalidUrlComponent.vue";
import FindPasswordWaitComponent from "@/components/user/FindPasswordWaitComponent.vue";
import FindPasswordComponent from "@/components/user/FindPasswordComponent.vue";
import ResetPasswordComponent from "@/components/user/ResetPasswordComponent.vue";
import FindIdSuccessComponent from "@/components/user/FindIdSuccessComponent.vue";
import FindIdComponent from "@/components/user/FindIdComponent.vue";

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
        { path: "orders", component: OrdersPage }
      ],
    },

    {
      path: "/auth",
      component: AuthPage,
      children: [
        {path: "login", component: LoginComponent, meta: { requiresAuth: false } },
        {path: "user/signup", component: UserSignupComponent, meta: { requiresAuth: false } },
        {path: "company/signup", component: CompanySignupComponent, meta: { requiresAuth: false } },
        {path: "invalid", component: InvalidUrlComponent, meta: { requiresAuth: false } },
        {path: "pwd/find/wait", component: FindPasswordWaitComponent, meta: { requiresAuth: false } },
        {path: "pwd/find", component: FindPasswordComponent, meta: { requiresAuth: false } },
        {path: "pwd/reset", component: ResetPasswordComponent, meta: { requiresAuth: false } },
        {path: "id/find/success", component: FindIdSuccessComponent, meta: { requiresAuth: false } },
        {path: "id/find", component: FindIdComponent, meta: { requiresAuth: false } },
        {path: "", redirect: "/auth/login", meta: { requiresAuth: false } },
      ]
    },
    
    {
      path: "/mypage",
      component: MyPage,
    },

  ]
});

export default router;
