<template>
    <div>
        <div class="css-heioij eug5r8l1">
            <div class="css-oc8mjz ed9qr673">
                <div class="css-eq7f8j ed9qr672">
                    <h2 class="css-1lmd4kz ed9qr671">주문 내역</h2>
                </div>
            </div>
        </div>
        <div>
            <div class="css-1xdhyk6 eug5r8l0">
                <div class="css-10ekv2i e13vhfr40">
                    <div class="css-1phmj5u e190ng8o0">
                        <div class="css-2kmie e1g49j8k1">
                            <div class="css-peeqro e190ng8o1">
                                <div class="css-jyp95e e1l5ke4x0">
                                    <button @click="toggleDetails" class="css-7sy6n e1rmfz7b0">
                                        <img src="@/assets/arrow.png" alt="icon" width="40" height="40"
                                            :style="{ transform: arrowRotate, transition: 'transform 0.3s' }">
                                    </button>
                                    <div class="css-7uztss e1rmfz7b4">
                                        <div>
                                            <p
                                                class="_97oqoup _97oqouv _97oqou5 ldmw177c _97oqou19 _97oqouj ldmw177q _97oqou12 _97oqouc ldmw177j css-wcc2f6 e1rmfz7b3">
                                                2024.09.05
                                            </p>
                                            <div class="css-k7chvl e1rmfz7b1">
                                                <p
                                                    class="_97oqoup _97oqoux _97oqou7 ldmw177a _97oqou1b _97oqoul ldmw177s _97oqou13 _97oqoud ldmw177i css-1vgapaa e1rmfz7b2">
                                                    주문번호 2307216320130
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div height="1px" width="100%" class="css-yng2ii e1ypu1ln0"></div>
                                    <div class="css-1t4yb7q e1xhbacy2">
                                        <div class="css-wnosz2 e1xhbacy0">
                                            <p
                                                class="_97oqoup _97oqouv _97oqou5 ldmw177c _97oqou19 _97oqouj ldmw177q _97oqou12 _97oqouc ldmw177j css-1n1zmlq e1xhbacy1">
                                                주문완료
                                            </p>
                                        </div>
                                    </div>
                                    <div class="css-vts8bc e9fxgjh2">
                                        <div class="css-1tf2711 e73mjag8">
                                            <a href="/goods/5054274" class="css-1ll9bqd e73mjag0">
                                                <img src="https://img-cf.kurly.com/hdims/resize/%5E%3E120x%3E156/cropcenter/120x156/quality/85/src/shop/data/goods/1594097581645l0.jpg"
                                                    alt="[정기배송] 한 끼 보리샐러드" class="css-13pph03 e73mjag7">
                                            </a>
                                            <div class="_17g6wc40">
                                                <a href="/goods/5054274" class="css-1ll9bqd e73mjag0">
                                                    <p
                                                        class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-1dl78ek e73mjag3">
                                                        [정기배송] 한 끼 보리샐러드
                                                    </p>
                                                </a>
                                                <div class="css-1tf2711 e73mjag8">
                                                    <p
                                                        class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou11 _97oqoub ldmw177k css-1dl78ek e73mjag4">
                                                        89,900원
                                                    </p>
                                                    <p class="css-if5wh3 e73mjag1">145,000원</p>
                                                    <div width="1px" height="10px" class="css-9ib26w e1ypu1ln0"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="css-8vtrb0 eus1fbt1"></div>
                                </div>
                            </div>
                        </div>
                        <div class="css-21ijkk e190ng8o3"></div>
                    </div>
                </div>
            </div>
            <div class="css-3dze2x eug5r8l1" v-show="isDetailsVisible" id="details">
                <button class="css-f848a6 e2jhvp32">
                    <p
                        class="_97oqoup _97oqouu _97oqou4 ldmw177d _97oqou18 _97oqoui ldmw177p _97oqou12 _97oqouc ldmw177j css-1dl78ek e2jhvp31">
                        결제정보
                    </p>
                </button>
                <div class="css-d3v9zr e14m4ys50" style="opacity: 1; height: auto;">
                    <div class="css-1a0zxau e13968o84">
                        <div class="css-1aim50k e93c1qv0">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                                상품금액</p>
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                                145,000원</p>
                        </div>
                        <div class="css-1aim50k e2upnqp1">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-15bah7q e2upnqp0">
                                상품할인금액</p>
                            <div class="css-8yre18 e2upnqp1">
                                <p
                                    class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-uwqhso e2upnqp0">
                                    -55,100원</p>
                            </div>
                        </div>
                        <div class="css-1aim50k e2upnqp1">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-15bah7q e2upnqp0">
                                포인트</p>
                            <div class="css-8yre18 e2upnqp1">
                                <p
                                    class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-uwqhso e2upnqp0">
                                    -2,000원</p>
                            </div>
                        </div>
                        <div class="css-1aim50k e2upnqp1">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-15bah7q e2upnqp0">
                                배송비</p>
                            <div class="css-8yre18 e2upnqp1">
                                <p
                                    class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-uwqhso e2upnqp0">
                                    +3,000원</p>
                            </div>
                        </div>
                        <div class="css-1aim50k e2upnqp1">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-15bah7q e2upnqp0">
                                결제방법</p>
                            <div class="css-8yre18 e2upnqp1">
                                <p
                                    class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-uwqhso e2upnqp0">
                                    카카오페이</p>
                            </div>
                        </div>
                        <div class="css-1aim50k e2upnqp1">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                                총 결제금액</p>
                            <div class="css-8yre18 e2upnqp1">
                                <p
                                    class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                                    90,900원</p>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="css-f848a6 e2jhvp32">
                    <p
                        class="_97oqoup _97oqouu _97oqou4 ldmw177d _97oqou18 _97oqoui ldmw177p _97oqou12 _97oqouc ldmw177j css-1dl78ek e2jhvp31">
                        주문정보</p>
                </button>
                <div class="css-d3v9zr e14m4ys50" style="opacity: 1; height: auto;">
                    <div class="css-1a0zxau e1ckt0s50">
                        <div class="css-1aim50k e93c1qv0">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                                주문번호</p>
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                                2307216320130</p>
                        </div>
                        <div class="css-1aim50k e2upnqp1">
                            <p
                                class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-15bah7q e2upnqp0">
                                결제 일시</p>
                            <div class="css-8yre18 e2upnqp1">
                                <p
                                    class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-uwqhso e2upnqp0">
                                    2024.09.05 16:33:45</p>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="css-f848a6 e2jhvp32">
                    <p
                        class="_97oqoup _97oqouu _97oqou4 ldmw177d _97oqou18 _97oqoui ldmw177p _97oqou12 _97oqouc ldmw177j css-1dl78ek e2jhvp31">
                        배송정보</p>
                </button>
                <div class="css-d3v9zr e14m4ys50" style="opacity: 1; height: auto;">
                    <div class="css-6z4447 e1n2ou003">
                        <p
                            class="_97oqoup _97oqouv _97oqou5 ldmw177c _97oqou19 _97oqouj ldmw177q _97oqou12 _97oqouc ldmw177j css-luewyl e1n2ou001">
                            심키즈</p>
                        <p
                            class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-4qwok1 e1n2ou001">
                            010-1234-****</p>
                        <p
                            class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-303o8l e1n2ou001">
                            서울특별시 광진구 군자로0길 50-2</p>
                    </div>
                </div>
                <div class="css-od0sqq ecvmg6w3">
                    <p
                        class="_97oqoup _97oqoux _97oqou7 ldmw177a _97oqou1b _97oqoul ldmw177s _97oqou13 _97oqoud ldmw177i css-1fdfbqy ecvmg6w1">
                        주문취소는 [주문완료] 상태일 경우에만 가능합니다.</p>
                    <p
                        class="_97oqoup _97oqoux _97oqou7 ldmw177a _97oqou1b _97oqoul ldmw177s _97oqou13 _97oqoud ldmw177i css-1fdfbqy ecvmg6w1">
                        단, 일부 상품의 경우 [주문완료] 상태이더라도 상품의 특성상 주문취소가 불가능할 수 있습니다.</p>
                    <button disabled
                        class="tew5wjw tew5wj0 ldmw1780 tew5wjy tew5wj19 tew5wj1b tew5wj14 tew5wj3 ldmw1717i ldmw17183 ldmw1715v ldmw177b tew5wj17 tew5wj1c tew5wj5 ldmw177j tew5wj1l tew5wje ldmw17y6 ldmw17ok ldmw178w tew5wjp ldmw171du css-jz9jxv ecvmg6w2">
                        <p
                            class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou12 _97oqouc ldmw177j">
                            주문 취소</p>
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            isDetailsVisible: false,
            arrowRotated: false,
        };
    },
    computed: {
        arrowRotate() {
            return this.arrowRotated ? 'rotate(90deg)' : 'rotate(0deg)';
        },
    },
    methods: {
        toggleDetails() {
            this.isDetailsVisible = !this.isDetailsVisible;
            this.arrowRotated = !this.arrowRotated;
        },
    },
};
</script>

<style scoped>
body {
    margin: 0;
}

body,
button,
input,
select,
textarea {
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

body,
html {
    height: 100%;
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

html {
    line-height: 1.15;
    -webkit-text-size-adjust: 100%;
}

.css-u71x2d {
    position: relative;
    min-width: 1050px;
    background-color: rgb(242, 245, 248);
    padding: 20px 0;
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
    margin-bottom: 0px;
}

.css-oc8mjz {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    margin: 0px 20px;
    padding: 25px 0px 20px;
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
    background-color: #f2f5f8;
}

.css-1phmj5u {
    background-color: #f2f5f8;
}

.css-2kmie {
    position: relative;
    margin-top: 0px;
    transition: transform, 0.2s ease-out;
    will-change: transform;
    overscroll-behavior-y: contain;
}

.css-peeqro {
    padding: 20px 0 0;
}

.css-jyp95e {
    padding: 20px 16px;
    background-color: #fff;
    border-radius: 16px;
}

.css-7uztss {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    /* 여백 추가 */
}

.css-wcc2f6 {
    padding-bottom: 2px;
    color: #222;
    margin-bottom: 5px;
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

p {
    display: block;
    margin-block-start: 0.2em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    unicode-bidi: isolate;
}

.css-k7chvl {
    cursor: pointer;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
}

.css-1vgapaa {
    color: #848f9a;
    padding-right: 4px;
}

.ldmw177s {
    line-height: 20px;
}

.ldmw177i {
    font-weight: 400;
}

.ldmw177a {
    font-size: 14px;
}

.css-7sy6n {
    width: 75px;
    height: 40px;
    flex-shrink: 0;
    position: absolute;
    right: -6px;
}

html,
button,
input,
select,
textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

[type=button],
[type=reset],
[type=submit],
button {
    -webkit-appearance: button;
}

._1k44z3a0,
.hbkc73d {
    align-items: center;
    justify-content: center;
}

._1k44z3a0 {
    display: inline-flex;
}

i {
    font-style: italic;
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

button {
    border-radius: 0;
}



button {
    overflow: visible;
    background-color: transparent;
    border: none;
}

svg:not(:root) {
    overflow: hidden;
}



button,
input[type=button],
input[type=reset],
input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
}

.css-yng2ii {
    min-width: 100%;
    min-height: 1px;
    margin: 16px 0 20px 0;
    background-color: #eceff3;
}

.css-1t4yb7q {
    display: flex;
    margin-bottom: 16px;
    -webkit-box-pack: justify;
    justify-content: space-between;
}

.css-wnosz2 {
    display: inline-flex;
}

.css-1n1zmlq:first-of-type {
    margin-right: 4px;
}

.css-1n1zmlq {
    color: #8d4cc4;
}

.css-vts8bc {
    padding-bottom: 16px;
}

.css-1tf2711 {
    display: flex;
    flex-direction: row;
    -webkit-box-pack: start;
    justify-content: flex-start;
    -webkit-box-align: center;
    align-items: center;
}

.css-1ll9bqd {
    cursor: default;
}

a {
    background-color: transparent;
}

a {
    text-decoration: none;
    color: inherit;
}

.css-13pph03 {
    min-width: 56px;
    width: 56px;
    height: 72px;
    margin-right: 12px;
    background-color: rgb(245, 245, 245);
    border-radius: 8px;
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

._17g6wc40,
.tew5wjw {
    box-sizing: border-box;
}

.css-s5xdrg {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
}

.css-1fqzsmf {
    color: #848f9a;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.ldmw177t {
    line-height: 18px;
}

.ldmw1779 {
    font-size: 13px;
}

.css-1dl78ek {
    color: #222;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.ldmw177r {
    line-height: 22px;
}

.ldmw177b {
    font-size: 16px;
}

.ldmw177k {
    font-weight: 700;
}

.css-if5wh3 {
    color: #505760;
    font-size: 13px;
    line-height: 18px;
    text-decoration-line: line-through;
    margin-left: 4px;
}

.css-9ib26w {
    min-width: 1px;
    min-height: 10px;
    margin: 0 6px;
    background-color: #eceff3;
}

.css-1dl5ekv {
    color: 18px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.css-8vtrb0 {
    display: flex;
}

.css-21ijkk {
    width: 100%;
    height: 1px;
}

body {
    margin: 0;
}

body,
button,
input,
select,
textarea {
    font-size: 14px;
    color: #333;
}

body,
button,
input,
select,
textarea {
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

body,
html {
    height: 100%;
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

body {
    display: block;
    margin: 8px;
}

body,
button,
input,
select,
textarea {
    font-size: 14px;
    color: #333;
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

.css-ha2unt {
    padding-bottom: 20px;
    color: rgb(51, 51, 51);
    font-size: 20px;
    font-style: normal;
    font-weight: 500;
    line-height: 28px;
}

.css-1gn0w45 {
    min-width: 100%;
    min-height: 2px;
    margin: 0px;
    background-color: rgb(51, 51, 51);
}

.css-1ca7lx5 {
    background: #fff;
    padding: 16px 5px;
    border-radius: 0 0 10px 10px;
}

.css-fhxb3m {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
}

.css-kegbry p:first-of-type {
    margin-bottom: 2px;
}

.css-1pjpfkv {
    color: #848f9a;
}

.ldmw177s {
    line-height: 20px;
}

.ldmw177i {
    font-weight: 400;
}

.ldmw177a {
    font-size: 14px;
}

.css-10ijdu3 {
    color: #222;
}

.ldmw177r {
    line-height: 22px;
}

.ldmw177j {
    font-weight: 600;
}

.ldmw177b {
    font-size: 16px;
}

.css-8w1507 {
    min-width: 100%;
    min-height: 1px;
    margin: 16px 0;
    background-color: #eceff3;
}

.css-l8gklh {
    color: #565e67;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.css-1o9lzzy {
    padding: 20px 0 12px;
    color: #222;
}

.ldmw177p {
    line-height: 28px;
}

.ldmw177d {
    font-size: 18px;
}

.css-gtsx9s {
    border-radius: 16px;
    background: #fff;
    padding: 16px;
}

.css-1t4yb7q {
    display: flex;
    margin-bottom: 16px;
    -webkit-box-pack: justify;
    justify-content: space-between;
}

.css-ywohcx {
    display: inline-flex;
    margin-top: 4px;
}

.css-1n1zmlq:first-of-type {
    margin-right: 4px;
}

.css-1n1zmlq {
    color: #7542a1;
}

.ldmw177q {
    line-height: 26px;
}

.ldmw177c {
    font-size: 18px;
}

.css-16s18qq>div {
    margin-bottom: 20px;
}

.css-1tf2711 {
    display: flex;
    flex-direction: row;
    -webkit-box-pack: start;
    justify-content: flex-start;
    -webkit-box-align: center;
    align-items: center;
}

a {
    background-color: transparent;
}

a {
    text-decoration: none;
    color: inherit;
}

.css-13pph03 {
    min-width: 56px;
    width: 56px;
    height: 72px;
    margin-right: 12px;
    background-color: rgb(245, 245, 245);
    border-radius: 8px;
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

._17g6wc40,
.tew5wjw {
    box-sizing: border-box;
}

.css-s5xdrg {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
}

.css-1fqzsmf {
    color: #848f9a;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.ldmw177t {
    line-height: 18px;
}

.ldmw1779 {
    font-size: 13px;
}

.css-n86rv6 {
    color: #222;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.css-1dl78ek {
    color: #222;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.css-if5wh3 {
    color: #bcc4cc;
    font-size: 13px;
    line-height: 18px;
    text-decoration-line: line-through;
    margin-left: 4px;
}

.css-9ib26w {
    min-width: 1px;
    min-height: 10px;
    margin: 0 6px;
    background-color: #eceff3;
}

.css-1dl5ekv {
    color: #565e67;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
}

.css-8vtrb0 {
    display: flex;
}

.css-f848a6 {
    display: flex;
    width: 100%;
    flex-direction: row;
    padding: 20px 10px 4px;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    align-items: center;
}

html,
button,
input,
select,
textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

[type=button],
[type=reset],
[type=submit],
button {
    -webkit-appearance: button;
}

button,
select {
    text-transform: none;
}

button,
input {
    overflow: visible;
}

.css-y11erl {
    display: flex;
    rotate: -180deg;
}

.css-d3v9zr {
    overflow: hidden;
}

.css-1a0zxau {
    border-radius: 16px;
    background: #fff;
    padding: 20px 16px;
}

.css-1aim50k:not(:last-of-type) {
    margin-bottom: 12px;
}

.css-1aim50k {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    align-items: center;
}

.css-15bah7q {
    color: #848f9a;
}

.css-8yre18 {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
}

.css-uwqhso {
    color: #a7b2bc;
}

.css-6z4447 {
    border-radius: 16px;
    background: #fff;
    padding: 20px 16px;
}

.css-6z4447>p:not(:last-of-type) {
    margin-bottom: 6px;
}

.css-luewyl {
    color: #222;
}

.css-4qwok1 {
    color: #848f9a;
}

.css-303o8l {
    color: #a7b2bc;
}

.css-od0sqq {
    border-radius: 16px;
    background: #fff;
    padding: 20px 16px;
    margin-top: 20px;
}

.css-1fdfbqy {
    position: relative;
    color: #848f9a;
    padding-left: 10px;
}

.css-1fdfbqy~p {
    margin-top: 4px;
}

.tew5wj1l:disabled {
    color: #222;
    background-color: #eceff3;
}

button[disabled],
input[disabled] {
    cursor: pointer;
}

.css-jz9jxv {
    width: 100%;
    margin-top: 16px;
}

.tew5wj19 {
    border-width: 1px;
    border-style: solid;
}

.ldmw171du {
    border-radius: 10px;
}

.ldmw17183 {
    padding-right: 16px;
}

.ldmw1717i {
    padding-left: 16px;
}

.ldmw1715v {
    height: 48px;
}

.ldmw17y6 {
    border-color: #dfe4eb;
}

.ldmw177b {
    font-size: 16px;
}

.css-3dze2x {
    overflow: hidden;
    width: 650px;
    background-color: transparent;
    border-radius: 16px;
}

.css-1xdhyk6 {
    background-color: rgb(255, 255, 255);
    border-radius: 16px 16px 0 0;
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

button {
    overflow: visible;
    background-color: transparent;
    border: none;
}

.tew5wjw {
    overflow: hidden;
    transition: all .1s ease-out;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    border-width: 0;
}
</style>