<template>
  <div class="css-1ykiyus">
    <div class="css-1uom1od">
      <h2 class="css-10owlr">주문서</h2>
      <div class="css-ixlb9s">
        <div class="css-zo1i6f">
          <h3 class="css-1ddzp0m">주문 상품</h3>
          <button @click="toggleContent" id="fold-button" class="css-lvqq7y">
            <img
              src="@/assets/drop-down-icon.svg"
              :class="{ rotated: isIconRotated }"
            />
          </button>
        </div>
        <div class="css-r6muhy">
          {{ boardInfo.title }}
          상품을 주문합니다.
        </div>
        <!--주문한 상품 -->
        <div
          :class="['css-jyekn0', { hidden: !isToggleContentVisible }]"
          id="toggle-content"
        >
          <ul class="css-10yu6xo">
            <p class="ldmw177b ldmw177r ldmw177j">주문 내역</p>
            <ul class="css-wtzqk0">
              <div>
                <div
                  width="100%"
                  height="var(--ldmw1716)"
                  class="css-2rblr6 e1ypu1ln0"
                ></div>
                <li
                  v-for="(item, index) in orderedProducts"
                  :key="index"
                  class="css-1wqgkv4 e17q5gas10"
                >
                  <div class="css-16mmcnu">
                    <div class="css-d3v9zr">
                      <p class="ldmw177b ldmw177r ldmw177i css-139udnv">
                        {{ item.name }}
                      </p>
                      <p class="ldmw1779 ldmw177t ldmw177i css-e712oj">
                        {{ item.description }}
                      </p>
                    </div>
                    <div class="css-1qoq5cy">
                      <p
                        class="ldmw177b ldmw177r ldmw177k css-m5oldq e17q5gas2"
                      >
                        {{ item.price.toLocaleString() }}원
                      </p>
                      <span class="css-1s0al7f e17q5gas1"
                        >{{ item.originalPrice.toLocaleString() }}원</span
                      >
                      <div
                        width="var(--ldmw1716)"
                        height="var(--ldmw17v)"
                        class="css-7kvzoe e1ypu1ln0"
                      ></div>

                      <p
                        class="_97oqoup _97oqouw _97oqou6 ldmw177b _97oqou1a _97oqouk ldmw177r _97oqou13 _97oqoud ldmw177i css-nasq0f e17q5gas4"
                      >
                        {{ item.quantity }}개
                      </p>
                    </div>
                  </div>
                </li>
              </div>
            </ul>
          </ul>
        </div>

        <div class="css-12aowi2 edbbr7c2">
          <h3 class="css-1ddzp0m edbbr7c1">주문자 정보</h3>
        </div>
        <div id="kurly-orderer-info" class="css-shoa2s e1vfdada3">
          <div class="css-yazyg9 e150alo82">
            <span class="css-ln1csn e150alo81">보내는 분</span>
            <div class="css-82a6rk e150alo80">
              <div class="css-t6o2y8 e1vfdada2">{{ ordererInfo.name }}</div>
            </div>
          </div>
          <div class="css-yazyg9">
            <span class="css-ln1csn">휴대폰</span>
            <div class="css-82a6rk">
              <div class="css-t6o2y8">{{ ordererInfo.phone }}</div>
            </div>
          </div>
          <div class="css-yazyg9">
            <span class="css-ln1csn">이메일</span>
            <div class="css-82a6rk">
              {{ ordererInfo.email }}
              <div class="css-1r0yqr6 e1vfdada1">
                <p class="css-i7dt79 e1vfdada0">
                  정보 변경은 Dealivery &gt; 개인정보 수정 메뉴에서 가능합니다.
                </p>
              </div>
            </div>
          </div>
        </div>
        <div id="shipping-container">
          <div class="css-12aowi2 edbbr7c2">
            <h3 class="css-1ddzp0m edbbr7c1">배송 정보</h3>
            <div class="css-1te1vur e1wlxftg2">
              <div
                @click="toggleDeliveryNotiBtn"
                class="delivery-notice css-1he5996 e1wlxftg1"
              >
                <span>배송지 변경 안내</span
                ><img
                  src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8dGl0bGU+Y29tbW9uX2J0bl9xdWVzdGlvbjwvdGl0bGU+CiAgICA8ZyBpZD0iY29tbW9uX2J0bl9xdWVzdGlvbiIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTkuOTkzLDMuNyBDMTEuNzM0ODYwNCwzLjcgMTMuMzExNDEzLDQuNDA1Mzc4MTIgMTQuNDUyNjI3LDUuNTQ1NDUxOTcgQzE1LjU5NDA1OTQsNi42ODU3NDQwOSAxNi4zLDguMjYwOTE3NTQgMTYuMywxMCBDMTYuMywxMS43MzkwODI1IDE1LjU5NDA1OTQsMTMuMzE0MjU1OSAxNC40NTI2MjcsMTQuNDU0NTQ4IEMxMy4zMTE0MTMsMTUuNTk0NjIxOSAxMS43MzQ4NjA0LDE2LjMgOS45OTMsMTYuMyBDOC4yNTQ5MjA3OCwxNi4zIDYuNjgyMTMzMDUsMTUuNTk0ODI0NCA1LjU0MzY3MTgsMTQuNDU1MjIzNiBDNC40MDQzNzU5NSwxMy4zMTQ3ODczIDMuNywxMS43MzkzMjc1IDMuNywxMCBDMy43LDguMjYwNjcyNSA0LjQwNDM3NTk1LDYuNjg1MjEyNzMgNS41NDM2NzE4LDUuNTQ0Nzc2NDQgQzYuNjgyMTMzMDUsNC40MDUxNzU1OSA4LjI1NDkyMDc4LDMuNyA5Ljk5MywzLjcgTDkuOTkzLDMuNyBaIiBpZD0iY29tbW9uX2ljb25fdG9vbHRpcCIgc3Ryb2tlPSIjOTk5OTk5IiBzdHJva2Utd2lkdGg9IjEuNCI+PC9wYXRoPgogICAgICAgIDxwYXRoIGQ9Ik03LjUsOC4yMTA4MTA4MSBMOC43NzAyNzAyNyw4LjIxMDgxMDgxIEM4LjgxMzUxMzUxLDcuNTUxMzUxMzUgOS4yNjIxNjIxNiw3LjEyOTcyOTczIDkuOTU5NDU5NDYsNy4xMjk3Mjk3MyBDMTAuNjQwNTQwNSw3LjEyOTcyOTczIDExLjA5NDU5NDYsNy41MzUxMzUxNCAxMS4wOTQ1OTQ2LDguMDk3Mjk3MyBDMTEuMDk0NTk0Niw4LjU4NzgzNzg0IDEwLjkwMzgyODgsOC44Njk0NDQ0NCAxMC4zNTU3NTU4LDkuMjI1MjM3NzQgTDEwLjE5NzI5NzMsOS4zMjQzMjQzMiBDOS40NTEzNTEzNSw5Ljc2MjE2MjE2IDkuMTM3ODM3ODQsMTAuMjQ4NjQ4NiA5LjE4NjQ4NjQ5LDExLjA1OTQ1OTUgTDkuMTkxODkxODksMTEuNDI3MDI3IEwxMC40NDU5NDU5LDExLjQyNzAyNyBMMTAuNDQ1OTQ1OSwxMS4xMTg5MTg5IEMxMC40NDU5NDU5LDEwLjU4Mzc4MzggMTAuNjQ1OTQ1OSwxMC4zMDgxMDgxIDExLjM1NDA1NDEsOS44OTE4OTE4OSBDMTIuMDg5MTg5Miw5LjQ1NDA1NDA1IDEyLjUsOC44NzU2NzU2OCAxMi41LDguMDQ4NjQ4NjUgQzEyLjUsNi44NTQwNTQwNSAxMS41MTA4MTA4LDYgMTAuMDI5NzI5Nyw2IEM4LjQyNDMyNDMyLDYgNy41NDMyNDMyNCw2LjkyOTcyOTczIDcuNSw4LjIxMDgxMDgxIFogTTkuODc4Mzc4MzgsMTQuMDA1NDA1NCBDOS4zMzc4Mzc4NCwxNC4wMDU0MDU0IDguOTgxMDgxMDgsMTMuNjY0ODY0OSA4Ljk4MTA4MTA4LDEzLjE0NTk0NTkgQzguOTgxMDgxMDgsMTIuNjIxNjIxNiA5LjMzNzgzNzg0LDEyLjI4MTA4MTEgOS44NzgzNzgzOCwxMi4yODEwODExIEMxMC40Mjk3Mjk3LDEyLjI4MTA4MTEgMTAuNzc1Njc1NywxMi42MjE2MjE2IDEwLjc3NTY3NTcsMTMuMTQ1OTQ1OSBDMTAuNzc1Njc1NywxMy42NjQ4NjQ5IDEwLjQyOTcyOTcsMTQuMDA1NDA1NCA5Ljg3ODM3ODM4LDE0LjAwNTQwNTQgWiIgaWQ9Ij8iIGZpbGw9IiM5OTk5OTkiIGZpbGwtcnVsZT0ibm9uemVybyI+PC9wYXRoPgogICAgPC9nPgo8L3N2Zz4="
                  alt="questionIcon"
                  class="css-1aynuae eegrh1g0"
                />
              </div>
              <div
                :class="!isDeliveryNotiVisible ? 'css-mubs8e' : 'css-zs97el'"
              >
                <span>마이페이지에서 배송지를 변경할 수 있어요.</span>
              </div>
            </div>
          </div>
          <div class="css-5d6nlw e17yjk9v4">
            <div class="css-1gshg9u e150alo82">
              <span class="css-qq9ke6 e744wfw0">*</span>
              <span class="css-ln1csn e150alo81">받는 사람</span>
              <div class="css-82a6rk e150alo80">
                <div class="css-input-container">
                  <input
                    type="text"
                    v-model="receiverName"
                    class="css-input"
                    placeholder="받는 사람 이름을 입력하세요"
                    maxlength="10"
                  />
                </div>
              </div>
            </div>

            <!--츄가아ㅏㅏㅏㅏ-->
            <div class="css-1gshg9u e150alo82">
              <span class="css-qq9ke6 e744wfw0">*</span>
              <span class="css-ln1csn e150alo81">전화번호</span>
              <div class="css-82a6rk e150alo80">
                <div class="css-input-container">
                  <input
                    type="text"
                    v-model="receiverPhoneNumber"
                    class="css-input"
                    placeholder="전화번호를 입력하세요"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="css-5d6nlw e17yjk9v4">
            <div class="css-1gshg9u e150alo82">
              <span class="css-qq9ke6 e744wfw0">*</span>
              <span class="css-ln1csn e150alo81">배송지</span>
              <div class="css-82a6rk e150alo80">
                <span class="css-3uygi7 e17yjk9v3">기본배송지</span>
                <p class="css-36j4vu e17yjk9v2">
                  {{ ordererInfo.defaultAddress }}
                </p>
                <div class="css-iqoq9n e17yjk9v0">
                  <button
                    class="css-1xky6jf e4nu7ef3"
                    type="button"
                    width="60"
                    height="30"
                    radius="3"
                  >
                    <span class="css-nytqmg e4nu7ef1">변경</span>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div></div>
        <div class="css-4c6dm7 epvroj93">
          <div class="css-134fy6p epvroj92">
            <div class="css-17t7y0p edbbr7c2">
              <h3 class="css-1ddzp0m edbbr7c1">포인트</h3>
            </div>
            <div class="css-pkhh3q e150alo82">
              <span class="css-ln1csn e150alo81">포인트</span>
              <div class="css-82a6rk e150alo80">
                <div class="css-iptwzv e1gm2j0y9">
                  <div
                    data-testid="available-total-point"
                    class="css-1az0nid e1gm2j0y8"
                  >
                    <span class="css-cp6cch e1gm2j0y4"
                      >최대 사용 가능 포인트 :
                      {{ maximumAvailablePoint.toLocaleString() }}
                      <span class="css-o5boot e1gm2j0y5">p</span></span
                    >
                    <span class="css-o5boot e1gm2j0y5"
                      >보유 포인트 :
                      {{ ordererInfo.point.toLocaleString() }} p</span
                    >
                  </div>
                </div>
                <div class="css-1s0y7rc e1gm2j0y2">
                  <div class="css-16axygr e1uzxhvi6">
                    <div height="44" class="css-t7kbxx e1uzxhvi3">
                      <input
                        data-testid="input-box"
                        name="point-usage"
                        placeholder="0"
                        type="text"
                        height="44"
                        class="css-1quw3ub e1uzxhvi2"
                        :value="usedPoint"
                        @input="onPointInput"
                        @keypress="isNumber"
                      />
                    </div>
                  </div>
                  <button
                    class="css-197i5eo e4nu7ef3"
                    type="button"
                    width="100"
                    height="44"
                    radius="3"
                    @click="useAllPoints"
                  >
                    <span class="css-nytqmg e4nu7ef1">모두사용</span>
                  </button>
                </div>

                <div class="css-1waf5ak ezr038b1"></div>
              </div>
            </div>
            <div id="payment-methods">
              <div class="css-12aowi2 edbbr7c2">
                <h3 class="css-1ddzp0m edbbr7c1">결제 수단</h3>
              </div>
              <div class="css-1gshg9u e150alo82">
                <span class="css-qq9ke6 e744wfw0">*</span>
                <span class="css-ln1csn e150alo81">결제수단 선택</span>
                <div class="css-82a6rk e150alo80">
                  <div class="css-gd125q e4nb37r1">
                    <div>
                      <div class="css-18dvwsu">
                        <button
                          @click="selectPaymentMethod('kakaopay')"
                          type="button"
                          :class="{
                            'css-1wlyg0y': selectedPaymentMethod !== 'kakaopay',
                            'css-1fecctx': selectedPaymentMethod === 'kakaopay',
                          }"
                          color="#f6e500"
                        >
                          <img src="@/assets/kakao-pay-icon.svg" />
                        </button>
                      </div>
                      <div class="css-18dvwsu">
                        <button
                          @click="selectPaymentMethod('tosspay')"
                          type="button"
                          :class="{
                            'css-1wlyg0y': selectedPaymentMethod !== 'tosspay',
                            'tosspay-selected':
                              selectedPaymentMethod === 'tosspay',
                          }"
                          color="#f6e500"
                        >
                          <img src="@/assets/logo-toss-pay.svg" />
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="css-12aowi2 edbbr7c2">
              <h3 class="css-1ddzp0m edbbr7c1">개인정보 및 결제 동의</h3>
            </div>
            <div class="css-37px7p eh5sxvr5">
              <div class="css-vtybye eh5sxvr2">
                <span class="css-0 eh5sxvr1"
                  >개인정보 수집∙이용 및 처리 동의</span
                ><button class="privacy-consent css-1q6jmiw eh5sxvr0">
                  보기
                </button>
              </div>
              <strong class="css-87vide eh5sxvr4"
                >위 내용을 확인 하였으며 결제에 동의합니다.</strong
              >
            </div>
            <p class="css-1tak4sl eh5sxvr3">
              주문완료 상태일 경우에만 주문 취소가 가능하며, 상품 미배송 시
              결제하신 수단으로 환불됩니다.
            </p>

            <div class="css-1azakc el0c5j40">
              <button
                @click="makePayment"
                :class="{
                  'css-1lha8en': selectedPaymentMethod,
                  'payment-inactive': !selectedPaymentMethod || !isFormValid,
                }"
                type="button"
                width="240"
                height="56"
                radius="3"
                :disabled="!selectedPaymentMethod || !isFormValid"
              >
                <span class="css-nytqmg e4nu7ef1"
                  >{{ totalAmount.toLocaleString() }}원 결제하기</span
                >
              </button>
            </div>
          </div>
          <div class="css-1rz7w0y epvroj91">
            <div class="payment-summary css-9i9om4 epvroj90">
              <div class="css-6hge48 edbbr7c2">
                <h3 class="css-1ddzp0m edbbr7c1">결제 금액</h3>
              </div>
              <div class="css-hdnk73 e12lli9y0">
                <div class="css-1hvttuk eahaaoi12">
                  <div class="css-1rmc3ba eahaaoi11">주문금액</div>
                  <div>
                    <span class="css-2pg1ps eahaaoi10"
                      ><span class="css-rfpchb eahaaoi3"></span
                      >{{ totalAmount.toLocaleString() }}</span
                    ><span class="css-158icaa eahaaoi8">원</span>
                  </div>
                </div>
                <div class="css-sk644d eahaaoi9">
                  <div class="css-zjik7 eahaaoi0">
                    <img src="@/assets/price-list-icon.svg" />
                    <div class="css-1rmc3ba eahaaoi11">상품금액</div>
                  </div>
                  <div>
                    <span class="css-2pg1ps eahaaoi10"
                      ><span class="css-rfpchb eahaaoi3"></span
                      >{{ originalTotalAmount.toLocaleString() }}</span
                    ><span class="css-158icaa eahaaoi8">원</span>
                  </div>
                </div>
                <div class="css-sk644d eahaaoi9">
                  <div class="css-zjik7 eahaaoi0">
                    <img src="@/assets/price-list-icon.svg" />
                    <div class="css-1rmc3ba eahaaoi11">상품할인금액</div>
                  </div>
                  <div>
                    <span class="css-2pg1ps eahaaoi10"
                      ><span class="css-rfpchb eahaaoi3">-</span
                      >{{
                        (originalTotalAmount - totalAmount).toLocaleString()
                      }}</span
                    ><span class="css-158icaa eahaaoi8">원</span>
                  </div>
                </div>
                <div class="css-1hvttuk eahaaoi12">
                  <div class="css-1rmc3ba eahaaoi11">포인트</div>
                  <div>
                    <span class="css-2pg1ps eahaaoi10"
                      ><span class="css-rfpchb eahaaoi3"></span
                      >{{ usedPoint }}</span
                    ><span class="css-158icaa eahaaoi8">원</span>
                  </div>
                </div>
                <div class="css-1hgn7mh eahaaoi7">
                  <div class="css-1rmc3ba eahaaoi11">최종결제금액</div>
                  <div>
                    <span class="css-2pg1ps eahaaoi10"
                      ><span class="css-rfpchb eahaaoi3"></span
                      >{{ totalAmount.toLocaleString() }}</span
                    ><span class="css-158icaa eahaaoi8">원</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useOrderStore } from "@/stores/useOrderStore";
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";

export default {
  name: "OrdersPage",
  data() {
    return {
      isIconRotated: false,
      isToggleContentVisible: false,
      isDeliveryNotiVisible: false,
      selectedPaymentMethod: null, // 선택된 결제 수단
      boardInfo: {},
      ordererInfo: {},
      orderedProducts: [],
      usedPoint: 0,
      maximumAvailablePoint: 0,
      receiverName: "",
      receiverPhoneNumber: "",
    };
  },
  computed: {
    ...mapStores(useOrderStore, useUserStore),
    totalAmount() {
      return (
        this.orderStore.orderInfo.orderedProducts.reduce((total, product) => {
          return total + product.price * product.quantity;
        }, 0) - this.usedPoint
      );
    },
    originalTotalAmount() {
      return this.orderStore.orderInfo.orderedProducts.reduce(
        (total, product) => {
          return total + product.originalPrice * product.quantity;
        },
        0
      );
    },
    isFormValid() {
      // 받는 사람 이름과 전화번호가 모두 입력되어야 유효한 상태로 설정
      return this.receiverName !== "" && this.receiverPhoneNumber !== "";
    },
  },
  created() {
    if (
      this.orderStore.boardInfo == null ||
      /*this.orderStore.orderedProducts == null*/
      this.orderStore.orderInfo.orderedProducts == null
    ) {
      return this.$router.push("/");
    }

    this.boardInfo = this.orderStore.boardInfo;
    this.orderedProducts = this.orderStore.orderInfo.orderedProducts; //this.orderStore.orderedProducts;

    this.ordererInfo = {
      // this.ordererInfo = this.userStore.
      name: "유송연",
      phone: "010-1111-1111",
      email: "simkids@gmail.com",
      point: 24890,
      defaultAddress:
        " 서울 동작구 상도로 지하 76 (7호선 신대방삼거리역) Beyond SW 캠프",
    };

    let tenPercentOfPrice = Math.round(this.totalAmount * (10 / 100));

    this.maximumAvailablePoint =
      this.ordererInfo.point < tenPercentOfPrice
        ? this.ordererInfo.point
        : tenPercentOfPrice;

    history.pushState(null, null, location.href);
    window.addEventListener("popstate", this.handlePopState);
  },
  beforeUnmount() {
    window.removeEventListener("popstate", this.handlePopState);
  },

  methods: {
    async handlePopState() {
      this.orderStore.cancelOrder(
        this.orderStore.orderInfo.orderIdx,
        "/",
        "주문이 취소되었습니다."
      );
    },

    toggleContent() {
      this.isIconRotated = !this.isIconRotated;
      this.isToggleContentVisible = !this.isToggleContentVisible;
    },
    toggleDeliveryNotiBtn() {
      this.isDeliveryNotiVisible = !this.isDeliveryNotiVisible;
    },
    selectPaymentMethod(method) {
      if (method == this.selectedPaymentMethod) {
        this.selectedPaymentMethod = null;
        return;
      }

      this.selectedPaymentMethod = method;
    },

    validateAll() {
      const phoneRegex = /^\d{3}-\d{4}-\d{4}$/;
      const fields = [
        {
          value: this.receiverPhoneNumber,
          message: "휴대폰 번호를 입력해주세요.",
          regex: phoneRegex,
          regexMessage: "-를 포함한 휴대폰번호 11자리를 입력해주세요.",
        },
        {
          value: this.receiverName,
          message: "받는 사람의 이름을 입력해주세요.",
        },
      ];

      for (const field of fields) {
        if (field.value.trim().length === 0) {
          alert(field.message);
          return false;
        }
        if (field.regex && !field.regex.test(field.value)) {
          alert(field.regexMessage);
          return false;
        }
      }

      return true;
    },

    async makePayment() {
      if (this.validateAll()) {
        const paymentData = {
          paymentMethod: this.selectedPaymentMethod,
          address: this.ordererInfo.defaultAddress, // 배송지 정보 가져오기
          usedPoint: this.usedPoint, // 사용한 포인트
          totalAmount: this.totalAmount, // 전체 결제금액에서 포인트 차감
          receiverName: this.receiverName, // 사용자가 입력한 받는 사람 이름
          receiverPhoneNumber: this.receiverPhoneNumber, // 사용자가 입력한 전화번호
        };

        await this.orderStore.makePayment(paymentData);
      }
    },
    isNumber(event) {
      const char = event.key;

      // 한글 입력 차단
      const isKorean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(char);
      if (isKorean) {
        event.preventDefault();
        return;
      }

      // 숫자가 아닌 문자 차단
      if (!/[0-9]/.test(char)) {
        event.preventDefault();
      }
    },
    onPointInput(event) {
      // 입력값에서 숫자가 아닌 문자를 모두 제거
      let inputValue = event.target.value.replace(/[^0-9]/g, "");

      // 0으로 시작하는 경우 제거
      if (inputValue.length > 1 && inputValue[0] === "0") {
        inputValue = inputValue.replace(/^0+/, "");
      }

      // 최대 사용 가능 포인트 초과 방지
      const availableMax = Math.min(
        this.ordererInfo.point,
        this.maximumAvailablePoint
      );
      this.usedPoint = inputValue
        ? Math.min(Number(inputValue), availableMax)
        : 0;

      // 필터링된 값을 다시 input에 반영
      event.target.value = this.usedPoint;
    },
    useAllPoints() {
      if (this.ordererInfo.point <= this.maximumAvailablePoint) {
        this.usedPoint = this.ordererInfo.point;
      } else {
        this.usedPoint = this.maximumAvailablePoint;
      }
    },
  },
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}

.rotated {
  transform: rotate(-180deg);
}

.hidden {
  display: none !important;
}

.css-1ykiyus h2 {
  font-weight: 500;
  font-size: 28px;
  line-height: 36px;
  letter-spacing: -0.5px;
}

.css-1ykiyus > div {
  padding-top: 50px;
}

.css-10owlr {
  padding-bottom: 48px;
  font-size: 30px;
  line-height: 50px;
  text-align: center;
}

.css-1uom1od {
  width: 1050px;
  padding: 60px 0px;
  margin: 0px auto;
}

.css-ixlb9s {
  letter-spacing: -0.5px;
}

.css-zo1i6f {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  border-bottom: 1px solid rgb(51, 51, 51);
}

.css-1ddzp0m {
  padding: 16px 0px;
  font-weight: 500;
  font-size: 20px;
  color: rgb(51, 51, 51);
  line-height: 29px;
}

.css-lvqq7y {
  border: 0px;
  background: none;
  outline: none;
}

.css-r6muhy {
  padding: 28px 0px;
  font-size: 16px;
  line-height: 24px;
  font-weight: 500;
  text-align: center;
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-12aowi2 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  margin-top: 75px;
  border-bottom: 1px solid rgb(51, 51, 51);
}

.css-shoa2s {
  padding: 10px 0px;
}

.css-yazyg9 {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding: 8px 0px;
}

.css-yazyg9 > span {
  line-height: 24px;
}

.css-ln1csn {
  display: inline-block;
  width: 160px;
  margin-right: 30px;
  font-weight: 500;
  font-size: 14px;
  line-height: 44px;
  color: rgb(51, 51, 51);
  letter-spacing: -0.32px;
  vertical-align: top;
}

.css-82a6rk {
  flex: 1 1 0%;
}

.css-t6o2y8 {
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  line-height: 24px;
}

.css-1r0yqr6 {
  margin-top: 10px;
}

.css-i7dt79 {
  font-size: 12px;
  line-height: 21px;
  color: rgb(102, 102, 102);
}

.css-1te1vur {
  position: relative;
  cursor: pointer;
}

.css-1he5996 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  font-size: 14px;
  color: rgb(153, 153, 153);
  font-weight: 600;
  line-height: 20px;
}

.css-mubs8e {
  /*배송지 안내 토글 꺼졌을 때*/
  position: absolute;
  right: 0px;
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(51, 51, 51);
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  color: rgb(102, 102, 102);
  white-space: nowrap;
  z-index: 3;
  visibility: hidden;
  opacity: 0;
  transition: 0.3s;
  border-radius: 3px;
  padding: 14px;
  margin-top: 11px;
}

.css-zs97el {
  /*배송지 안내 토글 켰을 때*/
  position: absolute;
  right: 0px;
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(51, 51, 51);
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  color: rgb(102, 102, 102);
  white-space: nowrap;
  z-index: 3;
  visibility: visible;
  opacity: 1;
  transition: 0.3s;
  border-radius: 3px;
  padding: 14px;
  margin-top: 11px;
}

.css-5d6nlw {
  border-bottom: 1px solid rgb(244, 244, 244);
  padding-bottom: 20px;
}

.css-1gshg9u {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding-top: 20px;
}

.css-5d6nlw > div > span:first-of-type {
  line-height: 24px;
}

.css-3uygi7 {
  display: inline-block;
  height: 22px;
  padding: 0px 8px;
  margin-bottom: 8px;
  border-radius: 11px;
  font-weight: 500;
  font-size: 12px;
  line-height: 22px;
  color: rgb(102, 102, 102);
  background-color: rgb(247, 247, 247);
  vertical-align: top;
}

.css-36j4vu {
  display: block;
  font-size: 14px;
  line-height: 24px;
  color: rgb(51, 51, 51);
}

.css-iqoq9n {
  margin-top: 20px;
}

.css-1xky6jf {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 60px;
  height: 30px;
  border-radius: 3px;
  color: rgb(51, 51, 51);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
}

.css-1xky6jf > span {
  font-size: 12px;
  line-height: 28px;
  font-weight: 500;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-1gshg9u {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding-top: 20px;
}

.css-1y0xj4c > div > span:first-of-type {
  line-height: 24px;
}

.css-f170u1 {
  color: rgb(240, 63, 64);
  font-size: 14px;
  line-height: 24px;
}

.css-q8bpgr {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 60px;
  height: 30px;
  border-radius: 3px;
  color: rgb(95, 0, 128);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(95, 0, 128);
}

.css-q8bpgr > span {
  font-size: 12px;
  line-height: 28px;
  font-weight: 500;
}

.css-17t7y0p {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  margin-top: 75px;
  border-bottom: 1px solid rgb(51, 51, 51);
  -webkit-box-pack: start;
  justify-content: flex-start;
  gap: 8px;
}

.css-1ddzp0m {
  padding: 16px 0px;
  font-weight: 500;
  font-size: 20px;
  color: rgb(51, 51, 51);
  line-height: 29px;
}

svg:not(:root) {
  overflow: hidden;
}

.css-pkhh3q {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding-top: 20px;
  border-top: 1px solid rgb(244, 244, 244);
}

.css-iptwzv {
  padding: 13px 0px 8px;
  width: 414px;
}

.css-1az0nid {
  display: flex;
  padding-bottom: 8px;
  -webkit-box-pack: justify;
  justify-content: space-between;
  line-height: 24px;
  letter-spacing: -0.5px;
  color: rgb(51, 51, 51);
}

.css-o5boot {
  font-size: 14px;
  font-weight: 400;
}

.css-cp6cch {
  font-size: 16px;
  font-weight: 500;
}

.css-o5boot {
  font-size: 14px;
  font-weight: 400;
}

.css-1jyizs5 {
  font-size: 14px;
  font-weight: 400;
  line-height: 20px;
  letter-spacing: -0.5px;
}

.css-99eyqt {
  display: flex;
  padding-bottom: 8px;
  -webkit-box-pack: justify;
  justify-content: space-between;
  color: rgb(102, 102, 102);
}

.css-5uvmcx {
  display: flex;
  position: relative;
  -webkit-box-align: center;
  align-items: center;
  flex-shrink: 0;
}

.css-1s0y7rc {
  display: flex;
  column-gap: 8px;
  -webkit-box-align: center;
  align-items: center;
}

.css-16axygr {
  width: 306px;
  padding-bottom: 0px;
}

.css-t7kbxx {
  position: relative;
  height: 44px;
}

.css-16axygr input {
  border-radius: 3px;
  font-size: 14px;
  height: 44px;
  color: rgb(204, 204, 204);
}

.css-1quw3ub {
  width: 100%;
  height: 44px;
  padding: 0px 11px 1px 15px;
  border-radius: 4px;
  border: 1px solid rgb(221, 221, 221);
  font-weight: 400;
  font-size: 16px;
  line-height: 42px;
  color: rgb(51, 51, 51) !important;
  outline: none;
  box-sizing: border-box;
}

.css-197i5eo {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 100px;
  height: 44px;
  border-radius: 3px;
  color: rgb(51, 51, 51);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
}

.css-197i5eo > span {
  font-size: 14px;
  font-weight: 500;
  border-radius: 3px;
}

.css-54a39b {
  margin-top: 10px;
  font-size: 12px;
  font-weight: 400;
  line-height: 16px;
  letter-spacing: -0.5px;
  color: rgb(102, 102, 102);
}

.css-f3vz0n {
  font-weight: 500;
}

.css-1waf5ak {
  margin-top: 10px;
  font-size: 12px;
  line-height: 18px;
  color: rgb(102, 102, 102);
}

.css-18dvwsu {
  display: flex;
  margin-top: 10px;
}

.css-1wlyg0y {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  position: relative;
  width: 300px;
  height: 48px;
  border: 1px solid rgb(226, 226, 226);
  font-size: 14px;
  font-weight: 500;
  border-radius: 3px;
}

.css-1fecctx {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  position: relative;
  width: 300px;
  height: 48px;
  border: 1px solid rgb(246, 229, 0);
  font-size: 14px;
  color: rgb(255, 255, 255);
  background: rgb(246, 229, 0);
  font-weight: 500;
  border-radius: 3px;
}

.tosspay-selected {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  position: relative;
  width: 300px;
  height: 48px;
  font-size: 14px;
  border: 1px solid #0f0f0f;
  color: rgb(255, 255, 255);
  background: #fff;
  font-weight: 500;
  border-radius: 3px;
}

.css-37px7p {
  padding: 20px 0px;
  margin-bottom: 20px;
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-vtybye {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  font-weight: 400;
  font-size: 14px;
  line-height: 19px;
}

.css-1q6jmiw {
  color: rgb(153, 153, 153);
  text-decoration: underline;
}

.privacy-consent {
  font-family: inherit;
  font-size: 100%;
  line-height: 1.15;
  margin: 0;
  border-radius: 0;
  overflow: visible;
  background-color: transparent;
  border: none;
}

.css-vtybye + strong {
  padding-top: 20px;
}

.css-87vide {
  display: block;
  font-weight: 500;
  font-size: 16px;
  line-height: 21px;
  color: rgb(51, 51, 51);
}

.css-1tak4sl {
  padding-left: 16px;
  font-size: 12px;
  line-height: 16px;
  color: rgb(102, 102, 102);
}

.css-1tak4sl::before {
  display: inline-block;
  width: 16px;
  margin-left: -16px;
  content: "※";
}

.css-1azakc {
  text-align: center;
}

.css-1lha8en {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 240px;
  height: 56px;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  background-color: rgb(95, 0, 128);
  border: 0px none;
  margin: 40px auto 30px;
  font-weight: 500;
}

.payment-inactive {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 240px;
  height: 56px;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  background-color: #6c757d;
  border: 0px none;
  margin: 40px auto 30px;
  font-weight: 500;
  cursor: not-allowed;
  opacity: 0.65;
}

/*결제 금액 aside*/

.css-4c6dm7 {
  /*결제 금액 감싸는 부모 태그*/
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
}

.css-134fy6p {
  /*결제 금액 부분 왼쪽 */
  width: 742px;
}

.css-1rz7w0y {
  position: relative;
  width: 284px;
}

.css-1gxrfiw {
  width: 284px;
  position: sticky;
  top: 60px;
}

.payment-summary {
  position: sticky;
  top: 10px;
  z-index: 100;
}

.css-6hge48 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
}

.css-1ddzp0m {
  padding: 16px 0px;
  font-weight: 500;
  font-size: 20px;
  color: rgb(51, 51, 51);
  line-height: 29px;
}

.css-hdnk73 {
  width: 100%;
  padding: 17px 16px 18px 18px;
  background: rgb(250, 250, 250);
  border: 1px solid rgb(242, 242, 242);
}

.css-1hvttuk:first-of-type {
  margin-top: 0px;
}

.css-1hvttuk {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  margin-top: 16px;
  font-size: 16px;
  color: rgb(51, 51, 51);
  padding-bottom: 4px;
}

.css-1rmc3ba {
  min-width: 96px;
}

.css-2pg1ps {
  font-size: 18px;
}

.css-rfpchb {
  display: inline-block;
  margin-right: 4px;
}

.css-158icaa {
  margin-left: 4px;
}

.css-sk644d {
  display: flex;
  margin-top: 8px;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  font-size: 14px;
  color: rgb(153, 153, 153);
}

.css-zjik7 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
}

.css-1rmc3ba {
  min-width: 96px;
}

.css-sk644d .eahaaoi10 {
  font-size: 14px;
}

.css-158icaa {
  margin-left: 4px;
}

.css-1bj5qaf {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  margin-top: 16px;
  font-size: 16px;
  color: rgb(51, 51, 51);
}

.css-2pg1ps {
  font-size: 18px;
}

.css-158icaa {
  margin-left: 4px;
}

.css-1hgn7mh {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  margin-top: 16px;
  padding: 16px 0px 1px;
  border-top: 1px solid rgb(244, 244, 244);
  font-size: 16px;
  color: rgb(76, 76, 76);
}

.css-1hgn7mh .eahaaoi11 {
  display: flex;
  flex-shrink: 0;
  font-weight: 400;
}

.css-1hgn7mh .eahaaoi10 {
  font-size: 22px;
  font-weight: bold;
}

/* 주문 상품 상세 정보 리스트*/

.css-jyekn0 {
  display: flex;
  flex-direction: column;
  gap: 16px;
  background-color: rgb(250, 250, 250);
  padding: 24px;
  letter-spacing: normal;
}

.css-10yu6xo {
  background-color: #fff;
  padding: 16px 16px 16px;
  border-radius: 12px;
  font-size: 16px;
  line-height: 22px;
}

ul {
  list-style-type: none;
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

.css-wtzqk0 {
  padding-top: 12px;
}

.css-2rblr6 {
  min-width: 100%;
  min-height: 1px;
  margin: 0 0 12px;
  background-color: #eceff3;
}

.css-1wqgkv4 {
  display: flex;
  flex-direction: row;
  margin-bottom: 16px;
}

.css-fwfvkg {
  position: relative;
  overflow: hidden;
  min-width: 56px;
  height: 72px;
  margin-right: 12px;
  background-color: rgb(245, 245, 245);
  border-radius: 8px;
}

.css-16mmcnu {
  display: flex;
  flex-direction: column;
  -webkit-box-pack: center;
  justify-content: center;
}

.css-d3v9zr {
  overflow: hidden;
}

.css-139udnv {
  max-height: 38px;
  color: #222;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-1qoq5cy {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  padding-top: 6px;
}

.css-m5oldq {
  font-weight: 700;
  color: #222;
}

.css-1s0al7f {
  margin-left: 4px;
  color: #bcc4cc;
  word-break: break-all;
  text-decoration: line-through;
  font-size: 13px;
  line-height: 18px;
}

.css-7kvzoe {
  min-width: 1px;
  min-height: 12px;
  margin: 0 6px;
  background-color: #eceff3;
}

.css-nasq0f {
  color: #565e67;
}

.css-e712oj {
  padding-top: 2px;
  color: #848f9a;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
}

.css-1wqgkv4 {
  margin-bottom: 20px;
}

.css-1wqgkv4:last-of-type {
  margin-bottom: 0px;
}

.point-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 10px;
}

.point-input-section {
  display: flex;
  align-items: center;
}

.input-wrapper {
  position: relative;
  height: 44px;
  flex: 1;
}

.point-input {
  width: 100%;
  height: 44px;
  padding: 0 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 16px;
}

.use-all-button {
  margin-left: 10px;
  height: 44px;
  padding: 0 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 3px;
  font-size: 16px;
  cursor: pointer;
}

.use-all-button:hover {
  background-color: #0056b3;
}

.point-info {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: #333;
}

.point-info span {
  margin-right: 15px;
}

.css-input-container {
  display: flex;
  align-items: center;
  height: 44px;
}

.css-input {
  width: 30%;
  height: 44px;
  padding: 0 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 16px;
  box-sizing: border-box;
}

.css-input:focus {
  outline: none;
  border-color: #007bff;
}

.css-qq9ke6 {
  color: rgb(238, 106, 123);
}
</style>
