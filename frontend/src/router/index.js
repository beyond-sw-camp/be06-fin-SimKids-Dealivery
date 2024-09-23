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
import MyPageAddressComponent from "@/components/mypage/MypageAddressComponent.vue";
import CompanyQnAListPage from "@/pages/company/qna/CompanyQnAListPage.vue";
import LoginRedirectPage from "@/pages/common/LoginRedirectPage.vue";
import SocialSignupComponent from "@/components/user/SocialSignupComponent.vue";
import MypageOrderDetail from "@/components/mypage/MypageOrderDetailComponent.vue"
import MypageDetailEditComponent from "@/components/mypage/MypageDetailEditComponent.vue";
import MypageDetailComponent from "@/components/mypage/MypageDetailComponent.vue";
import { useUserStore } from "@/stores/useUserStore";


const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      component: MainPage,
      children: [],
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
        {
          path: "social/signup",
          component: SocialSignupComponent,
          meta: { requiresAuth: false }
        },
        { path: "", redirect: "/auth/login", meta: { requiresAuth: false } },
      ],
    },
    { path: "/login/redirect", component: LoginRedirectPage },
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
    { path: "/orders", component: OrdersPage, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
    { path: "/board/detail/:idx", component: BoardDetailPage },
    {
      path: "/product-boards/company",
      component: CompanyBoardPostPage,
      meta: { requiresAuth: true, roles:['ROLE_COMPANY'] }
    },
    {
      path: "/product-boards/company/:idx",
      name: "CompanyBoardPostPage",
      component: () =>
        import("../pages/company/board/CompanyBoardPostPage.vue"),
      props: true,
      meta: { requiresAuth: true, roles:['ROLE_COMPANY'] }
    },
    {
      path: "/product-boards/company/list",
      component: CompanyBoardListPage,
      meta: { requiresAuth: true, roles:['ROLE_COMPANY'] }
    },
    {
      path: "/orders/company/list",
      component: CompanyOrderListPage,
      meta: { requiresAuth: true, roles:['ROLE_COMPANY'] }
    },
    {
      path: "/qna/company/list",
      component: CompanyQnAListPage,
      meta: { requiresAuth: true, roles:['ROLE_COMPANY']}
    },
    {
      path: "/mypage",
      component: MyPage,
      redirect: "/mypage/order",
      meta: { requiresAuth: true, roles:['ROLE_USER'] },
      children: [
        { path: "order", component: MypageOrderListComponent, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
        { path: 'order/:orderId', component: MypageOrderDetail, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
        { path: "qna", component: MypageQnAComponent, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
        { path: "likes", component: MypageLikesEventComponent, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
        { path: "address", component: MyPageAddressComponent, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
        { path: "detail/edit", component: MypageDetailEditComponent, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
        { path: "detail", component: MypageDetailComponent, meta: { requiresAuth: true, roles:['ROLE_USER'] } },
      ],
    },
  ],
});

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore(); // Pinia 스토어
  const isAuthenticated = userStore.isLogined; // 로그인 상태 확인
  const userRoles = userStore.roles; // 사용자 역할 (예: ['user'])

  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  const requiredRoles = to.matched.flatMap(record => record.meta.roles || []);

  if (requiresAuth) {
    if (!isAuthenticated) {
      alert("로그인이 필요한 서비스입니다.");
      next({ path: '/auth/login', query: { redirect: to.fullPath } });
    } else if (!requiredRoles.some(role => userRoles.includes(role))) {
      alert("페이지에 접근할 수 있는 권한이 없습니다.");
      next('/'); // 권한 없음 페이지로 리다이렉트
    } else {
      next(); // 경로 허용
    }
  } else {
    next(); // 인증 필요 없는 페이지는 그대로 허용
  }
});
export default router;
