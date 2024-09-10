<template>
    <div>
        <div class="css-t79vuj e15sbxqa2">
            <div v-show="!userStore.isLogined" class="css-1xfyvd1 eo7pjfk4">
                <router-link to="/auth/login" class="css-oyffzd eo7pjfk2 top-menu-link">로그인</router-link>
                <div class="css-1qgm48u eo7pjfk0">
                </div>
                <RouterLink to="/auth/user/signup" class="css-xygizb eo7pjfk2 top-menu-link">일반회원가입</RouterLink>
                <div class="css-1qgm48u eo7pjfk0">
                </div>
                <RouterLink to="/auth/company/signup" class="css-xygizb eo7pjfk2 top-menu-link">업체회원가입</RouterLink>
            </div>

            <div v-show="userStore.isLogined" class="css-1xfyvd1 eo7pjfk4">
                <span class="css-oyffzd eo7pjfk2 top-menu-link" @click="logout">로그아웃</span>
                <div class="css-1qgm48u eo7pjfk0">
                </div>
                <RouterLink to="/mypage" class="css-xygizb eo7pjfk2 top-menu-link">마이페이지</RouterLink>
                
                
            </div>


            <div class="css-r7wmjj e15sbxqa3">
                <div class="css-boc80u ekdqe1a1"><img
                        src="https://github.com/user-attachments/assets/97e43864-23d5-4f0d-844d-8a8e20aeade2"
                        alt="딜리버리 로고" class="css-17mnrrx e1s3pt0j0 logo" @click="routeTo('/')"><button
                        class="active css-mxd3pm ekdqe1a0"><router-link
                            to="/">Dealivery</router-link></button><button
                        class=" css-mxd3pm ekdqe1a0">Company</button></div>
                <div class="css-pqw0uk e1493ofl4">
                    <div class="css-w444a2 e1493ofl1"><input id="gnb_search" placeholder="검색어를 입력해주세요" required=""
                            class="css-11ntk83 e1493ofl3" value=""><button id="submit" aria-label="submit"
                            class="css-ywxmlw e1493ofl0"></button></div>
                </div>
                <div class="css-pqw0uk e15sbxqa1">
                    <div class="css-c4pbxv e15sbxqa0">
                        <button class="css-231fw3 etxxzpc0" aria-label="찜하기" type="button"></button>
                        <div class="css-ff2aah e14oy6dx2"><button class="css-g25h97 e14oy6dx1"></button></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="header" class="css-17tqugj e17w4cfr1">
            <div class="css-mlddcv e17w4cfr6">
                <div class="css-0 e17w4cfr3">
                    <div class="css-axtlq9 eqn756m2"><span class="css-1k5gn9s eqn756m1"></span><span
                            class="css-t75x7c eqn756m0">카테고리</span></div>
                </div>
                <ul class="css-1887xqd e17w4cfr5">
                    <li class="css-59mmhh e17w4cfr4"><span class="css-1xyu7j9 e17w4cfr2">식품</span></li>
                    <li class="css-59mmhh e17w4cfr4"><span class="css-1xyu7j9 e17w4cfr2">의류</span></li>
                    <li class="css-59mmhh e17w4cfr4"><span class="css-1xyu7j9 e17w4cfr2">뷰티</span></li>
                    <li class="css-59mmhh e17w4cfr4"><span class="css-1xyu7j9 e17w4cfr2">라이프</span></li>
                </ul>
                <div class="css-s5xdrg e17w4cfr0"></div>
            </div>
        </div>
        <div hidden="" class="css-1px7x3s e1py5jsz0"></div>
    </div>
</template>

<script>
import { useUserStore } from '@/stores/useUserStore';
import { mapStores } from 'pinia';
export default {
    name: 'HeaderComponent',
    computed: {
        ...mapStores(useUserStore)
    },
    methods:{
        routeTo(path){
            this.$router.push(path);
        },
        logout(){
            this.userStore.isLogined = false;
            this.userStore.roles = [];
            this.routeTo('/');
        }
    }
}
</script>


<style scoped>
*,
:after,
:before {
    box-sizing: border-box;
    margin: 0;
}

a {
  text-decoration: none; /* 밑줄 제거 (필요 시) */
}


*,
:after,
:before,
legend,
td,
th {
    padding: 0;
}

img {
    border-style: none;
}

.logo{
    cursor: pointer;
}

img,
legend {
    border: 0;
    vertical-align: top;
}

canvas,
img,
video {
    max-width: 9%;
}

html,
button,
input,
select,
textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

button,
input {
    overflow: visible;
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

.css-mxd3pm.active,
.css-mxd3pm:hover {
    font-weight: 500;
    color: rgb(95, 0, 128);
}

.css-mxd3pm:not(:last-of-type)::after {
    content: "";
    position: absolute;
    width: 1px;
    height: 14px;
    margin-top: 5px;
    margin-left: 11px;
    background-color: rgb(226, 226, 226);
}

.css-mxd3pm {
    flex-shrink: 0;
    margin-left: 20px;
    font-size: 18px;
    font-weight: normal;
    line-height: 1.33;
    letter-spacing: normal;
    color: rgb(181, 181, 181);
    cursor: pointer;
}


.css-t79vuj {
    /* 헤더 전체에 적용되는 css */
    position: relative;
    width: 1050px;
    height: 100px;
    margin: 0px auto;
    letter-spacing: -0.3px;
}

.css-1xfyvd1 {
    /* 헤더 우측 상단에 회원가입|로그인|고객센터 css */
    position: absolute;
    right: 0px;
    top: 12px;
    z-index: 320;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    font-size: 12px;
}

.css-1qgm48u {
    /* 회원가입|로그인|고객센터 이 부분에 "|" <- 이 css */
    width: 1px;
    height: 13px;
    margin: 0px 12px;
    background-color: rgb(217, 217, 217);
}

.css-xygizb {
    /* 헤더 우측 상단에 회원가입 글씨 css */
    display: block;
    color: rgb(95, 0, 128);
    cursor: pointer;
}


.css-oyffzd {
    /* 헤더 우측 상단에 로그인 글씨 css */
    display: block;
    cursor: pointer;
}

.css-1qolcqm {
    /* 헤더 우측 상단에 고객센터 글씨 css */
    line-height: 35px;
    position: relative;
}

.css-oyffzd {
    /* 헤더 우측 상단에 고객센터 글씨 css */
    display: block;
    cursor: pointer;
}

.css-1lrerrk {
    /* 헤더 우측 상단에 고객센터 아이콘*/
    width: 8px;
    height: 5px;
    background-image: url(https://res.kurly.com/pc/ico/1908/ico_down_16x10.png);
    background-size: cover;
    background-position: center center;
    display: inline-block;
    margin-left: 5px;
    margin-bottom: 1px;
}

.css-r7wmjj {
    height: 100px;
    padding-top: 36px;
}

.css-boc80u {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    height: 63px;
}

.css-17mnrrx {
    flex: 0 0 82px;
}

.css-mxd3pm.active,
.css-mxd3pm:hover {
    font-weight: 500;
    color: rgb(95, 0, 128);
}

.css-mxd3pm {
    flex-shrink: 0;
    margin-left: 20px;
    font-size: 18px;
    font-weight: normal;
    line-height: 1.33;
    letter-spacing: normal;
    color: rgb(181, 181, 181);
    cursor: pointer;
}

.css-mxd3pm:last-of-type {
    margin-left: 22px;
}

.css-pqw0uk {
    position: relative;
    width: 1050px;
    margin: 0px auto;
}

.css-w444a2 {
    /* 검색창 */
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: justify;
    justify-content: space-between;
    position: absolute;
    left: 325px;
    top: -55px;
    width: 400px;
    height: 48px;
    padding-left: 14px;
    border: 1px solid rgb(95, 0, 128);
    border-radius: 6px;
    background-color: rgb(255, 255, 255);
    box-shadow: rgb(247, 247, 247) 0px 0px 0px 1px inset;
}

.css-11ntk83 {
    /* 검색창 안에 input 부분 */
    width: 300px;
    background-color: inherit;
    border: none;
    outline: none;
    font-size: 16px;
    letter-spacing: -0.33px;
}

.css-ywxmlw {
    /* 검색 돋보기 아이콘 */
    position: relative;
    width: 30px;
    height: 30px;
    margin: 10px;
    bottom: 3px;
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzYiIGhlaWdodD0iMzYiIHZpZXdCb3g9IjAgMCAzNiAzNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgZD0iTTAgMGgzNnYzNkgweiIvPgogICAgICAgIDxnIHN0cm9rZT0iIzVGMDA4MCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2Utd2lkdGg9IjEuNyI+CiAgICAgICAgICAgIDxwYXRoIGQ9Im0yNi4wODEgMjYuMDgxLTQuMTItNC4xMk0xNi40NjcgMjMuMzM0YTYuODY3IDYuODY3IDAgMSAwIDAtMTMuNzM0IDYuODY3IDYuODY3IDAgMCAwIDAgMTMuNzM0eiIvPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+Cg==) 0px 0px no-repeat;
}

.css-pqw0uk {
    /* 헤더의 밑부분 */
    position: relative;
    width: 1050px;
    margin: 0px auto;
}

.css-c4pbxv {
    /* 우측에 하트, 카트 아이콘 감싸는 거 */
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    position: absolute;
    right: -6px;
    top: -49px;
}

.css-231fw3 {
    /* 하트 아이콘 */
    width: 36px;
    height: 36px;
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzYiIGhlaWdodD0iMzYiIHZpZXdCb3g9IjAgMCAzNiAzNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yOC45MjcgOC44OTNhNi40NiA2LjQ2IDAgMCAwLTkuMTM5IDBsLTEuODI5IDEuODI4LTEuODI3LTEuODI4YTYuNDYyIDYuNDYyIDAgMSAwLTkuMTQgOS4xMzhMOC44MiAxOS44Nmw4LjQzMiA4LjQzNGExIDEgMCAwIDAgMS40MTQgMGw4LjQzMy04LjQzNGgwbDEuODI4LTEuODI4YTYuNDYgNi40NiAwIDAgMCAwLTkuMTM4eiIgc3Ryb2tlPSIjMzMzIiBzdHJva2Utd2lkdGg9IjEuNyIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIiBzdHJva2UtbGluZWNhcD0icm91bmQiLz4KPC9zdmc+Cg==) 50% 50% no-repeat;
}

.css-ff2aah {
    /* 하트랑 카트 아이콘 사이에 공백 */
    position: relative;
    margin-left: 20px;
}

.css-g25h97 {
    /* 카트 아이콘 */
    display: block;
    width: 36px;
    height: 36px;
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzYiIGhlaWdodD0iMzYiIHZpZXdCb3g9IjAgMCAzNiAzNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTM2IDM2SDBWMGgzNnoiLz4KICAgICAgICA8ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSg1LjE2NCA2LjU0NykiIHN0cm9rZT0iIzMzMyIgc3Ryb2tlLWxpbmVjYXA9InNxdWFyZSIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLXdpZHRoPSIxLjciPgogICAgICAgICAgICA8cGF0aCBkPSJtMjUuNjggMy42Ni0yLjcyIDExLjU3SDcuMzdMNC42NiAzLjY2eiIvPgogICAgICAgICAgICA8Y2lyY2xlIGN4PSIyMC41MiIgY3k9IjIwLjc4IiByPSIyLjE0Ii8+CiAgICAgICAgICAgIDxjaXJjbGUgY3g9IjkuODEiIGN5PSIyMC43OCIgcj0iMi4xNCIvPgogICAgICAgICAgICA8cGF0aCBkPSJNMCAwaDMuOGwxLjc2IDcuNSIvPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+Cg==);
    cursor: pointer;
}

.css-17tqugj {
    /* 헤더 하단 */
    min-width: 1050px;
    letter-spacing: -0.3px;
    background-color: rgb(255, 255, 255);
    position: relative;
    width: 100%;
    z-index: 300;
}

.css-mlddcv {
    position: relative;
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    width: 1050px;
    height: 56px;
    margin: 0px auto;
}

.css-axtlq9 {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    height: 100%;
    cursor: pointer;
}

.css-1k5gn9s {
    /* 카테고리 아이콘 */
    width: 16px;
    height: 14px;
    margin-right: 14px;
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNiAxNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0wIDBoMTZ2MS43SDBWMHptMCA2LjE1aDE2djEuN0gwdi0xLjd6bTAgNi4xNWgxNlYxNEgwdi0xLjd6IiBmaWxsPSIjMzMzIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiLz4KPC9zdmc+Cg==) 0px 0px / 16px 14px no-repeat;
}

.css-t75x7c {
    /* 카테고리 글자 */
    font-size: 16px;
    font-weight: 500;
    line-height: 20px;
    letter-spacing: -0.3px;
    color: rgb(51, 51, 51);
}

.css-1887xqd {
    display: flex;
    margin-left: 30px;
}

.css-59mmhh {
    display: flex;
    -webkit-box-pack: center;
    justify-content: center;
    width: 150px;
    height: 55px;
    padding-top: 18px;
    line-height: 20px;
    text-align: center;
}

.css-1xyu7j9 {
    height: fit-content;
    font-size: 16px;
    font-weight: 500;
    color: rgb(51, 51, 51);
    cursor: pointer;
}

button {
    overflow: visible;
    background-color: transparent;
    border: none;
    cursor: pointer;
}
</style>