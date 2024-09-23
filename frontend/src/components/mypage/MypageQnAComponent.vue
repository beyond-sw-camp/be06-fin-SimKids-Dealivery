<template>
    
    <div class="css-heioij eug5r8l1">
        <div class="css-1xdhyk6 eug5r8l0">
            <ul>
                <li class="css-1w5u25a e1q2koch0" v-for="(inquiry, index) in inquiries || []" :key="index">
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
                        <a :href="'/product/' + inquiry.productBoardIdx" class="product-image-wrap">
                            <!-- 이미지가 없는 경우 대비하여 기본 이미지를 제공 -->
                            <img :src="getProductImage(inquiry)" :alt="inquiry.title">
                        </a>
                    </button>
                    <div name="fade" @after-enter="afterEnter" @after-leave="afterLeave">
                        <div v-if="showDetailIndex === index" class="row-inquiry-detail">
                            <div class="inquiry-detail-content">
                                <!-- 아이콘과 질문/답변을 같은 줄에 배치 -->
                                <div class="content-row flex-row">
                                    <div class="icon-wrap">
                                        <img src="https://res.kurly.com/kurly/ico/2021/question_24_24_purple.svg"
                                            alt="질문">
                                    </div>
                                    <p class="subject">{{ inquiry.content }}</p>
                                </div>
                                <div class="content-row horizontal-answers flex-row" v-if="inquiry.answers.length > 0">
                                    <div v-for="(answer, answerIndex) in inquiry.answers" :key="answerIndex"
                                        class="answer-section flex-row">
                                        <div class="icon-wrap">
                                            <img src="https://res.kurly.com/kurly/ico/2021/answer_24_24_purple.svg"
                                                alt="답변">
                                        </div>
                                        <div class="answer-content">
                                            <p class="subject">{{ answer.content }}</p>
                                            <div class="answer-meta">
                                                <span class="answer-date">{{ formatDate(answer.createdAt) }}</span>
                                            </div>
                                        </div>
                                    </div>
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
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            inquiries: [],  // 서버로부터 받아온 문의 목록
            showDetailIndex: null
        };
    },
    mounted() {
        this.loadMyInquiries();  // 컴포넌트가 마운트될 때 로그인된 사용자의 문의 목록을 불러옴
    },
    methods: {
        async loadMyInquiries() {
            try {
                const response = await axios.get('/api/qna/question/list/my');
                this.inquiries = response.data.result;  // 문의 목록을 데이터에 저장
                console.log(this.inquiries);
            } catch (error) {
                console.error("로그인된 사용자의 문의 목록을 불러오는 데 실패했습니다.", error);
            }
        },
        toggleDetail(index) {
            this.showDetailIndex = this.showDetailIndex === index ? null : index;
        },
        afterEnter(el) {
            el.style.maxHeight = 'none';  // 애니메이션 후 스타일 설정
        },
        afterLeave(el) {
            el.style.maxHeight = '0px';  // 애니메이션 후 스타일 설정
        },
        formatDate(dateString) {
            const date = new Date(dateString);
            if (isNaN(date.getTime())) {
                return 'Invalid Date';
            }
            return date.toLocaleDateString(); // 원하는 형식으로 변환
        },
        getProductImage(inquiry) {
            return inquiry.productImageUrl || "https://via.placeholder.com/150";  // 기본 이미지 제공
        }
    },
    mounted(){
    window.scrollTo({
            top: 100,
            left: 0,
            behavior: 'smooth'
        });
  },
}
</script>


<style scoped>
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
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
}

.answer-section {
    flex: 1 1 auto;
    min-width: 200px;
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
</style>
