<template>
  <div class="css-18tunez ei8jk451">
    <div class="css-1i60c0e extobua1">
      <h3 class="css-i804ml extobua0">
        {{
          currentCategory == "undefined"
            ? currentSearch == "undefined"
              ? "전체"
              : currentSearch == "진행 전"
              ? "진행 전"
              : `"${currentSearch}"에 대한 검색 결과`
            : currentCategory
        }}
      </h3>
      <!-- <ul class="css-raoddi erzdokb2">
                <li v-for="(category, index) in categories" :key="index" class="css-1h52dri erzdokb1"
                    @click="handleClick(index)">
                    <a
                        :class="{ 'category-selected': selectedIndex === index, 'category-unselected': selectedIndex !== index }">
                        {{ category }}
                    </a>
                </li>
            </ul> -->
    </div>

    <div class="css-uh04a1 e19n19480">
      <ul class="css-6q2h7w e19n19481">
        <ProductBoardListCardComponent
          v-for="data in dataList"
          :key="data.idx"
          :data="data"
        ></ProductBoardListCardComponent>
      </ul>
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
</template>

<script>
import ProductBoardListCardComponent from "./ProductBoardListCardComponent.vue";
import { useBoardStore } from "@/stores/useBoardStore";
import { mapStores } from "pinia";
export default {
  name: "ProductBoardListComponent",
  data() {
    return {
      num: 21,
      categories: ["의류", "가전제품", "가구", "식품", "스포츠", "악기"],
      selectedIndex: 0,
      selectedCategory: null,
      totalPages: 100,
      pages: [],
      pagesPerGroup: 5,
      dataList: [],
    };
  },
  components: {
    ProductBoardListCardComponent,
  },
  watch: {
    "$route.query.page": "getList",
    "$route.query.category": "getList",
    "$route.query.search": "getList",
  },
  created() {
    this.getList();
    this.selectedCategory = this.categories[0];
  },
  computed: {
    ...mapStores(useBoardStore),
    currentPage() {
      return Number(this.$route.query.page) || 1;
    },
    currentCategory() {
      return String(this.$route.query.category);
    },
    currentSearch() {
      return String(this.$route.query.search);
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
  methods: {
    async getList() {
      this.dataList = await this.boardStore.getList(
        this.currentPage,
        this.currentCategory,
        this.currentSearch
      );
      this.totalPages = this.dataList.totalPages;
      this.dataList = this.dataList.content;
      console.log(this.dataList);
    },
    handleClick(index) {
      this.selectedIndex = index;
      this.selectedCategory = this.categories[index];
    },
    // 특정 페이지로 이동
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
  },
};
</script>

<style scoped>
* {
  font-family: "Noto Sans KR", "malgun gothic", "AppleGothic", "dotum",
    "sans-serif";
}

.css-18tunez {
  position: relative;
  display: flex;
  flex-direction: column;
  margin-bottom: 80px;
}

.css-1i60c0e {
  width: 1200px;
  min-width: 900px;
  margin: 0px auto;
}

.css-i804ml {
  margin-top: 50px;
  margin-bottom: 50px;
  font-weight: 500;
  font-size: 28px;
  color: rgb(51, 51, 51);
  line-height: 35px;
  letter-spacing: -1px;
  text-align: center;
}

.css-raoddi {
  display: grid;
  grid-template-columns: repeat(4, 180px);
  gap: 16px 83px;
  overflow: hidden;
  margin-top: 28px;
  padding: 30px 40px;
  border: 1px solid rgb(226, 226, 226);
  line-height: 20px;
}

.category-selected {
  letter-spacing: -1px;
  cursor: pointer;
  font-weight: 700;
  color: rgb(95, 0, 128);
}

a {
  background-color: transparent;
}

a {
  text-decoration: none;
  color: inherit;
}

.css-1h52dri {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

li {
  display: list-item;
  text-align: -webkit-match-parent;
  unicode-bidi: isolate;
}

ol,
ul {
  list-style-type: none;
}

h3 {
  display: block;
  font-size: 1.17em;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
  unicode-bidi: isolate;
}

.css-1i60c0e {
  width: 1100px;
  margin: 0px auto;
  min-width: 1100px;
}

.css-uh04a1 {
  overflow: hidden;
  width: 1200px;
  min-width: 900px;
  margin: 0 auto 50px;
  padding: 0 50px 4px;
}

html,
body,
div,
span,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video,
input,
textarea,
button,
select {
  margin: 0;
  padding: 0;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

div {
  display: block;
  unicode-bidi: isolate;
}

.css-6q2h7w {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-flex-wrap: wrap;
  -webkit-flex-wrap: wrap;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  -webkit-align-items: flex-start;
  -webkit-box-align: flex-start;
  -ms-flex-align: flex-start;
  align-items: flex-start;
  -webkit-box-pack: start;
  -ms-flex-pack: start;
  -webkit-justify-content: flex-start;
  margin: 0 -10px;
}

ol,
ul,
li {
  list-style: none;
}

ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 3em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  unicode-bidi: isolate;
}

/* 카드 css */
.css-1tvfamc {
  width: 33%;
  padding: 0px 10px 95px;
}

.css-1tvfamc {
  flex: 0 0 auto;
  box-sizing: border-box;
}

li {
  display: list-item;
  text-align: -webkit-match-parent;
  unicode-bidi: isolate;
}

.css-3f55di {
  display: block;
}

a {
  -webkit-text-decoration: none;
  text-decoration: none;
  outline: none;
}

.category-unselected {
  letter-spacing: -1px;
  cursor: pointer;
}

.css-3f55di img {
  width: 100%;
}

img {
  vertical-align: top;
  border: 0;
}

img {
  overflow-clip-margin: content-box;
  overflow: clip;
}

a:-webkit-any-link {
  color: -webkit-link;
  cursor: pointer;
}

.css-wjc4eh {
  position: relative;
  padding-bottom: 30px;
}

.css-sdq6iq {
  margin-top: 13px;
  font-size: 26px;
  line-height: 32px;
  color: rgb(0, 0, 0);
}

h2 {
  display: block;
  font-size: 1.5em;
  margin-block-start: 0.83em;

  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
  unicode-bidi: isolate;
}

.css-11z2gb8 {
  min-height: 48px;
  margin-top: 6px;
  font-size: 15px;
  line-height: 24px;
  color: rgb(48, 48, 51);
}

p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  unicode-bidi: isolate;
}

.css-1712oqi {
  position: relative;
  height: 2px;
  margin-top: 40px;
  background: rgb(228, 228, 228);
}

/* 주황 막대 부분 */

.css-1d3r7u5 {
  position: absolute;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  visibility: hidden;
}

.css-2ugc89 {
  position: absolute;
  top: -41px;
  box-sizing: border-box;
  min-width: 48px;
  padding: 0px 10px;
  font-size: 14px;
  font-weight: 800;
  line-height: 30px;
  color: rgb(255, 255, 255);
  text-align: center;
  white-space: nowrap;
  background: rgb(255, 72, 0);
  transition: 0.4s;
}

.css-2ugc89::after {
  content: "";
  position: absolute;
  bottom: -5px;
  left: 50%;
  margin-left: 0px;
  border-width: 5px 5px 0px 0px;
  border-style: solid;
  border-top-color: rgb(255, 72, 0);
  border-right-color: transparent;
}

.css-eio8nx {
  position: absolute;
  top: -41px;
  box-sizing: border-box;
  min-width: 48px;
  padding: 0px 10px;
  font-size: 14px;
  font-weight: 800;
  line-height: 30px;
  color: rgb(255, 255, 255);
  text-align: center;
  white-space: nowrap;
  background: rgb(255, 72, 0);
  transition: 0.4s;
}

.css-eio8nx::after {
  content: "";
  position: absolute;
  bottom: -5px;
  left: 50%;
  margin-left: 0px;
  border-width: 5px 5px 0px 0px;
  border-style: solid;
  border-top-color: rgb(255, 72, 0);
  border-right-color: transparent;
}

.css-eio8nx {
  position: absolute;
  top: -41px;
  box-sizing: border-box;
  min-width: 48px;
  padding: 0px 10px;
  font-size: 14px;
  font-weight: 800;
  line-height: 30px;
  color: rgb(255, 255, 255);
  text-align: center;
  white-space: nowrap;
  background: rgb(255, 72, 0);
  transition: 0.4s;
}

.css-eio8nx::after {
  content: "";
  position: absolute;
  bottom: -5px;
  left: 50%;
  margin-left: 0px;
  border-width: 5px 5px 0px 0px;
  border-style: solid;
  border-top-color: rgb(255, 72, 0);
  border-right-color: transparent;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

.css-1d3r7u5 {
  position: absolute;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  visibility: hidden;
}

.css-8frsro {
  position: absolute;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
}

.css-e335q6 {
  position: absolute;
  top: 0px;
  left: 0px;
  height: 2px;
  background: rgb(255, 72, 0);
}

/* 주황 막대 부분 종료 */

.css-islv3w {
  display: block;
  margin-top: 16px;
  font-size: 16px;
  font-weight: 700;
  line-height: 20px;
  color: rgb(0, 0, 0);
}

.css-12f2xxd:empty {
  margin: 0px;
}

.css-12f2xxd {
  margin-right: 10px;
  color: rgb(255, 72, 0);
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

a {
  background-color: transparent;
}

a {
  text-decoration: none;
  color: inherit;
}

*,
:after,
:before {
  box-sizing: border-box;
  margin: 0;
}

.cursor-pointer {
  cursor: pointer;
}

.relative {
  position: relative;
}

.w-6 {
  width: 1.5rem;
}

.h-6 {
  height: 1.5rem;
}

.pointer-events-none {
  pointer-events: none;
}

svg {
  display: block;
  vertical-align: middle;
}

.z-10 {
  z-index: 10;
}

.top-2 {
  top: 0.5rem;
}

.right-2 {
  right: 1rem;
}

.absolute {
  position: absolute;
}

.absolute-parent {
  position: relative;
}

.space-y-12 > :not([hidden]) ~ :not([hidden]) {
  --tw-space-y-reverse: 0;
  margin-top: calc(0.75rem * calc(1 - var(--tw-space-y-reverse)));
  margin-bottom: calc(0.75rem * var(--tw-space-y-reverse));
}

.mr-20 {
  margin-right: 1.25rem;
}

.text-xs-bold {
  font-weight: 700;
}

.text-xs,
.text-xs-bold {
  font-size: 11px;
  line-height: 136%;
}

.align-baseline {
  vertical-align: baseline;
}

.gap-2 {
  gap: 0.125rem;
}

.items-center {
  align-items: center;
}

.flex {
  display: flex;
}

.mb-2 {
  margin-bottom: 0.125rem;
}

button {
  cursor: pointer;
  background: transparent;
  border: 0;
  outline: none;
}

.space-y-2 > :not([hidden]) ~ :not([hidden]) {
  --tw-space-y-reverse: 0;
  margin-top: calc(0.125rem * calc(1 - var(--tw-space-y-reverse)));
  margin-bottom: calc(0.125rem * var(--tw-space-y-reverse));
}

.text-s,
.text-s-bold {
  font-size: 12px;
  line-height: 136%;
}

.text-s {
  font-weight: 400;
}

.line-clamp-2 {
  -webkit-line-clamp: 2;
}

.line-clamp-1,
.line-clamp-2 {
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
}

.text-l-bold {
  font-weight: 700;
}

.text-l,
.text-l-bold {
  font-size: 14px;
  line-height: 140%;
}

.gap-2 {
  gap: 0.125rem;
}

.items-center {
  align-items: center;
}

.flex {
  display: flex;
}

p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  unicode-bidi: isolate;
}

.line-clamp-2 {
  color: black;
}

.css-1066lcq {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  margin-top: 10px;
}

.css-1fttcpj {
  display: flex;
  flex-direction: column;
}

.css-1nqogjr .product-info .product-price .dimmed-price {
  display: block;
  padding-top: 2px;
  color: rgb(181, 181, 181);
  font-size: 14px;
  font-weight: 400;
  line-height: 18px;
  text-decoration: line-through;
}

.css-105zgxh {
  color: rgb(34, 34, 34);
  white-space: nowrap;
}

.css-1nqogjr .product-info .product-price .discount-rate {
  margin-right: 7px;
}

.css-1nqogjr .product-info .product-price .discount-rate,
.css-1nqogjr .product-info .product-price .sales-price {
  font-size: 16px;
  font-weight: 700;
  line-height: 1.5;
  white-space: nowrap;
}

.css-19lkxd2 {
  color: rgb(250, 98, 47);
}

.css-1nqogjr .product-info .product-price .discount-rate,
.css-1nqogjr .product-info .product-price .sales-price {
  font-size: 16px;
  font-weight: 700;
  line-height: 1.5;
  white-space: nowrap;
}

.css-1nqogjr .product-info .product-price .dimmed-price {
  display: block;
  padding-top: 2px;
  color: rgb(181, 181, 181);
  font-size: 14px;
  font-weight: 400;
  line-height: 18px;
  text-decoration: line-through;
}

.discount {
  margin-top: 0.1rem;
}

.discount-rate {
  margin-right: 0.3rem;
}

.dimmed-price-div > .dimmed-price {
  display: block;
  padding-top: 2px;
  color: rgb(181, 181, 181);
  font-size: 16px;
  font-weight: 400;
  line-height: 18px;
  text-decoration: line-through;
}

.line-clamp-2 {
  font-size: 15px;
  font-weight: 500;
}

.product-price {
  font-weight: 700;
}
</style>
