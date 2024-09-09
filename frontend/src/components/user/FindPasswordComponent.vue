<template>
  <div class="css-140915z e13dlrpy2">
    <div class="css-7500ra e13dlrpy0">비밀번호 찾기</div>
    <div class="css-u3y03b e13dlrpy1">
      <div class="css-1izr46f e1fu5st01">
        <button type="button" :class="{ 'active': userType === 'user', 'inactive': userType !== 'user' }"
          class="e1fu5st00" @click="setUserType('user')">일반 회원</button>
        <button type="button" :class="{ 'active': userType === 'company', 'inactive': userType !== 'company' }"
          class="inactive e1fu5st00" @click="setUserType('company')">업체 회원</button>
      </div>
      <form class="css-s3iz85 e1mnvs534">
        <div class="css-1blp8ou e1mnvs533">
          <div class="css-1yjqrpx e1uzxhvi6">
            <label for="name" class="css-c3g9of e1uzxhvi4">이름</label>
            <div class="css-176lya2 e1uzxhvi3">
              <input data-testid="input-box" id="name" name="name" placeholder="이름을 입력해 주세요" type="text"
                class="css-u52dqk e1uzxhvi2" value="">
            </div>
          </div>

        </div>
        <div class="css-1blp8ou e1mnvs533">
          <div class="css-1yjqrpx e1uzxhvi6">
            <label for="phone" class="css-c3g9of e1uzxhvi4">이메일</label>
            <div class="css-176lya2 e1uzxhvi3">
              <input data-testid="input-box" id="email" name="email" placeholder="이메일을 입력해 주세요" type="email" height="46"
                class="css-u52dqk e1uzxhvi2" value="">
            </div>
          </div>
        </div>
        <div class="css-3vxi16 e1mnvs530">
          <button @click="findPwd" class="css-1s9rhb5 e4nu7ef3" radius="4" type="button">
            <span class="css-nytqmg e4nu7ef1">확인</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>

export default {
  name: 'FindPasswordComponent',
  data() {
    return { userType: 'user' }
  },
  methods: {
    setUserType(type) {
      this.userType = type;
    },
    findPwd() {
      this.$router.push('/auth/pwd/find/wait');
    }
  }


}
</script>
<style scoped>
*{
    font-family: "Noto Sans KR", "malgun gothic", "AppleGothic", "dotum", "sans-serif";
}

.css-140915z {
    min-width: 1050px;
    margin-top: 90px;
    margin-bottom: 60px;
    background-color: white;
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

html, button, input, select, textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

html {
    line-height: 1.15;
    -webkit-text-size-adjust: 100%;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

body, button, input, select, textarea {
    font-size: 14px;
    color: #333;
}

div {
    display: block;
    unicode-bidi: isolate;
}

.css-7500ra {
    padding-bottom: 30px;
    font-weight: 700;
    font-size: 20px;
    text-align: center;
}

.css-u3y03b {
    max-width: 400px;
    padding: 0 10px 6px 10px;
    margin: auto;
    position: relative;
    background-color: white;
}

.css-1izr46f {
    background-color: white;
    padding: 0 15px 0 15px;
    margin: 0;
    box-shadow: inset 0 -0.5px 0 0 #ddd;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-flex-wrap: nowrap;
    -webkit-flex-wrap: nowrap;
    -ms-flex-wrap: nowrap;
    flex-wrap: nowrap;
    text-align: center;
}

.inactive {
    display: block;
    -webkit-box-flex: 1;
    -webkit-flex-grow: 1;
    -ms-flex-positive: 1;
    flex-grow: 1;
    position: relative;
    height: 48px;
    font-weight: 400;
    font-size: 16px;
    color: #666;
    line-height: 18px;
}

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

html, button, input, select, textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

button, select {
    text-transform: none;
}

button, input {
    overflow: visible;
}

button {
    border-radius: 0;
}

.active {
    display: block;
    -webkit-box-flex: 1;
    -webkit-flex-grow: 1;
    -ms-flex-positive: 1;
    flex-grow: 1;
    position: relative;
    height: 48px;
    font-weight: 500;
    font-size: 16px;
    color: #5f0080;
    line-height: 18px;
    box-shadow: inset 0px -2px 0px 0px #5f0080;
}


.css-s3iz85 {
    padding: 24px 20px;
}

form {
    display: block;
    margin-top: 0em;
    unicode-bidi: isolate;
}

.css-1yjqrpx {
    padding-bottom: 12px;
}

.css-c3g9of {
    display: inline-block;
    padding: 8px 0 11px;
    font-size: 14px;
    font-weight: 500;
    line-height: 19px;
    color: #333;
}

.css-176lya2 {
    position: relative;
    height: 48px;
}
.css-u52dqk {
    width: 100%;
    height: 46px;
    padding: 0 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid #ddd;
    font-weight: 400;
    font-size: 16px;
    line-height: 1.5;
    color: #333;
    outline: none;
    box-sizing: border-box;
}

html, button, input, select, textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

button, input[type=button], input[type=reset], input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
}

button {
    overflow: visible;
    background-color: transparent;
    border: none;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.css-1blp8ou:not(:first-of-type) {
    margin-top: 8px;
}

.css-1yjqrpx {
    padding-bottom: 12px;
}

.css-c3g9of {
    display: inline-block;
    padding: 8px 0 11px;
    font-size: 14px;
    font-weight: 500;
    line-height: 19px;
    color: #333;
}

.css-176lya2 {
    position: relative;
    height: 48px;
}

.css-u52dqk {
    width: 100%;
    height: 46px;
    padding: 0 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid #ddd;
    font-weight: 400;
    font-size: 16px;
    line-height: 1.5;
    color: #333;
    outline: none;
    box-sizing: border-box;
}

.css-3vxi16 {
    margin-top: 18px;
}

.css-1s9rhb5:disabled {
    background-color: #ddd;
}

button[disabled], input[disabled] {
    cursor: default;
}

.css-1s9rhb5 {
    display: block;
    padding: 0 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 4px;
    color: #fff;
    background-color: #5f0080;
    border: 0 none;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

body, button, input, select, textarea {
    font-size: 14px;
    color: #333;
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

html, button, input, select, textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

label {
    cursor: default;
}

.css-c3g9of {
    display: inline-block;
    padding: 8px 0 11px;
    font-size: 14px;
    font-weight: 500;
    line-height: 19px;
    color: #333;
}

.css-1iilz1t {
    position: relative;
    height: 46px;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

*, :after, :before, legend, td, th {
    padding: 0;
}
</style>
