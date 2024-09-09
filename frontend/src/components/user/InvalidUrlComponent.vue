<template>
<div class="css-140915z eivgx682">
        <div class="css-c81mcc eivgx681">
            <div class="css-18z48ti eivgx680">
                <p class="css-4354vd">유효하지 않은 URL</p>
                <p class="css-whlh61">유효하지 않은 URL입니다.<br>메일함을 확인하시거나 비밀번호 찾기를 다시 시도해 주세요.</p>
            </div><button class="css-kpkqzs e4nu7ef3" type="button" width="200" height="44" @click="toFindPwd"><span
                    class="css-nytqmg e4nu7ef1">다시 비밀번호 찾기</span></button>
        </div>
    </div>
</template>

<script>

export default {
  name: 'InvalidUrlComponent',
  methods: {
    toFindPwd(){
        this.$router.push("/auth/pwd/find");
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
    padding: 50px 0;
    background-color: white;
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

.css-c81mcc {
    position: relative;
    max-width: 400px;
    margin: auto;
    text-align: center;
}

.css-18z48ti {
    width: 100%;
    height: 100%;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    text-align: center;
}

.css-4354vd {
    font-size: 28px;
    font-weight: 500;
    color: #666666;
}

p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    unicode-bidi: isolate;
}

.css-4354vd:before {
    content: "";
    display: block;
    width: 100px;
    height: 100px;
    margin: 0 auto 20px;
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTAwIiBoZWlnaHQ9IjEwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKDEwIDEwKSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgICAgICA8Y2lyY2xlIHN0cm9rZT0iI0Q5RDlEOSIgc3Ryb2tlLXdpZHRoPSIyIiBjeD0iNDAiIGN5PSI0MCIgcj0iMzkiLz4KICAgICAgICA8cGF0aCBkPSJNNDAuNSA1NGEyLjUgMi41IDAgMSAxIDAgNSAyLjUgMi41IDAgMCAxIDAtNXptLS4wNS0zM2EyLjI1IDIuMjUgMCAwIDEgMi4yNSAyLjI1djIyLjVhMi4yNSAyLjI1IDAgMSAxLTQuNSAwdi0yMi41QTIuMjUgMi4yNSAwIDAgMSA0MC40NSAyMXoiIGZpbGw9IiNEOUQ5RDkiLz4KICAgIDwvZz4KPC9zdmc+Cg==);
    -webkit-background-size: cover;
    background-size: cover;
}

.css-4354vd {
    font-size: 28px;
    font-weight: 500;
    color: #666666;
}

.css-whlh61 {
    margin-top: 20px;
    font-size: 16px;
    color: #b5b5b5;
    line-height: 1.44;
}

.css-c81mcc button {
    display: inline-block;
    margin-top: 30px;
}

.css-kpkqzs {
    display: block;
    padding: 0 10px;
    text-align: center;
    overflow: hidden;
    width: 200px;
    height: 44px;
    border-radius: 3px;
    color: #fff;
    background-color: #5f0080;
    border: 0 none;
}

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

button, select {
    text-transform: none;
}

button, input {
    overflow: visible;
}

button, input, optgroup, select, textarea {
    font-family: inherit;
    font-size: 100%;
    line-height: 1.15;
    margin: 0;
}

button, input[type=button], input[type=reset], input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
}

button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.css-c81mcc button span {
    font-size: 14px;
    font-weight: 500;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}
</style>
