<template>
  <HeaderComponent></HeaderComponent>
  <BannerComponent></BannerComponent>
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from '@/components/common/HeaderComponent.vue';
import FooterComponent from '@/components/common/FooterComponent.vue';
import BannerComponent from '@/components/common/BannerComponent.vue';

export default {
  name: 'MainPage',
  props: {
    msg: String
  },
  components: {
    HeaderComponent,
    BannerComponent,
    FooterComponent
  }

}
</script>
<style scoped></style>
