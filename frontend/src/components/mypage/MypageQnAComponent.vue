<template>
    <div class="css-heioij eug5r8l1">
        <div class="css-1xdhyk6 eug5r8l0">
            <ul>
                <li class="css-1w5u25a e1q2koch0" v-for="(inquiry, index) in inquiries" :key="index">
                    <button type="button" class="inquiry-product-info" @click="toggleDetail(index)">
                        <div class="product-info-wrap">
                            <div class="product-name">{{ inquiry.productTitle }}</div>
                            <div class="inquiry-subject">
                                <p class="subject-text">{{ inquiry.title }}</p>
                            </div>
                            <div class="status-and-created-at">
                                <span class="status-text commented">{{ inquiry.answerStatus }}</span>
                                <span class="text-divider"></span>
                                <span class="created-at-text">{{ formatDate(inquiry.createdAt) }}</span>
                            </div>
                        </div>
                        <a :href="'/board/detail/' + inquiry.productBoardIdx" class="product-image-wrap">
                            <img :src="inquiry.productImageUrl || 'https://via.placeholder.com/150'" :alt="inquiry.title">
                        </a>
                    </button>
                    <div name="fade" @after-enter="afterEnter" @after-leave="afterLeave">
                        <div v-if="showDetailIndex === index" class="row-inquiry-detail">
                            <div class="inquiry-detail-content">
                                <!-- 아이콘과 질문/답변을 같은 줄에 배치 -->
                                <div class="content-row flex-row">
                                    <div class="icon-wrap">
                                        <img src="https://res.kurly.com/kurly/ico/2021/question_24_24_purple.svg" alt="질문">
                                    </div>
                                    <p class="subject">{{ inquiry.content }}</p>
                                </div>
                                <div class="content-row horizontal-answers flex-row" v-if="inquiry.answers.length > 0">
                                    <div v-for="(answer, answerIndex) in inquiry.answers" :key="answerIndex"
                                        class="answer-section flex-row">
                                        <div class="icon-wrap">
                                            <img src="https://res.kurly.com/kurly/ico/2021/answer_24_24_purple.svg" alt="답변">
                                        </div>
                                        <div class="answer-content">
                                            <p class="subject">{{ answer.content }}</p>
                                            <div class="answer-meta">
                                                <span class="answer-date">{{ formatDate(answer.createdAt) }}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- 답변대기 상태일 때만 수정/삭제 버튼 표시 -->
                                <div class="css-1j49yxi e11ufodi1" v-if="inquiry.answerStatus === '답변대기'">
                                    <button @click="openEditModal(inquiry, index)">수정</button>
                                    <button @click="deleteInquiry(inquiry.idx, index)">삭제</button>
                                </div>
                                <div v-if="inquiry.answers.length === 0" class="no-answer">
                                    <img src="https://res.kurly.com/kurly/ico/2021/answer_24_24_purple.svg" alt="답변">
                                    아직 답변이 없습니다.
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            <!-- 페이지 네비게이션 -->
            <div class="css-rdz8z7 e82lnfz1" v-if="totalPages > 1">
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
        </div>
    </div>

    <!-- 수정 모달 -->
    <QnaRegisterModalComponent v-if="showEditInquiryModal" :initialSubject="selectedInquiry.title"
        :initialContent="selectedInquiry.content" :inquiryId="selectedInquiry.idx" @close="closeModal"
        @submit="updateInquiry" :isEditMode="true" :productBoardIdx="selectedInquiry.productBoardIdx"
        :thumbnail="selectedInquiry.productImageUrl" :title="selectedInquiry.productTitle" />
</template>

<script>
import { useBoardStore } from "@/stores/useBoardStore";
import { mapStores } from "pinia";
import axios from "axios";
import QnaRegisterModalComponent from "../qna/QnaRegisterModalComponent.vue"; // 수정 모달

export default {
    components: { QnaRegisterModalComponent },
    data() {
        return {
            inquiries: [],  // 서버로부터 받아온 문의 목록
            showDetailIndex: null,
            showEditInquiryModal: false,
            selectedInquiry: null,  // 선택된 문의 데이터
            currentPage: 1,       // 현재 페이지
            pagesPerGroup: 5,     // 한 그룹에 보여줄 페이지 수
            pageSize: 5,          // 페이지 당 아이템 개수
            totalPages: 0,        // 전체 페이지 개수
            totalInquiries: 0,    // 전체 문의 개수
            currentPageGroup: 1,  // 현재 페이지 그룹
        };
    },
    mounted() {
        this.loadMyInquiries();  // 컴포넌트가 마운트될 때 로그인된 사용자의 문의 목록을 불러옴
    },
    computed: {
        ...mapStores(useBoardStore),
        visiblePages() {
            const startPage = (this.currentPageGroup - 1) * this.pagesPerGroup + 1;
            const endPage = Math.min(this.currentPageGroup * this.pagesPerGroup, this.totalPages);

            const pages = [];
            for (let i = startPage; i <= endPage; i++) {
            pages.push(i);
            }
            return pages;
        }
    },
    methods: {
        async loadMyInquiries(page = 1) {
            try {
                const response = await axios.get(`/api/qna/question/list/my?page=${page}&size=${this.pageSize}`)

                if (response.data.isSuccess) {
                    this.inquiries = response.data.result.content;
                    this.totalInquiries = response.data.result.totalElements;
                    this.totalPages = response.data.result.totalPages;
                } else {
                    console.error("문의 목록 불러오기 실패:", response.data.message);
                    return;
                }

                // 각 문의에 대한 상품 이미지 URL을 추가로 불러옴 (병렬 처리)
                const productDetails = await Promise.all(
                    this.inquiries.map(inquiry => this.boardStore.getDetail(inquiry.productBoardIdx))
                );

                // 상품 이미지 URL을 각 문의에 추가
                this.inquiries.forEach((inquiry, index) => {
                    inquiry.productImageUrl = productDetails[index]?.productThumbnailUrls?.[0] || null;
                }); 
        
            } catch (error) {
                console.error("로그인된 사용자의 문의 목록을 불러오는 데 실패했습니다.", error);
            }
        },
        toggleDetail(index) {
            this.showDetailIndex = this.showDetailIndex === index ? null : index;
        },
        openEditModal(inquiry) {
            this.selectedInquiry = inquiry;
            this.showEditInquiryModal = true; // 수정 모달 표시
        },
        closeModal() {
            this.showEditInquiryModal = false;
        },
        async updateInquiry(updatedInquiry) {
        // updatedInquiry에 idx가 없으면 경고 메시지를 출력하고 종료
        if (!updatedInquiry || !updatedInquiry.idx) {
            console.error("No idx found in updated inquiry:", updatedInquiry);
            return;
        }

        try {
            // PUT 요청을 통해 문의 수정
            const response = await axios.put(`/api/qna/question/update/${updatedInquiry.idx}`, updatedInquiry);
            if (response.data.isSuccess) {
                // 문의 수정 후 목록을 다시 로드
                await this.loadMyInquiries();
                this.showEditInquiryModal = false;  // 모달 닫기
            } else {
                console.error("문의 수정 실패:", response.data.message);
            }
        } catch (error) {
            console.error("문의 수정 중 오류 발생:", error);
        }
    },
        async deleteInquiry(idx, index) {
            try {
                await axios.delete(`/api/qna/question/delete/${idx}`);
                this.inquiries.splice(index, 1); // 삭제 후 목록에서 제거
                this.totalInquiries -= 1; // 전체 문의 수 감소

                if (this.currentPage > 1 && this.inquiries.length === 0) {
                    this.currentPage -= 1;
                }
                
                this.loadMyInquiries(this.currentPage);  // 현재 페이지 데이터 다시 로드
                this.showDetailIndex = null; // 페이지 이동 시 토글 상태 초기화
            } catch (error) {
                console.error("문의 삭제 실패:", error);
            }
        },
        formatDate(dateString) {
            const date = new Date(dateString);
            return isNaN(date.getTime()) ? 'Invalid Date' : date.toLocaleDateString(); 
        },
        // 특정 페이지로 이동
        goToPage(pageNumber) {
            if (pageNumber >= 1 && pageNumber <= this.totalPages) {
                this.currentPage = pageNumber;
                this.loadMyInquiries(pageNumber); // 해당 페이지 데이터 로드

                // 현재 페이지 그룹 업데이트 (페이지 그룹에 따른 계산)
                const newPageGroup = Math.ceil(pageNumber / this.pagesPerGroup);
                if (newPageGroup !== this.currentPageGroup) {
                    this.currentPageGroup = newPageGroup;
                }

                this.showDetailIndex = null; // 페이지 이동 시 토글 초기화
            }
        },
         // 처음 페이지로 이동
        goToFirstPage() {
            this.goToPage(1); // 첫 페이지로 이동
        },
        // 마지막 페이지로 이동
        goToLastPage() {
            this.goToPage(this.totalPages); // 마지막 페이지로 이동
        },
        // 다음 페이지 이동
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.goToPage(this.currentPage + 1); // 현재 페이지의 다음 페이지로 이동
            }
        },
        // 이전 페이지 이동
        prevPage() {
            if (this.currentPage > 1) {
                this.goToPage(this.currentPage - 1); // 현재 페이지의 이전 페이지로 이동
            }
        },
    },
}
</script>


<style scoped>
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

.row-inquiry-detail>.inquiry-detail-content>.content-row {
    display: flex;
    align-items: center;
    /* 아이콘과 텍스트를 같은 높이로 정렬 */
    justify-content: flex-start;
    margin-bottom: 20px;
}

.row-inquiry-detail>.inquiry-detail-content>.content-row>.icon-wrap {
    flex-shrink: 0;
    display: inline-flex;
    /* 아이콘을 줄에 맞게 배치 */
    align-items: center;
    /* 아이콘과 텍스트를 수평 정렬 */
}

.row-inquiry-detail>.inquiry-detail-content>.content-row>.subject {
    margin-left: 12px;
    font-weight: 400;
    line-height: 24px;
    letter-spacing: 0px;
    min-height: 24px;
    word-break: break-all;
    display: flex;
}

:root {
    --swiper-theme-color: #007aff;
}

body {
    margin: 0;
}

body,
html {
    height: 100%;
}

*,
:after,
:before,
legend,
td,
th {
    padding: 0;
}

*,
:after,
:before {
    box-sizing: border-box;
    margin: 0;
}

button,
select {
    text-transform: none;
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

button,
input {
    overflow: visible;
}

body,
button,
input,
select,
textarea {
    font-size: 14px;
    color: #333;
}

html,
button,
input,
select,
textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
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

html {
    line-height: 1.15;
    -webkit-text-size-adjust: 100%;
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

.css-heioij {
    overflow: hidden;
    width: 650px;
    background-color: rgb(255, 255, 255);
    border-radius: 16px;
}

.css-oc8mjz {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    margin: 0px 20px;
    padding: 25px 0px 20px;
    border-bottom: 2px solid rgb(51, 51, 51);
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

ol,
ul {
    list-style-type: none;
}

.css-1qphylu {
    position: absolute;
    top: 70px;
    right: 20px;
    bottom: -236px;
    z-index: 1;
    margin-top: 236px;
}

.css-1l2tkkh {
    overflow: hidden;
    width: 375px;
    border-radius: 16px;
}

.css-3rbu7q.pc {
    padding-bottom: 0px;
}

.css-3rbu7q {
    padding-bottom: 20px;
    background-color: rgb(242, 245, 248);
}

.css-3rbu7q.pc .top-info-section {
    margin-bottom: 8px;
    padding: 24px 20px 20px;
}

.css-3rbu7q .top-info-section {
    padding: 24px 16px 20px;
}

.css-3rbu7q>div {
    background-color: rgb(255, 255, 255);
}

.css-jagjhu {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    align-items: center;
}

.css-jagjhu .grade-name {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    min-width: calc(100% - 100px);
}

.css-1w5u25a>.inquiry-product-info {
    position: relative;
    -webkit-tap-highlight-color: transparent;
    text-align: left;
    width: 100%;
    background-color: rgb(255, 255, 255);
    padding: 16px 20px;
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: justify;
    justify-content: space-between;
    border: none;
    /* 모든 보더 제거 */
}

[type=button],
[type=reset],
[type=submit],
button {
    -webkit-appearance: button;
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.product-name {
    max-width: 70vw;
    overflow: hidden;
    margin-bottom: 4px;
    font-size: 13px;
    line-height: 20px;
    color: rgb(153, 153, 153);
    white-space: nowrap;
    text-overflow: ellipsis;
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.inquiry-subject {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: start;
    justify-content: flex-start;
    margin-bottom: 15px;
    margin-top: 15px;
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.inquiry-subject>.subject-text {
    overflow: hidden;
    max-width: 80vw;
    font-size: 15px;
    line-height: 18px;
    font-weight: 400;
    color: rgb(51, 51, 51);
    white-space: nowrap;
    text-overflow: ellipsis;
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.status-and-created-at {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    color: rgb(153, 153, 153);
    line-height: 16px;
    font-size: 13px;
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.status-and-created-at>.status-text.commented {
    color: rgb(95, 0, 128);
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.status-and-created-at>.status-text {
    color: rgb(153, 153, 153);
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.status-and-created-at>.text-divider {
    margin: 3px 6px 0px;
    width: 1px;
    height: 10px;
    background-color: rgb(226, 226, 226);
}

.css-1w5u25a>.inquiry-product-info>.product-info-wrap>.status-and-created-at>.created-at-text {
    font-size: 13px;
}

.css-1w5u25a>.inquiry-product-info>.product-image-wrap {
    display: block;
    border-radius: 6px;
    overflow: hidden;
    width: 60px;
    height: 60px;
}

.css-1w5u25a>.row-inquiry-detail {
    overflow: hidden;
    max-height: 0;
    background-color: rgb(250, 250, 250);
    transition: max-height 0.5s ease;
}

.css-1w5u25a>.row-inquiry-detail-enter-active,
.css-1w5u25a>.row-inquiry-detail-leave-active {
    transition: max-height 0.5s ease;
}

.css-1w5u25a>.row-inquiry-detail-enter {
    max-height: 0;
}

.css-1w5u25a>.row-inquiry-detail-enter-to {
    max-height: 1000px;
    /* 넉넉한 값으로 설정하여 내용이 보이게 */
}

.row-inquiry-detail>.inquiry-detail-content {
    padding: 20px;
}

.row-inquiry-detail>.inquiry-detail-content>.content-row:last-child {
    margin-bottom: 0px;
}

.resize-triggers,
.resize-triggers>div,
.contract-trigger:before {
    content: " ";
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    overflow: hidden;
    z-index: -1;
}

.resize-triggers {
    animation: 1ms resizeanim;
    visibility: hidden;
    position: absolute;
    width: 100%;
    height: 100%;
    pointer-events: none;
}

.resize-triggers>div {
    position: absolute;
    top: 0;
    left: 0;
    width: 200%;
    height: 200%;
}

@keyframes resizeanim {
    from {
        width: 100%;
        height: 100%;
    }

    to {
        width: 100%;
        height: 100%;
    }
}

img,
legend {
    border: 0;
    vertical-align: top;
}

img {
    overflow-clip-margin: content-box;
    overflow: clip;
}

.css-1w5u25a>.inquiry-product-info>.product-image-wrap img {
    border: 0;
    vertical-align: top;
    width: 60px;
    height: 60px;
}

.row-inquiry-detail .status-and-created-at {
    display: flex;
    justify-content: flex-end;
    /* 오른쪽 정렬 */
    margin-top: 10px;
    /* 상단 여백 추가 (필요에 따라 조절) */
}

.row-inquiry-detail .created-at-text {
    font-size: 14px;
    color: #848f9a;
    /* 색상 필요에 따라 조절 */
}

/* Flex 속성으로 답변을 가로로 나열 */
.horizontal-answers {
    display: block;
    flex-direction: row;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
}

.answer-section {
    width: 100%; /* 한 줄에 하나씩 답변이 나열되도록 설정 */
}

.answer-content {
    margin-top: 25px;
    margin-left: 12px;
    font-weight: 400;
    line-height: 24px;
    letter-spacing: 0px;
    min-height: 24px;
    word-break: break-all;
    display: flex;
    /* 수직 정렬을 위해 flex-direction을 column으로 설정 */
    flex-direction: column;
    justify-content: space-between;
}

.answer-meta {
    margin-top: 10px;
    font-size: 12px;
    color: gray;
    
}

.no-answer {
    margin-top: 10px;
    color: gray;
    display: flex;
    align-items: center;
}

.no-answer img {
    margin-right: 12px;
    /* 아이콘과 텍스트 사이에 여백 추가 */
}

.css-1w5u25a>.inquiry-product-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.product-info-wrap {
    flex: 1;
}

.product-image-wrap img {
    width: 60px;
    height: 60px;
    object-fit: cover;
}

.row-inquiry-detail {
    margin-top: 10px;
    padding: 10px;
    background-color: #f9f9f9;
    border-radius: 5px;
}

.row-inquiry-detail .status-and-created-at {
    display: flex;
    justify-content: flex-end;
}

.row-inquiry-detail .created-at-text {
    font-size: 14px;
    color: #848f9a;
}

.flex-row {
    display: flex;
    align-items: center;
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
</style>
