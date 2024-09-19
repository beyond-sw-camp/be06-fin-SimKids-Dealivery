<template>
  <HeaderComponent />
  <div id="top">
    <div class="css-n48rgu">
      <div class="css-16c0d8l">
        <main id="product-atf" class="css-1eoy87d">
          <BoardDetailThumnailComponent :thumbnails="thumbnails" />
          <BoardDetailProductInfoComponent @submitOrder="submitOrder" />
        </main>
        <BoardDetailNavComponent :tableData="tableData" />
      </div>
    </div>
  </div>
  <FooterComponent />
</template>

<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import FooterComponent from "@/components/common/FooterComponent.vue";
import BoardDetailThumnailComponent from "@/components/board/BoardDetailThumnailComponent.vue";
import BoardDetailProductInfoComponent from "@/components/board/BoardDetailProductInfoComponent.vue";
import BoardDetailNavComponent from "@/components/board/BoardDetailNavComponent.vue";

import { useOrderStore } from "@/stores/useOrderStore";
import { mapStores } from "pinia";

export default {
  name: "OrdersPage",
  components: {
    HeaderComponent,
    FooterComponent,
    BoardDetailThumnailComponent,
    BoardDetailProductInfoComponent,
    BoardDetailNavComponent,
  },
  computed: {
    ...mapStores(useOrderStore),
  },
  data() {
    return {
      activeTab: "description", // 초기에는 '상품설명' 탭이 활성화됨
      thumbnails: [
        {
          src: "https://product-image.kurly.com/hdims/resize/%5E%3E720x%3E936/cropcenter/720x936/quality/85/src/product/image/c0599d4f-d892-4d43-a22d-277459e929bd.jpg",
        },
        {
          src: "https://pbs.twimg.com/media/EE0R8XcU0AAlbth.jpg",
        },
      ],

    };
  },
  methods: {
    async submitOrder(cartItems) {
      console.log(cartItems);
      let result = await this.orderStore.submitOrder(cartItems);
      if (result) {
        console.log("[SUCCESS] 주문 생성 성공");
        this.$router.push("/orders");
      }
    },
  },
};
</script>

<style scoped>
/*공통 부분*/
* {
  margin: 0;
}

img {
  border-style: none;
}

section {
  display: block;
}

ol,
ul {
  list-style-type: none;
  padding: 0;
}

.css-n48rgu {
  width: 100%;
  position: relative;
}

.css-16c0d8l {
  position: relative;
  width: 1050px;
  margin: 0px auto;
  padding-top: 30px;
}

.css-1eoy87d {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
}
</style>
