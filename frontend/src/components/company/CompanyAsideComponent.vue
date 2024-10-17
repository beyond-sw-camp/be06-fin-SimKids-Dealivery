<template>
  <div id="snb" class="snb_my">
    <h2 class="tit_snb">판매자 메뉴</h2>
    <div class="inner_snb">
      <ul class="list_menu">
        <li>
          <a href="/product-boards/company">게시글 등록</a>
        </li>
        <li>
          <a href="/product-boards/company/list">게시글 조회</a>
        </li>
        <li>
          <a href="/orders/company/list">주문 관리</a>
        </li>
        <li>
          <a href="/qna/company/list">1:1문의 관리</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "CompanyAsideComponent",
  props: {},
};
</script>

<style scoped>
#content {
  min-width: 1050px;
}

.page_aticle {
  width: 1050px;
  margin: 0 auto;
}

.page_aticle.aticle_type2 {
  padding-top: 65px;
}

#snb {
  float: left;
  width: 200px;
}

#snb .tit_snb {
  padding: 8px 0 33px 1px;
  font-weight: 700;
  font-size: 30px;
  line-height: 34px;
  color: #333;
  letter-spacing: -0.5px;
}

#snb .inner_snb {
  border: 1px solid #f2f2f2;
  border-bottom: 0;
}

#snb .list_menu li {
  border-bottom: 1px solid #f2f2f2;
}

#snb .list_menu li.on a,
#snb .list_menu li a:hover {
  background: #fafafa
    url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px
    52%;
  background-size: 6px 11px;
  font-weight: 700;
  color: #5f0080;
}

#snb .list_menu li a {
  display: block;
  overflow: hidden;
  padding: 15px 0 15px 20px;
  background: #fff url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11.svg)
    no-repeat 174px 52%;
  background-size: 6px 11px;
  font-size: 14px;
  color: #666;
  line-height: 20px;
  letter-spacing: -0.3px;
}

.snb_my {
  top: 220px;
  left: 400px;
  margin-right: -670px;
}

.page_aticle.aticle_type2 .page_section {
  float: right;
  width: 820px;
}

.page_aticle .head_aticle {
  padding: 5px 0 34px;
}

.page_aticle .head_aticle .tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -0.5px;
  display: inline-block;
}

body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
form,
fieldset,
legend,
input,
button,
textarea,
p,
blockquote,
th,
td,
a,
span {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

@font-face {
  font-family: nanum gothic;
  font-style: normal;
  font-weight: 400;
  src: url(https://res.kurly.com/fonts/NanumGothic-Regular.woff2)
      format("woff2"),
    url(https://res.kurly.com/fonts/NanumGothic-Regular.woff) format("woff"),
    url(https://res.kurly.com/fonts/NanumGothic-Regular.otf) format("opentype");
}

@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 200;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Light.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Light.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Light.otf) format("opentype");
}

@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 400;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Regular.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Regular.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Regular.otf) format("opentype");
}

@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 700;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Medium.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Medium.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Medium.otf) format("opentype");
}

@font-face {
  font-family: noto sans;
  font-style: normal;
  font-weight: 800;
  src: url(https://res.kurly.com/fonts/NotoSansKR-Bold.woff2) format("woff2"),
    url(https://res.kurly.com/fonts/NotoSansKR-Bold.woff) format("woff"),
    url(https://res.kurly.com/fonts/NotoSansKR-Bold.otf) format("opentype");
}

body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
form,
fieldset,
legend,
input,
button,
textarea,
p,
blockquote,
th,
td,
a,
span {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html,
body {
  height: 100%;
}

body,
input,
select,
textarea,
button {
  font-family: noto sans, malgun gothic, AppleGothic, dotum;
  line-height: 1;
  letter-spacing: -0.05em;
  color: #4c4c4c;
  font-size: 15px;
  max-width: 100%;
}

div,
th,
td,
li,
dt,
dd,
p {
  word-break: break-all;
}

img,
video,
canvas {
  max-width: 100%;
}

img {
  border: none;
  vertical-align: top;
}

button {
  outline: none;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

a {
  text-decoration: none;
  background-color: transparent;
  color: inherit;
}

a:active,
a:hover {
  outline: 0;
  cursor: pointer;
}

b,
strong {
  font-weight: 700;
}

h1 {
  font-size: 2em;
}

fieldset {
  border: none;
}

li {
  list-style: none;
}

input {
  line-height: normal;
  outline: none;
}

*::after,
*::before {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
</style>
