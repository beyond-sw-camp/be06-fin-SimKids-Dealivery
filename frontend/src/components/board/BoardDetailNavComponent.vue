<template>
  <div class="css-16c0d8l">
    <nav class="css-1le17tz en4zazl1">
      <ul class="css-tse2s2 en4zazl0">
        <li :class="[
          'css-1tzhzcg',
          'efe6b6j1',
          'tab',
          { active: activeTab === 'description' },
        ]" @click.prevent="activeTab = 'description'">
          <a class="css-1t0ft7s efe6b6j0"><span class="name">상품설명</span></a>
        </li>
        <li :class="[
          'css-1tzhzcg',
          'efe6b6j1',
          'tab',
          { active: activeTab === 'inquiries' },
        ]" @click.prevent="loadInquiries">
          <a class="css-1t0ft7s efe6b6j0"><span class="name">문의</span></a>
        </li>
      </ul>
    </nav>
    <!-- 상품 상세 설명 section -->
    <div class="css-0 el27cq1">
      <div id="description" class="css-18eozqj el27cq0" v-show="activeTab === 'description'">
        <div class="css-1d3w5wq e1d86arr0">
          <div class="css-1lyi66c">
            <div class="goods_wrap">
              <div class="goods_note">
                <div class="context">
                  <div class="pic">
                    <img :src="detail" class="responsive-image" />
                  </div>
                  <p class="words"></p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="css-8k5a6c ezy1ugy1"></div>
      </div>
    </div>

    <!-- 문의 하기 리스트 section -->
    <div class="css-30tvht eewa3w91" v-show="activeTab === 'inquiries'">
      <div class="css-17juoyc eewa3w90">
        <button class="css-mhrz8m e4nu7ef3" type="button" width="120" height="40" @click="openNewInquiryModal">
          <span class="css-nytqmg e4nu7ef1">문의하기</span>
        </button>
      </div>
      <div class="css-pncj1z eu9pqlw4">
        <strong class="css-83ihkd eu9pqlw3">상품 문의</strong>
        <ul class="css-8sw7yu eu9pqlw2">
          <li class="css-owypta eu9pqlw1">
            상품에 대한 문의를 남기는 공간입니다. 해당 게시판의 성격과 다른 글은
            사전동의 없이 담당 게시판으로 이동될 수 있습니다.
          </li>
        </ul>
      </div>
      <table class="css-1d3w5wq e1wj4bbj1">
        <thead class="css-1xukm34 e1wj4bbj0">
          <tr>
            <th class="title">제목</th>
            <th class="author">작성자</th>
            <th class="created-date">작성일</th>
            <th class="status">답변상태</th>
          </tr>
        </thead>

        <tbody v-for="(row, index) in localTableData" :key="index">
          <tr @click="toggleInquiry(index)" class="css-atz965 e1l5ky7y9">
            <td class="css-1brd6ns e1l5ky7y8">{{ row.title }}</td>
            <td class="css-1pkqelu e1l5ky7y7">
              {{ maskAuthorName(row.userName) }}
            </td>
            <td class="css-1pkqelu e1l5ky7y6">
              {{
                row.modifiedAt
                  ? formatDate(row.modifiedAt)
                  : formatDate(row.createdAt)
              }}
            </td>
            <td class="css-bhr3cq e1l5ky7y5">{{ row.answerStatus }}</td>
          </tr>
          <tr v-show="expandedInquiryIndex === index" class="css-1mvq381 e61d7mt0">
            <td colspan="4">
              <div class="css-tnubsz e1ptpt003">
                <div class="css-1n83etr e1ptpt002">
                  <div class="css-m1wgq7 e1ptpt001">
                    <span class="css-z93ubq e1kayg9i0"></span>
                  </div>
                  <div class="css-1bv2zte e1ptpt000">
                    <span>{{ row.content }}<br /></span>
                  </div>
                </div>
                <div class="css-1j49yxi e11ufodi1" v-if="shouldShowEditDeleteButtons(row)">
                  <button type="button" @click="openEditModal(index)">수정</button>
                  <button type="button" class="css-1ankuif e11ufodi0" @click="deleteInquiry(row.idx, index)">삭제</button>
                </div>
              </div>
              <div class="css-tnubsz e1ptpt003" v-if="row.answerStatus !== '답변대기'">
                <div class="css-1n83etr e1ptpt002" v-for="(answer, answerIndex) in row.answers" :key="answerIndex">
                  <div class="css-m1wgq7 e1ptpt001">
                    <span class="css-1non6l6 ey0f1wv0"></span>
                  </div>
                  <div class="css-1bv2zte e1ptpt000">
                    <div>{{ answer.content }}</div>
                    <div class="css-17g9jzg e1gk8zam0 answer-date-left">{{ answer.createdAt ? formatDate(answer.createdAt) : '작성 시간 없음' }}</div>
                  </div>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="css-rdz8z7 e82lnfz1" v-if="totalInquiries > 0">
        <!-- 처음 페이지로 이동 -->
          <a class="page-unselected" @click="goToFirstPage">
            <img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAHUlEQVR42mNgAIPi/8X/kWkwA8SE0UQIMJAsCKMBBzk27fqtkcYAAAAASUVORK5CYII="
              alt="처음 페이지로 이동"
            />
          </a>
          <!-- 이전 페이지로 이동 -->
          <a class="page-unselected" @click="prevPage">
            <img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAHCAQAAABqrk9lAAAAGElEQVR42mNgAIPi/8X/4QwwE5PBQJADAAKSG3cyVhtXAAAAAElFTkSuQmCC"
              alt="이전 페이지로 이동"/>
          </a>
          <!-- 페이지 번호 표시 -->
          <!-- 페이지 번호 표시 -->
          <a
            v-for="pageNumber in visiblePages"
            :key="pageNumber"
            :class="pageNumber === currentPage ? 'page-selected' : 'page-unselected'"
            @click="goToPage(pageNumber)"
          >
            {{ pageNumber }}
          </a>
          <!-- 다음 페이지로 이동 -->
          <a class="page-unselected" @click="nextPage">
            <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAHCAQAAABqrk9lAAAAGUlEQVR42mMo/l/8nwECQEwCHEwGhAlRBgA2mht3SwgzrwAAAABJRU5ErkJggg=="
            alt="다음 페이지로 이동"/>
          </a>
          <!-- 마지막 페이지로 이동 -->
          <a class="page-unselected" @click="goToLastPage">
            <img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAHCAQAAABwkq/rAAAAIElEQVR42mMo/l/8n4GBgQFGQ5kgDowmQZCwAMImhDkAb0k27Zcisn8AAAAASUVORK5CYII="
              alt="마지막 페이지로 이동"/>
          </a>
        </div>
    </div>

    <!-- 새 문의 작성 모달 -->
    <QnaRegisterModalComponent v-if="showNewInquiryModal" @close="closeModal" @submit="addNewInquiry"
      :isEditMode="false" :productBoardIdx="productBoardIdx" :thumbnail="thumbnails[0]" :title="productTitle" />

    <!-- 수정 모달 -->
    <QnaRegisterModalComponent v-if="showEditInquiryModal" :initialSubject="selectedInquiry.title"
      :initialContent="selectedInquiry.content" :inquiryId="selectedInquiry.idx" @close="closeModal"
      @submit="updateInquiry" :isEditMode="true" :productBoardIdx="productBoardIdx" :thumbnail="thumbnails[0]"
      :title="productTitle" />
  </div>
</template>

<script>
import { useQnaStore } from "@/stores/useQnaStore";
import QnaRegisterModalComponent from "../qna/QnaRegisterModalComponent.vue";
import { mapStores } from "pinia";
import { useUserStore } from "@/stores/useUserStore";
import axios from 'axios'; 

export default {
  name: "BoardDetailNavComponent",
  computed: {
    ...mapStores(useQnaStore, useUserStore),
    userType() {
      if (this.userStore.roles.includes("ROLE_USER")) {
        return "ROLE_USER";
      } else if (this.userStore.roles.includes("ROLE_COMPANY")) {
        return "ROLE_COMPANY";
      }
      return null;
    },
    totalPages() {
      return Math.ceil(this.totalInquiries / this.pageSize);
    },
    paginatedInquiries() {
      return this.localTableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
    },
    visiblePages() {
      const pageNumbers = [];
      const startPage = Math.floor((this.currentPage - 1) / this.pagesPerGroup) * this.pagesPerGroup + 1;
      const endPage = Math.min(startPage + this.pagesPerGroup - 1, this.totalPages);
      
      for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(i);
      }
      return pageNumbers;
    },
    startPage() {
      return Math.floor((this.currentPage - 1) / this.pagesPerGroup) * this.pagesPerGroup + 1;
    },
    endPage() {
      return Math.min(this.startPage + this.pagesPerGroup - 1, this.totalPages);
    },
  },
  props: {
    thumbnails: {
      type: Array,
      required: true,
    },
    detail: {
      type: String,
      required: true,
    },
    productBoardIdx: {
      type: Number,
      required: true,
    },
    productTitle: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      activeTab: "description",
      hasPrev: false,
      hasNext: true,
      showNewInquiryModal: false,
      showEditInquiryModal: false,
      selectedInquiry: {
        title: "",
        content: "",
      },
      localTableData: [],
      expandedInquiryIndex: null,
      editingIndex: null,
      currentPage: 1,
      pageSize: 5,
      totalInquiries: 0,
      pagesPerGroup: 5,
      // 사용자 정보를 직접 관리하기 위해 새로운 상태 추가
      userDetailWithoutAlert: {
      email: "",
      roles: [],
      },
    };
  },
  methods: {
    loadInquiries() {
      this.activeTab = "inquiries";

      // 사용자 정보를 alert 없이 로드
      this.getUserDetailWithoutAlert().then(() => {
        this.qnaStore.fetchInquiries(this.productBoardIdx, this.currentPage).then(() => {
          this.totalInquiries = this.qnaStore.totalInquiries;

          this.localTableData = this.qnaStore.inquiries;
        });
      });
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString("ko-KR", { year: "numeric", month: "2-digit", day: "2-digit" });
    },
    openNewInquiryModal() {
      if (!this.userStore.isLogined) {
        alert("로그인 후 이용해주세요.");
      } else if (this.userType === "ROLE_COMPANY") {
        alert("일반회원만 문의 가능합니다.");
      } else if (this.userType === "ROLE_USER") {
        this.showNewInquiryModal = true;
      }
    },
    openEditModal(index) {
      const selectedInquiry = { ...this.localTableData[index] };
      this.selectedInquiry = selectedInquiry;
      this.editingIndex = index;
      this.showEditInquiryModal = true;
    },
    closeModal() {
      this.showNewInquiryModal = false;
      this.showEditInquiryModal = false;
    },
    toggleInquiry(index) {
      this.expandedInquiryIndex =
        this.expandedInquiryIndex === index ? null : index;
    },
    maskAuthorName(name) {
      if (!name) {
        return "익명";
      }
      if (name.length <= 2) {
        return name[0] + "*";
      }
      return name[0] + "*".repeat(name.length - 2) + name[name.length - 1];
    },
    afterEnter(el) {
      el.style.maxHeight = "none";
    },
    afterLeave(el) {
      el.style.maxHeight = "0px";
    },
    addNewInquiry(newInquiry) {
      this.localTableData.unshift(newInquiry);  // 새로 등록된 문의를 맨 위에 추가
      this.totalInquiries += 1;  // 전체 문의 개수 증가
      this.checkPageAdjustments();
      this.closeModal();
    },
    updateInquiry(updatedInquiry) {
      if (this.editingIndex !== null) {
        this.localTableData[this.editingIndex] = {
          ...this.localTableData[this.editingIndex],
          ...updatedInquiry,
        };
        this.editingIndex = null;
        this.closeModal();
      }
    },
    deleteInquiry(idx, index) {
      // 서버로 삭제 요청 보내기
      this.qnaStore.deleteInquiry(idx, index).then(() => {
        // localTableData에서 해당 문의를 즉시 제거
        this.localTableData.splice(index, 1);
        this.totalInquiries -= 1; // 전체 문의 개수 감소
        this.checkPageAdjustments(); // 페이징 조정

        // 삭제된 문의가 화면에 보이지 않도록 즉시 반영
        if (this.expandedInquiryIndex === index) {
          this.expandedInquiryIndex = null;
        } else if (this.expandedInquiryIndex > index) { 
          this.expandedInquiryIndex -= 1;
        }
      }).catch(error => {
        console.error("문의 삭제 중 오류 발생:", error);
      });
    },
    // 수정/삭제 버튼이 노출될 조건 체크
    shouldShowEditDeleteButtons(row) {
      return row.answerStatus === '답변대기' && row.email === this.userDetailWithoutAlert.email;
    },
    checkPageAdjustments() {
      // 페이징 계산을 다시 하여 데이터가 제대로 분할되고 표시되는지 확인
      if (this.totalInquiries <= (this.currentPage - 1) * this.pageSize && this.currentPage > 1) {
        this.currentPage -= 1;
      }
      this.loadInquiries();  // 페이지 재로딩
    },
    goToPage(pageNumber) {
      if (pageNumber >= 1 && pageNumber <= this.totalPages) {
        this.currentPage = pageNumber;
        this.loadInquiries();
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
    // 새로운 사용자 정보 로드 메서드 추가
    async getUserDetailWithoutAlert() {
      try {
        const response = await axios.get("/api/user/detail", {
          withCredentials: true,
        });
        if (response.data.code === 1000) {
          this.userDetailWithoutAlert = response.data.result;
          return true;
        } else {
          console.log("사용자 정보 조회 실패");
          return false;
        }
      } catch (error) {
        console.error("사용자 정보 조회 중 오류 발생", error);
        return false;
      }
    },
  },
  watch: {
    localTableData: {
      handler() { this.$forceUpdate(); },
      deep: true
    }
  },
  components: {
    QnaRegisterModalComponent,
  },
};
</script>


<style scoped>
.author {
  width: 150px; /* 원하는 고정 너비로 설정 */
  text-align: center; /* 내용 가운데 정렬 */
}

.created-date {
  width: 150px; /* 헤더 셀에도 동일한 고정 너비 적용 */
}

.status {
  width: 150px; /* 본문 셀에도 동일한 고정 너비 적용 */
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

button {
  cursor: pointer;
}

.css-1le17tz {
  z-index: 20;
  top: 56px;
  width: 1010px;
  background-color: #fff;
  margin-top: 50px;
  margin-bottom: 50px;
}

.css-tse2s2 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-flex-wrap: wrap;
  -webkit-flex-wrap: wrap;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  height: 60px;
}

.css-1tzhzcg {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  border-width: 1px 0 1px 1px;
  border-color: #eee;
  border-style: solid;
  background-color: #fafafa;
  box-sizing: border-box;
}

.css-1tzhzcg:last-of-type {
  border-right-width: 1px;
}

.css-1t0ft7s {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  height: 100%;
}

.css-1tzhzcg .name {
  font-size: 16px;
  font-weight: 500;
  color: #666;
}

/*문의 하기*/

.css-30tvht {
  margin-bottom: 60px;
  margin-top: 60px;
}

.css-17juoyc {
  position: absolute;
  right: 0;
}

.css-17juoyc button {
  /*문의 하기 버튼*/
  border-radius: 3px;
}

.css-mhrz8m {
  display: block;
  padding: 0 10px;
  text-align: center;
  overflow: hidden;
  width: 120px;
  height: 40px;
  border-radius: 3px;
  color: #fff;
  background-color: #5f0080;
  border: 0 none;
}

.css-17juoyc span {
  font-size: 14px;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-pncj1z {
  padding-bottom: 40px;
  letter-spacing: -0.5px;
}

.css-83ihkd {
  display: block;
  padding-bottom: 16px;
  font-size: 24px;
  font-weight: 500;
  line-height: 21px;
  color: #333;
}

.css-8sw7yu {
  line-height: 19px;
  font-weight: 400;
  color: #999;
}

.css-owypta {
  padding-left: 10px;
}

/*문의 테이블*/
.css-1d3w5wq {
  width: 100%;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

.css-1xukm34 {
  height: 58px;
  border-top: 2px solid #333;
  border-bottom: 1px solid #333;
}

.css-atz965 {
  height: 64px;
  border-bottom: 1px solid rgb(244, 244, 244);
  line-height: 19px;
  letter-spacing: -0.5px;
  cursor: pointer;
}

.css-sno6ed {
  text-align: left;
  padding: 0px 20px;
  cursor: pointer;
  color: rgb(153, 153, 153);
}

.css-s5xdrg {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

.css-lrsm5m {
  display: block;
  width: 14px;
  height: 14px;
  background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTIiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxMiAxNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9IiNCNUI1QjUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTExLjA0MyAxMi41NzVhLjI1OS4yNTkgMCAwIDEtLjI1OC4yNTdIMS4yMTRhLjI1OS4yNTkgMCAwIDEtLjI1OC0uMjU3VjUuNDJjMC0uMTQyLjExNy0uMjU4LjI1OC0uMjU4aDkuNTdjLjE0NCAwIC4yNi4xMTYuMjYuMjU4djcuMTU1ek0zLjY4NSAzLjIzN0EyLjI4MyAyLjI4MyAwIDAgMSA1Ljk2Ni45NTdhMi4yODIgMi4yODIgMCAwIDEgMi4yODEgMi4yOHYuOTY4SDMuNjg1di0uOTY4em03LjEuOTY4aC0xLjU4di0uOTY4QTMuMjQxIDMuMjQxIDAgMCAwIDUuOTY1IDAgMy4yNCAzLjI0IDAgMCAwIDIuNzMgMy4yMzd2Ljk2OEgxLjIxNEMuNTQ0IDQuMjA1IDAgNC43NSAwIDUuNDJ2Ny4xNTVjMCAuNjY5LjU0NSAxLjIxNCAxLjIxNCAxLjIxNGg5LjU3Yy42NzEgMCAxLjIxNi0uNTQ1IDEuMjE2LTEuMjE0VjUuNDJjMC0uNjctLjU0NS0xLjIxNS0xLjIxNS0xLjIxNXoiLz4KICAgICAgICA8cGF0aCBkPSJNNSA4LjJjMCAuMzQzLjE4NC42MzIuNDQ4LjgxMnYxLjMzaDEuMTAzdi0xLjMzQS45ODYuOTg2IDAgMCAwIDcgOC4yYTEgMSAwIDAgMC0yIDB6Ii8+CiAgICA8L2c+Cjwvc3ZnPgo=) no-repeat;
  margin-left: 6px;
}

.css-bhr3cq {
  text-align: center;
  color: rgb(95, 0, 128);
}

.css-18ad0gx {
  position: relative;
  width: 100%;
  height: 44px;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-top: 20px;
}

.css-rzcdhr:disabled {
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-prev-disabled.svg);
  cursor: default;
}

.css-rzcdhr {
  width: 44px;
  height: 44px;
  padding: 0;
  margin: 0;
  border: 0;
  cursor: pointer;
  background-color: transparent;
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-prev-activated.svg);
  background-repeat: no-repeat;
  -webkit-background-size: cover;
  background-size: cover;
}

.css-1jwilit:disabled {
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-next-disabled.svg);
  cursor: default;
}

.css-sxxs1g button+button {
  margin-left: 12px;
}

.css-1jwilit {
  width: 44px;
  height: 44px;
  padding: 0;
  margin: 0;
  border: 0;
  cursor: pointer;
  background-color: transparent;
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-next-activated.svg);
  background-repeat: no-repeat;
  -webkit-background-size: cover;
  background-size: cover;
}

.css-7qb0sc {
  overflow: hidden;
  position: absolute;
  left: -9999px;
  width: 1px;
  height: 1px;
}

.tab {
  cursor: pointer;
}

.tab.active {
  background: #fff;
  /* 활성화된 탭 배경색 */
}

.tab.active a span {
  color: #5f0080;
  /* 활성화된 탭 텍스트 색상 */
}

.inquiry-detail {
  display: block;
  /* table-row에서 block으로 변경 */
  overflow: hidden;
  max-height: 0;
  transition: max-height 0.3s ease-out;
  /* 부드러운 애니메이션 */
}

.inquiry-detail-expanded {
  max-height: 500px;
  /* 최대 펼쳐질 높이 */
}

tbody {
  display: table-row-group;
  vertical-align: middle;
  unicode-bidi: isolate;
  border-color: inherit;
}

.css-atz965 {
  height: 64px;
  border-bottom: 1px solid rgb(244, 244, 244);
  line-height: 19px;
  letter-spacing: -0.5px;
}

tr {
  display: table-row;
  vertical-align: inherit;
  unicode-bidi: isolate;
  border-color: inherit;
}

.css-1brd6ns {
  max-width: 250px;
  text-align: left;
  padding: 0px 20px;
  cursor: pointer;
}

.css-1pkqelu {
  text-align: center;
  color: rgb(153, 153, 153);
}

td {
  display: table-cell;
  vertical-align: inherit;
  unicode-bidi: isolate;
}

.css-1mvq381 {
  background-color: rgb(250, 250, 250);
  border-bottom: 1px solid rgb(244, 244, 244);
  max-width: 100px;
}

.css-tnubsz {
  max-width: 1010px;
}

div {
  display: block;
  unicode-bidi: isolate;
}

.css-1n83etr {
  display: flex;
  padding: 22px 20px 25px;
  align-items: flex-start;
}

.css-m1wgq7 span {
  width: 24px;
  height: 24px;
  display: block;
}

.css-z93ubq {
  width: 28px;
  height: 28px;
  background-image: url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGc+CiAgICAgICAgICAgIDxnPgogICAgICAgICAgICAgICAgPGc+CiAgICAgICAgICAgICAgICAgICAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTQ0NSAtODc2KSB0cmFuc2xhdGUoNDIzIDI3NikgdHJhbnNsYXRlKDIgMzE2KSB0cmFuc2xhdGUoMjAgMjg0KSI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxjaXJjbGUgY3g9IjEyIiBjeT0iMTIiIHI9IjEyIiBmaWxsPSIjQTg2NEQ4Ii8+CiAgICAgICAgICAgICAgICAgICAgICAgIDxwYXRoIGZpbGw9IiNGRkYiIGQ9Ik0xMS45NDYgNy41YzIuNDU2IDAgNC40NDYgMS45OSA0LjQ0NiA0LjQ0NiAwIC45MDctLjI3MSAxLjc1LS43MzcgMi40NTNsLjg0NS44NDMtMS4yNTggMS4yNTgtLjg0My0uODQ1Yy0uNzAzLjQ2Ni0xLjU0Ni43MzctMi40NTMuNzM3LTIuNDU1IDAtNC40NDYtMS45OS00LjQ0Ni00LjQ0NkM3LjUgOS40OTEgOS40OSA3LjUgMTEuOTQ2IDcuNXptMCAxLjc3OGMtMS40NzMgMC0yLjY2OCAxLjE5NS0yLjY2OCAyLjY2OHMxLjE5NSAyLjY2OCAyLjY2OCAyLjY2OGMuNDEyIDAgLjgwMi0uMDk0IDEuMTUtLjI2bC0uMzY5LS4zNyAxLjI1OC0xLjI1Ny4zNjkuMzY5Yy4xNjYtLjM0OC4yNi0uNzM4LjI2LTEuMTUgMC0xLjQ3My0xLjE5NS0yLjY2OC0yLjY2OC0yLjY2OHoiLz4KICAgICAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgIDwvZz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPgo=");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: contain;
}

.css-1bv2zte {

  margin-left: 12px;
  padding-top: 2px;
  font-size: 14px;
  word-break: break-all;
  line-height: 19px;
  letter-spacing: -0.5px;
}

.css-1non6l6 {
  width: 28px;
  height: 28px;
  background-image: url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGc+CiAgICAgICAgICAgIDxnPgogICAgICAgICAgICAgICAgPGc+CiAgICAgICAgICAgICAgICAgICAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTQ0NSAtMTkxOCkgdHJhbnNsYXRlKDQyMyAyNzYpIHRyYW5zbGF0ZSgyIDE2MjIpIHRyYW5zbGF0ZSgyMCAyMCkiPgogICAgICAgICAgICAgICAgICAgICAgICA8Y2lyY2xlIGN4PSIxMiIgY3k9IjEyIiByPSIxMiIgZmlsbD0iIzVGMDA4MCIvPgogICAgICAgICAgICAgICAgICAgICAgICA8cGF0aCBmaWxsPSIjRkZGIiBmaWxsLXJ1bGU9Im5vbnplcm8iIGQ9Ik05Ljc2IDE1LjcwOGwuNTQtMS40MDRoMy40NTZsLjU1MiAxLjQwNGgyLjE0OEwxMyA3LjVoLTEuOTMyTDcuNiAxNS43MDhoMi4xNnptMy4zMjQtMy4xMzJoLTIuMTEybDEuMDU2LTIuNzEyIDEuMDU2IDIuNzEyeiIvPgogICAgICAgICAgICAgICAgICAgIDwvZz4KICAgICAgICAgICAgICAgIDwvZz4KICAgICAgICAgICAgPC9nPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+Cg==");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: contain;
}

.css-17g9jzg {
  font-size: 14px;
  line-height: 22px;
  color: rgb(153, 153, 153);
}

.css-1j49yxi {
  width: 100%;
  display: flex;
  -webkit-box-pack: end;
  justify-content: flex-end;
}

.css-1j49yxi button {
  position: relative;
  padding: 0px 11px;
  font-size: 14px;
  line-height: 20px;
  border: 0px;
  background: none;
  color: rgb(153, 153, 153);
}

.css-1ankuif::before {
  position: absolute;
  top: 5px;
  left: 0px;
  width: 1px;
  height: 12px;
  background: rgb(238, 238, 238);
  vertical-align: top;
  content: "";
}

.responsive-image {
  width: 1050px;
  height: auto; /* 이미지 비율에 맞게 자동으로 높이 설정 */
  object-fit: contain; /* 비율을 유지하면서 영역에 맞게 이미지 표시 */
}

.answer-date-left {
  text-align: left; /* 날짜를 왼쪽 정렬 */
  margin-top: 8px; /* 답변 내용과 날짜 사이 간격 추가 */
  color: rgb(153, 153, 153); /* 날짜 텍스트 색상 */
}
</style>




