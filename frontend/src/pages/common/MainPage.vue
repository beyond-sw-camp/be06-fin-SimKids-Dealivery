<template>
  <HeaderComponent></HeaderComponent>
  <BannerComponent></BannerComponent>
  <div class="container">
    <SectionTitleComponent></SectionTitleComponent>
    <MainCardViewComponent></MainCardViewComponent>
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
  props: {
    msg: String
  },
  components: {
    HeaderComponent,
    BannerComponent,
    SectionTitleComponent,
    MainCardViewComponent,
    FooterComponent,
  }

}
</script>

<style scoped>
.container {
  width: 100%;
  max-width: 100%;
  margin: 0 auto;
  padding: 0 15px;
  /* 여백 추가 (좌우 15px) */
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
</style>
