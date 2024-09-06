<template>
<div class="css-1bb6q2p etkckst2">
        <div class="css-19zrxsg etkckst1">로그인</div>
        <div class="css-1axolzg etkckst0">
            <div class="css-1izr46f e1fu5st01">
                <button type="button"
                :class="{'active': userType === 'user', 'inactive': userType !== 'user'}"
                 class="e1fu5st00" @click="setUserType('user')">일반 회원</button>
                <button type="button"
                :class="{'active': userType === 'company', 'inactive': userType !== 'company'}"
                class="inactive e1fu5st00" @click="setUserType('company')">업체 회원</button>
            </div>
            <form class="formbox">
                <div class="css-46b038 e18ap6t76 formbox">
                    <div class="css-1accgqb e1uzxhvi6">
                        <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" name="id"
                                placeholder="아이디를 입력해주세요" type="text" class="css-u52dqk e1uzxhvi2" value=""></div>
                    </div>
                    <div class="css-1accgqb e1uzxhvi6">
                        <div class="css-176lya2 e1uzxhvi3">
                            <input data-testid="input-box" name="password" placeholder="비밀번호를 입력해주세요" type="password"
                                autocomplete="off" class="css-u52dqk e1uzxhvi2" value="">
                        </div>
                    </div>
                </div>
                <div class="css-1vjdduq e18ap6t75 formbox">
                    <a class="css-i4t6me e18ap6t74"><router-link to="/auth/id/find">아이디 찾기</router-link></a>
                    <span class="css-1cgn39v e18ap6t73"></span>
                    <a class="css-i4t6me e18ap6t74"><router-link to="/auth/pwd/find">비밀번호 찾기</router-link></a>
                </div>
                <div class="css-s4i9n2 e18ap6t71 formbox">
                    <button class="css-qaxuc4 e4nu7ef3" type="submit" height="54" radius="3">
                        <span class="css-nytqmg e4nu7ef1">로그인</span>
                    </button>
                    
                    <button class="css-hxorrg e4nu7ef3" id="user" @click="routeToSignup('user')" type="button" height="54" radius="3" style="margin-top: 10px;">
                        <span class="css-nytqmg e4nu7ef1" >일반회원가입</span>
                    </button>
                    <button class="css-hxorrg e4nu7ef3" id="company" @click="routeToSignup('company')" type="button" height="54" radius="3">
                        <span class="css-nytqmg e4nu7ef1" >업체회원가입</span>
                    </button>
                </div>
            </form>
        </div>
    </div>

</template>

<script>

export default {
  name: 'LoginComponent',
  data(){
    return {userType:'user'}
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
      this.userType = type;
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
</style>
