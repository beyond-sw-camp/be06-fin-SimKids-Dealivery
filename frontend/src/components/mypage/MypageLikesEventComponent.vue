<template>
  <div class="css-heioij eug5r8l1">
    <div class="css-ehagcz eug5r8l0">
      <div class="css-1l3y783 e1a4z271" style="position: relative">
        <div class="css-1gp1092 e1a4z270">
          전체 <strong>{{ this.totalElements }}</strong
          >개
        </div>
        <div v-if="dataList.length === 0" class="no-items-message">
          찜한 게시글이 없습니다.
        </div>
        <div v-else style="overflow: visible; width: 0px">
          <div>
            <div
              aria-label="grid"
              aria-readonly="true"
              class="ReactVirtualized__Grid ReactVirtualized__List"
              role="grid"
              tabindex="0"
              style="
                box-sizing: border-box;
                direction: ltr;
                height: auto;
                position: relative;
                width: 610px;
                will-change: transform;
              "
            >
              <div
                class="ReactVirtualized__Grid__innerScrollContainer"
                role="rowgroup"
                style="
                  width: auto;
                  height: 274px;
                  max-width: 610px;
                  max-height: 274px;
                  position: relative;
                "
              >
                <div
                  v-for="data in this.dataList"
                  :key="data.idx"
                  class="css-9v8hu9 e19er7v46"
                  :style="{
                    top: index * 137 + 'px',
                    position: 'relative',
                    height: '137px',
                    left: '0px',
                    width: '100%',
                  }"
                >
                  <a
                    :href="`/board/detail/${data.idx}`"
                    class="css-4srzau e19er7v45"
                  >
                    <span width="90" height="117" class="css-ew8abz eq9umyc2">
                      <img
                        :src="data.productThumbnailUrl"
                        alt=""
                        class="css-0"
                      />
                    </span>
                  </a>
                  <div class="css-7rpa25 e19er7v44">
                    <div>
                      <div class="css-1nf3eox e19er7v42">
                        <a
                          :href="`/board/detail/${data.idx}`"
                          class="css-4ejps8 e19er7v43"
                          >{{ data.title }}</a
                        >
                      </div>
                      <div class="css-13kofxh e19er7v41">
                        <span class="css-x4qt93 e1alt0er2"
                          >{{ data.discountRate }}%</span
                        >
                        <span class="css-153tu4t e1alt0er1"
                          >{{
                            Math.round(
                              data.price * (1 - data.discountRate / 100)
                            ).toLocaleString()
                          }}원</span
                        >
                        <span class="css-1ufyr2r e1alt0er0"
                          >{{ data.price.toLocaleString() }}원</span
                        >
                      </div>
                    </div>
                    <div class="css-2qdoqn e19er7v40">
                      <button
                        class="css-17giheb e4nu7ef3"
                        @click="removeItem(data.idx)"
                      >
                        <span class="css-nytqmg e4nu7ef1">삭제</span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="resize-triggers">
          <div class="expand-trigger">
            <div style="width: 611px; height: 323px"></div>
          </div>
          <div class="contract-trigger"></div>
        </div>
        <div v-if="dataList.length > 0">
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
            ><a class="page-unselected e82lnfz0" @click="goToPage(totalPages)"
              ><img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAIElEQVR42mMo/l/8n4GBgQFGQ5kgDowmQZCwAMImhDkAb0k27Zcisn8AAAAASUVORK5CYII="
                alt="마지막 페이지로 이동하기 아이콘"
            /></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useBoardStore } from "@/stores/useBoardStore";
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";
export default {
  data() {
    return {
      dataList: [],
      totalPages: 1,
      pages: [],
      pagesPerGroup: 5,
      totalElements: 0,
      request: {
        productBoardIdx: null,
      },
    };
  },
  computed: {
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
    ...mapStores(useBoardStore),
    ...mapStores(useUserStore),
  },
  created() {
    this.getDataList();
  },
  watch: {
    // page가 변경되면 getDataList 호출
    "$route.query.page"() {
      this.getDataList();
    },
  },
  methods: {
    async getDataList() {
      console.log(this.currentPage);
      const response = await this.boardStore.getLikesList(this.currentPage);
      if (response.totalPages > 0) {
        this.totalPages = response.totalPages;
      }
      this.totalElements = response.totalElements;
      this.dataList = response.content;
      console.log(this.dataList);
    },
    goToPage(pageNumber) {
      if (pageNumber >= 1 && pageNumber <= this.totalPages) {
        this.$router.push({
          query: {
            page: pageNumber,
            category: this.currentCategory,
            search: this.currentSearch,
          },
        });
      } else if (pageNumber < 1) {
        alert("첫 번째 페이지입니다.");
      } else {
        alert("마지막 페이지입니다.");
      }
    },
    // 이전 페이지 그룹으로 이동
    prevPageGroup() {
      const newPage = this.startPage - 1;
      this.goToPage(newPage);
    },
    // 다음 페이지 그룹으로 이동
    nextPageGroup() {
      const newPage = this.endPage + 1;
      this.goToPage(newPage);
    },
    removeItem(index) {
      this.dataList.splice(index, 1);
      this.toggleHeart(index);
    },
    async toggleHeart(index) {
      this.request.productBoardIdx = index;
      if (!this.userStore.isLogined) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push("/auth/login");
        return;
      }
      if (await this.userStore.like(this.request)) {
        alert("관심 등록을 취소했습니다.");
      } else {
        alert("실패했습니다.");
      }
      window.location.reload();
    },
  },
  mounted() {
    window.scrollTo({
      top: 100,
      left: 0,
      behavior: "smooth",
    });
  },
};
</script>

<style scoped>
.no-items-message {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 274px;
  /* 내용이 있는 영역과 맞추기 위해 설정 */
  font-size: 16px;
  color: #888;
}

body {
  margin: 0;
}

body,
button,
input,
select,
textarea {
  font-size: 14px;
  color: #333;
}

body,
button,
input,
select,
textarea {
  font-size: 14px;
  color: #333;
}

body {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  background-color: #fff;
  -webkit-tap-highlight-color: transparent;
}

body,
html {
  height: 100%;
}

*,
:after,
:before {
  box-sizing: border-box;
  margin: 0;
}

*,
:after,
:before,
legend,
td,
th {
  padding: 0;
}

body {
  display: block;
  margin: 8px;
}

.css-u71x2d {
  position: relative;
  min-width: 1050px;
  background-color: rgb(242, 245, 248);
}

.css-72lz6z {
  display: flex;
  align-items: flex-start;
  gap: 20px;
  -webkit-box-pack: center;
  justify-content: center;
  padding: 50px 0px 80px;
  margin: 0px auto;
}

.css-heioij {
  overflow: hidden;
  width: 650px;
  background-color: rgb(255, 255, 255);
  border-radius: 16px;
}

.css-oc8mjz {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  margin: 0px 20px;
  padding: 25px 0px 20px;
  border-bottom: 2px solid rgb(51, 51, 51);
}

.css-eq7f8j {
  display: flex;
  align-items: flex-end;
}

.css-1lmd4kz {
  font-weight: 500;
  font-size: 20px;
  letter-spacing: -0.5px;
  line-height: 28px;
}

h2 {
  display: block;
  font-size: 1.5em;
  margin-block-start: 0.83em;
  margin-block-end: 0.83em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
  unicode-bidi: isolate;
}

.css-1vbzf1d {
  padding-left: 8px;
  font-size: 13px;
  line-height: 20px;
  letter-spacing: -0.2px;
  color: rgb(153, 153, 153);
}

.css-ehagcz {
  padding: 0px 20px;
}

.css-1l3y783 {
  padding: 8px 0px 10px;
}

.css-1gp1092 {
  color: rgb(51, 51, 51);
  padding: 8px 0px 6px;
}

b,
strong {
  font-weight: bolder;
}

*,
:after,
:before,
legend,
td,
th {
  padding: 0;
}

.css-9v8hu9 {
  display: flex;
  padding: 10px 0px;
}

.css-4srzau {
  display: block;
  position: relative;
  width: 200px;
  height: 117px;
  /* background-color: rgb(245, 245, 245); */
}

.css-7rpa25 {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  flex-direction: column;
  width: calc(100% - 90px);
  padding-left: 16px;
  padding-top: 10px;
}

.css-1nf3eox {
  display: -webkit-box;
  overflow: hidden;
  word-break: break-all;
  white-space: normal;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  line-height: 19px;
}

.css-4ejps8 {
  display: block;
}

a {
  background-color: transparent;
}

a {
  text-decoration: none;
  color: inherit;
}

.css-13kofxh {
  margin-top: 4px;
}

.css-13kofxh > span {
  margin-right: 4px;
  line-height: 19px;
}

.css-x4qt93 {
  color: rgb(250, 98, 47);
  font-weight: bold;
}

.css-1ufyr2r {
  color: rgb(181, 181, 181);
  font-size: 12px;
  text-decoration: line-through;
}

.css-2qdoqn {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  gap: 6px;
}

.css-2qdoqn > button {
  width: 100%;
}

.css-17giheb {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 104px;
  height: 36px;
  border-radius: 4px;
  color: rgb(51, 51, 51);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
}

[type="button"],
[type="reset"],
[type="submit"],
button {
  -webkit-appearance: button;
}

html,
button,
input,
select,
textarea {
  font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

.css-2qdoqn > button > span {
  font-size: 14px;
}

.css-x0zbdy {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 104px;
  height: 36px;
  border-radius: 4px;
  color: rgb(95, 0, 128);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(95, 0, 128);
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-1m3kac1 {
  display: inline-block;
  width: 20px;
  height: 20px;
  margin-right: 4px;
  vertical-align: middle;
}

img {
  border-style: none;
}

img,
legend {
  border: 0;
  vertical-align: top;
}

canvas,
img,
video {
  max-width: 100%;
}

img {
  overflow-clip-margin: content-box;
  overflow: clip;
  /* height: 130px; */
}

.css-ew8abz img {
  border-radius: 4px;
}

button,
input[type="button"],
input[type="reset"],
input[type="submit"] {
  -webkit-appearance: button;
  cursor: pointer;
}

.css-153tu4t {
  color: rgb(51, 51, 51);
  font-weight: bold;
}

.css-heioij {
  overflow: hidden;
  width: 650px;
  background-color: rgb(255, 255, 255);
  border-radius: 16px;
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
.css-0 {
  overflow: clip;
  height: 130px;
}
</style>
