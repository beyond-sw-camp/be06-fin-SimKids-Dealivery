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
                      :disabled="!isActivate"
                      :min="minDateTime"
                    />
                    <span class="separator">~</span>
                    <input
                      type="datetime-local"
                      v-model="endTime"
                      id="endTime"
                      name="endTime"
                      class="datetime-input"
                      @change="validateDates"
                      :disabled="!isActivate"
                      :min="minDateTime"
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
                        :disabled="!isActivate"
                      />
                    </span>
                    <p class="char-count">{{ charCount }} / 50</p>
                  </div>
                </td>
              </tr>

              <tr>
                <th>상품 추가</th>
                <td colspan="2">
                  <div v-if="products.length < 5">
                    <button
                      id="board-link"
                      class="addProductBtn"
                      @click="displayModal"
                      :disabled="!isActivate"
                    >
                      추가
                    </button>
                  </div>
                  <div v-else>상품은 5개까지 등록할 수 있습니다.</div>
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
                                :class="[
                                  'product_delete',
                                  { 'disabled-btn': !isActivate },
                                ]"
                                @click="deleteProduct(index)"
                                :disabled="!isActivate"
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
                <th>할인율</th>
                <td>
                  <div class="discountRate">
                    <input
                      class="discountRate_input"
                      :vale="discountRate"
                      type="text"
                      name="discountRate"
                      min="0"
                      max="100"
                      :placeholder="
                        data != null ? data.discountRate : '0 ~ 100'
                      "
                      @input="onDiscountRateInput"
                      :disabled="!isActivate"
                    />
                    <p>%</p>
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
                        :disabled="!isActivate"
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
                          :isActivate="isActivate"
                        />
                      </div>
                    </div>
                  </div>
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
                          :isActivate="isActivate"
                        />
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div id="product_submit" class="pd_submit">
          <button :disabled="!isActivate" @click="sendData">등록하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CompanyBoardModalComponent from "./CompanyBoardModalComponent.vue";
import CompanyBoardPhotoUploadComponent from "./CompanyBoardPhotoUploadComponent.vue";
import { mapStores } from "pinia";
import { useBoardStore } from "../../stores/useBoardStore";

export default {
  name: "CompanyBoardPostComponent",
  components: {
    CompanyBoardModalComponent,
    CompanyBoardPhotoUploadComponent,
  },
  props: {
    data: {
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      isDisplayModal: false,
      dateErrorMsg: "",
      startTime: "", // 전송 데이터
      endTime: "", // 전송 데이터
      title: "", // 전송 데이터
      discountRate: 0, // 전송 데이터
      charCount: 0,
      products: [], // 전송 데이터
      category: "", // 전송 데이터
      thumbnailImages: [], // 전송 데이터
      detailImage: [], // 전송 데이터
      isActivate: true,
      minDateTime: "",
    };
  },
  created() {
    if (this.$route.params.idx !== undefined) {
      this.isActivate = false;
    }
  },
  mounted() {
    this.setMinDateTime();
  },
  computed: {
    ...mapStores(useBoardStore),
  },
  watch: {
    data: {
      immediate: true,
      handler(newValue) {
        if (newValue) {
          this.startTime = newValue.startedAt || "";
          this.endTime = newValue.endedAt || "";
          this.title = newValue.title || "";
          this.charCount = this.title.length || 0;
          this.products = newValue.products || [];
          this.category = newValue.category || "";
        }
      },
    },
  },
  methods: {
    setMinDateTime() {
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const date = String(now.getDate()).padStart(2, "0");
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = now.getMinutes() >= 30 ? "30" : "00"; // 현재 분을 기준으로 반올림
      this.minDateTime = `${year}-${month}-${date}T${hours}:${minutes}`;
    },
    validateTime(time) {
      const dateTime = new Date(time);
      const minutes = dateTime.getMinutes();
      return minutes === 0 || minutes === 30;
    },
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
        return false;
      }

      const startHour = startTime.getHours();
      if (startHour < 9 || startHour >= 22) {
        this.dateErrorMsg = "시작 시간은 09:00 ~ 22:00 사이여야 합니다.";
        return false;
      }

      const duration = (endTime - startTime) / (1000 * 60 * 60);
      if (duration < 2 || duration > 48) {
        this.dateErrorMsg =
          "시작 시간 ~ 종료 시간은 2 ~ 48시간 사이여야 합니다.";
        return false;
      }

      if (!this.validateTime(startTime) || !this.validateTime(endTime)) {
        this.dateErrorMsg =
          "시작/종료 시간은 정각(00분) 또는 30분이어야 합니다.";
        return false;
      }

      return true;
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
    },
    addProduct(product) {
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
    },
    updateDetailImage(imageData) {
      this.detailImage = imageData.images;
    },
    validateAllData() {
      if (this.startTime.length < 1 || this.endTime.length < 1) {
        alert("기간 설정을 해주세요.");
        return false;
      }
      if (!this.validateDates()) {
        alert(this.dateErrorMsg);
        return false;
      }
      if (this.title.length < 1 || this.title.length > 50) {
        alert(
          this.title.length === 0
            ? "제목을 입력해주세요."
            : "제목을 50자 이하로 입력해주세요."
        );
        return false;
      }
      if (this.category === "") {
        alert("카테고리를 설정해주세요.");
        return false;
      }
      if (this.products.length === 0) {
        alert("상품을 등록해주세요.");
        return false;
      }
      if (this.thumbnailImages.length === 0) {
        alert("상품 썸네일 이미지를 최소 1장이상 등록해주세요.");
        return false;
      }
      if (this.detailImage.length === 0) {
        alert("상품 상세 이미지를 등록해주세요.");
        return false;
      }
      return true;
    },
    onDiscountRateInput(event) {
      // 입력값에서 숫자가 아닌 문자를 모두 제거
      let inputValue = event.target.value.replace(/[^0-9]/g, "");

      // 0으로 시작하는 경우 제거
      if (inputValue.length > 1 && inputValue[0] == "0") {
        inputValue = inputValue.replace(/^0+/, "");
      }

      if (parseInt(inputValue) > 100) {
        inputValue = 100;
      }

      event.target.value = inputValue;
      this.discountRate = inputValue;
    },
    sendData() {
      if (!this.validateAllData()) {
        return;
      }
      const req = {
        title: this.title,
        discountRate: parseInt(this.discountRate),
        products: this.products,
        startedAt: this.startTime,
        endedAt: this.endTime,
        category: this.category,
        thumbnailImages: this.thumbnailImages,
        detailImage: this.detailImage,
      };
      const response = this.boardStore.createProductBoard(req);
      if (response) {
        this.$router.push("/product-boards/company/list");
      }
    },
  },
};
</script>

<style scoped>
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
button,
div,
h1,
h2,
h3,
h4,
h5,
h6,
input,
p,
span,
td,
th {
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
button,
div,
h1,
h2,
h3,
h4,
h5,
h6,
input,
p,
span,
td,
th {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body,
html {
  height: 100%;
}
body,
button,
input,
select {
  font-family: noto sans, malgun gothic, AppleGothic, dotum;
  line-height: 1;
  letter-spacing: -0.05em;
  color: #4c4c4c;
  font-size: 15px;
  max-width: 100%;
}
div,
p,
td,
th {
  word-break: break-all;
}
button {
  outline: 0;
  background-color: transparent;
  border: none;
  cursor: pointer;
}
h1 {
  font-size: 2em;
}
input {
  line-height: normal;
  outline: 0;
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
tbody input {
  border: 1px solid #d4d4d4;
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
input:focus {
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
.text5 {
  width: 75px;
  text-align: right;
}
select:focus {
  outline: 1px solid #5f0080;
}
.text6 {
  width: 150px;
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
  opacity: 0;
  position: absolute;
}
#image_container {
  overflow: hidden;
}
#product_submit {
  border: 1px solid #d4d4d4;
  position: absolute;
  bottom: 12px;
  left: 50%;
  height: 40px;
  width: 134.5px;
}
.p_board td {
  display: flex;
}
#product_submit button {
  width: 100%;
  height: 100%;
}
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
.product_detail td {
  display: block;
}
.product_detail .product_tit {
  font-size: 16px;
  font-weight: 700;
}
.product_detail .image_box {
  width: 100%;
  height: 250px;
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
.addProductBtn {
  outline: 0;
  background-color: #4c4c4c;
  color: white;
  padding: 10px 20px;
  border-radius: 3px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  color: white;
}

.disabled-btn {
  border-radius: 3px;
  padding: 10px 5px;
}

.discountRate {
  display: flex;
  align-items: center;
}

.discountRate_input {
  width: 100px;
  height: 32px;
}

.discountRate p {
  margin-left: 5px;
  text-align: center;
}
</style>
