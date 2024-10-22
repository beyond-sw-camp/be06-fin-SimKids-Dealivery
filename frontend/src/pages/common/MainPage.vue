<template>
  <HeaderComponent></HeaderComponent>
  <BannerComponent></BannerComponent>
  <div class="container">
    <SectionTitleComponent
      :title="notice[0]"
      :subtitle="notice[1]"
    ></SectionTitleComponent>
    <div class="main-card-container">
      <MainCardViewComponent
        v-for="data in openDataList"
        :key="data.idx"
        :data="data"
      ></MainCardViewComponent>
    </div>
    <ObserverComponent @show="getOpenList"></ObserverComponent>

    <SectionTitleComponent
      :title="notice[2]"
      :subtitle="notice[3]"
    ></SectionTitleComponent>
    <br />
    <div class="board-card-container">
      <ProductBoardListCardComponent
        v-for="data in readyDataList"
        :key="data.idx"
        :data="data"
      ></ProductBoardListCardComponent>
    </div>
    <ObserverComponent @show="getReadyList"></ObserverComponent>
  </div>
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import FooterComponent from "@/components/common/FooterComponent.vue";
import BannerComponent from "@/components/mainpage/BannerComponent.vue";
import SectionTitleComponent from "@/components/mainpage/SectionTitleComponent.vue";
import MainCardViewComponent from "@/components/mainpage/MainCardViewComponent.vue";
import ProductBoardListCardComponent from "@/components/mainpage/ProductBoardListCardComponent.vue";
import ObserverComponent from "@/components/mainpage/ObserverComponent.vue";
import { useBoardStore } from "@/stores/useBoardStore";
import { mapStores } from "pinia";

export default {
  name: "MainPage",
  components: {
    HeaderComponent,
    BannerComponent,
    SectionTitleComponent,
    MainCardViewComponent,
    ProductBoardListCardComponent,
    ObserverComponent,
    FooterComponent,
  },
  computed: {
    ...mapStores(useBoardStore),
  },
  data() {
    return {
      readyDataList: [],
      openDataList: [],
      readyPage: 1,
      openPage: 1,
      notice: [
        "🎉 특가 가득! 진행 중인 이벤트 🎉",
        "놓치지 말고 지금 주문하세요!",
        "✨ 오픈 예정 이벤트 ✨",
        "관심 등록 후 찾아보세요!",
      ],
    };
  },
  // created() {
  //   this.getReadyList();
  // },
  methods: {
    async getReadyList() {
      const newData = await this.boardStore.getMainList(
        this.readyPage,
        "진행 전"
      );
      this.readyDataList = [...this.readyDataList, ...newData.content];
      this.readyPage += 1;
    },
    async getOpenList() {
      const newData = await this.boardStore.getMainList(
        this.openPage,
        "진행 중"
      );
      console.log(newData);
      this.openDataList = [...this.openDataList, ...newData.content];
      this.openPage += 1;
    },
  },
};
</script>

<style scoped>
.container {
  width: 100%;
  max-width: 100%;
  margin: 0 auto;
  padding: 15px;
}

@media (min-width: 576px) {
  .container {
    max-width: 540px;
  }
}

@media (min-width: 768px) {
  .container {
    max-width: 720px;
  }
}

@media (min-width: 992px) {
  .container {
    max-width: 960px;
  }
}

@media (min-width: 1200px) {
  .container {
    max-width: 1140px;
  }
}

@media (min-width: 1400px) {
  .container {
    max-width: 1320px;
  }
}

.main-card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  /* 카드들을 왼쪽부터 배치 */
  gap: 30px;
}

.main-card-container > * {
  flex-basis: calc(50% - 30px);
  /* 카드의 너비를 50%로 설정 */
  /* max-width: calc(50% - 30px); */
  /* 너비 최대값 설정 */
  box-sizing: border-box;
}

@media (max-width: 768px) {
  .main-card-container > * {
    flex-basis: 100%;
    /* 작은 화면에서는 카드가 한 줄에 하나만 나옴 */
    max-width: 100%;
  }
}

.board-card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 20px;
}

.board-card-container > * {
  flex: 0 0 calc(33.33% - 20px);
  /* 카드 너비를 3개로 고정 */
  max-width: calc(33.33% - 20px);
  /* 3개로 고정된 너비 */
  box-sizing: border-box;
}

@media (max-width: 768px) {
  .board-card-container > * {
    flex: 0 0 calc(50% - 20px);
    /* 작은 화면에서는 한 줄에 2개 */
    max-width: calc(50% - 20px);
  }
}

@media (max-width: 576px) {
  .board-card-container > * {
    flex: 0 0 100%;
    /* 더 작은 화면에서는 한 줄에 하나만 */
    max-width: 100%;
  }
}
</style>
