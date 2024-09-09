<template>
<div class="css-140915z e1lv8394">
        <div class="css-6mht10 e1lv8393">
            <div class="css-1ymrnwr e1lv8392">고객님의 Dealivery 계정을 찾았습니다.<div class="css-1jlgal3 e1lv8391">아이디 확인 후 로그인해 주세요.
                </div>
            </div>
            <ul class="css-14o5m2r ewnnxio4">
                <li class="css-1qp8f6i ewnnxio3"><img class="css-ate73y e1raycba0"
                        src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNDAiIGhlaWdodD0iNDAiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgICA8ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiPgogICAgICAgIDxjaXJjbGUgZmlsbD0iI0Y1RjVGNSIgY3g9IjIwIiBjeT0iMjAiIHI9IjIwIi8+CiAgICAgICAgPHBhdGggZD0iTTIwIDE5YzQuNjUzIDAgOC41IDMuNDc2IDguNSA3LjcyIDAgLjQzLS4zNDYuNzgtLjc3My43OEgxMi4yNzNhLjc3Ni43NzYgMCAwIDEtLjc3My0uNzhjMC00LjI4NSAzLjgyNC03LjcyIDguNS03Ljcyem0wLTguNWEzLjQgMy40IDAgMSAxIDAgNi44IDMuNCAzLjQgMCAwIDEgMC02Ljh6IiBzdHJva2U9IiNDQ0MiIGZpbGw9IiNDQ0MiLz4KICAgIDwvZz4KPC9zdmc+Cg=="
                        alt="정보보기">
                    <div class="css-yzpdz ewnnxio2">
                        <div class="css-1kkr6j1 ewnnxio1" id="foundId">simkids</div>
                        <div class="css-1081952 ewnnxio0" id="foundRegisteredAt">가입일 2024.09.03.</div>
                    </div>
                </li>
            </ul>
            <div class="css-gyfngb e1lv8390"><button @click="routeToPwdFind" class="css-ufulao e4nu7ef3" type="button"><span
                        class="css-nytqmg e4nu7ef1">비밀번호 찾기</span></button><button @click="routeToLogin" class="css-1qirdbn e4nu7ef3"
                    type="button"><span class="css-nytqmg e4nu7ef1">로그인</span></button></div>
        </div>
    </div>
</template>

<script>

export default {
  name: 'FindIdSuccessComponent',
  methods:{
    routeToLogin(){
      this.$router.push('/auth/login');
    },
    routeToPwdFind(){
      this.$router.push('/auth/pwd/find');
    }
  }
}
</script>
<style scoped>
*{
    font-family: "Noto Sans KR", "malgun gothic", "AppleGothic", "dotum", "sans-serif";
}
button{
  cursor: pointer;
}

.css-140915z {
    min-width: 1050px;
    padding: 50px 0px;
    background-color: white;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

div {
    display: block;
    unicode-bidi: isolate;
}

body, button, input, select, textarea {
    font-size: 14px;
    color: #333;
}

html {
    line-height: 1.15;
    -webkit-text-size-adjust: 100%;
}

.css-6mht10 {
    max-width: 400px;
    padding: 0px 30px 30px;
    margin: auto;
    position: relative;
    background-color: white;
}

.css-1ymrnwr {
    padding-top: 30px;
    font-weight: 500;
    font-size: 17px;
    line-height: 23px;
}

css-1jlgal3 {
    display: block;
    padding-top: 4px;
    font-weight: 400;
    font-size: 13px;
    color: rgb(153, 153, 153);
    line-height: 18px;
}
.css-14o5m2r {
    padding: 12px 0px;
}

ol, ul {
    list-style-type: none;
}

*, :after, :before, legend, td, th {
    padding: 0;
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

.css-1qp8f6i {
    display: flex;
}

li {
    display: list-item;
    text-align: -webkit-match-parent;
    unicode-bidi: isolate;
}

.css-ate73y {
    width: 40px;
    height: 40px;
    display: block;
    margin: 17px 12px 16px 0px;
}

img {
    border-style: none;
}

img, legend {
    border: 0;
    vertical-align: top;
}

canvas, img, video {
    max-width: 100%;
}

img {
    overflow-clip-margin: content-box;
    overflow: clip;
}

.css-1qp8f6i:first-of-type > div {
    border: none;
}

.css-yzpdz {
    -webkit-box-flex: 1;
    flex-grow: 1;
    border-top: 1px solid rgb(238, 238, 238);
}

.css-1kkr6j1 {
    padding-top: 17px;
    font-weight: 500;
    font-size: 16px;
    line-height: 21px;
}

.css-1081952 {
    display: block;
    padding-top: 4px;
    font-size: 12px;
    color: rgb(153, 153, 153);
}
.css-gyfngb {
    margin-top: 60px;
}

.css-gyfngb > button:first-of-type {
    margin-bottom: 10px;
}

.css-ufulao {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 3px;
    color: rgb(95, 0, 128);
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(95, 0, 128);
}

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

button, select {
    text-transform: none;
}

button, input, optgroup, select, textarea {
    font-family: inherit;
    font-size: 100%;
    line-height: 1.15;
    margin: 0;
}

button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

.css-1qirdbn {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 3px;
    color: rgb(255, 255, 255);
    background-color: rgb(95, 0, 128);
    border: 0px none;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

.css-1jlgal3 {
    display: block;
    padding-top: 4px;
    font-weight: 400;
    font-size: 13px;
    color: rgb(153, 153, 153);
    line-height: 18px;
}
</style>
