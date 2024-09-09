<template>
  <HeaderComponent></HeaderComponent>
  <BannerComponent></BannerComponent>
  <div class="container">
    <SectionTitleComponent :title="notice[0]" :subtitle="notice[1]"></SectionTitleComponent>
    <div class="card-container">
      <MainCardViewComponent></MainCardViewComponent>
      <MainCardViewComponent></MainCardViewComponent>
      <MainCardViewComponent></MainCardViewComponent>
      <MainCardViewComponent></MainCardViewComponent>
      <!-- 추가 카드들 -->
    </div>
    <SectionTitleComponent :title="notice[2]" :subtitle="notice[3]"></SectionTitleComponent>
  </div>
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from '@/components/common/HeaderComponent.vue';
import FooterComponent from '@/components/common/FooterComponent.vue';
import BannerComponent from '@/components/mainpage/BannerComponent.vue';
import SectionTitleComponent from '@/components/mainpage/SectionTitleComponent.vue';
import MainCardViewComponent from '@/components/mainpage/MainCardViewComponent.vue';

export default {
  name: 'MainPage',
  components: {
    HeaderComponent,
    BannerComponent,
    SectionTitleComponent,
    MainCardViewComponent,
    FooterComponent,
  },
  data() { 
    return {
      "notice": ["🎉 특가 가득! 진행 중인 세일 🎉", "놓치지 말고 지금 주문하세요!", "✨ 오픈 예정 이벤트 ✨", "관심 등록 후 찾아보세요!"],
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  max-width: 100%;
  margin: 0 auto;
  padding: 15px;
}

@media(min-width:576px) {
  .container {
    max-width: 540px;
  }
}

@media(min-width:768px) {
  .container {
    max-width: 720px;
  }
}

@media(min-width:992px) {
  .container {
    max-width: 960px;
  }
}

@media(min-width:1200px) {
  .container {
    max-width: 1140px;
  }
}

@media(min-width:1400px) {
  .container {
    max-width: 1320px;
  }
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  /* 카드들을 중앙에 정렬 */
  gap: 15px;
  /* 카드 간의 간격 조절 */
}

.card-container>* {
  flex: 1 1 calc(50% - 15px);
  /* 카드 너비를 50%로 설정하고, 카드 간의 간격 고려 */
  box-sizing: border-box;
  /* 여백 및 패딩을 포함하여 박스 크기 조정 */
}
</style>
