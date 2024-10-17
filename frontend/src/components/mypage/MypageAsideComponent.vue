<template>
    <div class="css-1l2tkkh eug5r8l2">
        <div class="pc css-3rbu7q epggjnz0">
            <div class="top-info-section">
                <div class="pc css-jagjhu e1w7rsa02">
                    <div class="grade-name">
                        <div v-text="userStore.userDetail.name+'님'" class="user-name"></div>
                    </div>
                </div>
                <div class="css-rurvds e5rtjnl2">
                    <div class="pc css-z53t5q e5rtjnl1">
                        <div class="item-wrap">
                            <button class="menu-item">
                                <div class="name-text">포인트</div>
                                <div class="amount-text">
                                    <div v-text="userStore.userDetail.point" class="number-text"></div>
                                    원
                                </div>
                            </button>
                        </div>
                    </div>
                    <a href="/member/membership" class="pc css-1d9zl68 e5rtjnl0"></a>
                </div>
            </div>
            <div id="menu">
                <div class="pc css-1ozh7ew e1n1zlz21">
                    <div class="css-1tzlowo e1ifbcqu0">자주찾는 메뉴</div>
                    <div class="menu-wrap">
                        <button class="menu-link" @click="setActiveMenu('order')">
                            <img src="@/assets/cart-icon.svg" class="icon" width="28" height="28" />
                            <span class="menu-item" :class="{ active: isActive('order') }">
                                주문 내역
                            </span>
                        </button>
                        <button class="menu-link" @click="setActiveMenu('likes')">
                            <img src="@/assets/heart-icon.svg" class="icon" width="29" height="28" />
                            <span class="menu-item" :class="{ active: isActive('likes') }">
                                찜한 게시글
                            </span>
                        </button>
                    </div>
                </div>
                <div class="menu-info-section">
                    <div class="css-bivr1n eoeriki0">
                        <div class="css-1tzlowo e1ifbcqu0">문의</div>
                        <a class="css-1qe2c6r e1ytq75h2" @click="setActiveMenu('qna')">
                            <span class="menu-item" :class="{ active: isActive('qna') }">My 문의</span>
                        </a>
                    </div>
                    <div class="css-bivr1n eoeriki0">
                        <div class="css-1tzlowo e1ifbcqu0">내 정보관리</div>
                        <a class="css-1qe2c6r e1ytq75h2" @click="setActiveMenu('info')">
                            <span class="menu-item" :class="{ active: isActive('info') }">내 정보</span>
                        </a>
                        <a class="css-1qe2c6r e1ytq75h2" @click="setActiveMenu('update-info')">
                            <span class="menu-item" :class="{ active: isActive('update-info') }">개인정보 수정</span>
                        </a>
                        <a class="css-1qe2c6r e1ytq75h2" @click="setActiveMenu('address')">
                            <span class="menu-item" :class="{ active: isActive('address') }">배송지 관리</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useUserStore } from '@/stores/useUserStore';
import { mapStores } from 'pinia';
export default {
    name: 'MypageAsideComponent',
    data() {
        return {
            activeMenu: '',  // 기본으로 활성화된 메뉴
        };
    },
    computed: {
        ...mapStores(useUserStore)
     },
    methods: {
        setActiveMenu(menu) {
            this.activeMenu = menu;
            this.$emit('menu-click', menu);  // 클릭된 메뉴를 부모 컴포넌트에 전달
        },
        isActive(menu) {
            return this.activeMenu === menu;
        },
        updateActiveMenuBasedOnRoute() {
            const currentRoute = this.$route.path;
            if (currentRoute.includes('order')) {
                this.activeMenu = 'order';
            } else if (currentRoute.includes('likes')) {
                this.activeMenu = 'likes';
            } else if (currentRoute.includes('qna')) {
                this.activeMenu = 'qna';
            } else if (currentRoute.includes('info')) {
                this.activeMenu = 'info';
            } else if (currentRoute.includes('update-info')) {
                this.activeMenu = 'update-info';
            } else if (currentRoute.includes('address')) {
                this.activeMenu = 'address';
            }
        }
    },
    mounted() {
        this.updateActiveMenuBasedOnRoute();  // 페이지 로드 시 현재 경로에 따라 활성화된 메뉴 설정
        this.userStore.getDetail();
    },
    watch: {
        '$route'() {  // 라우트가 변경될 때마다 호출
            this.updateActiveMenuBasedOnRoute();
        }
    }
}
</script>


<style scoped>

.css-72lz6z {
    display: flex;
    align-items: flex-start;
    gap: 20px;
    -webkit-box-pack: center;
    justify-content: center;
    padding: 50px 0px 80px;
    margin: 0px auto;
}

.css-1l2tkkh {
    overflow: hidden;
    width: 300px;
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

.css-jagjhu.pc .user-name {
    font-weight: 500;
    font-size: 20px;
}

.css-jagjhu .user-name {
    overflow: hidden;
    padding-left: 5px;
    font-weight: 600;
    font-size: 20px;
    line-height: 28px;
    letter-spacing: -0.2px;
    white-space: nowrap;
    text-overflow: ellipsis;
}

.css-rurvds {
    margin-top: 12px;
    border: 1px solid rgb(226, 226, 226);
    border-radius: 12px;
}

.css-z53t5q {
    display: flex;
}

.css-z53t5q .item-wrap {
    position: relative;
    min-width: 50%;
}

.css-z53t5q .item-wrap .menu-item {
    display: block;
    width: 100%;
    min-height: 81px;
    padding: 20px 12px 16px 16px;
    text-align: left;
}

.css-z53t5q .item-wrap .name-text {
    font-weight: 400;
    font-size: 12px;
    line-height: 16px;
    color: rgb(102, 102, 102);
}

.css-z53t5q.pc .amount-text {
    font-weight: 500;
}

.css-z53t5q .item-wrap .amount-text {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    padding-top: 6px;
    font-weight: 600;
    line-height: 20px;
    letter-spacing: -0.4px;
}

.css-z53t5q.pc .number-text {
    font-size: 24px;
}

.css-z53t5q .item-wrap .number-text {
    padding-right: 2px;
    font-weight: 700;
    font-size: 20px;
    line-height: 24px;
    letter-spacing: -0.8px;
}

.css-1d9zl68.pc {
    font-weight: 500;
}

.css-1d9zl68 {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    align-items: center;
    width: 100%;
    padding: 12px 12px 12px 14px;
    border-radius: 0px 0px 12px 12px;
    font-weight: 600;
    font-size: 13px;
    line-height: 18px;
    word-break: break-all;
    background-color: rgb(234, 247, 250);
}

.css-1ozh7ew.pc {
    margin: 0px;
    padding: 20px 25px;
}

.css-3rbu7q>div {
    background-color: rgb(255, 255, 255);
}

.css-1tzlowo {
    width: 100%;
    padding: 12px 0px 6px;
    font-weight: 400;
    line-height: 20px;
    color: rgb(181, 181, 181);
}

.css-1ozh7ew.pc .menu-wrap {
    flex-direction: column;
    align-items: flex-start;
}

.css-1ozh7ew .menu-wrap {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
}

.css-1ozh7ew.pc .menu-link {
    flex-direction: row;
    min-width: 100%;
    font-weight: 500;
    height: 54px;
    padding: 16px 0px;
    font-size: 16px;
}

.css-1ozh7ew .menu-link {
    display: flex;
    flex-direction: column;
    -webkit-box-align: center;
    align-items: center;
    position: relative;
    min-width: 56px;
    font-weight: 600;
    line-height: 18px;
    cursor: pointer;
}

.css-1ozh7ew.pc .menu-link>img {
    margin: 0px 8px 0px 0px;
}

.css-1ozh7ew .menu-link>img {
    margin: 0px 0px 8px;
}

.css-10aedqr {
    display: flex;
    gap: 2px;
}

.css-3rbu7q.pc .menu-info-section {
    padding: 0px 25px;
}

.css-3rbu7q>div {
    background-color: rgb(255, 255, 255);
}

.css-bivr1n {
    display: flex;
    flex-wrap: wrap;
    padding: 12px 0px 16px;
    border-top: 1px solid rgb(238, 238, 238);
}

.css-1qe2c6r {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    min-width: 100%;
    padding: 12px 0px;
    font-weight: 500;
    cursor: pointer;
}

.css-1mq58uz {
    font-size: 16px;
    line-height: 22px;
    color: rgb(51, 51, 51);
    white-space: nowrap;
}

/*-------------  마이페이지 주문 내역 부분 ------------------*/
.css-heioij {
    overflow: hidden;
    width: 700px;
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

.css-10ekv2i {
    background-color: var(--ldmw176w);
}

.css-ole4zz {
    background-color: var(--ldmw176x);
    border-bottom-right-radius: var(--ldmw171g);
    border-bottom-left-radius: var(--ldmw171g);
    height: 20px;
}

.css-glfjkp {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    flex-direction: column;
    -webkit-box-pack: center;
    justify-content: center;
    width: 100%;
    height: 700px;
    background-color: var(--ldmw176x);
    margin-top: 16px;
    border-radius: var(--ldmw171g);
}

.css-1f8oun9 {
    margin-top: var(--ldmw17w);
    color: var(--ldmw176l);
}

.ldmw177q {
    line-height: 26px;
}

.ldmw177j {
    font-weight: 600;
}

.ldmw177c {
    font-size: 18px;
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
    max-width: 13%;
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
    box-shadow: rgba(0, 0, 0, 0.07) 0px 3px 4px 0px;
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

.icon {
    transition: stroke 0.3s ease;
    /* 테두리 색상 전환 효과 */
    stroke: black;
    /* 기본 테두리 색상 */
}

.menu-item {
    color: black;
    /* 기본 글자 색상 */
    cursor: pointer;
}

.menu-item.active {
    color: rgb(95, 0, 128);
    /* 선택된 글자 색상 */
}

.menu-item.active .icon {
    stroke: rgb(95, 0, 128);
    /* 선택된 아이콘 테두리 색상 */
}
</style>