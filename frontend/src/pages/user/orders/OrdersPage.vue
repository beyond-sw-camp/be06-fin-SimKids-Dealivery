<template>
  <OrdersMainComponent />
</template>

<script>
import OrdersMainComponent from "@/components/orders/OrdersMainComponent.vue";
export default {
  name: "OrdersPage",
  props: {
    msg: String,
  },
  components: {
    OrdersMainComponent,
  },
};
</script>
<style scoped>
</style>
