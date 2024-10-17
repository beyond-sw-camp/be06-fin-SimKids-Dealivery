<template>
  <HeaderComponent></HeaderComponent>
  <div id="container">
    <div id="main">
      <div id="content">
        <div class="page_aticle aticle_type2">
          <CompanyAsideComponent></CompanyAsideComponent>
          <div id="viewOrderList" class="page_section section_orderlist">
            <div class="head_aticle">
              <h2 class="tit">1:1 문의 관리</h2>
            </div>

            <CompanyQnAManageComponent></CompanyQnAManageComponent>
            <!-- <OrderListCardComponent
                :dataList="boards"
                :noDataMsg="'작성한 게시글이 없습니다.'"
              /> -->
          </div>
        </div>
      </div>
    </div>
  </div>
  <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from "../../../components/common/HeaderComponent.vue";
import FooterComponent from "../../../components/common/FooterComponent.vue";
import CompanyAsideComponent from "@/components/company/CompanyAsideComponent.vue";
import CompanyQnAManageComponent from "../../../components/company/CompanyQnAManageComponent.vue";

//   import OrderListCardComponent from "../../../components/company/OrderListCardComponent.vue";
// import { mapStores } from "pinia";
// import { useCompanyBoardStore } from "../../../stores/UseCompanyBoardStore";

export default {
  name: "CompanyQnAListPage",
  components: {
    CompanyAsideComponent,
    CompanyQnAManageComponent,
    //   OrderListCardComponent,
    HeaderComponent,
    FooterComponent,
  },
  data() {
    return {
      boards: [],
    };
  },
  computed: {
    // ...mapStores(useCompanyBoardStore),
  },
  created() {
    // this.setBoards();
  },
  methods: {
    // async setBoards() {
    //   this.boards = await this.companyBoardStore.getProductBoardList();
    // },
    selectDateRange(option) {
      this.selectedDateRange = option;
    },
    selectOrderStatus(option) {
      this.selectedOrderStatus = option;
    },
  },
};
</script>

<style scoped>
#content {
  min-width: 1050px;
}

.page_aticle {
  width: 1050px;
  margin: 0 auto;
}

.page_aticle.aticle_type2 {
  padding-top: 65px;
}

.page_aticle.aticle_type2 .page_section {
  float: right;
  width: 820px;
}

.page_aticle .head_aticle {
  padding: 5px 0 34px;
}

.page_aticle .head_aticle .tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -0.5px;
  display: inline-block;
}

body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
form,
fieldset,
legend,
input,
button,
textarea,
p,
blockquote,
th,
td,
a,
span {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
