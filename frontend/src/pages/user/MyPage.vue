<template>
  <HeaderComponent></HeaderComponent>
  <div class="page-wrapper">
    <div class="page-layout">
      <MypageAsideComponent
        @menu-click="handleMenuClick"
      ></MypageAsideComponent>
      <div class="content-wrapper">
        <TitleComponent :title="currentTitle" />
        <router-view></router-view>
        <!-- 하위 라우터 컴포넌트가 제목 아래에 표시됩니다. -->
      </div>
    </div>
  </div>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import MypageAsideComponent from "@/components/mypage/MypageAsideComponent.vue";
import FooterComponent from "@/components/common/FooterComponent.vue";
import TitleComponent from "@/components/mypage/TitleComponent.vue";
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";

export default {
  name: "MyPage",
  components: {
    HeaderComponent,
    MypageAsideComponent,
    TitleComponent,
    FooterComponent,
  },
  data() {
    return {
      currentTitle: "", // 기본 Title은 빈 값으로 설정
    };
  },
  methods: {
    async handleMenuClick(menu) {
      if (menu === "order") {
        this.currentTitle = "주문 내역";
        this.$router.push("/mypage/order");
      } else if (menu === "qna") {
        this.currentTitle = "My 문의";
        this.$router.push("/mypage/qna");
      } else if (menu === "likes") {
        this.currentTitle = "찜한 게시글";
        this.$router.push("/mypage/likes");
      } else if (menu === "address") {
        this.currentTitle = "배송지 관리";
        await this.getDeliveryList();
        this.$router.push("/mypage/address");
      } else if (menu === "info") {
        this.currentTitle = "내 정보";

        await this.getUserInfo();
        this.$router.push("/mypage/detail");
      } else if (menu === "update-info") {
        await this.getUserInfo();
        this.currentTitle = "개인정보 수정";
        this.$router.push("/mypage/detail/edit");
      }
    },

    async getUserInfo() {
      if (!(await this.userStore.getDetail())) {
        alert("회원정보를 가져오는데 실패했습니다.");
      }
    },
    async getDeliveryList() {
      if (!(await this.userStore.getDeliveryList())) {
        alert("회원정보를 가져오는데 실패했습니다.");
      }
    },
    updateTitleBasedOnRoute() {
      const currentRoute = this.$route.path;
      if (currentRoute.includes("order/")) {
        this.currentTitle = "주문 상세";
      } else if (currentRoute.includes("order")) {
        this.currentTitle = "주문 내역";
      } else if (currentRoute.includes("qna")) {
        this.currentTitle = "My 문의";
      } else if (currentRoute.includes("likes")) {
        this.currentTitle = "찜한 게시글";
      } else if (currentRoute.includes("address")) {
        this.currentTitle = "배송지 관리";
      } else if (currentRoute.includes("detail/edit")) {
        this.currentTitle = "개인정보 수정";
      } else if (currentRoute.includes("detail")) {
        this.currentTitle = "내 정보";
      }
    },
  },
  computed: {
    ...mapStores(useUserStore),
  },
  mounted() {
    this.updateTitleBasedOnRoute(); // 페이지 로드 시 현재 경로에 따라 타이틀 설정
  },
  watch: {
    $route() {
      // to 변수 제거
      this.updateTitleBasedOnRoute(); // 라우트 변경 시 타이틀 업데이트
    },
  },
};
</script>

<style scoped>
.page-wrapper {
  position: relative;
  min-width: 1050px;
  background-color: rgb(242, 245, 248);
  padding: 20px 0;
}

.page-layout {
  display: flex;
  align-items: flex-start;
  gap: 20px;
  justify-content: center;
  padding: 50px 0px 70px 100px;
  margin: 0 auto;
  /* 좌우 중앙 정렬 */
  max-width: 1150px;
  /* 최대 너비 설정 */
  width: 100%;
  /* 부모 컨테이너 너비 채우기 */
}

.content-wrapper {
  display: flex;
  flex-direction: column;
  gap: 20px;
  /* 제목과 하위 컴포넌트 사이 여백 추가 */
  width: 100%;
}
</style>
