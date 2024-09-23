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
          <th class="tit_delete">답변확인</th>
        </tr>
      </thead>
      <tbody id="addrList">
        <tr v-for="(inquiry, index) in filteredInquiries" :key="index">
          <td class="select type_radio">
            <label class="skin_checkbox">
              <p>{{ index + 1 }}</p>
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
            <button name="delete" value="" class="product_delete" @click="openAnswerListModal(inquiry)">
              삭제
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 기존의 답변 등록 모달 -->
    <CompanyQnAModalComponent v-if="isDisplayModal" :title="selectedInquiry.title" :content="selectedInquiry.content"
      :thumbnail="selectedInquiry.thumbnail" :productTitle="selectedInquiry.productTitle"
      :questionIdx="selectedInquiry.idx" @closeModal="closeModal" @registerAnswer="onAnswerRegistered" />

    <!-- 답변 리스트 모달 -->
    <CompanyAnswerListModalComponent v-if="isDisplayAnswerListModal" :selectedInquiry="selectedInquiry"
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
    };
  },
  computed: {
    ...mapStores(useBoardStore),
    filteredInquiries() {
      // 기업회원이 작성한 게시글에 달린 문의만 필터링
      return this.inquiries;
    },
  },
  mounted() {
    this.loadInquiries(); // 기업 회원 이메일을 기반으로 문의 목록 로드
  },
  methods: {
    async loadInquiries() {
      try {
        // 로그인된 기업 회원의 게시글에 달린 문의 조회
        const response = await axios.get("/api/qna/question/list/company");
        this.inquiries = response.data.result; // 데이터를 inquiries에 저장
        console.log(this.inquiries); // 데이터를 확인해서 thumbnail과 productTitle이 포함되어 있는지 확인
      } catch (error) {
        console.error("문의 목록 로드 실패:", error);
      }
    },
    async openModal(inquiry) {
      try {
        console.log(inquiry); // inquiry 객체의 내용을 확인하여 productBoardIdx 값이 있는지 확인

        // 스토어에서 상품 상세 정보 가져오기
        const boardDetail = await this.boardStore.getProductBoardDetail(inquiry.productBoardIdx);
        const lastAnswerContent = inquiry.answers.length > 0 ? inquiry.answers[inquiry.answers.length - 1].content : "";

        // 문의 정보에 썸네일 이미지와 게시글 제목 추가
        this.selectedInquiry = {
          ...inquiry,
          thumbnail: boardDetail.productThumbnailUrls ? boardDetail.productThumbnailUrls[0] : null, // 썸네일 이미지
          productTitle: boardDetail.title || "No Title", // 게시글 제목
          answerContent: lastAnswerContent, // 마지막 답변 내용 추가
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
      this.selectedInquiry = inquiry;
      this.isDisplayAnswerListModal = true;
    },
    closeAnswerListModal() {
      this.selectedInquiry = null;
      this.isDisplayAnswerListModal = false;
    },
    removeAnswerFromList(answerId) {
      this.selectedInquiry.answers = this.selectedInquiry.answers.filter(answer => answer.idx !== answerId);
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
    async registerAnswer(answerContent) {
      try {
        const response = await axios.post(`/api/question/${this.selectedInquiry.id}/answer`, { content: answerContent });
        const newAnswer = response.data.result;

        // 반응성을 유지하기 위해 새로운 배열을 생성하여 answers에 반영
        this.selectedInquiry.answers = [...this.selectedInquiry.answers, newAnswer];

        // 전체 문의 목록에서도 해당 문의의 답변 목록에 새 답변 추가
        const inquiryIndex = this.inquiries.findIndex(inquiry => inquiry.idx === this.selectedInquiry.idx);
        if (inquiryIndex !== -1) {
          // 기존 answers 배열에 새 답변 추가
          this.inquiries[inquiryIndex].answers = [...this.inquiries[inquiryIndex].answers, newAnswer];
        }

        // 상태를 '답변완료'로 변경
        this.selectedInquiry.answerStatus = '답변완료';
        if (inquiryIndex !== -1) {
          this.inquiries[inquiryIndex].answerStatus = '답변완료';
        }

        this.closeModal();
      } catch (error) {
        console.error("답변 등록 실패:", error);
      }
    },
    onAnswerRegistered(newAnswer) {
      if (this.selectedInquiry && Array.isArray(this.selectedInquiry.answers)) {
        // 기존 answers 배열을 새로운 배열로 재할당하여 반응성 유도
        this.selectedInquiry.answers = [...this.selectedInquiry.answers, newAnswer];

        // 답변이 등록되었으므로 상태를 '답변완료'로 변경
        this.selectedInquiry.answerStatus = '답변완료';

        // 전체 문의 목록에서도 해당 문의의 상태 업데이트
        const inquiryIndex = this.inquiries.findIndex(inquiry => inquiry.idx === this.selectedInquiry.idx);
        if (inquiryIndex !== -1) {
          this.inquiries[inquiryIndex].answerStatus = '답변완료';
        }
      }
    }
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
