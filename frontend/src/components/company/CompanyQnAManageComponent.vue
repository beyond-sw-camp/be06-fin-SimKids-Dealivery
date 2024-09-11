<template>
  <div id="tblParent" class="type_select">
    <table class="tbl tbl_type1">
      <thead>
        <tr>
          <th class="tit_select">번호</th>
          <th class="tit_title">제목</th>
          <th class="tit_name">작성자</th>

          <th class="tit_status">상태</th>
          <th class="tit_date">작성일</th>
          <th class="tit_write">답변확인</th>
          <th class="tit_delete">삭제</th>
        </tr>
      </thead>
      <tbody id="addrList">
        <tr>
          <td class="select type_radio">
            <label class="skin_checkbox">
              <p>2</p>
            </label>
          </td>
          <td class="title">
            <span class="addr">이벤트 문의 드립니다.</span>
          </td>
          <td class="name">
            <span>심키즈</span>
          </td>

          <td>
            <span class="status star">답변 대기</span>
          </td>

          <td>
            <span class="date">2024-09-05</span>
          </td>

          <td>
            <div v-if="isDisplayModal">
              <CompanyQnAModalComponent @closeModal="displayModal" />
            </div>
            <button @click="displayModal" class="ico modify" target="_blank">
              등록하기
            </button>
          </td>
          <td class="delete_position">
            <button name="delete" value="" class="product_delete">
              삭제하기
            </button>
          </td>
        </tr>
        <tr>
          <td class="select type_radio">
            <label class="skin_checkbox">
              <p>1</p>
            </label>
          </td>
          <td class="title">
            <span class="addr">상품 수량이 얼마나 남았나요?</span>
          </td>
          <td class="name">
            <span>김키즈</span>
          </td>

          <td>
            <span class="status complete">답변 완료</span>
          </td>

          <td>
            <span class="date">2024-09-04</span>
          </td>

          <td>
            <button
              type="button"
              class="ico modify"
              onclick="popup()"
              target="_blank"
            >
              등록하기
            </button>
          </td>
          <td class="delete_position">
            <button type="submit" name="delete" value="" class="product_delete">
              삭제하기
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import CompanyQnAModalComponent from "./CompanyQnAModalComponent.vue";

export default {
  name: "CompanyQnAManageComponent",
  components: {
    CompanyQnAModalComponent,
  },
  data() {
    return {
      isDisplayModal: false,
    };
  },
  methods: {
    displayModal() {
      this.isDisplayModal = !this.isDisplayModal;
    },
    // closeModal() {
    //   this.$emit("closeModal");
    // },
  },
};
</script>

<style scoped>
div {
  display: block;
  unicode-bidi: isolate;
}
.tbl_type1 {
  border-bottom: 1px solid #f4f4f4;
}

.page_aticle .tbl {
  width: 100%;
}

.type_select .tit_select {
  display: table-cell;
}

.tbl_type1 .tit_select {
  width: 60px;
}

.tbl_type1 th {
  padding: 17px 0 20px;
  vertical-align: top;
}

.page_aticle .tbl_type1 th {
  font-weight: 700;
  font-size: 14px;
  line-height: 20px;
  color: #333;
  letter-spacing: -0.5px;
}

.type_select .tit_address {
  padding-left: 0;
  text-indent: -8px;
}

.tbl_type1 .tit_address {
  width: auto;
}

.tbl_type1 .tit_receive {
  width: 120px;
}

.tbl_type1 .tit_phone {
  width: 100px;
}

.tbl_type1 .tit_delivery {
  width: 100px;
}

.tbl_type1 .tit_modify {
  width: 60px;
}

.tbl_type1 tr:first-child td,
.tbl_type1 td {
  height: 90px;
  font-size: 14px;
  color: #333;
  line-height: 22px;
  letter-spacing: -0.4px;
  vertical-align: middle;
  text-align: center;
}

.page_aticle .tbl_type1 tr:first-child td {
  border-top: 1px solid #333;
}

.type_select .select {
  display: table-cell;
}

.type_radio label {
  font-size: 14px;
  line-height: 22px;
}

.screen_out {
  display: block;
  overflow: hidden;
  position: absolute;
  left: -9999px;
  width: 1px;
  height: 1px;
  font-size: 0;
  line-height: 0;
  text-indent: -9999px;
}

.tbl_type1 tr:first-child td,
.tbl_type1 td {
  height: 90px;
  font-size: 14px;
  color: #333;
  line-height: 22px;
  letter-spacing: -0.4px;
  vertical-align: middle;
  text-align: center;
}

.type_select .address {
  padding-left: 2px;
}

.tbl_type1 .address {
  padding: 20px 10px 20px 20px;
  text-align: left;
}

.tbl_type1 .addr {
  padding: 0;
  font-size: 16px;
  color: #333;
  line-height: 24px;
  letter-spacing: -0.3px;
  text-align: left;
  word-break: break-all;
}

.tbl_type1 .star {
  color: #5f0080;
}

.tbl_type1 .complete {
  color: #333;
}

.tbl_type1 .modify {
  width: 24px;
  height: 24px;
  border: 0 none;
  background: url(https://res.kurly.com/mobile/ico/2006/ico_modify.png)
    no-repeat 50% 50%;
  font-size: 0;
  line-height: 0;
  cursor: pointer;
}

#tblParent {
  border-top: 2px solid #333;
  margin-bottom: 120px;
}

.delete_position {
  position: relative;
}

.product_delete {
  position: absolute;
  width: 24px;
  height: 24px;
  border: 0 none;
  background: url(https://res.kurly.com/pc/service/cart/2007/ico_delete.svg)
    no-repeat 50% 50%;
  font-size: 0;
  line-height: 0;
  top: 33px;
  left: 10px;
}

.no_data {
  display: none;
  border-bottom: 1px solid #dddfe1;
  padding: 90px 0 220px 0;
  font-size: 15px;
  color: #757575;
  text-align: center;
}
</style>
