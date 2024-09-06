<template>
  <div id="viewOrderList" class="page_section section_orderlist">
    <div class="head_aticle">
      <h2 class="tit">게시글 등록</h2>
    </div>

    <div class="list_order">
      <!-- <form action="" method="post" class=""> -->
      <div class="size">
        <div class="p_board">
          <table width="100%">
            <tbody>
              <tr>
                <th>기간 설정</th>
                <td colspan="2">
                  <div class="datetime-container">
                    <input
                      type="datetime-local"
                      id="starttime"
                      name="starttime"
                      class="datetime-input"
                    />
                    <span class="separator">~</span>
                    <input
                      type="datetime-local"
                      id="endtime"
                      name="endtime"
                      class="datetime-input"
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th>게시글 제목</th>
                <td colspan="2">
                  <div>
                    <span>
                      <input
                        type="text"
                        name="product_name"
                        class="i_text text1"
                        onkeyup="chkword_name(this, 80)"
                      />
                    </span>
                  </div>
                </td>
              </tr>

              <tr>
                <th>상품 추가</th>
                <td colspan="2">
                  <button id="board-link" @click="displayModal">추가</button>
                  <div v-if="isDisplayModal">
                    <CompanyBoardModalComponent @closeModal="displayModal" />
                  </div>
                </td>
              </tr>
              <tr>
                <th>카테고리</th>
                <td>
                  <div>
                    <span>
                      <select name="product_category" class="product_category">
                        <option value="">------- 선택하세요 -------</option>
                        <option>식품</option>
                        <option>의류</option>
                        <option>뷰티</option>
                        <option>라이프</option>
                      </select>
                    </span>
                  </div>
                </td>
              </tr>

              <tr class="product_detail">
                <th>이미지 등록</th>

                <td>
                  <div class="product_main">
                    <span class="product_tit">상품 썸네일 이미지</span>
                  </div>

                  <div>
                    <div class="image_box">
                      <div class="image_add">
                        <input
                          type="file"
                          name="product_main_image"
                          accept="image/*"
                          id="image_input"
                          onchange="set_main_image(event);"
                        />
                        <button
                          type="button"
                          class="image_input_button"
                        ></button>
                        <div id="image_main_container"></div>
                        <div style="margin-bottom: 20px">
                          <button
                            type="button"
                            name="image_main_container"
                            class="remove"
                            onclick="img_remove(this)"
                          >
                            <span> 삭제 </span>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div style="margin-top: 70px"></div>

                  <div class="product_main" style="margin-top: 30px">
                    <span class="product_tit">상품 상세 이미지</span>
                  </div>

                  <div>
                    <div class="image_box">
                      <div class="image_add">
                        <input
                          type="file"
                          name="product_detail_image"
                          accept="image/*"
                          id="image_input"
                          onchange="set_detail_image(event);"
                        />
                        <button
                          type="button"
                          class="image_input_button"
                        ></button>
                        <div id="image_detail_container"></div>

                        <div style="margin-bottom: 20px">
                          <button
                            type="button"
                            name="image_detail_container"
                            class="remove"
                            onclick="img_remove(this)"
                          >
                            <span> 삭제 </span>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div style="margin-top: 70px"></div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div id="product_submit" class="pd_submit">
          <!-- <button type="submit">등록하기</button> -->
        </div>
      </div>
      <!-- </form> -->
    </div>
  </div>
</template>

<script>
import CompanyBoardModalComponent from "./CompanyBoardModalComponent.vue";
export default {
  name: "CompanyBoardPostComponent",
  components: {
    CompanyBoardModalComponent,
  },
  data() {
    return {
      isDisplayModal: false,
    };
  },
  methods: {
    displayModal() {
      //   event.stopPropagation();
      if (this.isDisplayModal) {
        this.isDisplayModal = false;
      } else {
        this.isDisplayModal = true;
      }
      console.log(this.isDisplayModal);
      //   this.isDisplayModal = true;
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

#snb {
  float: left;
  width: 200px;
}

#snb .tit_snb {
  padding: 8px 0 33px 1px;
  font-weight: 700;
  font-size: 30px;
  line-height: 34px;
  color: #333;
  letter-spacing: -0.5px;
}

#snb .inner_snb {
  border: 1px solid #f2f2f2;
  border-bottom: 0;
}

#snb .list_menu li {
  border-bottom: 1px solid #f2f2f2;
}

#snb .list_menu li.on a,
#snb .list_menu li a:hover {
  background: #fafafa
    url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px
    52%;
  background-size: 6px 11px;
  font-weight: 700;
  color: #5f0080;
}

#snb .list_menu li a {
  display: block;
  overflow: hidden;
  padding: 15px 0 15px 20px;
  background: #fff url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11.svg)
    no-repeat 174px 52%;
  background-size: 6px 11px;
  font-size: 14px;
  color: #666;
  line-height: 20px;
  letter-spacing: -0.3px;
}

.snb_my {
  position: absolute;
  top: 220px;
  left: 400px;
  margin-right: -670px;
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

html,
body {
  height: 100%;
}

body,
input,
select,
textarea,
button {
  font-family: noto sans, malgun gothic, AppleGothic, dotum;
  line-height: 1;
  letter-spacing: -0.05em;
  color: #4c4c4c;
  font-size: 15px;
  max-width: 100%;
}

div,
th,
td,
li,
dt,
dd,
p {
  word-break: break-all;
}

img,
video,
canvas {
  max-width: 100%;
}

img {
  border: none;
  vertical-align: top;
}

button {
  outline: none;
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

b,
strong {
  font-weight: 700;
}

h1 {
  font-size: 2em;
}

fieldset {
  border: none;
}

li {
  list-style: none;
}

input {
  line-height: normal;
  outline: none;
}

*::after,
*::before {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.section_orderlist .list_order {
  padding-top: 10px;
  border-top: 2px solid #333;
}

.p_board tbody th {
  border: 1px solid #dedede;
  text-align: left;
  word-break: keep-all;
  padding: 10px 10px 10px 10px;
  vertical-align: top;
  color: #2e3039;
  background-color: #f3f3f3;
  width: 177px;
  font-size: 15px;
}

.p_board tbody td {
  border: 1px solid #dedede;
  padding: 10px 15px;
  line-height: 24px;
  vertical-align: top;
}

.size {
  padding-bottom: 50px;
  background: #fff;
  position: relative;
}

tbody th {
  width: 100px;
}

tbody input,
textarea {
  border: 1px solid #d4d4d4;
}

.form_style {
  padding: 30px 30px 0 30px;
  box-sizing: border-box;
}

.i_text {
  height: 32px;
  line-height: 14px;
  font-size: 14px;
}

.text1 {
  width: 400px;
}

input:focus,
textarea:focus {
  outline: 1px solid #5f0080;
}

.text2 {
  width: 500px;
}

.text3 {
  width: 75px;
  text-align: right;
}

.text4 {
  width: 75px;
  text-align: right;
}

.num1 {
  width: 45px;
  text-align: center;
}

.text5 {
  width: 75px;
  text-align: right;
}

#delivery_type {
  height: 34px;
  font-size: 16px;
  border: 1px solid #d4d4d4;
}

select:focus {
  outline: 1px solid #5f0080;
}

.text6 {
  width: 150px;
}

#packing_type {
  height: 34px;
  font-size: 16px;
  border: 1px solid #d4d4d4;
}

table td .product_category {
  height: 34px;
  font-size: 16px;
  border: 1px solid #d4d4d4;
}

.text7 {
  width: 150px;
}

table .text8 {
  height: 150px;
  width: 500px;
  resize: none;
}

#image_input {
  width: 100%;
  height: 100%;
  cursor: pointer;
}

.image_box {
  width: 268px;
  height: 268px;
  position: relative;
}

.image_add {
  width: 100%;
  height: 100%;
  border: 1px solid #d4d4d4;
  position: relative;
}

.image_add .image_input_button::before {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -1px 0 0 -8px;
  width: 16px;
  height: 2px;
  background-color: #565656;
  background-image: none;
}

.image_add .image_input_button::after {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  width: 2px;
  height: 16px;
  margin: -8px 0 0 -1px;
  background-color: #565656;
  background-image: none;
}

.image_add input {
  /* 파일 업로드 찾아보기 버튼 숨기기 */
  opacity: 0;
  position: absolute;
}

#image_container {
  overflow: hidden;
}

#image_container img {
  position: absolute;
  top: -1px;
  left: 0px;
  width: 269px;
  height: 227px;
}

.image_save {
  display: block;
  float: left;
  text-align: center;
}

.image_save ul {
  display: flex;
}

.image_save ul li {
  width: 120px;
  height: 120px;
  position: relative;
  border: 1px solid #d4d4d4;
  margin-left: 35px;
}

.re_upload {
  border: 1px solid #d4d4d4;
  position: absolute;
  bottom: 0px;
  left: 0px;
  height: 40px;
  width: 134.5px;
}

.remove {
  border: 1px solid #d4d4d4;
  position: absolute;
  bottom: -1px;
  right: 0px;
  height: 40px;
  width: 100%;
  background-color: #f3f3f3;
}

.sub_image .sub_image_input_button::before {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -1px 0 0 -8px;
  width: 16px;
  height: 2px;
  background-color: #565656;
  background-image: none;
}

.sub_image .sub_image_input_button::after {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  width: 2px;
  height: 16px;
  margin: -8px 0 0 -1px;
  background-color: #565656;
  background-image: none;
}

.sub_image input {
  /* 파일 업로드 찾아보기 버튼 숨기기 */
  opacity: 0;
  position: absolute;
  left: 0px;
}

#sub_image_input {
  width: 100%;
  height: 100%;
  cursor: pointer;
}

#sub_image_container img,
#sub2_image_container img {
  position: absolute;
  top: -1px;
  left: 0px;
  width: 122px;
  height: 119px;
}

.img_upload {
  display: flex;
}

.sub_button {
  position: relative;
}

.sub_re_upload {
  border: 1px solid #d4d4d4;
  position: absolute;
  top: -1px;
  left: 35px;
  height: 31px;
  width: 61.5px;
}

#product_submit {
  border: 1px solid #d4d4d4;
  position: absolute;
  bottom: 12px;
  left: 50%;
  height: 40px;
  width: 134.5px;
}

.txtByte {
  vertical-align: bottom;
  color: #97989b;
}

span.txtByte {
  margin-left: 5px;
  margin-top: 8px;
}

.txtByte strong {
  color: #5f0080;
  vertical-align: bottom;
}

.p_board td {
  display: flex;
}

.p_board td .won {
  margin-left: 5px;
  font-size: 16px;
  margin-top: 5px;
}

.won_type {
  margin-left: 10px;
  font-size: 13px;
  margin-top: 5px;
  color: #c15858;
}

.input_ex {
  margin-left: 7px;
  margin-top: 4px;
  font-size: 14px;
}

.p_board table td .txtByte_big {
  margin-top: 55px;
  margin-left: 5px;
}

#product_submit button {
  width: 100%;
  height: 100%;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.product_detail td {
  display: block;
}

.product_detail .product_main {
  text-align: center;
  margin-bottom: 20px;
}

.product_detail .product_tit {
  font-size: 16px;
  font-weight: bold;
}

.product_detail .image_box {
  width: 100%;
  height: 500px;
}

#image_main_container img {
  position: absolute;
  top: -1px;
  left: 0px;
  width: 605px;
  height: 500px;
}

.product_detail .re_upload {
  border: 1px solid #d4d4d4;
  position: absolute;
  bottom: -40px;
  left: 0px;
  height: 40px;
  width: 50%;
}

.product_detail .remove {
  border: 1px solid #d4d4d4;
  position: absolute;
  bottom: -40px;
  right: 0px;
  height: 40px;
  width: 100%;
  background-color: #f3f3f3;
}

.product_detail .main_description {
  height: 150px;
  width: 100%;
  resize: none;
  padding: 10px 10px;
  font-size: 18px;
}

#image_detail_container img {
  position: absolute;
  top: -1px;
  left: 0px;
  width: 605px;
  height: 500px;
}

.datetime-container {
  display: flex;
  align-items: center;
}

.datetime-input {
  padding: 5px;
  width: 200px;
  font-size: 16px;
}

.separator {
  font-size: 16px;
  margin: 0 20px;
}

.p_board .tbl_product {
  display: block;
}

#addrList .name {
  padding: 10px 10px 10px 10px;
  background-color: #fff;
}

#addrList .price {
  padding: 10px 10px 10px 10px;
  background-color: #fff;
}

#addrList .stock {
  padding: 10px 10px 10px 10px;
  background-color: #fff;
}

#addrList .delete_position {
  padding: 10px 10px 10px 10px;
  background-color: #fff;
}
</style>
