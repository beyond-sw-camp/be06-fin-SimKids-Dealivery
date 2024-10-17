<template>
  <HeaderComponent />
  <OrdersMainComponent />
  <FooterComponent />
</template>

<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import FooterComponent from "@/components/common/FooterComponent.vue";
import OrdersMainComponent from "@/components/orders/OrdersMainComponent.vue";
export default {
  name: "OrdersPage",
  components: {
    HeaderComponent,
    OrdersMainComponent,
    FooterComponent,
  },
};
</script>
<style scoped>
</style>
