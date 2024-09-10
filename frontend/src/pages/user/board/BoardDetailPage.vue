<template>
  <div id="top">
    <div class="css-n48rgu">
      <div class="css-16c0d8l">
        <main id="product-atf" class="css-1eoy87d">
          <BoardDetailThumnailComponent :thumbnails="thumbnails" />
          <BoardDetailProductInfoComponent />
        </main>
        <BoardDetailNavComponent :tableData="tableData" />
      </div>
    </div>
  </div>
</template>

<script>
import BoardDetailThumnailComponent from "@/components/board/BoardDetailThumnailComponent.vue";
import BoardDetailProductInfoComponent from "@/components/board/BoardDetailProductInfoComponent.vue";
import BoardDetailNavComponent from "@/components/board/BoardDetailNavComponent.vue";

export default {
  name: "OrdersPage",
  props: {
    msg: String,
  },
  components: {
    BoardDetailThumnailComponent,
    BoardDetailProductInfoComponent,
    BoardDetailNavComponent,
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
      tableData: [
        {
          title: "아이도",
          author: "엄*희",
          date: "2022.04.20",
          status: "답변완료",
        },
        {
          title: "아이도",
          author: "엄*희",
          date: "2022.04.20",
          status: "답변완료",
        },
      ],
    };
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
