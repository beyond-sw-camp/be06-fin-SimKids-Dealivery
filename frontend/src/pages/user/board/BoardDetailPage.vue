<template>
  <div id="top">
    <HeaderComponent />
    <div class="css-n48rgu">
      <div class="css-16c0d8l">
        <main id="product-atf" class="css-1eoy87d">
          <BoardDetailThumnailComponent :thumbnails="thumbnails" />
          <BoardDetailProductInfoComponent
            :data="data"
            :productBoardIdx="productBoardIdx"
            @submitOrder="submitOrder"
          />
        </main>
        <BoardDetailNavComponent
          :thumbnails="thumbnails"
          :detail="detail"
          :productBoardIdx="productBoardIdx"
          :productTitle="productTitle"
        />
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
import { useBoardStore } from "@/stores/useBoardStore";
import { useOrderStore } from "@/stores/useOrderStore";
import { mapStores } from "pinia";

export default {
  name: "BoardDetailPage",
  components: {
    HeaderComponent,
    FooterComponent,
    BoardDetailThumnailComponent,
    BoardDetailProductInfoComponent,
    BoardDetailNavComponent,
  },
  computed: {
    ...mapStores(useOrderStore),
    ...mapStores(useBoardStore),
  },
  data() {
    return {
      activeTab: "description", // 초기에는 '상품설명' 탭이 활성화됨
      thumbnails: [],
      detail: "",
      productBoardIdx: 1, // 추후에 실제 상품의 boardIdx로 변경
      productTitle: "",
      data: null,
    };
  },
  created() {
    this.productBoardIdx = parseInt(this.$route.params.idx);
    this.getData();
  },
  methods: {
    async getData() {
      this.data = await this.boardStore.getDetail(this.productBoardIdx);
      this.productTitle = this.data.title;
      this.thumbnails = this.data.productThumbnailUrls;
      this.detail = this.data.productDetailUrl;
      console.log(this.data);
    },
    async submitOrder(cartItems) {
      console.log(cartItems);
      let result = await this.orderStore.submitOrder(cartItems);
      if (result) {
        console.log("[SUCCESS] 주문 생성 성공");
        this.$router.push("/orders");
      }
    },
    addNewInquiry(registeredInquiry) {
      this.qnaStore.addNewInquiry(registeredInquiry);
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
