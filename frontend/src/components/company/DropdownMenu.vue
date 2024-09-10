<template>
  <div :class="dropdown_menu">
    <h3 class="screen_out">{{ title }}</h3>
    <a href="#none" @click.prevent="toggleDropdown" :class="btn_layer_class">
      {{ selectedOption }}
    </a>
    <ul v-show="isDropdownOpen" :class="layer_search_class">
      <li v-for="option in options" :key="option">
        <a
          href="#none"
          :class="{ on: selectedOption === option }"
          @click.prevent="selectOption(option)"
        >
          {{ option }}
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "DropdownMenu",
  props: {
    title: {
      type: String,
      required: true,
    },
    options: {
      type: Array,
      required: true,
    },
    selectedOption: {
      type: String,
      default: "",
    },
    dropdown_menu: {
      type: String,
      default: "",
    },
    btn_layer_class: {
      type: String,
      default: "",
    },
    layer_search_class: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      isDropdownOpen: false,
    };
  },
  methods: {
    toggleDropdown() {
      this.isDropdownOpen = !this.isDropdownOpen;
    },
    selectOption(option) {
      this.$emit("update:selectedOption", option);
      this.isDropdownOpen = false;
    },
    handleClickOutside(event) {
      const dropdown = this.$el;
      if (!dropdown.contains(event.target)) {
        this.isDropdownOpen = false;
      }
    },
  },
  mounted() {
    document.addEventListener("click", this.handleClickOutside);
  },
  beforeUnmount() {
    document.removeEventListener("click", this.handleClickOutside);
  },
};
</script>

<style scoped>
a,
body,
div,
h1,
h2,
h3,
h4,
h5,
h6,
li,
ul {
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
a,
body,
div,
h1,
h2,
h3,
h4,
h5,
h6,
li,
ul {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body,
html {
  height: 100%;
}
body {
  font-family: noto sans, malgun gothic, AppleGothic, dotum;
  line-height: 1;
  letter-spacing: -0.05em;
  color: #4c4c4c;
  font-size: 15px;
  max-width: 100%;
}
div,
li {
  word-break: break-all;
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
h1 {
  font-size: 2em;
}
li {
  list-style: none;
}
::after,
::before {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.search_date {
  position: relative;
  z-index: 1;
}
.screen_out {
  display: block;
  overflow: hidden;
  position: absolute;
  left: -9999px;
  width: 1px;
  height: 1px;
  font-size: 0;
  line-height: 0;
  text-indent: -9999px;
}
.order_state,
.search_date {
  position: relative;
  z-index: 1;
}
.search_date .btn_layer_date {
  overflow: hidden;
  position: absolute;
  right: 0;
  top: -74px;
  width: 157px;
  height: 44px;
  padding: 11px 0 0 15px;
  border: 1px solid #ddd;
  font-size: 14px;
  color: #666;
  line-height: 18px;
  letter-spacing: -0.5px;
}
.search_date .layer_search_date {
  display: block;
  position: absolute;
  right: 0;
  top: -30px;
  width: 157px;
  border: 1px solid #dddfe1;
  border-top: 0;
}
.search_date .layer_search_date li:first-child {
  border-top: 0;
}
.search_date .layer_search_date li {
  background-color: #fff;
}
.search_date .layer_search_date a.on {
  background-color: #f7f7f7;
}
.search_date .layer_search_date a {
  display: block;
  overflow: hidden;
  height: 32px;
  padding: 7px 0 0 8px;
  font-size: 14px;
  color: #666;
  line-height: 18px;
  letter-spacing: -0.2px;
}
.search_date .btn_layer_date:after {
  content: "";
  position: absolute;
  left: 128px;
  top: 18px;
  width: 12px;
  height: 8px;
  background: url(https://res.kurly.com/pc/ico/2008/ico_down_12x8.svg) no-repeat
    0 0;
  background-size: 12px 8px;
}
.order_state .btn_layer_order {
  overflow: hidden;
  position: absolute;
  right: 195px;
  top: -74px;
  width: 157px;
  height: 44px;
  padding: 11px 0 0 15px;
  border: 1px solid #ddd;
  font-size: 14px;
  color: #666;
  line-height: 18px;
  letter-spacing: -0.5px;
}
.order_state .layer_search_order {
  display: block;
  position: absolute;
  right: 195px;
  top: -30px;
  width: 157px;
  border: 1px solid #dddfe1;
  border-top: 0;
}
.order_state .layer_search_order li:first-child {
  border-top: 0;
}
.order_state .layer_search_order li {
  background-color: #fff;
}
.order_state .layer_search_order a.on {
  background-color: #f7f7f7;
}
.order_state .layer_search_order a {
  display: block;
  overflow: hidden;
  height: 32px;
  padding: 7px 0 0 8px;
  font-size: 14px;
  color: #666;
  line-height: 18px;
  letter-spacing: -0.2px;
}
.order_state .btn_layer_order:after {
  content: "";
  position: absolute;
  left: 128px;
  top: 18px;
  width: 12px;
  height: 8px;
  background: url(https://res.kurly.com/pc/ico/2008/ico_down_12x8.svg) no-repeat
    0 0;
  background-size: 12px 8px;
}
</style>
