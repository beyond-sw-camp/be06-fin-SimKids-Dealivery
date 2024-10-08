<template>
    <div role="presentation" class="MuiDialog-root css-b5lr18" style="position: fixed; z-index: 1300; inset: 0px;">
        <div class="MuiBackdrop-root" aria-hidden="true"
            style="opacity: 1; transition: opacity 225ms cubic-bezier(0.4, 0, 0.2, 1);"></div>
        <div tabindex="0" data-test="sentinelStart"></div>
        <div class="MuiDialog-container MuiDialog-scrollPaper" role="none presentation" tabindex="-1"
            style="opacity: 1; transition: opacity 225ms cubic-bezier(0.4, 0, 0.2, 1);">
            <div class="MuiPaper-root MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthSm MuiDialog-paperFullWidth MuiPaper-elevation24 MuiPaper-rounded"
                role="dialog">
                <div class="MuiDialogContent-root css-ew9uri">
                    <div class="css-190e3ze eg43r0m0">
                        <div class="css-1c7i6of et95tiw2">
                            <div class="css-1d3g9q7 et95tiw1">{{ isEditMode ? '문의 수정하기' : '상품 문의하기' }}</div>
                            <span class="css-e50sj0 et95tiw0" @click="closeModal"></span>
                        </div>
                        <div class="css-1tm481w eell72m3">
                            <div class="css-l4dbne eell72m2">
                                <img :src="thumbnail" class="css-1vpfo16 eell72m1">
                            </div>
                            <div class="css-1mysn55 eell72m0">
                                <span>{{ title }}</span>
                            </div>
                        </div>
                        <div class="css-4qu8li e43j10r2">
                            <div class="css-171jy4 e43j10r1"><span>제목</span></div>
                            <div class="css-ehb3da e43j10r0">
                                <div class="css-1u16q1v e1uzxhvi6">
                                    <div height="42" class="css-1xbd2py e1uzxhvi3">
                                        <input data-testid="input-box" name="subject" placeholder="제목을 입력해 주세요"
                                            type="text" height="42" class="css-cjei9u e1uzxhvi2" v-model="subject"
                                            maxlength="35">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="css-4qu8li e43j10r2">
                            <div class="css-171jy4 e43j10r1"><span>내용</span></div>
                            <div class="css-ehb3da e43j10r0">
                                <div class="css-17xxk8 e6w4oc80">
                                    <div class="css-0 e1tjt2bn7">
                                        <div class="css-l45xk5 e1tjt2bn5">
                                            <div class="placeholder css-1ofzfyp e1tjt2bn6" v-if="!content && !focused">
                                                <div class="css-ylej1f e41kell0">
                                                    <strong>상품문의 작성 전 확인해 주세요</strong>
                                                    <strong>글자 수 제한</strong>
                                                    <ul>
                                                        <li>제목은 공백을 제외하고 최소 2자 이상, 최대 35자까지 입력해야 등록 가능합니다.</li>
                                                        <li>내용은 공백을 제외하고 최소 5자 이상, 최대 255자까지 입력해야 등록 가능합니다.</li>
                                                    </ul>
                                                    <strong>주문취소</strong>
                                                    <ul>
                                                        <li>[결제대기] 단계 : [마이페이지 > 주문내역 > 주문내역 상세] 에서 직접 취소 가능</li>
                                                        <li>생산이 시작된 [주문완료] 이후에는 취소가 제한되는 점 고객님의 양해 부탁드립니다.</li>
                                                    </ul>
                                                    <p>※ 주문상품의 부분 취소는 불가능합니다. 전체 주문 취소 후 재구매 해주세요.</p>
                                                </div>
                                            </div>
                                            <textarea inputmode="text" aria-label="textarea-message" name="content"
                                                class="css-5etceh e1tjt2bn1" v-model="content" @focus="focused = true"
                                                @blur="focused = false" @input="limitContentLength"
                                                style="overflow-y: auto;"></textarea>
                                            <span class="content-length-counter css-dbwxb9 e1tjt2bn0">
                                                <span><span :class="{ 'flashing': content.length > 255 }"
                                                        class="css-14kcwq8 e1tjt2bn2">{{ content.length }}</span>
                                                    <span class="css-14kcwq8 e1tjt2bn3">/ 255</span>
                                                </span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="css-f9c7pn e6wys6s0">
                            <button class="css-wg85j7 e4nu7ef3" type="button" @click="closeModal">
                                <span class="css-nytqmg e4nu7ef1">취소</span>
                            </button>
                            <button class="css-f4f4h7 e4nu7ef3" type="button" :disabled="!isFormValid"
                                @click="submitForm">
                                <span class="css-nytqmg e4nu7ef1">{{ isEditMode ? '수정' : '등록' }}</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div tabindex="0" data-test="sentinelEnd"></div>
    </div>
</template>

<script>
import axios from 'axios';
import { useQnaStore } from "@/stores/useQnaStore";
import { mapStores } from "pinia";
import { useUserStore } from "@/stores/useUserStore";  // 사용자 스토어 가져오기

export default {
    name: "QnaRegisterModalComponent",
    props: {
        isEditMode: {
            type: Boolean,
            default: false
        },
        initialSubject: {
            type: String,
            default: ""
        },
        initialContent: {
            type: String,
            default: ""
        },
        inquiryId: {
            type: Number, // 수정 모드일 때 사용할 문의 ID
            default: null
        },
        thumbnail: {
            type: String,
            required: true
        },
        title: {
            type: String,
            required: true
        },
        productBoardIdx: {
            type: Number,
            required: true,  // 상품 ID가 필수 값
        },
    },
    data() {
        return {
            subject: this.initialSubject,
            content: this.initialContent,
            focused: false,
        };
    },
    watch: {
        // props가 변경될 때 data에 반영
        initialSubject(newVal) {
            this.subject = newVal;
        },
        initialContent(newVal) {
            this.content = newVal;
        }
    },
    computed: {
        isFormValid() {
            return this.subject.trim().length >= 2 && this.content.trim().length >= 5;
        },
        ...mapStores(useQnaStore, useUserStore),
        localTableData() {
            return this.qnaStore.inquiries;
        },
    },
    methods: {
        closeModal() {
            this.$emit("close");
        },
        async submitForm() {
            // 문의 수정 또는 등록 로직
            const inquiryData = {
                idx: this.inquiryId,  // 수정 시 ID 전달
                title: this.subject,
                content: this.content,
                productBoardIdx: this.productBoardIdx,
            };

            try {
                if (this.isEditMode && this.inquiryId) {
                    // 수정 모드일 때 PUT 요청
                    const response = await axios.put(`/api/qna/question/update/${this.inquiryId}`, inquiryData);
                    if (response.data.isSuccess) {
                        this.$emit("submit", inquiryData); // 부모 컴포넌트에 수정된 데이터를 전달
                    }
                } else {
                    // 등록 모드일 때 POST 요청
                    const response = await axios.post('/api/qna/question/create', inquiryData);
                    if (response.data.isSuccess) {
                        this.$emit("submit", inquiryData); // 부모 컴포넌트에 등록된 데이터를 전달
                    }
                }
                this.closeModal();
            } catch (error) {
                console.error(this.isEditMode ? "문의 수정 중 오류 발생" : "문의 등록 중 오류 발생", error);
            }
        },
        limitContentLength() {
            if (this.content.length > 255) {
                this.content = this.content.slice(0, 255);
            }
        }
    }
};
</script>


<style scoped>
*,
:after,
:before {
    box-sizing: border-box;
    margin: 0;
}

*,
:after,
:before,
legend,
td,
th {
    padding: 0;
}

.MuiBackdrop-root {
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    z-index: -1;
    position: fixed;
    align-items: center;
    justify-content: center;
    background-color: rgba(0, 0, 0, 0.5);
    -webkit-tap-highlight-color: transparent;
}

.MuiDialog-container {
    height: 100%;
    outline: 0;
}

.MuiDialog-scrollPaper {
    display: flex;
    align-items: center;
    justify-content: center;
}

.css-b5lr18 .MuiPaper-elevation24 {
    box-shadow: none;
}

.css-b5lr18 .MuiPaper-root {
    max-width: fit-content;
    border-radius: 12px;
}

.MuiDialog-paperFullWidth {
    width: calc(100% - 64px);
}

.MuiDialog-paperWidthSm {
    max-width: 600px;
}

.MuiDialog-paperScrollPaper {
    display: flex;
    max-height: calc(100% - 64px);
    flex-direction: column;
}

.MuiDialog-paper {
    margin: 32px;
    position: relative;
    overflow-y: auto;
}

.MuiPaper-root {
    color: rgba(0, 0, 0, 0.87);
    transition: box-shadow 300ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
    background-color: #fff;
}

.css-ew9uri.MuiDialogContent-root:first-of-type {
    padding: 0px;
}

.MuiDialogContent-root {
    flex: 1 1 auto;
    padding: 8px 24px;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
}

.css-190e3ze {
    display: flex;
    flex-direction: column;
    width: 700px;
    padding: 30px;
    background: rgb(255, 255, 255);
}

.css-1c7i6of {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    padding-bottom: 22px;
    border-bottom: 1px solid rgb(244, 244, 244);
    letter-spacing: -1px;
}

.css-1d3g9q7 {
    font-size: 24px;
    font-weight: 500;
    line-height: 30px;
    color: rgb(51, 51, 51);
}

.css-e50sj0 {
    width: 32px;
    height: 32px;
    display: inline-block;
    background-image: url("data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzIiIGhlaWdodD0iMzIiIHZpZXdCb3g9IjAgMCAzMiAzMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgZD0iTTAgMGgzMnYzMkgweiIvPgogICAgICAgIDxnIHN0cm9rZT0iIzk5OSIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIj4KICAgICAgICAgICAgPHBhdGggZD0iTTI2IDI2IDYgNk02IDI2IDI2IDYiLz4KICAgICAgICA8L2c+CiAgICAgICAgPHBhdGggZD0ibTYuMjg0IDUuNTg5LjA3LjA1N0wxNiAxNS4yOTNsOS42NDYtOS42NDdhLjUuNSAwIDAgMSAuNzY1LjYzOGwtLjA1Ny4wN0wxNi43MDcgMTZsOS42NDcgOS42NDZhLjUuNSAwIDAgMS0uNjM4Ljc2NWwtLjA3LS4wNTdMMTYgMTYuNzA3bC05LjY0NiA5LjY0N2EuNS41IDAgMCAxLS43NjUtLjYzOGwuMDU3LS4wN0wxNS4yOTMgMTYgNS42NDYgNi4zNTRhLjUuNSAwIDAgMSAuNjM4LS43NjV6IiBmaWxsPSIjOTk5Ii8+CiAgICA8L2c+Cjwvc3ZnPgo=");
    background-size: cover;
    background-position: center center;
    cursor: pointer;
}

.css-1tm481w {
    display: flex;
    padding: 16px 0px;
    border-bottom: 1px solid rgb(244, 244, 244);
}

.css-l4dbne {
    flex: 1 1 0%;
    display: flex;
    -webkit-box-pack: start;
    justify-content: flex-start;
}

.css-1vpfo16 {
    width: 72px;
    height: 72px;
    object-fit: cover;
    border-radius: 4px;
    background-color: rgb(250, 250, 250);
    background-position: 50% 50%;
    background-size: cover;
    background-repeat: no-repeat;
}

img {
    border-style: none;
}

img,
legend {
    border: 0;
    vertical-align: top;
}

canvas,
img,
video {
    max-width: 100%;
}

.css-1mysn55 {
    flex: 4.7 1 0%;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
}

.css-1mysn55 span {
    font-size: 16px;
    font-weight: 500;
    line-height: 22px;
    color: rgb(51, 51, 51);
    text-overflow: ellipsis;
    letter-spacing: normal;
}

.css-4qu8li {
    display: flex;
    margin-top: 16px;
}

.css-171jy4 {
    width: 100px;
    padding-top: 12px;
}

.css-171jy4 span {
    padding-top: 10px;
    padding-left: 10px;
    font-size: 14px;
    font-weight: 500;
    color: rgb(51, 51, 51);
}

.css-ehb3da {
    flex: 6.5 1 0%;
}

.css-1u16q1v {
    padding-bottom: 0px;
}

.css-1xbd2py {
    position: relative;
    height: 42px;
}

.css-1u16q1v input {
    height: 44px;
    padding: 0px 11px 0px 15px;
    font-size: 14px;
}

.css-cjei9u {
    width: 100%;
    height: 42px;
    padding: 0px 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid rgb(221, 221, 221);
    font-weight: 400;
    font-size: 16px;
    line-height: 40px;
    color: rgb(51, 51, 51);
    outline: none;
    box-sizing: border-box;
}

.css-17xxk8 {
    position: relative;
}

.css-l45xk5 {
    position: relative;
    display: flex;
    flex-direction: column;
    height: 250px;
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(221, 221, 221);
    border-radius: 4px;
}

.css-17xxk8 textarea {
    font-size: 14px;
}

html,
button,
input,
select,
textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

textarea {
    width: 100%;
    height: 100%;
    padding: 15px;
    border: 1px solid rgb(221, 221, 221);
    font-size: 16px;
    line-height: 21px;
    color: rgb(51, 51, 51);
    resize: vertical;
}

input,
textarea {
    box-sizing: border-box;
    margin: 0;
    padding: 10px;
    border-radius: 4px;
}

.placeholder {
    /* 숨겨진 상태에서 보이도록 하는 기본 스타일 설정 */
    display: block;
}

.css-ylej1f {
    font-size: 14px;
    line-height: 20px;
    color: rgb(153, 153, 153);
    cursor: pointer;
}

.css-ylej1f strong {
    display: block;
    font-weight: 500;
    padding-bottom: 8px;
}

ol,
ul {
    list-style-type: none;
}

.css-ylej1f ul {
    margin: 0;
    padding: 0;
    list-style-position: inside;
}

.css-ylej1f ul li {
    padding: 0;
    margin: 0;
    text-indent: -20px;
    /* 항목 앞의 들여쓰기를 제거하기 위한 설정 */
    padding-left: 20px;
    /* 항목 내용의 들여쓰기를 추가 */
}

li {
    display: list-item;
    text-align: -webkit-match-parent;
    unicode-bidi: isolate;
}

ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
    unicode-bidi: isolate;
}

.css-ylej1f ul+strong {
    padding-top: 10px;
}

.css-f9c7pn {
    display: flex;
    -webkit-box-pack: center;
    justify-content: center;
    gap: 8px;
    border-top: 1px solid rgb(244, 244, 244);
    text-align: center;
    padding-top: 20px;
}

.css-f9c7pn button {
    width: 110px;
    height: 45px;
    border-radius: 3px;
}

.css-wg85j7 {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 3px;
    color: rgb(51, 51, 51);
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(221, 221, 221);
}

.css-dbwxb9 {
    padding: 0px 16px 16px;
    font-size: 12px;
    text-align: right;
}

.css-f4f4h7:disabled {
    background-color: rgb(221, 221, 221);
    color: #fff;
    border: 0px;
}

.css-f4f4h7 {
    background-color: #5f0080;
    color: white;
}

.css-nytqmg {
    display: inline-block;
    font-size: 14px;
    font-weight: 500;
}

.css-ylej1f ul+p {
    padding-top: 4px;
}

.css-ylej1f p {
    color: rgb(240, 63, 64);
}

*,
:after,
:before,
legend,
td,
th {
    padding: 0;
}

.css-17xxk8 .placeholder {
    overflow-y: auto;
    z-index: 0;
    height: 86%;
}

.css-1ofzfyp {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    padding: 15px 15px 32px;
}

.css-5etceh {
    display: block;
    width: 100%;
    height: 100%;
    padding: 15px 16px;
    font-size: 16px;
    line-height: 21px;
    word-break: break-all;
    z-index: 1;
    background: none;
    border-radius: 4px;
    color: rgb(51, 51, 51);
    outline: none;
    resize: vertical;
    border: none;
}
</style>
