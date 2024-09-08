<template>
  <HeaderComponent></HeaderComponent>
  <BannerComponent></BannerComponent>
  <SectionTitleComponent></SectionTitleComponent>
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from '@/components/common/HeaderComponent.vue';
import FooterComponent from '@/components/common/FooterComponent.vue';
import BannerComponent from '@/components/common/BannerComponent.vue';
import SectionTitleComponent from '@/components/common/SectionTitleComponent.vue';

export default {
  name: 'MainPage',
  props: {
    msg: String
  },
  components: {
    HeaderComponent,
    BannerComponent,
    SectionTitleComponent,
    FooterComponent,
  }

}
</script>
<style scoped></style>
