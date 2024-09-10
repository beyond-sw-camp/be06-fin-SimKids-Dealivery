<template>
    <div class="css-heioij eug5r8l1">
        <div class="css-1xdhyk6 eug5r8l0">
            <ul>
                <li class="css-1w5u25a e1q2koch0" v-for="(inquiry, index) in inquiries" :key="index">
                    <button type="button" class="inquiry-product-info" @click="toggleDetail(index)">
                        <div class="product-info-wrap">
                            <div class="product-name">{{ inquiry.productName }}</div>
                            <div class="inquiry-subject">
                                <p class="subject-text">{{ inquiry.subject }}</p>
                            </div>
                            <div class="status-and-created-at">
                                <span class="status-text commented">{{ inquiry.status }}</span>
                                <span class="text-divider"></span>
                                <span class="created-at-text">{{ inquiry.date }}</span>
                            </div>
                        </div>
                        <a :href="inquiry.productLink" class="product-image-wrap">
                            <img :src="inquiry.productImage" :alt="inquiry.productName">
                        </a>
                    </button>
                    <transition name="fade" @after-enter="afterEnter" @after-leave="afterLeave">
                        <div v-if="showDetailIndex === index" class="row-inquiry-detail">
                            <div class="inquiry-detail-content">
                                <div class="content-row">
                                    <div class="icon-wrap">
                                        <img src="https://res.kurly.com/kurly/ico/2021/question_24_24_purple.svg"
                                            alt="질문">
                                    </div>
                                    <p class="subject">{{ inquiry.question }}</p>
                                </div>
                                <div class="content-row">
                                    <div class="icon-wrap">
                                        <img src="https://res.kurly.com/kurly/ico/2021/answer_24_24_purple.svg"
                                            alt="답변">
                                    </div>
                                    <p class="subject">{{ inquiry.answer }}</p>
                                </div>
                                <div class="status-and-created-at">
                                    <span class="created-at-text">{{ inquiry.answerDate }}</span>
                                </div>
                            </div>
                        </div>
                    </transition>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            inquiries: [
                {
                    productName: "[라 메르] 쿠션 & 크렘 드 라 메르 15ml 크림 듀오 세트 4종",
                    subject: "추후에 할인 재예정 있을까요?",
                    status: "답변완료",
                    date: "2024.09.03",
                    productLink: "/goods/1000747340",
                    productImage: "https://product-image.kurly.com/product/image/71d93cd9-06fb-4f1a-a96d-27bcf9fb69bc.jpg",
                    question: "안녕하세요. 해당 상품 추후에 할인 재 예정 있는지 문의드립니다.",
                    answer: "안녕하세요. 고객님 항상 라 메르를 믿고 이용해 주시는 고객님께 감사 인사드립니다. 문의하신 상품의 경우 현재 진행하고 있는 할인 이벤트는 상시 할인 진행 예정인 점 안내해 드립니다. 감사합니다.",
                    answerDate: "2024.09.04"
                },
                // Add more items as needed
            ],
            showDetailIndex: null
        };
    },
    methods: {
        toggleDetail(index) {
            this.showDetailIndex = this.showDetailIndex === index ? null : index;
        },
        afterEnter(el) {
            el.style.maxHeight = 'none';
        },
        afterLeave(el) {
            el.style.maxHeight = '0px';
        }
    }
}
</script>

<style scoped>
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
}

.css-1w5u25a>.row-inquiry-detail-enter-active,
.css-1w5u25a>.row-inquiry-detail-leave-active {
    transition: max-height 0.5s ease;
}

.css-1w5u25a>.row-inquiry-detail-enter,
.css-1w5u25a>.row-inquiry-detail-leave-to {
    max-height: 0;
}

.css-1w5u25a>.row-inquiry-detail-enter-to {
    max-height: 300px;
    /* Adjust based on content height */
}

.row-inquiry-detail>.inquiry-detail-content {
    padding: 20px;
}

.row-inquiry-detail>.inquiry-detail-content>.content-row {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
    margin-bottom: 20px;
}

.row-inquiry-detail>.inquiry-detail-content>.content-row>.icon-wrap {
    flex-shrink: 0;
}

.row-inquiry-detail>.inquiry-detail-content>.content-row>.subject {
    margin-left: 12px;
    font-weight: 400;
    line-height: 24px;
    letter-spacing: 0px;
    min-height: 24px;
    word-break: break-all;
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
</style>
