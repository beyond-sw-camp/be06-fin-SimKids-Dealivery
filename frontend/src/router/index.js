import LoginComponent from "@/components/user/LoginComponent.vue";
import UserSignupComponent from "@/components/user/UserSignupComponent.vue";
import AuthPage from "@/pages/common/AuthPage.vue";
import MainPage from "@/pages/common/MainPage.vue";
import MyPage from "@/pages/user/MyPage.vue";
import CompanyBoardPostPage from "../pages/company/board/CompanyBoardPostPage.vue";
import CompanyBoardListPage from "../pages/company/board/CompanyBoardListPage.vue";
import OrdersPage from "@/pages/user/orders/OrdersPage.vue";
import { createRouter, createWebHistory } from "vue-router";
import CompanySignupComponent from "@/components/company/CompanySignupComponent.vue";
import InvalidUrlComponent from "@/components/user/InvalidUrlComponent.vue";
import FindPasswordWaitComponent from "@/components/user/FindPasswordWaitComponent.vue";
import FindPasswordComponent from "@/components/user/FindPasswordComponent.vue";
import ResetPasswordComponent from "@/components/user/ResetPasswordComponent.vue";
import FindIdSuccessComponent from "@/components/user/FindIdSuccessComponent.vue";
import FindIdComponent from "@/components/user/FindIdComponent.vue";
import BoardDetailPage from "@/pages/user/board/BoardDetailPage.vue";
import BoardListPage from "@/pages/common/BoardListPage.vue";
import ProductBoardListComponent from "@/components/mainpage/ProductBoardListComponent.vue";
import CompanyOrderListPage from "../pages/company/orders/CompanyOrderListPage.vue";
import MypageOrderListComponent from "@/components/mypage/MypageOrderListComponent.vue";
import MypageQnAComponent from "@/components/mypage/MypageQnAComponent.vue";
import MypageLikesEventComponent from "@/components/mypage/MypageLikesEventComponent.vue";


const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      component: MainPage,
      children: [
        { path: "board/detail/:idx", component: BoardDetailPage },
        { path: "orders", component: OrdersPage },
      ],
    },
    {
      path: "/auth",
      component: AuthPage,
      children: [
        {
          path: "login",
          component: LoginComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "user/signup",
          component: UserSignupComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "company/signup",
          component: CompanySignupComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "invalid",
          component: InvalidUrlComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "pwd/find/wait",
          component: FindPasswordWaitComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "pwd/find",
          component: FindPasswordComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "pwd/reset",
          component: ResetPasswordComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "id/find/success",
          component: FindIdSuccessComponent,
          meta: { requiresAuth: false },
        },
        {
          path: "id/find",
          component: FindIdComponent,
          meta: { requiresAuth: false },
        },
        { path: "", redirect: "/auth/login", meta: { requiresAuth: false } },
      ],
    },
    {
      path: "/board",
      component: BoardListPage,
      meta: { requiresAuth: false },
      children: [
        {
          path: "list",
          component: ProductBoardListComponent,
          meta: { requiresAuth: false },
        },
      ],
    },
    {
      path: "/product-boards/company",
      component: CompanyBoardPostPage,
    },
    {
      path: "/product-boards/company/:id",
      name: "CompanyBoardPostPage",
      component: () =>
        import("../pages/company/board/CompanyBoardPostPage.vue"),
      props: true,
    },
    {
      path: "/product-boards/company/list",
      component: CompanyBoardListPage,
    },
    {
      path: "/orders/company/list",
      component: CompanyOrderListPage,
    },
    {
      path: "/mypage",
      component: MyPage,
      redirect: "/mypage/order",  
      children: [
        { path: "order", component: MypageOrderListComponent }, 
        { path: "qna", component: MypageQnAComponent },
        { path: "likes", component: MypageLikesEventComponent }
      ]
    },
  ],
});

export default router;
