<template>
  <ul class="list_order">
    <li>
      <div class="date">2020.12.17 (12시 29분)</div>
      <div class="order_goods">
        <div class="name">
          <a href="#none" class="order-link" @click="displayModal"
            >[Dole] 후릇컵 3종 외 2건</a
          >
          <div v-if="isDisplayModal">
            <CompanyOrderModalComponent @closeModal="displayModal" />
          </div>
        </div>
        <div class="order_info">
          <div class="desc">
            <dl>
              <dt>주문번호</dt>
              <dd>1608175672040</dd>
            </dl>

            <dl>
              <dt>결제금액</dt>
              <dd>13,560원</dd>
            </dl>
          </div>

          <div class="desc" style="margin-left: 50px">
            <dl>
              <dt>주문자명</dt>
              <dd>심키즈</dd>
            </dl>

            <dl>
              <dt>주문방법</dt>
              <dd>카카오페이</dd>
            </dl>
          </div>
        </div>
        <div class="order_status">
          <span class="inner_status">
            <div class="order_status_box">주문 완료</div>
          </span>
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
import CompanyOrderModalComponent from "./CompanyOrderModalComponent.vue";

export default {
  name: "OrderListCardComponent",
  components: {
    CompanyOrderModalComponent,
  },
  props: {},
  data() {
    return {
      isDisplayModal: false,
    };
  },
  methods: {
    displayModal() {
      this.isDisplayModal = !this.isDisplayModal;
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

.section_orderlist .list_order {
  padding-top: 10px;
  border-top: 2px solid #333;
  margin-bottom: 100px;
}

.section_orderlist .list_order .date {
  padding-top: 20px;
  font-size: 16px;
  line-height: 24px;
  font-weight: 700;
  color: #666;
}

.section_orderlist .list_order .order_goods {
  position: relative;
  margin-top: 10px;
  padding: 0 20px;
  border: 1px solid #dddfe1;
}

.section_orderlist .list_order .name {
  padding: 20px 0 13px;
  border-bottom: 1px solid #dddfe1;
}

.section_orderlist .list_order .name a {
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

.section_orderlist .list_order .order_info {
  overflow: hidden;
  padding: 14px 0 20px;
}

.section_orderlist .list_order .desc {
  overflow: hidden;
  padding-top: 1px;
}

.section_orderlist .list_order .order_info dl:first-child {
  padding-top: 3px;
}

.section_orderlist .list_order .order_info dl {
  overflow: hidden;
  padding-top: 6px;
}

.section_orderlist .list_order .order_info dt {
  float: left;
  padding-right: 15px;
  font-size: 12px;
  color: #000;
  line-height: 20px;
}

.section_orderlist .list_order .order_info dd {
  float: left;
  font-size: 14px;
  line-height: 20px;
  font-weight: 700;
  color: #000;
}

/* .section_orderlist .list_order .order_info .end {
  color: #514859;
} */

.order_info {
  display: flex;
}

.section_orderlist .list_order .order_status {
  display: table;
  position: absolute;
  right: 57px;
  bottom: 0;
  height: 114px;
  vertical-align: middle;
}

.section_orderlist .list_order .inner_status {
  display: table-cell;
  vertical-align: middle;
}

.section_orderlist .list_order .order_status .link_review {
  margin-bottom: 4px;
  border: 1px solid #512771;
  background-color: #5f0080;
  color: #fff;
}

.section_orderlist .list_order .order_status .link {
  display: block;
  width: 90px;
  height: 34px;
  border: 1px solid #5f0080;
  background-color: #fff;
  font-size: 12px;
  color: #5f0080;
  line-height: 32px;
  text-align: center;
  cursor: pointer;
}

.section_orderlist .list_order .order_status .order_status_box {
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
}

#user_order_state {
  margin-bottom: 6px;
  padding-left: 10px;
  margin-top: 10px;
}

/* select:focus {
  outline: none;
} */

.no_order_data {
  display: none;
  border-bottom: 1px solid #dddfe1;
  padding: 90px 0 220px 0;
  font-size: 15px;
  color: #757575;
  text-align: center;
}
</style>
