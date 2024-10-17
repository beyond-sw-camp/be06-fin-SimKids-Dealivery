<template>
  <ul class="list_ul">
    <li v-for="data in dataList" :key="data.id">
      <div class="date">
        {{ getDuration(data.startedAt, data.endedAt) }} ({{
          getDurationTime(data.startedAt, data.endedAt)
        }}
        진행)
      </div>
      <div class="list_elems">
        <div class="image">
          <img :src="data.productThumbnailUrl" alt="상품 이미지" />
        </div>
        <div class="name">
          <a @click.prevent="goToBoardPost(data.idx)">{{ data.title }}</a>
        </div>
        <div class="elem_info">
          <div class="desc">
            <dl>
              <dt>카테고리</dt>
              <dd>{{ data.category }}</dd>
            </dl>
          </div>
        </div>
        <div class="elem_status_box">
          <span class="inner_status">
            <div
              :class="{
                status: true,
                complete: data.status === '진행 완료',
              }"
            >
              {{ data.status }}
            </div>
            <form action="#">
              <button
                @click="deleteItem(data.idx)"
                class="link ga_tracking_event"
                :disabled="data.status === '진행중'"
              >
                삭제
              </button>
            </form>
          </span>
        </div>
      </div>
    </li>
  </ul>
  <div class="no_order_data" v-if="dataList === null">
    {{ noDataMsg }}
  </div>
</template>

<script>
export default {
  name: "ListCardComponent",
  props: {
    noDataMsg: {
      type: String,
      default: "데이터가 없습니다.",
    },
    dataList: {
      type: Array,
      default: null,
    },
  },
  data() {
    return {};
  },
  methods: {
    getDuration(stratedAt, endedAt) {
      const startDate = new Date(stratedAt);
      const endDate = new Date(endedAt);

      const formatDate = (date) => {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");
        const hour = String(date.getHours()).padStart(2, "0");
        const minute = String(date.getMinutes()).padStart(2, "0");
        return `${year}-${month}-${day} ${hour}:${minute}`;
      };

      const formattedStartDate = formatDate(startDate);
      const formattedEndDate = formatDate(endDate);

      return `${formattedStartDate} ~ ${formattedEndDate}`;
    },
    getDurationTime(startedAt, endedAt) {
      const timeDifference = new Date(endedAt) - new Date(startedAt);

      return parseInt(timeDifference / (1000 * 60 * 60)) + "시간";
    },
    deleteItem(idx) {
      this.$emit("deleteItem", idx);
    },
    goToBoardPost(idx) {
      this.$router.push({
        name: "CompanyBoardPostPage",
        params: { idx: idx },
      });
    },
  },
};
</script>

<style scoped>
a,
body,
button,
dd,
div,
dl,
dt,
form,
li,
span,
ul {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
@font-face {
  font-family: nanum gothic;
  font-style: normal;
  font-weight: 400;
  src: url(https://res.kurly.com/fonts/NanumGothic-Regular.woff2)
      format("woff2"),
    url(https://res.kurly.com/fonts/NanumGothic-Regular.woff) format("woff"),
    url(https://res.kurly.com/fonts/NanumGothic-Regular.otf) format("opentype");
}
@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 200;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Light.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Light.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Light.otf) format("opentype");
}
@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 400;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Regular.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Regular.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Regular.otf) format("opentype");
}
@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 700;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Medium.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Medium.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Medium.otf) format("opentype");
}
@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 800;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Bold.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Bold.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Bold.otf) format("opentype");
}
a,
body,
button,
dd,
div,
dl,
dt,
form,
li,
span,
ul {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body,
html {
  height: 100%;
}
body,
button {
  font-family: noto sans, malgun gothic, AppleGothic, dotum;
  line-height: 1;
  letter-spacing: -0.05em;
  color: #4c4c4c;
  font-size: 15px;
  max-width: 100%;
}
dd,
div,
dt,
li {
  word-break: break-all;
}
img {
  max-width: 100%;
}
img {
  border: none;
  vertical-align: top;
}
button {
  outline: 0;
  background-color: transparent;
  border: none;
  cursor: pointer;
}
a {
  text-decoration: none;
  background-color: transparent;
  color: inherit;
}
a:active,
a:hover {
  outline: 0;
  cursor: pointer;
}
li {
  list-style: none;
}
::after,
::before {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.section_orderlist .list_ul {
  padding-top: 10px;
  border-top: 2px solid #333;
  margin-bottom: 100px;
}
.section_orderlist .list_ul .date {
  padding-top: 20px;
  font-size: 16px;
  line-height: 24px;
  font-weight: 700;
  color: #666;
}
.section_orderlist .list_ul .list_elems {
  position: relative;
  margin-top: 10px;
  padding: 0 20px;
  border: 1px solid #dddfe1;
}
.section_orderlist .list_ul .name {
  padding: 20px 0 13px;
  border-bottom: 1px solid #dddfe1;
}
.section_orderlist .list_ul .name a {
  display: block;
  overflow: hidden;
  background: url(https://res.kurly.com/pc/ico/1806/ico_arrow_10x15.png)
    no-repeat 100% 1px;
  font-size: 16px;
  line-height: 24px;
  font-weight: 700;
  color: #000;
  cursor: pointer;
}
.section_orderlist .list_ul .elem_info {
  overflow: hidden;
  padding: 14px 0 20px;
}
.section_orderlist .list_ul .desc {
  overflow: hidden;
  padding-top: 1px;
}
.section_orderlist .list_ul .elem_info dl:first-child {
  padding-top: 3px;
}
.section_orderlist .list_ul .elem_info dl {
  overflow: hidden;
  padding-top: 6px;
}
.section_orderlist .list_ul .elem_info dt {
  float: left;
  padding-right: 15px;
  font-size: 12px;
  color: #000;
  line-height: 20px;
}
.section_orderlist .list_ul .elem_info dd {
  float: left;
  font-size: 14px;
  line-height: 20px;
  font-weight: 700;
  color: #000;
}
.elem_info {
  display: flex;
}
.section_orderlist .list_ul .elem_status_box {
  display: table;
  position: absolute;
  right: 57px;
  bottom: 90px;
  height: 114px;
  vertical-align: middle;
}
.section_orderlist .list_ul .inner_status {
  display: table-cell;
  vertical-align: middle;
}
/* .section_orderlist .list_ul .elem_status_box .link {
  display: block;
  width: 90px;
  height: 34px;
  border: 1px solid #5f0080;
  background-color: #fff;
  font-size: 12px;
  color: #5f0080;
  line-height: 32px;
  text-align: center;
  cursor: default;
} */
.no_order_data {
  border-bottom: 1px solid #dddfe1;
  padding: 90px 0 220px 0;
  font-size: 15px;
  color: #757575;
  text-align: center;
}
.section_orderlist .list_ul .elem_status_box .link {
  display: block;
  width: 90px;
  height: 34px;
  border: 1px solid #5f0080;
  background-color: #fff;
  font-size: 12px;
  color: #5f0080;
  line-height: 32px;
  text-align: center;
  margin-bottom: 5px;
  cursor: pointer;
  margin-top: 5px;
}

.section_orderlist .list_ul .elem_status_box .inner_status .status {
  display: block;
  width: 90px;
  height: 34px;
  border: 1px solid #5f0080;
  background-color: #fff;
  font-size: 12px;
  color: #5f0080;
  line-height: 32px;
  text-align: center;
  margin-bottom: 5px;
  cursor: default;
  margin-top: 5px;
}

.section_orderlist .list_ul .elem_status_box .complete {
  display: block;
  width: 90px;
  height: 34px;
  border: 1px solid #333;
  background-color: #fff;
  font-size: 12px;
  color: #333;
  line-height: 32px;
  text-align: center;
  margin-bottom: 5px;
  cursor: default;
  margin-top: 5px;
}

.list_elems .image {
  margin-right: 20px;
  margin-top: 20px;
  width: 300px;
  height: 150px;
}
.list_elems .image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
button:disabled {
  background-color: #ccc !important;
  cursor: default !important;
  color: white !important;
  border: 1px solid !important;
}
</style>
