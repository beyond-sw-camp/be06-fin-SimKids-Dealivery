<template>
  <div id="productModal" class="modal" @click.self="closeModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <div class="MuiDialogContent-root css-ew9uri">
        <div class="css-190e3ze eg43r0m0">
          <div class="css-1c7i6of et95tiw2">
            <div class="css-1d3g9q7 et95tiw1">답변 등록하기</div>
          </div>
          <div class="css-1tm481w eell72m3">
            <div class="css-l4dbne eell72m2">
              <img :src="thumbnail" class="css-1vpfo16 eell72m1" alt="Product Image" />
            </div>
            <div class="css-1mysn55 eell72m0">
              <span>{{ productTitle }}</span>
            </div>
          </div>
          <div class="css-4qu8li e43j10r2">
            <div class="css-171jy4 e43j10r1">
              <span>문의 제목</span>
            </div>
            <div class="css-ehb3da e43j10r0">
              <div class="css-1u16q1v e1uzxhvi6">
                <div height="42" class="css-1xbd2py e1uzxhvi3">
                  <p>{{ title }}</p>
                </div>
              </div>
            </div>
          </div>
          <div class="css-4qu8li e43j10r2">
            <div class="css-171jy4 e43j10r1">
              <span>문의 내용</span>
            </div>
            <div class="css-ehb3da e43j10r0">
              <div class="css-1u16q1v e1uzxhvi6">
                <div height="42" class="css-1xbd2py e1uzxhvi3">
                  <p>{{ content }}</p>
                </div>
              </div>
            </div>
          </div>

          <div class="css-4qu8li e43j10r2">
            <div class="css-171jy4 e43j10r1">
              <span>답변</span>
            </div>
            <div class="css-ehb3da e43j10r0">
              <div class="css-17xxk8 e6w4oc80">
                <div class="css-0 e1tjt2bn7">
                  <div class="css-l45xk5 e1tjt2bn5">
                    <textarea inputmode="text" aria-label="textarea-message" name="content" placeholder="답변을 입력해 주세요"
                      class="css-5etceh e1tjt2bn1" v-model="textareaContent" @input="checkTextareaContent"></textarea>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="css-f9c7pn e6wys6s0">
            <button :class="{
                'css-f4f4h7 e4nu7ef3': true,
                'enabled-button': !isRegisterDisabled,
              }" type="button" :disabled="isRegisterDisabled" @click="registerAnswer">
              <span :class="{
                  'css-nytqmg e4nu7ef1': true,
                  'enabled-button': !isRegisterDisabled,
                }">등록</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CompanyQnAModalComponent",
  props: {
    thumbnail: {
      type: String,
      required: true,
    },
    productTitle: {
      type: String,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    content: {
      type: String,
      required: true,
    },
    answerContent: {
      type: String,
      default: "", // 기본값으로 빈 문자열
    },
    questionIdx: {
      type: Number,
      required: true, // 문의 ID
    },
  },
  mounted() {
    console.log("Thumbnail: ", this.thumbnail); // 데이터가 전달되었는지 확인
    console.log("Product Title: ", this.productTitle);
    // 만약 answerContent가 있으면 textareaContent에 세팅
    if (this.answerContent) {
      this.textareaContent = this.answerContent;
      this.isRegisterDisabled = false; // 답변이 있을 때는 버튼 활성화
    }
  },
  data() {
    return {
      textareaContent: "",
      isRegisterDisabled: true,
    };
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    checkTextareaContent() {
      this.isRegisterDisabled = this.textareaContent.trim() === "";
    },
    async registerAnswer() {
    try {
      // 답변 등록 API 호출
      const response = await axios.post("/api/qna/answer/create", {
        questionIdx: this.questionIdx,  // 문의 ID 전달
        content: this.textareaContent,  // 답변 내용 전달
      });
      const newAnswer = response.data.result;  // 서버에서 응답 받은 새 답변
      this.$emit("registerAnswer", newAnswer); // 부모 컴포넌트로 새 답변 전달

      // 등록된 답변을 selectedInquiry의 answers에 추가
      if (this.selectedInquiry && Array.isArray(this.selectedInquiry.answers)) {
        this.selectedInquiry.answers.push(newAnswer);
      }
      
      this.closeModal();  // 모달 닫기
      } catch (error) {
      console.error("답변 등록 실패:", error);
      }
    },
  },
};
</script>

<style scoped>
.modal {
  /* display: none; 기본적으로 숨김 상태 */
  position: fixed; /* 화면에 고정 위치 */
  z-index: 1000; /* 다른 요소 위에 표시 */
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4); /* 배경색과 투명도 */
}

.modal-content {
  background-color: #fefefe;
  margin: 7% auto; /* 상단 여백을 7% 설정하고, 자동으로 좌우 중앙 정렬 */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* 모달의 너비를 80%로 설정 */
  max-width: 680px;
  border-radius: 10px;
  /* max-width: 600px; 최대 너비 설정 */
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
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

div {
  display: block;
  unicode-bidi: isolate;
}

body,
button,
input,
select,
textarea {
  font-size: 14px;
  color: #333;
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
  max-width: fit-content;
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

.MuiPaper-elevation24 {
  box-shadow: 0px 11px 15px -7px rgba(0, 0, 0, 0.2),
    0px 24px 38px 3px rgba(0, 0, 0, 0.14), 0px 9px 46px 8px rgba(0, 0, 0, 0.12);
}

.MuiPaper-rounded {
  border-radius: 4px;
}

.MuiPaper-root {
  color: rgba(0, 0, 0, 0.87);
  transition: box-shadow 300ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  background-color: #fff;
}

.css-ew9uri.MuiDialogContent-root:first-of-type {
  padding: 0px;
}

.MuiDialogContent-root:first-child {
  padding-top: 20px;
}

.css-ew9uri.MuiDialogContent-root {
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
  width: 600px;
  padding: 15px;
  background: rgb(255, 255, 255);
}

.MuiPaper-root {
  color: rgba(0, 0, 0, 0.87);
  transition: box-shadow 300ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  background-color: #fff;
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
  background-image: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzIiIGhlaWdodD0iMzIiIHZpZXdCb3g9IjAgMCAzMiAzMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgZD0iTTAgMGgzMnYzMkgweiIvPgogICAgICAgIDxnIHN0cm9rZT0iIzk5OSIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIj4KICAgICAgICAgICAgPHBhdGggZD0iTTI2IDI2IDYgNk02IDI2IDI2IDYiLz4KICAgICAgICA8L2c+CiAgICAgICAgPHBhdGggZD0ibTYuMjg0IDUuNTg5LjA3LjA1N0wxNiAxNS4yOTNsOS42NDYtOS42NDdhLjUuNSAwIDAgMSAuNzY1LjYzOGwtLjA1Ny4wN0wxNi43MDcgMTZsOS42NDcgOS42NDZhLjUuNSAwIDAgMS0uNjM4Ljc2NWwtLjA3LS4wNTdMMTYgMTYuNzA3bC05LjY0NiA5LjY0N2EuNS41IDAgMCAxLS43NjUtLjYzOGwuMDU3LS4wN0wxNS4yOTMgMTYgNS42NDYgNi4zNTRhLjUuNSAwIDAgMSAuNjM4LS43NjV6IiBmaWxsPSIjOTk5Ii8+CiAgICA8L2c+Cjwvc3ZnPgo=);
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

img {
  overflow-clip-margin: content-box;
  overflow: clip;
}

.css-1mysn55 {
  flex: 6.5 1 0%;
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
  margin-bottom: 16px;
  text-align: left;
}

.css-171jy4 {
  width: 95px;
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
  height: 140px;
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
  border-radius: 4px;
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
  width: 160px;
  height: 52px;
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

[type="button"],
[type="reset"],
[type="submit"],
button {
  -webkit-appearance: button;
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

button {
  border-radius: 0;
}

button,
input[type="button"],
input[type="reset"],
input[type="submit"] {
  -webkit-appearance: button;
  cursor: pointer;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-f4f4h7:disabled {
  background-color: rgb(221, 221, 221);
}

button[disabled],
input[disabled] {
  cursor: default;
}

.css-f4f4h7 {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 100%;
  height: 52px;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  border: 0px none;
  background-color: rgb(221, 221, 221);
}

.css-17xxk8 textarea {
  font-size: 15px;
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
  resize: none;
  border: none;
}

textarea {
  overflow: auto;
}

.css-17xxk8 .placeholder {
  overflow-y: auto;
  z-index: 1;
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

.enabled-button {
  background-color: #5f0080 !important;
  color: #fff;
  border: none;
  cursor: pointer;
}

.css-1xbd2py p {
  padding-top: 12px;
  padding-left: 12px;
}

/* .tbl_type1 tr:first-child td[data-v-cc43e902],
.tbl_type1 td[data-v-cc43e902] {
  text-align: left;
} */
</style>

