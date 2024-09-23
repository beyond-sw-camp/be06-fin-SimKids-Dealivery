<template>
  <div>
    <ul class="list_order">
      <li v-for="data in dataList" :key="data.id">
        <div class="date">{{ formattedDate(data.modifiedAt) }}</div>
        <div class="order_goods">
          <div class="name">
            <a
              href="#none"
              class="order-link"
              @click="displayModal(data.orderIdx)"
              >{{ data.title }}</a
            >
          </div>
          <div class="order_info">
            <div class="desc">
              <dl>
                <dt>주문번호</dt>
                <dd>{{ data.ordersNumber }}</dd>
              </dl>

              <dl>
                <dt>결제금액</dt>
                <dd>{{ data.totalPaidAmount }}원</dd>
              </dl>
            </div>

            <div class="desc" style="margin-left: 50px">
              <dl>
                <dt>주문자명</dt>
                <dd>{{ data.ordererName }}</dd>
              </dl>

              <dl>
                <dt>주문방법</dt>
                <dd>
                  {{ data.payMethod == "kakaopay" ? "카카오페이" : "토스페이" }}
                </dd>
              </dl>
            </div>
          </div>
          <div class="order_status">
            <span class="inner_status">
              <div
                style="line-height: 24px"
                class="order_status_box"
                :class="{
                  completed: data.status === '주문 완료',
                  cancelled: data.status === '주문 취소',
                }"
              >
                {{ data.status }}
              </div>
            </span>
          </div>
        </div>
      </li>
    </ul>
    <div v-if="isDisplayModal">
      <CompanyOrderModalComponent
        @closeModal="closeModal"
        :orderIdx="selectedOrderIdx"
      />
    </div>
  </div>
</template>

<script>
import CompanyOrderModalComponent from "./CompanyOrderModalComponent.vue";

export default {
  name: "OrderListCardComponent",
  components: {
    CompanyOrderModalComponent,
  },
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
    return {
      isDisplayModal: false,
      selectedOrderIdx: null,
    };
  },
  methods: {
    displayModal(orderIdx) {
      this.isDisplayModal = !this.isDisplayModal;
      this.selectedOrderIdx = orderIdx;
    },
    closeModal() {
      this.isDisplayModal = false;
      this.selectedOrderIdx = null;
    },

    formattedDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);
      const hours = ("0" + date.getHours()).slice(-2);
      const minutes = ("0" + date.getMinutes()).slice(-2);

      return `${year}-${month}-${day} ${hours}:${minutes}`;
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

.section_orderlist .list_order .order_status .order_status_box {
  display: inline-block;
  padding: 5px 5px 5px 5px; /* 내부 여백 */
  font-size: 14px;
  font-weight: bold;
  color: #fff;
  border-radius: 20px; /* 둥근 모서리 */
  text-align: center;
  cursor: default;
  margin: 0 auto;
  margin-top: 14px;
}

.section_orderlist .list_order .order_status .order_status_box.completed {
  background-color: #5f0080;
  border: 1px solid #5f0080;
}

.section_orderlist .list_order .order_status .order_status_box.cancelled {
  background-color: #c5c5c5; /* 회색 배경 */
  border: 1px solid #c5c5c5;
}
</style>
