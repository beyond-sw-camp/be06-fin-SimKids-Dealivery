<template>
  <div>
    <div class="css-1xdhyk6 eug5r8l0">
      <div class="css-10ekv2i e13vhfr40">
        <div class="css-1phmj5u e190ng8o0">
          <div class="css-2kmie e1g49j8k1">
            <div class="css-peeqro e190ng8o1">
              <div class="css-jyp95e e1l5ke4x0">
                <button @click="showOrderDetail" class="css-7sy6n e1rmfz7b0">
                  <img
                    src="@/assets/arrow.png"
                    alt="icon"
                    width="40"
                    height="40"
                    style="transition: 'transform 0.3s'"
                  />
                </button>
                <div class="css-7uztss e1rmfz7b4">
                  <div>
                    <p
                      class="_97oqoup _97oqouv _97oqou5 ldmw177c _97oqou19 _97oqouj ldmw177q _97oqou12 _97oqouc ldmw177j css-wcc2f6 e1rmfz7b3"
                    >
                      {{ formattedDate(order.createdAt) }}
                    </p>
                    <div class="css-k7chvl e1rmfz7b1">
                      <p
                        class="_97oqoup _97oqoux _97oqou7 ldmw177a _97oqou1b _97oqoul ldmw177s _97oqou13 _97oqoud ldmw177i css-1vgapaa e1rmfz7b2"
                      >
                        주문번호 {{ order.ordersNumber }}
                      </p>
                    </div>
                  </div>
                </div>
                <div
                  height="1px"
                  width="100%"
                  class="css-yng2ii e1ypu1ln0"
                ></div>
                <div class="css-1t4yb7q e1xhbacy2">
                  <div class="css-wnosz2 e1xhbacy0">
                    <p
                      class="_97oqoup _97oqouv _97oqou5 ldmw177c _97oqou19 _97oqouj ldmw177q _97oqou12 _97oqouc ldmw177j css-1n1zmlq e1xhbacy1"
                    >
                      {{ order.status }}
                    </p>
                  </div>
                </div>
                <div class="css-vts8bc e9fxgjh2">
                  <div class="css-1tf2711 e73mjag8">
                    <a href="/goods/5054274" class="css-1ll9bqd e73mjag0">
                      <img
                        :src="order.thumnailUrl"
                        class="css-13pph03 e73mjag7"
                      />
                    </a>
                    <div class="_17g6wc40">
                      <a href="/goods/5054274" class="css-1ll9bqd e73mjag0">
                        <p
                          class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-1dl78ek e73mjag3"
                        >
                          {{ order.title }}
                        </p>
                      </a>
                      <div class="css-1tf2711 e73mjag8">
                        <p
                          class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou11 _97oqoub ldmw177k css-1dl78ek e73mjag4"
                        >
                          {{
                            Math.round(
                              order.minimumPrice *
                                (1 - order.discountRate / 100)
                            ).toLocaleString()
                          }}원
                        </p>
                        <p class="css-if5wh3 e73mjag1">
                          {{ order.minimumPrice.toLocaleString() }}원
                        </p>
                        <div
                          width="1px"
                          height="10px"
                          class="css-9ib26w e1ypu1ln0"
                        ></div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="css-8vtrb0 eus1fbt1"></div>
              </div>
            </div>
          </div>
          <div class="css-21ijkk e190ng8o3"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isDetailsVisible: false,
      arrowRotated: false,
    };
  },
  props: {
    order: {
      type: Object,
      required: true,
    },
  },
  methods: {
    showOrderDetail() {
      this.$router.push({ path: `/mypage/order/${this.order.orderIdx}` });
    },
    formattedDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);

      return `${year}.${month}.${day}`;
    },
  },
};
</script>

<style scoped>
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

html {
  line-height: 1.15;
  -webkit-text-size-adjust: 100%;
}

.css-u71x2d {
  position: relative;
  min-width: 1050px;
  background-color: rgb(242, 245, 248);
  padding: 20px 0;
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
  border-radius: 16px;
  margin-bottom: 0px;
}

.css-oc8mjz {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  margin: 0px 20px;
  padding: 25px 0px 20px;
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

.css-10ekv2i {
  background-color: #f2f5f8;
}

.css-1phmj5u {
  background-color: #f2f5f8;
}

.css-2kmie {
  position: relative;
  margin-top: 0px;
  transition: transform, 0.2s ease-out;
  will-change: transform;
  overscroll-behavior-y: contain;
}

.css-jyp95e {
  padding: 20px 16px;
  background-color: #fff;
  border-radius: 16px;
}

.css-7uztss {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  /* 여백 추가 */
}

.css-wcc2f6 {
  padding-bottom: 2px;
  color: #222;
  margin-bottom: 5px;
}

.ldmw177q {
  line-height: 26px;
}

.ldmw177j {
  font-weight: 600;
}

.ldmw177c {
  font-size: 18px;
}

p {
  display: block;
  margin-block-start: 0.2em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  unicode-bidi: isolate;
}

.css-k7chvl {
  cursor: pointer;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

.css-1vgapaa {
  color: #848f9a;
  padding-right: 4px;
}

.ldmw177s {
  line-height: 20px;
}

.ldmw177i {
  font-weight: 400;
}

.ldmw177a {
  font-size: 14px;
}

.css-7sy6n {
  width: 75px;
  height: 40px;
  flex-shrink: 0;
  position: absolute;
  right: -6px;
}

html,
button,
input,
select,
textarea {
  font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

[type="button"],
[type="reset"],
[type="submit"],
button {
  -webkit-appearance: button;
}

._1k44z3a0,
.hbkc73d {
  align-items: center;
  justify-content: center;
}

._1k44z3a0 {
  display: inline-flex;
}

i {
  font-style: italic;
}

button,
input {
  overflow: visible;
}

button,
input,
optgroup,
select,
textarea {
  font-family: inherit;
  font-size: 100%;
  line-height: 1.15;
  margin: 0;
}

button {
  border-radius: 0;
}

button {
  overflow: visible;
  background-color: transparent;
  border: none;
}

svg:not(:root) {
  overflow: hidden;
}

button,
input[type="button"],
input[type="reset"],
input[type="submit"] {
  -webkit-appearance: button;
  cursor: pointer;
}

.css-yng2ii {
  min-width: 100%;
  min-height: 1px;
  margin: 16px 0 20px 0;
  background-color: #eceff3;
}

.css-1t4yb7q {
  display: flex;
  margin-bottom: 16px;
  -webkit-box-pack: justify;
  justify-content: space-between;
}

.css-wnosz2 {
  display: inline-flex;
}

.css-1n1zmlq:first-of-type {
  margin-right: 4px;
}

.css-1n1zmlq {
  color: #8d4cc4;
}

.css-vts8bc {
  padding-bottom: 16px;
}

.css-1tf2711 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: start;
  justify-content: flex-start;
  -webkit-box-align: center;
  align-items: center;
}

.css-1ll9bqd {
  cursor: default;
}

a {
  background-color: transparent;
}

a {
  text-decoration: none;
  color: inherit;
}

.css-13pph03 {
  min-width: 56px;
  width: 56px;
  height: 72px;
  margin-right: 12px;
  background-color: rgb(245, 245, 245);
  border-radius: 8px;
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
}

._17g6wc40,
.tew5wjw {
  box-sizing: border-box;
}

.css-s5xdrg {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}

.css-1fqzsmf {
  color: #848f9a;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.ldmw177t {
  line-height: 18px;
}

.ldmw1779 {
  font-size: 13px;
}

.css-1dl78ek {
  color: #222;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.ldmw177r {
  line-height: 22px;
}

.ldmw177b {
  font-size: 16px;
}

.ldmw177k {
  font-weight: 700;
}

.css-if5wh3 {
  color: #505760;
  font-size: 13px;
  line-height: 18px;
  text-decoration-line: line-through;
  margin-left: 4px;
}

.css-9ib26w {
  min-width: 1px;
  min-height: 10px;
  margin: 0 6px;
  background-color: #eceff3;
}

.css-1dl5ekv {
  color: 18px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-8vtrb0 {
  display: flex;
}

.css-21ijkk {
  width: 100%;
  height: 1px;
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

body,
button,
input,
select,
textarea {
  font-size: 14px;
  color: #333;
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

.css-ha2unt {
  padding-bottom: 20px;
  color: rgb(51, 51, 51);
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: 28px;
}

.css-1gn0w45 {
  min-width: 100%;
  min-height: 2px;
  margin: 0px;
  background-color: rgb(51, 51, 51);
}

.css-1ca7lx5 {
  background: #fff;
  padding: 16px 5px;
  border-radius: 0 0 10px 10px;
}

.css-fhxb3m {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
}

.css-kegbry p:first-of-type {
  margin-bottom: 2px;
}

.css-1pjpfkv {
  color: #848f9a;
}

.ldmw177s {
  line-height: 20px;
}

.ldmw177i {
  font-weight: 400;
}

.ldmw177a {
  font-size: 14px;
}

.css-10ijdu3 {
  color: #222;
}

.ldmw177r {
  line-height: 22px;
}

.ldmw177j {
  font-weight: 600;
}

.ldmw177b {
  font-size: 16px;
}

.css-8w1507 {
  min-width: 100%;
  min-height: 1px;
  margin: 16px 0;
  background-color: #eceff3;
}

.css-l8gklh {
  color: #565e67;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-1o9lzzy {
  padding: 20px 0 12px;
  color: #222;
}

.ldmw177p {
  line-height: 28px;
}

.ldmw177d {
  font-size: 18px;
}

.css-gtsx9s {
  border-radius: 16px;
  background: #fff;
  padding: 16px;
}

.css-1t4yb7q {
  display: flex;
  margin-bottom: 16px;
  -webkit-box-pack: justify;
  justify-content: space-between;
}

.css-ywohcx {
  display: inline-flex;
  margin-top: 4px;
}

.css-1n1zmlq:first-of-type {
  margin-right: 4px;
}

.css-1n1zmlq {
  color: #7542a1;
}

.ldmw177q {
  line-height: 26px;
}

.ldmw177c {
  font-size: 18px;
}

.css-16s18qq > div {
  margin-bottom: 20px;
}

.css-1tf2711 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: start;
  justify-content: flex-start;
  -webkit-box-align: center;
  align-items: center;
}

a {
  background-color: transparent;
}

a {
  text-decoration: none;
  color: inherit;
}

.css-13pph03 {
  min-width: 56px;
  width: 56px;
  height: 72px;
  margin-right: 12px;
  background-color: rgb(245, 245, 245);
  border-radius: 8px;
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

._17g6wc40,
.tew5wjw {
  box-sizing: border-box;
}

.css-s5xdrg {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

.css-1fqzsmf {
  color: #848f9a;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.ldmw177t {
  line-height: 18px;
}

.ldmw1779 {
  font-size: 13px;
}

.css-n86rv6 {
  color: #222;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-1dl78ek {
  color: #222;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-if5wh3 {
  color: #bcc4cc;
  font-size: 13px;
  line-height: 18px;
  text-decoration-line: line-through;
  margin-left: 4px;
}

.css-9ib26w {
  min-width: 1px;
  min-height: 10px;
  margin: 0 6px;
  background-color: #eceff3;
}

.css-1dl5ekv {
  color: #565e67;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-8vtrb0 {
  display: flex;
}

.css-f848a6 {
  display: flex;
  width: 100%;
  flex-direction: row;
  padding: 20px 10px 4px;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  background-color: transparent;
}

html,
button,
input,
select,
textarea {
  font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

[type="button"],
[type="reset"],
[type="submit"],
button {
  -webkit-appearance: button;
}

button,
select {
  text-transform: none;
}

button,
input {
  overflow: visible;
}

.css-y11erl {
  display: flex;
  rotate: -180deg;
}

.css-d3v9zr {
  overflow: hidden;
}

.css-1a0zxau {
  border-radius: 16px;
  background: #fff;
  padding: 20px 16px;
}

.css-1aim50k:not(:last-of-type) {
  margin-bottom: 12px;
}

.css-1aim50k {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
}

.css-15bah7q {
  color: #848f9a;
}

.css-8yre18 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

.css-uwqhso {
  color: #a7b2bc;
}

.css-6z4447 {
  border-radius: 16px;
  background: #fff;
  padding: 20px 16px;
}

.css-6z4447 > p:not(:last-of-type) {
  margin-bottom: 6px;
}

.css-luewyl {
  color: #222;
}

.css-4qwok1 {
  color: #848f9a;
}

.css-303o8l {
  color: #a7b2bc;
}

.css-od0sqq {
  border-radius: 16px;
  background: #fff;
  padding: 20px 16px;
  margin-top: 20px;
}

.css-1fdfbqy {
  position: relative;
  color: #848f9a;
  padding-left: 10px;
}

.css-1fdfbqy ~ p {
  margin-top: 4px;
}

.tew5wj1l:disabled {
  color: #222;
  background-color: #eceff3;
}

button[disabled],
input[disabled] {
  cursor: pointer;
}

.css-jz9jxv {
  width: 100%;
  margin-top: 16px;
}

.tew5wj19 {
  border-width: 1px;
  border-style: solid;
}

.ldmw171du {
  border-radius: 10px;
}

.ldmw17183 {
  padding-right: 16px;
}

.ldmw1717i {
  padding-left: 16px;
}

.ldmw1715v {
  height: 48px;
}

.ldmw17y6 {
  border-color: #dfe4eb;
}

.ldmw177b {
  font-size: 16px;
}

.css-3dze2x {
  overflow: hidden;
  width: 650px;
  background-color: transparent;
  border-radius: 16px;
}

.css-1xdhyk6 {
  background-color: rgb(255, 255, 255);
  border-radius: 16px 16px 0 0;
}

button,
input,
optgroup,
select,
textarea {
  font-family: inherit;
  font-size: 100%;
  line-height: 1.15;
  margin: 0;
}

.tew5wjw {
  overflow: hidden;
  transition: all 0.1s ease-out;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border-width: 0;
}
</style>