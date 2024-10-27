<template>
<div class="css-1bb6q2p etkckst2">
    <div class="test-account-container">
        <p class="test-account-title"><strong>테스트 계정 안내</strong></p>
        <div class="test-account-content">
            <p><strong>일반 회원 1 :</strong> user01@test.com / Qwer1234!</p>
            <p><strong>일반 회원 2 :</strong> user02@test.com / Qwer1234!</p>
            <p><strong>업체 회원 :</strong> company01@test.com / Qwer1234!</p>
        </div>
    </div>
        <div class="css-19zrxsg etkckst1">로그인</div>
        <div class="css-1axolzg etkckst0">
            <div class="css-1izr46f e1fu5st01">
                <button type="button"
                :class="{'active': type === 'user', 'inactive': type !== 'user'}"
                class="e1fu5st00" @click="setUserType('user')">일반 회원</button>
                <button type="button"
                :class="{'active': type === 'company', 'inactive': type !== 'company'}"
                class="inactive e1fu5st00" @click="setUserType('company')">업체 회원</button>
            </div>
                <div class="css-46b038 e18ap6t76 formbox">
                    <form class="formbox">
                        <div class="css-1accgqb e1uzxhvi6">
                            <div class="css-176lya2 e1uzxhvi3"><input v-model="loginRequest.email" data-testid="input-box" name="email"
                                    placeholder="아이디를 입력해주세요" type="text" class="css-u52dqk e1uzxhvi2" value="" @keydown.enter="login" maxlength="30"></div>
                        </div>
                        <div class="css-1accgqb e1uzxhvi6">
                            <div class="css-176lya2 e1uzxhvi3">
                                <input v-model="loginRequest.password" data-testid="input-box" name="password" placeholder="비밀번호를 입력해주세요" type="password"
                                    autocomplete="off" class="css-u52dqk e1uzxhvi2" value="" @keydown.enter="login" maxlength="30">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="css-1vjdduq e18ap6t75 formbox">
                    <router-link to="/auth/id/find" class="css-i4t6me e18ap6t74">아이디 찾기</router-link>
                    <span class="css-1cgn39v e18ap6t73"></span>
                    <router-link to="/auth/pwd/find" class="css-i4t6me e18ap6t74">비밀번호 찾기</router-link>
                </div>
                <div class="css-s4i9n2 e18ap6t71 formbox">
                    <div class="sns-login" v-show="type === 'user'">
                    <ul class="sns-login-list">
                        <li>
                                <a @click="socialLogin('kakao')"><i class="ico-sns-loin-kakao kakao"></i><span class="blind">카카오 로그인</span></a>
                            </li>
                        <li>
                            <a @click="socialLogin('naver')"><i class="ico-sns-loin-naver naver"></i><span class="blind">네이버 로그인</span></a>
                            </li>
                        <li>
                            <a @click="socialLogin('google')"><i class="ico-sns-loin-google google"></i><span class="blind">구글 로그인</span></a>
                            </li>
                        
                        </ul>
                </div>
                    <button class="css-qaxuc4 e4nu7ef3" type="button" height="54" radius="3" @click="login">
                        <span class="css-nytqmg e4nu7ef1" >로그인</span>
                    </button>
                    
                    <button class="css-hxorrg e4nu7ef3" id="user" @click="routeToSignup('user')" type="button" height="54" radius="3" style="margin-top: 10px;">
                        <span class="css-nytqmg e4nu7ef1" >일반회원가입</span>
                    </button>
                    <button class="css-hxorrg e4nu7ef3" id="company" @click="routeToSignup('company')" type="button" height="54" radius="3">
                        <span class="css-nytqmg e4nu7ef1" >업체회원가입</span>
                    </button>
                </div>
        </div>
    </div>

</template>

<script>
import { useUserStore } from '@/stores/useUserStore';
import { mapStores } from 'pinia';

export default {
  name: 'LoginComponent',
  data(){
    return {type: "user", loginRequest:{
        email: "",
        password: ""
        }
    }
  },
  computed: {
        ...mapStores(useUserStore)
    },
  mounted() {
    if(this.userStore.isLogined){
        alert("잘못된 접근입니다.");
        this.$router.push("/");
    }
    window.scrollTo({
            top: 0,
            left: 0,
            behavior: 'instant'
        });
  },
  methods:{
    routeToSignup(type){
        if(type === 'user'){
          this.$router.push('/auth/user/signup');
        }else{
          this.$router.push('/auth/company/signup');
        }
    },
    setUserType(type){
      this.type = type;
    },
    async login(){
        if(this.validateForm()){
            const isSuccess = await this.userStore.login(this.type, this.loginRequest);
            if(isSuccess){
                const redirect = this.$route.query.redirect || '/';
                this.$router.push(redirect); // 로그인 후 리다이렉트
            }else{
                alert("로그인에 실패했습니다. 아이디, 비밀번호 또는 이메일 인증상태를 확인해주세요");
            }
        }
        
    },
    validateForm(){
        const idRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if(this.loginRequest.email.length === 0){
            alert("아이디를 입력해주세요");
            return false;
        }
        if(this.loginRequest.password.length === 0){
            alert("비밀번호를 입력해주세요");
            return false;
        }
        if(!idRegex.test(this.loginRequest.email)){
            alert("아이디 형식이 올바르지 않습니다.")
            return false;
        }
        if (this.loginRequest.email.length > 30 || this.loginRequest.password.length > 30){
            alert("아이디또는 비밀번호는 30자를 초과할 수 없습니다.")
            return false;
        }

        return true;
    },
    socialLogin(loginType){
        const redirectPath = this.$route.query.redirect; // ?redirect=경로 형태
        if (redirectPath) {
            const value = redirectPath; // "경로"만 추출
            this.userStore.socialRedirect = value; // userStore에 저장
        }
        const response = this.userStore.socialLogin(loginType);
        if(response === "fail"){
            alert("로그인에 실패했습니다.");
        }else {
            this.$router.push(response);
        }
        
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{
    font-family: "Noto Sans KR", "malgun gothic", "AppleGothic", "dotum", "sans-serif";
}

.css-1bb6q2p {
    min-width: 1050px;
    margin-top: 90px;
    margin-bottom: 60px;
    background-color: rgb(255, 255, 255);
}
*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

*, :after, :before, legend, td, th {
    padding: 0;
}
div {
    display: block;
    unicode-bidi: isolate;
}
body, button, input, select, textarea {
    font-size: 14px;
    color: #333;
}

.css-19zrxsg {
    font-weight: 700;
    font-size: 20px;
    line-height: 20px;
    text-align: center;
    margin-bottom: 20px;
}
body, button, input, select, textarea {
    font-size: 14px;
    color: #333;
}

.css-1axolzg {
    width: 370px;
    margin: 0px auto;
    letter-spacing: -0.6px;

}

css-46b038 {
    margin-top: 30px;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

.css-1accgqb:first-of-type {
    margin-bottom: 5px;
}

.css-1accgqb {
    padding-bottom: 12px;
}

.css-176lya2 {
    position: relative;
    height: 48px;
}

.css-1accgqb input {
    height: 54px;
    font-size: 14px;
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

.css-1accgqb {
    padding-bottom: 12px;
}

css-176lya2 {
    position: relative;
    height: 48px;
}

.css-1vjdduq {
    display: flex;
    -webkit-box-pack: end;
    justify-content: flex-end;
    margin-top: 10px;
    font-size: 13px;
}

.css-i4t6me {
    color: rgb(51, 51, 51);
    cursor: pointer;
}
a {
    background-color: transparent;
    text-decoration: none;
    color: inherit;
}

.css-1cgn39v {
    width: 1px;
    height: 10px;
    margin: 3px 6px 0px;
    background-color: rgb(51, 51, 51);
}

.css-i4t6me {
    color: rgb(51, 51, 51);
    cursor: pointer;
}

.css-s4i9n2 {
    margin-top: 28px;
}

.css-qaxuc4 {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 54px;
    border-radius: 3px;
    color: rgb(255, 255, 255);
    background-color: rgb(95, 0, 128);
    border: 0px none;
}

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

html, button, input, select, textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

.css-s4i9n2 button:last-of-type {
    margin-top: 10px;
}

.css-hxorrg {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 54px;
    border-radius: 3px;
    color: rgb(95, 0, 128);
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(95, 0, 128);
}

button{
    cursor: pointer;
}


/* 상단 회원선택 탭 css */
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

.css-u3y03b {
    max-width: 400px;
    padding: 0 10px 6px 10px;
    margin: auto;
    position: relative;
    background-color: white;
}

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
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

.formbox{
    margin-top: 1em;
    padding-left: 7px;
    padding-right: 7px;
}

.sns-login-list {
    text-align: center;
}
ol, ul {
    list-style: none;
}
.sns-login-list > li {
    display: inline-block;
    margin: 0 9px;
}

li {
    display: list-item;
    text-align: -webkit-match-parent;
    unicode-bidi: isolate;
    cursor: pointer;
}
a {
    background: transparent;
    text-decoration: none;
    color: inherit;
}

.sns-login-list{
    margin-bottom: 20px;
    margin-top: 10px;
}

.sns-login-list .ico-sns-loin .naver {
    background-position: left top;
}

.sns-login-list .ico-sns-loin-kakao {
    display: inline-block;
    width: 46px;
    height: 46px;
    overflow: hidden;
    background: url(./../../assets/kakao_login.png) no-repeat;
    background-size: auto 46px;
}

.sns-login-list .ico-sns-loin-google {
    display: inline-block;
    width: 46px;
    height: 46px;
    overflow: hidden;
    background: url(./../../assets/google_login.png) no-repeat;
    background-size: auto 46px;
}

.sns-login-list .ico-sns-loin-naver {
    display: inline-block;
    width: 46px;
    height: 46px;
    overflow: hidden;
    background: url(./../../assets/naver_login.png) no-repeat;
    background-size: auto 46px;
}

.blind {
    position: absolute;
    width: 1px;
    height: 1px;
    margin: -1px;
    padding: 0;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
}

.test-account-container {
    background-color: #f0f4fa;
    border: 1px solid #d0e4f7;
    border-radius: 10px;
    padding: 15px;
    margin-bottom: 20px;
    text-align: center;
    olor: #2a3f54;
}
.test-account-title {
    font-size: 16px;
    color: #0c3c78;
    margin-bottom: 10px;
}
.test-account-content p {
    margin: 5px 0;
    font-size: 14px;
    color: #3b5267;
}

</style>
