<template>
  <div id="viewOrderList" class="page_section section_orderlist">
    <div class="head_aticle">
      <h2 class="tit">게시글 등록</h2>
    </div>

    <div class="list_order">
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
                      v-model="startTime"
                      id="startTime"
                      name="startTime"
                      class="datetime-input"
                      @change="validateDates"
                    />
                    <span class="separator">~</span>
                    <input
                      type="datetime-local"
                      v-model="endTime"
                      id="endTime"
                      name="endTime"
                      class="datetime-input"
                      @change="validateDates"
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <th>게시글 제목</th>
                <td colspan="2">
                  <div class="input-container">
                    <span>
                      <input
                        type="text"
                        v-model="title"
                        class="i_text text1"
                        @input="validateTitle"
                        placeholder="제목을 입력하세요"
                      />
                    </span>
                    <p class="char-count">{{ charCount }} / 50</p>
                  </div>
                </td>
              </tr>

              <tr>
                <th>상품 추가</th>
                <td colspan="2">
                  <button id="board-link" @click="displayModal">추가</button>
                  <div v-if="isDisplayModal">
                    <CompanyBoardModalComponent
                      @closeModal="displayModal"
                      @addProduct="addProduct"
                    />
                  </div>
                </td>
                <td class="tbl_product">
                  <div v-if="products.length > 0">
                    <div id="tblParent" class="type_select">
                      <table class="tbl tbl_type1">
                        <thead>
                          <tr>
                            <th class="tit_name">상품명</th>
                            <th class="tit_price">가격</th>
                            <th class="tit_stock">남은 수량</th>
                            <th class="tit_delete">삭제</th>
                          </tr>
                        </thead>
                        <thead id="addrList">
                          <tr v-for="(product, index) in products" :key="index">
                            <th class="name">{{ product.name }}</th>
                            <th class="price">
                              <span
                                >{{
                                  parseInt(product.price).toLocaleString()
                                }}원</span
                              >
                            </th>
                            <th class="stock">
                              <span
                                >{{
                                  parseInt(product.stock).toLocaleString()
                                }}개</span
                              >
                            </th>
                            <th class="delete_position">
                              <button
                                name="delete"
                                class="product_delete"
                                @click="deleteProduct(index)"
                              >
                                삭제하기
                              </button>
                            </th>
                          </tr>
                        </thead>
                      </table>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <th>카테고리</th>
                <td>
                  <div>
                    <span>
                      <select
                        v-model="category"
                        name="product_category"
                        class="product_category"
                      >
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
                <th>상품 썸네일 이미지 등록</th>
                <td>
                  <div>
                    <div class="image_box">
                      <div class="image_add">
                        <CompanyBoardPhotoUploadComponent
                          :maxImages="8"
                          @updateContent="updateThumbnailImages"
                        />
                      </div>
                    </div>
                  </div>
                  <div style="margin-top: 70px"></div>
                </td>
              </tr>
              <tr class="product_detail">
                <th>상품 상세 이미지 등록</th>
                <td>
                  <div>
                    <div class="image_box">
                      <div class="image_add">
                        <CompanyBoardPhotoUploadComponent
                          :maxImages="1"
                          @updateContent="updateDetailImage"
                        />
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
          <button @click="sendData">등록하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CompanyBoardModalComponent from "./CompanyBoardModalComponent.vue";
import CompanyBoardPhotoUploadComponent from "./CompanyBoardPhotoUploadComponent.vue";

export default {
  name: "CompanyBoardPostComponent",
  components: {
    CompanyBoardModalComponent,
    CompanyBoardPhotoUploadComponent,
  },
  data() {
    return {
      isDisplayModal: false,
      startTime: "", // 전송 데이터
      endTime: "", // 전송 데이터
      isOccuredDateError: false,
      dateErrorMsg: "",
      title: "", // 전송 데이터
      charCount: 0,
      products: [], // 전송 데이터
      category: "", // 전송 데이터
      thumbnailImages: [], // 전송 데이터
      detailImage: [], // 전송 데이터
    };
  },
  methods: {
    validateDates() {
      const startTime = new Date(this.startTime);
      const endTime = new Date(this.endTime);
      const now = new Date();

      if (startTime < now) {
        const year = now.getFullYear();
        const month = now.getMonth() + 1; // getMonth()는 0부터 시작
        const date = now.getDate();
        const hours = now.getHours();
        const minutes = now.getMinutes();

        const nowTime = `${year}년 ${month}월 ${date}일 ${hours}시 ${minutes}분`;
        this.dateErrorMsg = `시작 시간은 ${nowTime} 이후로 설정해야 합니다.`;
        this.isOccuredDateError = true;
        return;
      }

      const startHour = startTime.getHours();
      if (startHour < 9 || startHour >= 22) {
        this.dateErrorMsg = "시작 시간은 09:00 ~ 22:00 사이여야 합니다.";
        this.isOccuredDateError = true;
        return;
      }

      const duration = (endTime - startTime) / (1000 * 60 * 60);
      if (duration < 2 || duration > 48) {
        this.dateErrorMsg =
          "시작 시간 ~ 종료 시간은 2 ~ 48시간 사이여야 합니다.";
        this.isOccuredDateError = true;
      }
      this.isOccuredDateError = false;
    },
    validateTitle() {
      this.charCount = this.title.length;
      if (this.charCount > 50) {
        alert("게시글 제목은 50자 이하로 입력해야 합니다.");
        this.title = this.title.slice(0, 50);
        this.charCount = 50;
        return;
      }
    },
    displayModal() {
      this.isDisplayModal = !this.isDisplayModal;
      console.log(this.isDisplayModal);
    },
    addProduct(product) {
      console.log("Product added:", product);
      const isDuplicate = this.products.some((p) => p.name === product.name);
      if (isDuplicate) {
        alert("이미 존재하는 상품명입니다.");
        return;
      }
      this.products.push(product);
    },
    deleteProduct(index) {
      this.products.splice(index, 1);
    },
    updateThumbnailImages(imageData) {
      this.thumbnailImages = imageData.images;
      console.log("Thumbnail images updated:", this.thumbnailImages);
    },
    updateDetailImage(imageData) {
      this.detailImage = imageData.images;
      console.log("detail image updated:", this.detailImage);
    },
    sendData() {
      if (this.startTime.length < 1 || this.endTime.length < 1) {
        alert("기간 설정을 해주세요.");
        return;
      }
      if (this.isOccuredDateError) {
        alert(this.dateErrorMsg);
        return;
      }
      if (this.title.length < 1) {
        alert("제목을 입력해주세요.");
        return;
      }
      if (this.title.length > 50) {
        alert("제목을 50자 이하로 입력해주세요.");
        return;
      }
      if (this.category === "") {
        alert("카테고리를 설정해주세요.");
        return;
      }
      if (this.products.length === 0) {
        alert("상품을 등록해주세요.");
        return;
      }
      if (this.thumbnailImages.length === 0) {
        alert("상품 썸네일 이미지를 최소 1장이상 등록해주세요.");
        return;
      }
      if (this.detailImage.length === 0) {
        alert("상품 상세 이미지를 등록해주세요.");
        return;
      }
      const productBoard = {
        productThumbnailUrls: this.thumbnailImages.map((image) => image.src),
        productDetailUrl: this.detailImage.map((image) => image.src),
        title: this.title,
        products: this.products,
        startedAt: this.startTime,
        endedAt: this.endTime,
        category: this.category,
      };
      axios
        .post("http://localhost:8000/", productBoard)
        .then((response) => {
          console.log("Success:", response.data);
          alert("상품이 성공적으로 등록되었습니다!");
        })
        .catch((error) => {
          console.error("There was an error!", error);
          alert("상품 등록 중 오류가 발생했습니다.");
        });
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

.char-count {
  margin-left: 10px;
  margin-top: 6px;
  color: #666;
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
  height: 250px;
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

.input-container {
  display: flex;
}
</style>
