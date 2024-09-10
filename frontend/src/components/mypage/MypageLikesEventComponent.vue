<template>
    <div class="css-heioij eug5r8l1">
        <div class="css-ehagcz eug5r8l0">
            <div class="css-1l3y783 e1a4z271" style="position: relative;">
                <div class="css-1gp1092 e1a4z270">전체 <strong>{{ totalItems }}</strong>개</div>
                <div v-if="items.length === 0" class="no-items-message">
                    찜한 게시글이 없습니다.
                </div>
                <div v-else style="overflow: visible; width: 0px;">
                    <div>
                        <div aria-label="grid" aria-readonly="true"
                            class="ReactVirtualized__Grid ReactVirtualized__List" role="grid" tabindex="0"
                            style="box-sizing: border-box; direction: ltr; height: auto; position: relative; width: 610px; will-change: transform; overflow: hidden;">
                            <div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup"
                                style="width: auto; height: 274px; max-width: 610px; max-height: 274px; overflow: hidden; position: relative;">
                                <div v-for="(item, index) in items" :key="index" class="css-9v8hu9 e19er7v46"
                                    :style="{ top: index * 137 + 'px', position: 'absolute', height: '137px', left: '0px', width: '100%' }">
                                    <a :href="item.link" class="css-4srzau e19er7v45">
                                        <span width="90" height="117" class="css-ew8abz eq9umyc2">
                                            <img :src="item.image" alt="" class="css-0" />
                                        </span>
                                    </a>
                                    <div class="css-7rpa25 e19er7v44">
                                        <div>
                                            <div class="css-1nf3eox e19er7v42">
                                                <a :href="item.link" class="css-4ejps8 e19er7v43">{{ item.title }}</a>
                                            </div>
                                            <div class="css-13kofxh e19er7v41">
                                                <span class="css-x4qt93 e1alt0er2">{{ item.discount }}%</span>
                                                <span class="css-153tu4t e1alt0er1">{{ formatPrice(item.price)
                                                    }}원</span>
                                                <span class="css-1ufyr2r e1alt0er0">{{ formatPrice(item.originalPrice)
                                                    }}원</span>
                                            </div>
                                        </div>
                                        <div class="css-2qdoqn e19er7v40">
                                            <button class="css-17giheb e4nu7ef3" @click="removeItem(index)">
                                                <span class="css-nytqmg e4nu7ef1">삭제</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="resize-triggers">
                    <div class="expand-trigger">
                        <div style="width: 611px; height: 323px;"></div>
                    </div>
                    <div class="contract-trigger"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            items: [
                {
                    title: '[라 메르] 아이 컨센트레이트 15mL',
                    link: '/goods/1000261988',
                    image: 'https://product-image.kurly.com/hdims/resize/%5E%3E120x%3E156/cropcenter/120x156/quality/85/src/product/image/4a74e3e8-1d07-498b-94f3-b0c078e6c59a.jpg',
                    discount: 15,
                    price: 344250,
                    originalPrice: 405000,
                },
                {
                    title: '[사미헌] 갈비탕',
                    link: '/goods/5026468',
                    image: 'https://product-image.kurly.com/hdims/resize/%5E%3E120x%3E156/cropcenter/120x156/quality/85/src/product/image/c4d41015-d188-4c68-b3e9-36968bf2110a.jpeg',
                    discount: 15,
                    price: 11050,
                    originalPrice: 13000,
                },
            ],
        };
    },
    computed: {
        totalItems() {
            return this.items.length;
        },
    },
    methods: {
        removeItem(index) {
            this.items.splice(index, 1);
        },
        formatPrice(value) {
            return value.toLocaleString();
        }
    },
};
</script>



<style scoped>
.no-items-message {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 274px;
    /* 내용이 있는 영역과 맞추기 위해 설정 */
    font-size: 16px;
    color: #888;
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

h2 {
    display: block;
    font-size: 1.5em;
    margin-block-start: 0.83em;
    margin-block-end: 0.83em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
    unicode-bidi: isolate;
}

.css-1vbzf1d {
    padding-left: 8px;
    font-size: 13px;
    line-height: 20px;
    letter-spacing: -0.2px;
    color: rgb(153, 153, 153);
}

.css-ehagcz {
    padding: 0px 20px;
}

.css-1l3y783 {
    padding: 8px 0px 10px;
}

.css-1gp1092 {
    color: rgb(51, 51, 51);
    padding: 8px 0px 6px;
}

b,
strong {
    font-weight: bolder;
}

*,
:after,
:before,
legend,
td,
th {
    padding: 0;
}

.css-9v8hu9 {
    display: flex;
    padding: 10px 0px;
}

.css-4srzau {
    display: block;
    position: relative;
    width: 90px;
    height: 117px;
    background-color: rgb(245, 245, 245);
}

.css-7rpa25 {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    flex-direction: column;
    width: calc(100% - 90px);
    padding-left: 16px;
}

.css-1nf3eox {
    display: -webkit-box;
    overflow: hidden;
    word-break: break-all;
    white-space: normal;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    line-height: 19px;
}

.css-4ejps8 {
    display: block;
}

a {
    background-color: transparent;
}

a {
    text-decoration: none;
    color: inherit;
}

.css-13kofxh {
    margin-top: 4px;
}

.css-13kofxh>span {
    margin-right: 4px;
    line-height: 19px;
}

.css-x4qt93 {
    color: rgb(250, 98, 47);
    font-weight: bold;
}

.css-1ufyr2r {
    color: rgb(181, 181, 181);
    font-size: 12px;
    text-decoration: line-through;
}

.css-2qdoqn {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    gap: 6px;
}

.css-2qdoqn>button {
    width: 100%;
}

.css-17giheb {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 104px;
    height: 36px;
    border-radius: 4px;
    color: rgb(51, 51, 51);
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(221, 221, 221);
}

[type=button],
[type=reset],
[type=submit],
button {
    -webkit-appearance: button;
}

html,
button,
input,
select,
textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

.css-2qdoqn>button>span {
    font-size: 14px;
}

.css-x0zbdy {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 104px;
    height: 36px;
    border-radius: 4px;
    color: rgb(95, 0, 128);
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(95, 0, 128);
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

.css-1m3kac1 {
    display: inline-block;
    width: 20px;
    height: 20px;
    margin-right: 4px;
    vertical-align: middle;
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

.css-ew8abz img {
    border-radius: 4px;
}

button,
input[type=button],
input[type=reset],
input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
}

.css-153tu4t {
    color: rgb(51, 51, 51);
    font-weight: bold;
}

.css-heioij {
    overflow: hidden;
    width: 650px;
    background-color: rgb(255, 255, 255);
    border-radius: 16px;
}
</style>