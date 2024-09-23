<template>
  <div class="e1e6st7u6 css-17p5a04 e1ne12di0">
    <div class="css-ghsdgz e1e6st7u4">
      <h2 class="css-n9ehvd e1e6st7u3">일일특가</h2>
      <h3 class="css-1fc0npt e1e6st7u2">24시간 한정 특가</h3>
      <div class="e1e6st7u5 css-we1gxf e17lobue1">
        <div class="lottie-timer">
          <img
            src="@/assets/clock.svg"
            alt="timer"
            style="width: 36px; height: 36px"
          />
        </div>
        <div class="time-units css-hb6k3w e17lobue0">
          <span>{{ hours }}</span> <span>{{ minutes }}</span>
          <span>{{ seconds }}</span>
        </div>
      </div>
      <p class="css-d0y7nj e1e6st7u1">망설이면 늦어요!</p>
    </div>
    <div class="css-1yx948y e1e6st7u0">
      <div class="deal-size-2 e1nucwdg0 e14udynn0 css-ae5x66 e1cmg4vm6">
        <div class="css-p55uhh">
          <div class="css-4vnm46">
            <div class="css-pw7jst e9elpup1" style="padding-bottom: 128.402%">
              <div class="css-j2pfbl e9elpup0">
                <span
                  style="
                    box-sizing: border-box;
                    display: block;
                    overflow: hidden;
                    width: initial;
                    height: initial;
                    background: none;
                    opacity: 1;
                    border: 0px;
                    margin: 0px;
                    padding: 0px;
                    position: absolute;
                    inset: 0px;
                  "
                >
                  <a :href="`/board/detail/${data.idx}`">
                    <img
                      alt=""
                      :src="data.productThumbnailUrl"
                      decoding="async"
                      data-nimg="fill"
                      class="css-1zjvv7"
                      style="
                        position: absolute;
                        inset: 0px;
                        box-sizing: border-box;
                        padding: 0px;
                        border: none;
                        margin: auto;
                        display: block;
                        width: 0px;
                        height: 0px;
                        min-width: 100%;
                        max-width: 100%;
                        min-height: 100%;
                        max-height: 100%;
                        object-fit: cover;
                      "
                      sizes="100vw"
                    />
                  </a>
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="button-wrapper">
          <button
            type="button"
            @click="toggleHeart"
            class="product-function css-13xu5fn e17x72af0"
          >
            <img
              :src="heartIcon"
              alt="heart icon"
              style="width: 21px; height: 21px; margin: 5px"
            />
            관심 등록
          </button>
        </div>
        <a :href="`/board/detail/${data.idx}`">
          <div class="product-info css-1ud9i0q e1cmg4vm4">
            <h2 class="product-name css-eaolx3 e1cmg4vm5">
              {{ data.companyName }}
            </h2>
            <h3 class="product-name css-eaolx3 e1cmg4vm5">{{ data.title }}</h3>
            <div class="content-row css-1066lcq e1cmg4vm3">
              <div class="product-price discount-price css-1fttcpj ei5rudb2">
                <div>
                  <span class="dimmed-price css-105zgxh ei5rudb1"
                    ><span class="price-number">{{
                      data.price.toLocaleString()
                    }}</span
                    ><span class="won">원</span></span
                  >
                </div>
                <div class="discount">
                  <span class="discount-rate css-19lkxd2 ei5rudb0"
                    >{{ data.discountRate }}%</span
                  ><span class="sales-price css-105zgxh ei5rudb1"
                    ><span class="price-number">{{
                      (
                        this.data.price *
                        (1 - this.data.discountRate / 100)
                      ).toLocaleString()
                    }}</span
                    ><span class="won">원</span></span
                  >
                </div>
              </div>
            </div>
          </div>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import heartIcon from "@/assets/heart-icon.svg";
import heartIconActive from "@/assets/heart-icon-active.svg";
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";
export default {
  data() {
    return {
      isActive: false,
      heartIcon: heartIcon,
      request:{
        productBoardIdx: null
      },
      hours: "00",
      minutes: "00",
      seconds: "00",
      intervalId: null,
    };
  },
  props: {
    data: {
      type: Object,
    },
  },
  computed: {
    ...mapStores(useUserStore),
  },  
  mounted() {
    this.startTimer();
    this.isActive = this.data.likes;
  },
  beforeUnmount() {
    clearInterval(this.intervalId);
  },
  methods: {
    startTimer() {
      const now = new Date();
      const endTime = new Date(this.data.endedAt);

      let remainingTime = Math.floor((endTime - now) / 1000); // 초 단위로 남은 시간 계산

      if (remainingTime <= 0) {
        this.resetTimer();
        return;
      }

      this.updateTime(remainingTime);

      this.intervalId = setInterval(() => {
        remainingTime -= 1;
        if (remainingTime <= 0) {
          clearInterval(this.intervalId);
          this.resetTimer();
        } else {
          this.updateTime(remainingTime);
        }
      }, 1000);
    },
    updateTime(remainingTime) {
      const hours = Math.floor(remainingTime / 3600);
      const minutes = Math.floor((remainingTime % 3600) / 60);
      const seconds = remainingTime % 60;

      this.hours = String(hours).padStart(2, "0");
      this.minutes = String(minutes).padStart(2, "0");
      this.seconds = String(seconds).padStart(2, "0");
    },
    resetTimer() {
      this.hours = "00";
      this.minutes = "00";
      this.seconds = "00";
    },
  },
  async toggleHeart() {
    this.request.productBoardIdx = this.data.idx;
      console.log(this.data.likes);
      if(!this.userStore.isLogined){
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push("/auth/login");
        return
      }
      if(await this.userStore.like(this.request)){
        this.isActive = !this.isActive;
        this.heartIcon = this.isActive ? heartIconActive : heartIcon;
      }else{
        alert("관심 등록에 실패했습니다.");
      }
  },
};
</script>

<style scoped>
.heart-icon {
  transition: fill 0.3s, stroke 0.3s;
}

.active {
  fill: pink;
  stroke: pink;
}

.button-wrapper {
  margin-top: 8px;
}

a {
  background-color: transparent;
}

a {
  text-decoration: none;
  color: inherit;
}

.css-17p5a04 {
  width: 470px;
  margin: 0px 0px;
  display: flex;
  -webkit-box-pack: justify;
  justify-content: center;
  padding: 20px 0px;
}

.css-n9ehvd {
  padding-top: 1px;
  padding-bottom: 0px;
  margin-bottom: 0px;
  font-size: 28px;
  color: rgb(51, 51, 51);
  font-weight: 500;
  line-height: 1.1;
  letter-spacing: -0.26px;
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

.css-1fc0npt {
  margin-top: 7px;
  padding-top: 0px;
  font-size: 16px;
  color: rgb(153, 153, 153);
  font-weight: normal;
  line-height: 1.3;
  letter-spacing: normal;
}

h3 {
  display: block;
  font-size: 1.17em;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
  unicode-bidi: isolate;
}

.css-we1gxf {
  display: flex;
  padding: 10px 0px 0px;
}

.css-we1gxf .lottie-timer {
  width: 36px;
  height: 47px;
  margin-right: 5px;
}

element.style {
  width: 100%;
  height: 100%;
  transform: translate3d(0px, 0px, 0px);
}

svg:not(:root) {
  overflow: hidden;
}

rect {
  width: 36;
  height: 36;
  x: 0;
  y: 0;
}

:not(svg) {
  transform-origin: 0px 0px;
}

.css-hb6k3w {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  width: 136px;
  font-size: 32px;
  font-weight: 700;
  color: rgb(51, 51, 51);
}

.css-we1gxf .time-units span:not(:last-of-type) {
  margin-right: 10px;
}

.css-we1gxf .time-units span {
  position: relative;
  flex-shrink: 0;
  min-width: 38px;
  line-height: 47px;
}

.css-d0y7nj {
  padding-top: 5px;
  margin-top: 2px;
  font-size: 14px;
  color: rgb(204, 204, 204);
  font-weight: normal;
  line-height: 1.43;
  letter-spacing: -0.4px;
}

.css-ghsdgz {
  width: 220px;
}

.css-1yx948y {
  display: flex;
  gap: 18px;
}

.css-ae5x66.deal-size-2 {
  width: 220px;
}

.css-ae5x66 {
  display: block;
  width: fit-content;
  color: rgb(51, 51, 51);
  padding: 0px;
}

.css-p55uhh {
  overflow: hidden;
  border-radius: 4px;
}

.css-pw7jst {
  position: relative;
  width: 100%;
}

.css-j2pfbl {
  position: absolute;
  inset: 0px;
}

.css-ae5x66 .product-function {
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  width: 100%;
  height: 36px;
  margin-top: 6px;
  padding-bottom: 1px;
  font-size: 16px;
  line-height: 29px;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 4px;
  cursor: pointer;
}

.css-13xu5fn {
  color: rgb(51, 51, 51);
  width: 100%;
  height: 32px;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  background-color: #fff;
}

[type="button"],
[type="reset"],
[type="submit"],
button {
  -webkit-appearance: button;
}

.css-ae5x66 .product-function > svg {
  width: 22px;
  height: 22px;
  margin: 1px 4px 0px 0px;
}

.css-13xu5fn > svg {
  pointer-events: none;
}

.css-ae5x66 .product-info {
  padding: 9px 0px 0px;
}

.css-ae5x66 .product-info {
  position: relative;
  padding: 8px 10px 0px 0px;
}

.css-1ud9i0q {
  padding: 8px 0px;
}

.css-ae5x66 .product-info .product-description {
  margin-bottom: 8px;
  font-size: 14px;
  color: rgb(153, 153, 153);
  line-height: 1.38;
  word-break: break-word;
}

.css-ae5x66 .product-info .product-description {
  margin-bottom: 6px;
}

.css-ae5x66 .product-info .product-name {
  font-size: 16px;
  line-height: 23px;
  font-weight: 400;
  margin-bottom: 8px;
  margin-top: 8px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  letter-spacing: normal;
  word-break: break-word;
  overflow-wrap: break-word;
}

.css-ae5x66.deal-size-2 .content-row {
  gap: 8px;
}

.css-ae5x66 .product-info .content-row {
  display: flex;
  flex-wrap: wrap;
  -webkit-box-pack: justify;
  justify-content: space-between;
}

.css-1066lcq {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
}

.css-ae5x66.deal-size-2 .product-price {
  padding-right: 6px;
}

.css-ae5x66 .product-info .product-price {
  flex-flow: row;
  -webkit-box-align: center;
  align-items: center;
}

.css-1fttcpj {
  display: flex;
  flex-direction: column;
}

.css-ae5x66 .product-info .product-price.discount-price > div:nth-of-type(1) {
  order: 2;
  margin-top: -3px;
}

.css-ae5x66 .product-info .product-price .dimmed-price {
  font-size: 14px;
  line-height: 20px;
  text-decoration: line-through;
}

.css-ae5x66 .product-info .product-price .dimmed-price {
  display: block;
  padding-top: 2px;
  color: rgb(181, 181, 181);
  font-size: 14px;
  font-weight: 400;
  line-height: 18px;
  text-decoration: line-through;
}

.css-105zgxh {
  color: rgb(34, 34, 34);
  white-space: nowrap;
}

.css-ae5x66 .product-info .product-price.discount-price > div:nth-of-type(2) {
  padding-right: 1px;
  order: 1;
}

.css-ae5x66 .product-info .product-price .discount-rate {
  font-size: 20px;
  line-height: 30px;
}

.css-ae5x66 .product-info .product-price .discount-rate {
  margin-right: 7px;
}

.css-ae5x66 .product-info .product-price .discount-rate,
.css-ae5x66 .product-info .product-price .sales-price {
  font-size: 16px;
  font-weight: 700;
  line-height: 1.5;
  white-space: nowrap;
}

.css-19lkxd2 {
  color: rgb(250, 98, 47);
}

.css-ae5x66 .product-info .product-price .sales-price {
  padding-right: 6px;
  font-size: 20px;
  line-height: 30px;
}

.css-ae5x66.deal-size-2 .review-count {
  padding-top: 7px;
}

.css-ae5x66 .product-info .review-count {
  padding-top: 8px;
  font-size: 14px;
  line-height: 20px;
}

.css-klq8ea {
  display: flex;
  color: rgb(167, 178, 188);
}

.css-ae5x66 .product-info .review-count .review-icon {
  width: 17px;
  height: 17px;
  margin: 1px 2px 0px 0px;
}

.css-15onb60 {
  padding-left: 2px;
  font-weight: 400;
}

.css-we1gxf .time-units span:not(:last-of-type)::after {
  content: ":";
  position: absolute;
  width: 11px;
  height: 50px;
  top: 0px;
  right: -11px;
  font-size: 25px;
}
</style>
