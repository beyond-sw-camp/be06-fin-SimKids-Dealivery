<template>
  <section class="css-1ua1wyk">
    <div class="deal">
      <p class="buy">이벤트 기간</p>
      <p class="time">
        {{ getDate(data.startedAt) }} ~ {{ getDate(data.endedAt) }}
      </p>
    </div>
    <div class="css-1qy9c46 ezpe9l12">
      <h1 class="css-13lg2xu ezpe9l11">
        {{ data.title }}
      </h1>
      <!-- <h2 class="css-1q0tnnd ezpe9l10">큰 일교차를 견대고 열매를 맺은</h2> -->
    </div>
    <h2 class="css-abwjr2 e1q8tigr4">
      <span class="css-5nirzt e1q8tigr3">{{ data.discountRate }}%</span
      ><span class="css-9pf1ze e1q8tigr2">{{
        (this.data.price * (1 - this.data.discountRate / 100)).toLocaleString()
      }}</span
      ><span class="css-1x9cx9j e1q8tigr1">원</span>
    </h2>
    <span class="css-1e1rd4p e1q8tigr0"
      ><span>{{ data.price.toLocaleString() }}원</span></span
    >
    <ul class="css-iqoq9n e6qx2kx2">
      <dl class="css-e6zlnr epzddad2">
        <dt class="css-lytdfk epzddad1">판매자</dt>
        <dd class="css-1k8t52o epzddad0">
          <p class="css-c02hqi e6qx2kx1">{{ data.companyName }}</p>
        </dd>
      </dl>
      <dl class="css-e6zlnr epzddad2">
        <dt class="css-lytdfk epzddad1">카테고리</dt>
        <dd class="css-1k8t52o epzddad0">
          <p class="css-c02hqi e6qx2kx1">{{ data.category }}</p>
        </dd>
      </dl>
    </ul>
    <!-- 판매 상품 옵션 -->
    <div class="css-2lvxh7 e1qy0s5w0">
      <dl class="css-e6zlnr epzddad2">
        <dt class="css-159o541 epzddad1">상품 선택</dt>
        <dd class="css-1k8t52o epzddad0">
          <div class="css-183fdlo e1aw4qzr0">
            <div>
              <div class="MuiFormControl-root css-tzsjye">
                <div
                  variant="outlined"
                  class="MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl jss1 css-xald09"
                >
                  <div
                    @click="toggleDropdown"
                    tabindex="0"
                    role="button"
                    aria-expanded="false"
                    aria-haspopup="listbox"
                    aria-label="Without label"
                    class="MuiSelect-select MuiSelect-outlined MuiOutlinedInput-input MuiInputBase-input css-qiwgdb"
                  >
                    상품을 선택해주세요
                  </div>
                  <input
                    aria-hidden="true"
                    tabindex="-1"
                    class="MuiSelect-nativeInput css-1k3x8v3"
                    value=""
                  />
                  <svg
                    class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiSelect-icon MuiSelect-iconOutlined css-1636szt"
                    focusable="false"
                    aria-hidden="true"
                    viewBox="0 0 24 24"
                    data-testid="ArrowDropDownIcon"
                  >
                    <path d="M7 10l5 5 5-5z"></path>
                  </svg>
                  <fieldset
                    aria-hidden="true"
                    class="MuiOutlinedInput-notchedOutline css-igs3ac"
                  >
                    <legend class="css-nnbavb">
                      <span class="notranslate"></span>
                    </legend>
                  </fieldset>
                </div>
              </div>

              <!--상품 옵션 리스트 -->
              <div
                v-show="isDropdownOpen"
                class="MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiMenu-paper MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation8 MuiPopover-paper css-185tsxr"
                tabindex="-1"
                style="
                  opacity: 1;
                  transform: none;
                  min-width: 432px;
                  height: fit-content;
                  padding: 0px;
                  box-shadow: none;
                  border-radius: 0px;
                  border: 1px solid rgb(221, 221, 221);
                  font-family: 'Noto Sans KR';
                  transition: opacity 290ms cubic-bezier(0.4, 0, 0.2, 1),
                    transform 193ms cubic-bezier(0.4, 0, 0.2, 1);
                  top: 182px;
                  left: 1112px;
                  transform-origin: 216px 0px;
                "
              >
                <ul
                  class="MuiList-root MuiList-padding MuiMenu-list css-r8u8y9"
                  role="listbox"
                  tabindex="-1"
                >
                  <li
                    v-for="option in this.data.products"
                    :key="option.idx"
                    @click="selectOption(option)"
                    class="MuiMenuItem-root MuiMenuItem-gutters MuiButtonBase-root css-1p79lf5"
                    tabindex="-1"
                    role="option"
                    aria-selected="false"
                  >
                    <div class="css-15h3zi e12wapb67">
                      <div class="css-unrh3l e12wapb66">
                        <p class="css-1k8t52o e12wapb65">
                          <span class="css-19h9nlb e12wapb64">
                            {{ option.name }}</span
                          >
                        </p>
                        <div class="css-t4macj e12wapb62">
                          <div class="css-1fvrsoi e12wapb60">
                            {{
                              (
                                option.price *
                                (1 - this.data.discountRate / 100)
                              ).toLocaleString()
                            }}원
                          </div>
                          <span class="css-1s0al7f e17q5gas1"
                            >{{ option.price.toLocaleString() }}원</span
                          >
                        </div>
                      </div>
                    </div>
                    <span class="MuiTouchRipple-root css-w0pj6f"></span>
                  </li>
                </ul>
              </div>
            </div>

            <!--추가된 상품 목록 리스트 : 수량 처리가능한 리스트-->
            <div class="cart-items">
              <div
                v-for="(item, index) in cartItems"
                :key="index"
                class="cart-option-item css-1cb5lnc e1bjklo18"
              >
                <div class="css-1qdyvok e1bjklo16">
                  <span class="css-1yojl0t e1bjklo14">{{ item.name }}</span>
                  <button
                    class="css-rrel8y e1ad0u7r0"
                    @click="removeItem(index)"
                  >
                    <img
                      src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8ZGVmcz4KICAgICAgICA8cGF0aCBkPSJNMTAuOTEgMTAuOTEgMCAwIiBpZD0iMDMxNHo5c3Z0YSIvPgogICAgICAgIDxwYXRoIGQ9Ik0wIDEwLjkxIDEwLjkxIDAiIGlkPSJ6cjgxbTJkYXZiIi8+CiAgICA8L2RlZnM+CiAgICA8ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCI+CiAgICAgICAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoNi41NDUgNi41NDUpIj4KICAgICAgICAgICAgPHVzZSBzdHJva2U9IiM2NjYiIHN0cm9rZS13aWR0aD0iLjU0NSIgeGxpbms6aHJlZj0iIzAzMTR6OXN2dGEiLz4KICAgICAgICAgICAgPHVzZSBzdHJva2U9IiM5OTkiIHN0cm9rZS13aWR0aD0iMS4wOTEiIHhsaW5rOmhyZWY9IiMwMzE0ejlzdnRhIi8+CiAgICAgICAgPC9nPgogICAgICAgIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKDYuNTQ1IDYuNTQ1KSI+CiAgICAgICAgICAgIDx1c2Ugc3Ryb2tlPSIjNjY2IiBzdHJva2Utd2lkdGg9Ii41NDUiIHhsaW5rOmhyZWY9IiN6cjgxbTJkYXZiIi8+CiAgICAgICAgICAgIDx1c2Ugc3Ryb2tlPSIjOTk5IiBzdHJva2Utd2lkdGg9IjEuMDkxIiB4bGluazpocmVmPSIjenI4MW0yZGF2YiIvPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+Cg=="
                      alt="삭제 버튼"
                    />
                  </button>
                </div>
                <div class="css-tk6lxo e1bjklo15">
                  <div class="css-nx0orh e1cqr3m40">
                    <button
                      type="button"
                      aria-label="수량내리기"
                      class="css-8azp8 e1hx75jb0"
                      @click="decreaseQuantity(index)"
                      :disabled="item.quantity <= 1"
                    ></button>
                    <div class="count css-6m57y0 e1cqr3m41">
                      {{ item.quantity }}
                    </div>
                    <button
                      type="button"
                      aria-label="수량올리기"
                      class="css-18y6jr4 e1hx75jb0"
                      @click="increaseQuantity(index)"
                    ></button>
                  </div>
                  <div class="css-1jzvrpg e1bjklo12">
                    <span class="css-gqkxk8 e1bjklo10"
                      >{{ item.price.toLocaleString() }}원</span
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </dd>
      </dl>
    </div>

    <div class="css-9y0nwt e17iylht0">
      <div class="css-ixlb9s eebc7rx4">
        <div class="css-yhijln eebc7rx3">
          <span class="css-w1is7v eebc7rx2">총 상품금액 :</span
          ><span class="css-x4cdgl eebc7rx1">{{
            totalPrice.toLocaleString()
          }}</span
          ><span class="css-1jb8hmu eebc7rx0">원</span>
        </div>
      </div>
    </div>
    <div class="css-gnxbjx e10vtr1i2">
      <button
        class="css-3z91zj e4nu7ef3"
        type="button"
        width="56"
        height="56"
        radius="3"
        @click="toggleHeart"
      >
        <span class="css-nytqmg e4nu7ef1">
          <img :src="heartImage" alt="Heart" />
        </span>
      </button>

      <button
        @click="emitSubmitOrder"
        :class="
          isBeforeOpenTime
            ? 'cart-button css-1qirdbn-disabled'
            : 'cart-button css-1qirdbn'
        "
        type="button"
        radius="3"
        :disabled="isBeforeOpenTime"
      >
        <span
          :class="
            isBeforeOpenTime ? 'css-nytqmg-disabled' : 'css-nytqmg e4nu7ef1'
          "
          >{{ isBeforeOpenTime ? "오픈 시간이 아닙니다" : "구매하기" }}</span
        >
      </button>
    </div>
  </section>
</template>

<script>
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";
export default {
  name: "BoardDetailProductInfoComponent",
  props: {
    data: {
      type: Object,
    },
    productBoardIdx: {},
  },
  data() {
    return {
      getDate(dateTime) {
        dateTime = new Date(dateTime);
        const year = dateTime.getFullYear();
        const month = String(dateTime.getMonth() + 1).padStart(2, "0");
        const day = String(dateTime.getDate()).padStart(2, "0");
        const hours = String(dateTime.getHours()).padStart(2, "0");
        const minutes = String(dateTime.getMinutes()).padStart(2, "0");
        return `${year}.${month}.${day}. ${hours}:${minutes}`;
      },
      isDropdownOpen: false,
      productOptions: [
        {
          idx: "1",
          label: "",
          originalPrice: 0,
        },
      ],
      cartItems: [],
      isHeartFilled: false,
      heartImage:
        "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzIiIGhlaWdodD0iMzIiIHZpZXdCb3g9IjAgMCAzMiAzMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yNS44MDcgNy44NjNhNS43NzcgNS43NzcgMCAwIDAtOC4xNzIgMEwxNiA5LjQ5N2wtMS42MzUtMS42MzRhNS43NzkgNS43NzkgMCAxIDAtOC4xNzMgOC4xNzJsMS42MzQgMS42MzQgNy40NjYgNy40NjdhMSAxIDAgMCAwIDEuNDE1IDBzMCAwIDAgMGw3LjQ2Ni03LjQ2N2gwbDEuNjM0LTEuNjM0YTUuNzc3IDUuNzc3IDAgMCAwIDAtOC4xNzJ6IiBzdHJva2U9IiM1RjAwODAiIHN0cm9rZS13aWR0aD0iMS42IiBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIvPgo8L3N2Zz4K",
      filledHeartImage:
        "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzIiIGhlaWdodD0iMzIiIHZpZXdCb3g9IjAgMCAzMiAzMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yNS44MDcgNy44NjNhNS43NzcgNS43NzcgMCAwIDAtOC4xNzIgMEwxNiA5LjQ5N2wtMS42MzUtMS42MzRhNS43NzkgNS43NzkgMCAxIDAtOC4xNzMgOC4xNzJsMS42MzQgMS42MzQgNy40NjYgNy40NjdhMSAxIDAgMCAwIDEuNDE1IDBzMCAwIDAgMGw3LjQ2Ni03LjQ2N2gwbDEuNjM0LTEuNjM0YTUuNzc3IDUuNzc3IDAgMCAwIDAtOC4xNzJ6IiBmaWxsPSIjRkY1QTVBIiBzdHJva2U9IiNGRjVBNUEiIHN0cm9rZS13aWR0aD0iMS42IiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIvPgo8L3N2Zz4K",
      emptyHeartImage:
        "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzIiIGhlaWdodD0iMzIiIHZpZXdCb3g9IjAgMCAzMiAzMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yNS44MDcgNy44NjNhNS43NzcgNS43NzcgMCAwIDAtOC4xNzIgMEwxNiA5LjQ5N2wtMS42MzUtMS42MzRhNS43NzkgNS43NzkgMCAxIDAtOC4xNzMgOC4xNzJsMS42MzQgMS42MzQgNy40NjYgNy40NjdhMSAxIDAgMCAwIDEuNDE1IDBzMCAwIDAgMGw3LjQ2Ni03LjQ2N2gwbDEuNjM0LTEuNjM0YTUuNzc3IDUuNzc3IDAgMCAwIDAtOC4xNzJ6IiBzdHJva2U9IiM1RjAwODAiIHN0cm9rZS13aWR0aD0iMS42IiBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIvPgo8L3N2Zz4K",
      request: {
        productBoardIdx: null,
      },
    };
  },
  created() {
    this.isHeartFilled = this.data.likes;
    if (this.isHeartFilled) {
      this.heartImage = this.filledHeartImage;
    }
  },
  computed: {
    isBeforeOpenTime() {
      return Date.now() < new Date(this.data.startedAt).getTime();
    },
    totalPrice() {
      return this.cartItems.reduce(
        (total, item) => total + item.price * item.quantity,
        0
      );
    },
    isAnySelected() {
      return this.cartItems.length > 0;
    },
    ...mapStores(useUserStore),
  },
  watch: {
    data: {
      immediate: true,
      handler(newValue) {
        if (newValue) {
          this.mapProductsToOptions();
        }
      },
    },
  },
  methods: {
    mapProductsToOptions() {
      this.productOptions = this.data.products.map((product) => ({
        idx: String(product.idx), // idx를 문자열로 변환
        label: product.name, // 이름 앞에 '[99치킨]' 추가
        originalPrice: product.price,
      }));
    },
    toggleDropdown() {
      this.isDropdownOpen = !this.isDropdownOpen;
    },

    selectOption(option) {
      const existingItem = this.cartItems.find(
        (item) => item.idx === option.idx
      );

      if (existingItem) {
        existingItem.quantity++;
      } else {
        this.cartItems.push({
          idx: option.idx,
          name: option.name,
          price: option.price * (1 - this.data.discountRate / 100),
          originalPrice: option.price,
          quantity: 1,
        });
      }

      this.isDropdownOpen = false;
    },

    increaseQuantity(index) {
      this.cartItems[index].quantity++;
    },

    decreaseQuantity(index) {
      if (this.cartItems[index].quantity > 1) {
        this.cartItems[index].quantity--;
      }
    },

    removeItem(index) {
      this.cartItems.splice(index, 1);
    },

    async toggleHeart() {
      this.request.productBoardIdx = this.productBoardIdx;
      if (!this.userStore.isLogined) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({
          path: "/auth/login",
          query: { redirect: this.$route.fullPath },
        });
        return;
      }
      if (await this.userStore.like(this.request)) {
        this.isHeartFilled = !this.isHeartFilled;
        this.heartImage = this.isHeartFilled
          ? this.filledHeartImage
          : this.emptyHeartImage;
      } else {
        alert("관심 등록에 실패했습니다.");
      }
    },

    emitSubmitOrder() {
      if (!this.userStore.isLogined) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({
          path: "/auth/login",
          query: { redirect: this.$route.fullPath },
        });
        return;
      }
      if (!this.isAnySelected) {
        alert("상품을 선택하세요.");
        return;
      }

      let orderRequest = {
        boardInfo: {
          idx: this.$route.params.idx,
          title: this.data.title,
          discountRate: this.data.discountRate,
        },
        cartItems: this.cartItems,
      };

      this.$emit("submitOrder", orderRequest);
    },
  },
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}

button {
  cursor: pointer;
}

/*상품 설명 (오른쪽 section)*/
.css-1ua1wyk {
  width: 560px;
}

.deal {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  overflow: hidden;
  position: relative;
  height: 50px;
  margin-bottom: 16px;
  background-color: rgba(189, 118, 255, 0.08);
  padding: 0 10px;
}

.deal p {
  margin: 0;
}

p.buy {
  font-size: 16px;
  color: #5f0080;
  font-weight: bold;
}

.deal p.buy:after {
  display: inline-block;
  width: 1px;
  height: 15px;
  margin-left: 9px;
  background: #5f0080;
  vertical-align: middle;
  content: "";
}

.deal p.time {
  margin-left: 20px;
}

.deal p.time:before {
  display: inline-block;
  width: 13px;
  height: 13px;
  margin-right: 12px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-product.png) -330px -30px
    no-repeat;
  vertical-align: middle;
  content: "";
}

.css-1qy9c46 {
  position: relative;
  display: flex;
  flex-direction: column;
  overflow-wrap: break-word;
}

.css-13lg2xu {
  width: 500px;
  font-weight: 500;
  font-size: 24px;
  color: rgb(51, 51, 51);
  line-height: 34px;
  letter-spacing: -0.5px;
  margin: 0px 20px 0px 0px;
}

.css-1q0tnnd {
  width: 500px;
  font-size: 14px;
  font-weight: 400;
  color: rgb(181, 181, 181);
  line-height: 19px;
  letter-spacing: -0.5px;
  margin-top: 4px;
}

.css-abwjr2 {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  padding-top: 20px;
  font-weight: bold;
  line-height: 30px;
  letter-spacing: -0.5px;
}

.css-5nirzt {
  padding-right: 9px;
  font-size: 28px;
  color: rgb(250, 98, 47);
}

.css-9pf1ze {
  padding-right: 4px;
  font-size: 28px;
  color: rgb(51, 51, 51);
}

.css-1x9cx9j {
  display: inline-block;
  position: relative;
  top: 3px;
  font-size: 18px;
  color: rgb(51, 51, 51);
  vertical-align: top;
}

.css-1e1rd4p {
  display: flex;
  flex-direction: row;
  margin-top: 8px;
}

.css-1e1rd4p > span {
  font-size: 16px;
  color: rgb(181, 181, 181);
  letter-spacing: -0.5px;
  text-decoration: line-through;
  margin-right: 1px;
}

.css-iqoq9n {
  margin-top: 20px;
}

.css-e6zlnr {
  display: flex;
  flex: 1 1 0%;
  flex-direction: row;
  align-items: flex-start;
  overflow: hidden;
  width: 100%;
  padding: 17px 0px 18px;
  border-top: 1px solid rgb(244, 244, 244);
  font-size: 14px;
  letter-spacing: -0.5px;
}

.css-1k8t52o {
  display: flex;
  flex: 1 1 0%;
  flex-direction: column;
}

.css-c02hqi {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.css-lytdfk {
  width: 128px;
  height: 100%;
  color: rgb(102, 102, 102);
  font-weight: 400;
  line-height: 19px;
}

/*상품 선택 옵션*/

.css-2lvxh7 {
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-159o541 {
  width: 128px;
  height: 100%;
  color: rgb(102, 102, 102);
  font-weight: 400;
  line-height: 40px;
}

.css-tzsjye {
  display: inline-flex;
  flex-direction: column;
  position: relative;
  min-width: 0px;
  padding: 0px;
  margin: 0px;
  border: 0px;
  vertical-align: top;
  width: 100%;
}

.css-183fdlo .MuiOutlinedInput-root {
  height: 40px;
}

.jss1 {
  font-family: Noto Sans KR;
}

.css-xald09 {
  font-family: Roboto, Helvetica, Arial, sans-serif;
  font-weight: 400;
  line-height: 1.4375em;
  letter-spacing: 0.00938em;
  color: rgba(0, 0, 0, 0.87);
  box-sizing: border-box;
  cursor: text;
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
  font-size: 12px;
  height: 44px;
  border-color: rgb(221, 221, 221);
  border-radius: 0px;
}

.css-qiwgdb.css-qiwgdb.css-qiwgdb {
  padding-right: 32px;
}

.css-qiwgdb.MuiSelect-select {
  height: auto;
  min-height: 1.4375em;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.css-qiwgdb {
  appearance: none;
  user-select: none;
  border-radius: 4px;
  cursor: pointer;
  font: inherit;
  letter-spacing: inherit;
  color: currentcolor;
  border: 0px;
  box-sizing: content-box;
  background: none;
  height: 1.4375em;
  margin: 0px;
  -webkit-tap-highlight-color: transparent;
  display: block;
  min-width: 0px;
  width: 100%;
  animation-name: mui-auto-fill-cancel;
  animation-duration: 10ms;
  padding: 16.5px 14px;
}

.css-1k3x8v3 {
  bottom: 0px;
  left: 0px;
  position: absolute;
  opacity: 0;
  pointer-events: none;
  width: 100%;
  box-sizing: border-box;
}

svg:not(:root) {
  overflow: hidden;
}

.css-1636szt {
  user-select: none;
  width: 1em;
  height: 1em;
  display: inline-block;
  fill: currentcolor;
  flex-shrink: 0;
  font-size: 1.5rem;
  position: absolute;
  right: 7px;
  top: calc(50% - 0.5em);
  color: rgba(0, 0, 0, 0.54);
  transition: fill 200ms cubic-bezier(0.4, 0, 0.2, 1);
}

.jss1 .MuiOutlinedInput-notchedOutline {
  border-color: #ddd !important;
}

.css-igs3ac {
  text-align: left;
  position: absolute;
  inset: -5px 0px 0px;
  margin: 0px;
  padding: 0px 8px;
  pointer-events: none;
  border-radius: inherit;
  border-style: solid;
  border-width: 1px;
  overflow: hidden;
  min-width: 0%;
  border-color: rgba(0, 0, 0, 0.23);
}

fieldset {
  margin: 0;
  padding: 0;
}

.css-nnbavb {
  float: unset;
  line-height: 11px;
  padding: 0px;
  transition: width 150ms cubic-bezier(0, 0, 0.2, 1);
}

legend {
  box-sizing: border-box;
  color: inherit;
  display: table;
  max-width: 100%;
  padding: 0;
  white-space: normal;
}

legend {
  border: 0;
  vertical-align: top;
}

/*상품 가격 정보*/

.css-9y0nwt {
  padding-top: 30px;
}

.css-ixlb9s {
  letter-spacing: -0.5px;
}

.css-yhijln {
  display: flex;
  -webkit-box-pack: end;
  justify-content: flex-end;
  align-items: flex-end;
}

.css-w1is7v {
  padding-right: 12px;
  font-size: 13px;
  font-weight: 500;
  color: rgb(51, 51, 51);
  line-height: 20px;
}

.css-x4cdgl {
  font-weight: bold;
  font-size: 32px;
  color: rgb(51, 51, 51);
  line-height: 36px;
}

.css-1jb8hmu {
  padding-left: 5px;
  font-size: 20px;
  font-weight: 600;
  color: rgb(51, 51, 51);
  line-height: 30px;
}

.css-gnxbjx {
  display: flex;
  gap: 8px;
  margin-top: 40px;
  justify-content: flex-end;
  align-items: flex-end;
}

.css-3z91zj {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 56px;
  height: 56px;
  border-radius: 3px;
  color: rgb(51, 51, 51);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
  cursor: pointer;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
}

.css-nytqmg-disabled {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
  cursor: not-allowed;
}

.css-1qirdbn {
  font-weight: 500;
  display: block;
  padding: 0 10px;
  text-align: center;
  overflow: hidden;
  width: 60%;
  height: 52px;
  border-radius: 3px;
  color: #fff;
  background-color: #5f0080;
  border: 0 none;
}

.css-1qirdbn-disabled {
  cursor: not-allowed;
  font-weight: 500;
  display: block;
  padding: 0 10px;
  text-align: center;
  overflow: hidden;
  width: 60%;
  height: 52px;
  border-radius: 3px;
  color: #fff;
  background-color: #dddddd;
  border: 0 none;
}

.order-inactive {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 60%;
  height: 52px;
  border-radius: 3px;
  color: #fff;
  background-color: #6c757d;
  border: 0 none;
  font-weight: 500;
  cursor: not-allowed;
  opacity: 0.65;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

/*상품 세부 리스트*/
.css-185tsxr {
  /*리스트 박스*/
  background-color: rgb(255, 255, 255);
  color: rgba(0, 0, 0, 0.87);
  box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 5px -3px,
    rgba(0, 0, 0, 0.14) 0px 8px 10px 1px, rgba(0, 0, 0, 0.12) 0px 3px 14px 2px;
  overflow-y: auto;
  overflow-x: hidden;
  min-width: 16px;
  min-height: 16px;
  max-width: calc(100% - 32px);
  max-height: calc(100% - 32px);
  transition: box-shadow 300ms cubic-bezier(0.4, 0, 0.2, 1);
  border-radius: 4px;
  outline: 0px;
}

.css-185tsxr ul {
  padding: 0px;
}

.css-r8u8y9 {
  list-style: none;
  margin: 0px;
  padding: 8px 0px;
  position: relative;
  outline: 0px;
}

.css-185tsxr ul li.MuiMenuItem-root:not(:last-of-type) {
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-185tsxr ul > li {
  font-family: "Noto Sans KR";
}

@media (min-width: 600px) {
  .css-1p79lf5 {
    min-height: auto;
  }
}

.css-1p79lf5 {
  -webkit-tap-highlight-color: transparent;
  background-color: transparent;
  outline: 0px;
  border: 0px;
  margin: 0px;
  border-radius: 0px;
  user-select: none;
  vertical-align: middle;
  appearance: none;
  font-family: Roboto, Helvetica, Arial, sans-serif;
  font-weight: 400;
  line-height: 1.5;
  letter-spacing: 0.00938em;
  display: flex;
  -webkit-box-pack: start;
  justify-content: flex-start;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
  text-decoration: none;
  min-height: 48px;
  box-sizing: border-box;
  font-size: 12px;
  padding: 0px;
  color: rgb(51, 51, 51);
  white-space: normal;
  cursor: pointer;
}

.css-15h3zi {
  width: 100%;
  padding: 14px 16px 12px 15px;
}

.css-185tsxr ul li.MuiMenuItem-root:not(:last-of-type) {
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-unrh3l {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  width: 100%;
}

.css-19h9nlb {
  width: 240px;
  line-height: 16px;
  font-size: 12px;
  letter-spacing: -0.5px;
  overflow-wrap: break-word;
}

.css-t4macj {
  display: flex;
  flex-shrink: 0;
  gap: 4px;
}

.css-1fvrsoi {
  font-size: 12px;
  font-weight: bold;
  letter-spacing: -0.5px;
  color: rgb(51, 51, 51);
}

.css-w0pj6f {
  overflow: hidden;
  pointer-events: none;
  position: absolute;
  z-index: 0;
  inset: 0px;
  border-radius: inherit;
}

/*추가된 리스트 - 수량 조절할 수 있는 */

.css-j9a02z {
  margin-top: 6px;
}

.css-1cb5lnc:last-of-type {
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-1cb5lnc {
  display: flex;
  flex-direction: column;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding: 11px 10px 11px 15px;
  font-size: 12px;
  border-left: 1px solid rgb(244, 244, 244);
  border-top: 1px solid rgb(244, 244, 244);
  border-right: 1px solid rgb(244, 244, 244);
}

.css-1qdyvok {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  align-items: flex-start;
}

.css-1yojl0t {
  line-height: 16px;
  width: 320px;
  min-height: 24px;
  color: rgb(51, 51, 51);
  overflow-wrap: break-word;
}

.css-rrel8y {
  width: 24px;
  height: 24px;
  background: transparent;
  border: none;
  cursor: pointer;
}

.css-tk6lxo {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  padding-top: 4px;
}

.css-nx0orh {
  display: inline-flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  border: 1px solid rgb(221, 223, 225);
  width: 88px;
  border-radius: 3px;
}

.css-8azp8 {
  display: inline-flex;
  width: 28px;
  height: 28px;
  border: none;
  font-size: 1px;
  color: transparent;
  background-size: cover;
  background-color: transparent;
  background-image: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAiIGhlaWdodD0iMzAiIHZpZXdCb3g9IjAgMCAzMCAzMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yMCAxNHYySDEwdi0yeiIgZmlsbD0iIzMzMyIgZmlsbC1ydWxlPSJub256ZXJvIi8+Cjwvc3ZnPgo=);
  vertical-align: top;
}

.css-6m57y0 {
  display: inline-flex;
  overflow: hidden;
  white-space: nowrap;
  -webkit-box-pack: center;
  justify-content: center;
  font-size: 14px;
  font-weight: 600;
  color: rgb(51, 51, 51);
  text-align: center;
  width: 31px;
  height: 28px;
  line-height: 28px;
}

.css-18y6jr4 {
  display: inline-flex;
  width: 28px;
  height: 28px;
  border: none;
  font-size: 1px;
  color: transparent;
  background-size: cover;
  background-color: transparent;
  background-image: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAiIGhlaWdodD0iMzAiIHZpZXdCb3g9IjAgMCAzMCAzMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0xNiAxMHY0aDR2MmgtNHY0aC0ydi00aC00di0yaDR2LTRoMnoiIGZpbGw9IiMzMzMiIGZpbGwtcnVsZT0ibm9uemVybyIvPgo8L3N2Zz4K);
  vertical-align: top;
}

.css-1jzvrpg {
  padding-top: 4px;
}

.css-gqkxk8 {
  font-weight: bold;
  font-size: 12px;
  color: rgb(51, 51, 51);
  padding-right: 4px;
}

.cart-items {
  margin-top: 20px;
}

.css-1s0al7f {
  margin-left: 4px;
  color: #bcc4cc;
  word-break: break-all;
  text-decoration: line-through;
  font-size: 13px;
  line-height: 18px;
}
</style>
