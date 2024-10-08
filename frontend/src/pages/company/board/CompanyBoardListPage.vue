<template>
  <HeaderComponent></HeaderComponent>
  <div id="container">
    <div id="main">
      <div id="content" class="content-box">
        <div class="page_aticle aticle_type2">
          <CompanyAsideComponent></CompanyAsideComponent>
          <div id="viewOrderList" class="page_section section_orderlist">
            <div class="head_aticle">
              <h2 class="tit">게시글 조회</h2>
            </div>
            <DropdownMenu
              :dropdown_menu="'search_date'"
              title="기간 선택"
              :options="dateOptions"
              :selectedOption="selectedDateRange"
              @update:selectedOption="selectDateRange"
              :btn_layer_class="'btn_layer_date'"
              :layer_search_class="'layer_search_date'"
            />

            <DropdownMenu
              :dropdown_menu="'order_state'"
              title="상태 선택"
              :options="statusOptions"
              :selectedOption="selectedOrderStatus"
              @update:selectedOption="selectOrderStatus"
              :btn_layer_class="'btn_layer_order'"
              :layer_search_class="'layer_search_order'"
            />

            <ListCardComponent
              :dataList="boards"
              :noDataMsg="'작성한 게시글이 없습니다.'"
              @deleteItem="deleteItem"
            />

            <div v-if="boards.length > 0">
              <div class="css-rdz8z7 e82lnfz1">
                <a class="page-unselected e82lnfz0" @click="goToPage(1)"
                  ><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAHUlEQVR42mNgAIPi/8X/kWkwA8SE0UQIMJAsCKMBBzk27fqtkcYAAAAASUVORK5CYII="
                    alt="처음 페이지로 이동하기 아이콘" /></a
                ><a class="page-unselected e82lnfz0" @click="prevPageGroup"
                  ><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAHCAQAAABqrk9lAAAAGElEQVR42mNgAIPi/8X/4QwwE5PBQJADAAKSG3cyVhtXAAAAAElFTkSuQmCC"
                    alt="이전 페이지로 이동하기 아이콘"
                /></a>

                <a
                  v-for="pageNumber in visiblePages"
                  :key="pageNumber"
                  :class="
                    pageNumber === currentPage
                      ? 'page-selected e82lnfz0'
                      : 'page-unselected e82lnfz0'
                  "
                  @click="goToPage(pageNumber)"
                >
                  {{ pageNumber }}
                </a>

                <a class="page-unselected e82lnfz0" @click="nextPageGroup"
                  ><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAHCAQAAABqrk9lAAAAGUlEQVR42mMo/l/8nwECQEwCHEwGhAlRBgA2mht3SwgzrwAAAABJRU5ErkJggg=="
                    alt="다음 페이지로 이동하기 아이콘" /></a
                ><a
                  class="page-unselected e82lnfz0"
                  @click="goToPage(totalPages)"
                  ><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAIElEQVR42mMo/l/8n4GBgQFGQ5kgDowmQZCwAMImhDkAb0k27Zcisn8AAAAASUVORK5CYII="
                    alt="마지막 페이지로 이동하기 아이콘"
                /></a>
              </div>
            </div>
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
import DropdownMenu from "../../../components/company/DropdownMenu.vue";
import ListCardComponent from "../../../components/company/ListCardComponent.vue";
import { mapStores } from "pinia";
import { useBoardStore } from "../../../stores/useBoardStore";

export default {
  name: "CompanyBoardListPage",
  components: {
    CompanyAsideComponent,
    DropdownMenu,
    ListCardComponent,
    HeaderComponent,
    FooterComponent,
  },
  data() {
    return {
      selectedDateRange: "전체기간",
      selectedOrderStatus: "전체보기",
      dateOptions: ["전체기간", "1개월", "3개월", "6개월", "12개월"],
      statusOptions: ["전체보기", "진행 전", "진행 중", "진행 완료"],
      boards: [],
      pagesPerGroup: 5,
      totalPages: 1,
    };
  },
  computed: {
    ...mapStores(useBoardStore),
    currentPage() {
      return Number(this.$route.query.page) || 1;
    },
    // 시작 페이지 번호 계산
    startPage() {
      return (
        Math.floor((this.currentPage - 1) / this.pagesPerGroup) *
          this.pagesPerGroup +
        1
      );
    },
    // 끝 페이지 번호 계산
    endPage() {
      return Math.min(this.startPage + this.pagesPerGroup - 1, this.totalPages);
    },
    // 현재 보여질 페이지 번호 목록
    visiblePages() {
      const pageNumbers = [];
      for (let i = this.startPage; i <= this.endPage; i++) {
        pageNumbers.push(i);
      }
      return pageNumbers;
    },
  },
  watch: {
    "$route.query.page": "setBoards",
    "$route.query.dateRange": "setBoards",
    "$route.query.orderStatus": "setBoards",
  },
  created() {
    this.setBoards();
  },
  methods: {
    async setBoards() {
      // URL 쿼리 파라미터에서 값을 초기화
      this.selectedDateRange = this.$route.query.dateRange || "전체기간";
      this.selectedOrderStatus = this.$route.query.orderStatus || "전체보기";

      const response = await this.boardStore.getProductBoardListWithOption(
        this.currentPage,
        this.selectedOrderStatus,
        this.extractNumber(this.selectedDateRange)
      );
      this.boards = response.content;
      this.totalPages = response.totalPages;
    },
    goToPage(pageNumber) {
      if (pageNumber >= 1 && pageNumber <= this.totalPages) {
        this.$router.push({
          query: {
            page: pageNumber,
            dateRange: this.selectedDateRange,
            orderStatus: this.selectedOrderStatus,
          },
        });
      } else if (pageNumber < 1) {
        alert("첫 번째 페이지입니다.");
      } else {
        alert("마지막 페이지입니다.");
      }
    },
    prevPageGroup() {
      const newPage = this.startPage - 1;
      this.goToPage(newPage);
    },
    nextPageGroup() {
      const newPage = this.endPage + 1;
      this.goToPage(newPage);
    },
    async selectDateRange(option) {
      console.log(option);
      this.selectedDateRange = option;
      this.$router.push({
        query: {
          page: this.currentPage,
          dateRange: this.selectedDateRange,
          orderStatus: this.selectedOrderStatus,
        },
      });
      await this.fetchBoards();
    },
    async selectOrderStatus(option) {
      console.log(option);
      this.selectedOrderStatus = option;
      this.$router.push({
        query: {
          page: this.currentPage,
          dateRange: this.selectedDateRange,
          orderStatus: this.selectedOrderStatus,
        },
      });
      await this.fetchBoards();
    },
    async fetchBoards() {
      const response = await this.boardStore.getProductBoardListWithOption(
        this.currentPage,
        this.selectedOrderStatus,
        this.extractNumber(this.selectedDateRange)
      );
      this.boards = response.content;
      this.totalPages = response.totalPages;
    },
    extractNumber(str) {
      if (typeof str !== "string") {
        str = String(str);
      }

      const number = str.match(/\d+/);
      return number ? parseInt(number[0], 10) : null;
    },
  },
};
</script>

<style scoped>
#content {
  min-width: 1050px;
}

.content_box {
  height: auto;
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

.css-rdz8z7 {
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
}

.page-unselected:first-of-type {
  border-left: 1px solid rgb(221, 221, 221);
}

.page-unselected {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border-top: 1px solid rgb(221, 221, 221);
  border-right: 1px solid rgb(221, 221, 221);
  border-bottom: 1px solid rgb(221, 221, 221);
  border-image: initial;
  border-left: none;
  cursor: pointer;
}

.page-selected {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border-top: 1px solid rgb(221, 221, 221);
  border-right: 1px solid rgb(221, 221, 221);
  border-bottom: 1px solid rgb(221, 221, 221);
  border-image: initial;
  border-left: none;
  cursor: pointer;
  background-color: rgb(247, 247, 247);
  color: rgb(95, 0, 128);
}
</style>
