<template>
  <HeaderComponent></HeaderComponent>
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from '@/components/common/HeaderComponent.vue';
import FooterComponent from '@/components/common/FooterComponent.vue';

export default {
  name: 'AuthPage',
  components: {
    HeaderComponent,
    FooterComponent
  }

}
</script>
<style scoped></style>
