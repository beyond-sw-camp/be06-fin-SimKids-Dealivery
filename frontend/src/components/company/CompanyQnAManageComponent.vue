<template>
  <div id="tblParent" class="type_select">
    <table class="tbl tbl_type1">
      <thead>
        <tr>
          <th class="tit_select">번호</th>
          <th class="tit_title">제목</th>
          <th class="tit_name">작성자</th>
          <th class="tit_status">상태</th>
          <th class="tit_date">문의 등록일</th>
          <th class="tit_write">답변등록</th>
          <th class="tit_delete">답변목록</th>
        </tr>
      </thead>
      <tbody id="addrList">
        <tr v-for="(inquiry, index) in filteredInquiries" :key="index">
          <td class="select type_radio">
            <label class="skin_checkbox">
              <p>{{ (currentPage - 1) * pageSize + index + 1 }}</p>
            </label>
          </td>
          <td class="title">
            <span class="addr">{{ inquiry.title }}</span>
          </td>
          <td class="name">
            <span>{{ maskAuthorName(inquiry.userName) }}</span>
          </td>
          <td>
            <span class="status star">{{ inquiry.answerStatus }}</span>
          </td>
          <td>
            <span class="date">{{ formatDate(inquiry.createdAt) }}</span>
          </td>
          <td>
            <button @click="openModal(inquiry)" class="ico modify" target="_blank">
              답변등록
            </button>
          </td>
          <td class="delete_position">
            <button class="product_delete" @click="openAnswerListModal(inquiry)">
              답변확인
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 페이징 버튼 -->
    <div class="css-rdz8z7 e82lnfz1" v-if="inquiries.length !== 0">
      <a class="page-unselected e82lnfz0" @click="goToFirstPage">
        <img
          src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAHUlEQVR42mNgAIPi/8X/kWkwA8SE0UQIMJAsCKMBBzk27fqtkcYAAAAASUVORK5CYII="
          alt="처음 페이지로 이동하기 아이콘" />
      </a>
      <a class="page-unselected e82lnfz0" @click="prevPage">
        <img
          src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAHCAQAAABqrk9lAAAAGElEQVR42mNgAIPi/8X/4QwwE5PBQJADAAKSG3cyVhtXAAAAAElFTkSuQmCC"
          alt="이전 페이지로 이동하기 아이콘"
        />
      </a>

      <a
        v-for="pageNumber in visiblePages"
        :key="pageNumber"
        :class="pageNumber === currentPage ? 'page-selected e82lnfz0' : 'page-unselected e82lnfz0'"
        @click="goToPage(pageNumber)"
      >
        {{ pageNumber }}
      </a>

      <a class="page-unselected e82lnfz0" @click="nextPage">
        <img
          src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAHCAQAAABqrk9lAAAAGUlEQVR42mMo/l/8nwECQEwCHEwGhAlRBgA2mht3SwgzrwAAAABJRU5ErkJggg=="
          alt="다음 페이지로 이동하기 아이콘"
        />
      </a>
      <a class="page-unselected e82lnfz0" @click="goToLastPage">
        <img
          src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAIElEQVR42mMo/l/8n4GBgQFGQ5kgDowmQZCwAMImhDkAb0k27Zcisn8AAAAASUVORK5CYII="
          alt="마지막 페이지로 이동하기 아이콘"
        />
      </a>
    </div>

    <!-- 모달들 -->
    <CompanyQnAModalComponent v-if="isDisplayModal" :title="selectedInquiry.title" :content="selectedInquiry.content"
      :thumbnail="selectedInquiry.thumbnail" :productTitle="selectedInquiry.productTitle"
      :questionIdx="selectedInquiry?.idx" @closeModal="closeModal" @registerAnswer="onAnswerRegistered"/>

    <CompanyAnswerListModalComponent v-if="isDisplayAnswerListModal" :key="selectedInquiry?.idx" :selectedInquiry="selectedInquiry"
      @closeModal="closeAnswerListModal" @answerDeleted="removeAnswerFromList" />
  </div>
</template>

<script>
import CompanyAnswerListModalComponent from "./CompanyAnswerListModalComponent.vue";
import CompanyQnAModalComponent from "./CompanyQnAModalComponent.vue";
import axios from "axios";
import { mapStores } from "pinia";
import { useBoardStore } from "@/stores/useBoardStore";

export default {
  name: "CompanyQnAManageComponent",
  components: {
    CompanyQnAModalComponent,
    CompanyAnswerListModalComponent,
  },
  data() {
    return {
      inquiries: [], // 서버에서 받아온 문의 목록
      isDisplayModal: false,
      isDisplayAnswerListModal: false,
      selectedInquiry: null,
      currentPage: 1, // 현재 페이지
      pageSize: 5, // 페이지당 표시할 문의 개수
      totalPages: 1, // 전체 페이지 수
      totalInquiries: 0, // 전체 문의 수
      pagesPerGroup: 5, // 한 그룹 당 페이지 수
    };
  },
  computed: {
    ...mapStores(useBoardStore),
    // 페이징 처리를 위한 필터링된 문의 목록
    filteredInquiries() {
      return this.inquiries; // inquiries 데이터를 그대로 반환
    },
    startPage() {
      return (
        Math.floor((this.currentPage - 1) / this.pagesPerGroup) * this.pagesPerGroup + 1
      );
    },
    endPage() {
      return Math.min(this.startPage + this.pagesPerGroup - 1, this.totalPages);
    },
    visiblePages() {
      const pageNumbers = [];
      for (let i = this.startPage; i <= this.endPage; i++) {
        pageNumbers.push(i);
      }
      return pageNumbers;
    },
  },
  mounted() {
    this.loadInquiries(); // 기업 회원 이메일을 기반으로 문의 목록 로드
  },
  methods: {
    async loadInquiries() {
      try {
        const response = await axios.get("/api/qna/question/list/company", {
          params: { page: this.currentPage }  // 페이지와 크기를 백엔드에 전달
        });
        this.inquiries = response.data.result.content;
        this.totalInquiries = response.data.result.totalElements;  // 전체 문의 개수
        this.totalPages = response.data.result.totalPages; // 총 페이지 수 계산
      } catch (error) {
        console.error("문의 목록 로드 실패:", error);
      }
    },
    async openModal(inquiry) {
    try {
      // 스토어에서 상품 상세 정보 가져오기
      const boardDetail = await this.boardStore.getProductBoardDetail(inquiry.productBoardIdx);
      const lastAnswerContent = inquiry.answers.length > 0 ? inquiry.answers[inquiry.answers.length - 1].content : "";

      // 문의 정보에 썸네일 이미지와 게시글 제목 추가
      this.selectedInquiry = {
        ...inquiry,
        thumbnail: boardDetail.productThumbnailUrls ? boardDetail.productThumbnailUrls[0] : null, // 썸네일 이미지
        productTitle: boardDetail.title || "No Title", // 게시글 제목
        answerContent: lastAnswerContent, // 마지막 답변 내용 추가
        answers: inquiry.answers || [],  // answers 배열이 없을 경우 빈 배열로 초기화
      };
      this.isDisplayModal = true; // 모달을 열기
      } catch (error) {
        console.error("상품 상세 정보 로드 실패:", error);
      }
    },
    closeModal() {
      this.selectedInquiry = null; // 선택된 문의 초기화
      this.isDisplayModal = false; // 모달 닫기
    },
    openAnswerListModal(inquiry) {
      // inquiry.answers는 이미 데이터에 포함되어 있으므로 별도로 호출할 필요 없음
      this.selectedInquiry = inquiry;
      this.isDisplayAnswerListModal = true;
    },
    closeAnswerListModal() {
      this.selectedInquiry = null;
      this.isDisplayAnswerListModal = false;
    },
    removeAnswerFromList(answerId, updatedInquiry) {
      // 부모 컴포넌트에서 상태를 관리하므로, 여기서 상태를 업데이트합니다.
      this.selectedInquiry.answers = updatedInquiry.answers;

      if (this.selectedInquiry.answers.length === 0) {
        this.selectedInquiry.answerStatus = '답변대기';
      }

      // 전체 문의 목록에서도 해당 문의의 상태를 업데이트할 수 있습니다.
    },
    async deleteInquiry(inquiryId) {
      try {
        await axios.delete(`/api/question/${inquiryId}/delete`);
        this.loadInquiries(); // 삭제 후 목록 다시 로드
      } catch (error) {
        console.error("문의 삭제 실패:", error);
      }
    },
    maskAuthorName(name) {
      // 작성자 이름 중간에 * 처리하는 함수
      if (!name) return "익명";
      if (name.length <= 2) return name[0] + "*";
      return name[0] + "*".repeat(name.length - 2) + name[name.length - 1];
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
      });
    },
    onAnswerRegistered(newAnswer) {
    // 방금 등록한 답변을 selectedInquiry의 answers에 추가
    if (this.selectedInquiry && Array.isArray(this.selectedInquiry.answers)) {
      // 배열을 재할당하여 반응성을 유지
      this.selectedInquiry.answers = [...this.selectedInquiry.answers, newAnswer];

      // 전체 문의 목록에서도 해당 문의의 상태를 업데이트
      const inquiryIndex = this.inquiries.findIndex(
        (inquiry) => inquiry.idx === this.selectedInquiry.idx
      );
      this.loadInquiries();
    if (inquiryIndex !== -1) {
      // inquiries 배열을 업데이트하여 반응성 유지
      this.inquiries = [
        ...this.inquiries.slice(0, inquiryIndex),
        {
        ...this.inquiries[inquiryIndex],
        answers: [...this.inquiries[inquiryIndex].answers, newAnswer],
        answerStatus: '답변완료',
        },
        ...this.inquiries.slice(inquiryIndex + 1),
        ];
        }
      }
    },
    goToPage(pageNumber) {
      if (pageNumber >= 1 && pageNumber <= this.totalPages) {
        this.currentPage = pageNumber;
      }
    },
    goToFirstPage(){
      this.goToPage(1);
    },
    goToLastPage(){
      this.goToPage(this.totalPages);
    },
    prevPage() {
      if (this.currentPage > 1) {
            this.goToPage(this.currentPage - 1); // 현재 페이지의 이전 페이지로 이동
        }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
            this.goToPage(this.currentPage + 1); // 현재 페이지의 다음 페이지로 이동
        }
    },
  },
  watch: {
    selectedInquiry: {
    handler(newVal) {
      // 새로운 answers 값이 기존 값과 다를 때만 업데이트
      if (newVal && newVal.answers && this.selectedInquiry.answers !== newVal.answers) {
        this.selectedInquiry.answers = [...newVal.answers];
      }
    },
    deep: true, // 객체 내부의 깊은 변화를 감지
    },
    currentPage() {
      this.loadInquiries();  // currentPage가 변경될 때 데이터를 다시 불러옵니다.
    },
  },
};
</script>



<style scoped>
.tit_title{
  width: 310px;
}

.tit_name {
  width: 75px; /* 원하는 고정 너비로 설정 */
  text-align: center; /* 내용 가운데 정렬 */
}

.tit_status {
  width: 75px; /* 헤더 셀에도 동일한 고정 너비 적용 */
}

.tit_date {
  width: 75px; /* 본문 셀에도 동일한 고정 너비 적용 */
}

.tit_write{
  width: 50px;
}

.tit_delete{
  width: 50px;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center; /* 수직 중앙 정렬 추가 */
  margin-top: 20px;
  bottom: 20px;
  left: 0;
  right: 0;
  gap: 5px; /* 요소 간의 간격을 추가 */
}

.page-unselected:first-of-type {
  border-left: 1px solid rgb(221, 221, 221);
}

.css-30tvht {
  position: relative;
  min-height: 400px; /* 페이지가 작을 때에도 테이블 높이를 일정하게 유지 */
  padding-bottom: 60px; /* 페이징 버튼이 겹치지 않도록 여유 공간 추가 */
}

.pagination button {
  padding: 5px 10px;
  margin: 0 5px;
  border: 1px solid #ccc;
  background-color: #fff;
  cursor: pointer;
}

.pagination button:disabled {
  cursor: not-allowed;
  color: #aaa;
}

.prev-button, .next-button {
  width: 40px;
  height: 40px;
  border: none;
  background-color: transparent;
  cursor: pointer;
  background-size: contain;
  background-repeat: no-repeat;
}

.prev-button:disabled, .next-button:disabled {
  cursor: not-allowed;
  opacity: 0.5; /* 비활성화 시 불투명하게 처리 */
}

.page-unselected {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border: 1px solid rgb(221, 221, 221);
  cursor: pointer;
}

.page-selected {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border: 1px solid rgb(221, 221, 221);
  cursor: pointer;
  background-color: rgb(247, 247, 247);
  color: rgb(95, 0, 128);
}

.page-unselected,
.page-selected {
  display: inline-flex; /* a 태그에 수평 배치를 적용 */
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border: 1px solid rgb(221, 221, 221);
  cursor: pointer;
}

.css-rdz8z7.e82lnfz1 {
  display: flex;
  justify-content: center;
  align-items: center;
  bottom: 0; /* 부모 요소의 맨 아래에 붙이기 */
  background-color: white; /* 배경을 흰색으로 설정하여 내용과 구분 */
  padding: 10px 0;
  z-index: 100; /* 다른 요소 위에 표시되도록 설정 */
  width: 100%; /* 너비를 부모 요소에 맞추기 */
  margin-top: 20px;
}

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
  width: 24px;
  height: 24px;
  border: 0 none;
  background: url('@/assets/arrow.png') no-repeat 50% 50%;
  background-size: contain;
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

.title {
  text-align: left !important; /* 강제로 왼쪽 정렬 */
  padding-left: 30px; /* 왼쪽에 여유 공간 추가 */
  padding-right: 30px;
}

.addr {
  text-align: left !important; /* 내부 텍스트도 강제로 왼쪽 정렬 */
  padding-left: 10px; /* 왼쪽에 여유 공간 추가 */
}
</style>

