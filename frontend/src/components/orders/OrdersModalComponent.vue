<template>
  <div class="modal" @click.self="closeModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>배송지 변경</h2>
      <hr class="thick-line" />
      <div class="css-ehagcz eug5r8l0">
        <ul>
          <div
            class="css-jxzkyr enjmmt32"
            v-for="delivery in userStore.userDetail.deliveries"
            :key="delivery.idx"
          >
            <div class="css-1nt6ns3 enjmmt31">
              <label class="css-1xdhyk6 e1dcessg3">
                <div class="css-79hxr7 e1dcessg1">
                  <img
                    :id="delivery.idx"
                    :src="
                      selected.idx == delivery.idx
                        ? require('../../assets/filled-custom-radio.svg')
                        : require('../../assets/outline-custom-radio.svg')
                    "
                    width="24"
                    height="24"
                    @click="checkRadio(delivery)"
                  />
                </div>
                <span></span>
              </label>
            </div>

            <div data-testid="address-area" class="css-upe1zs e77s2kj4">
              <div v-if="delivery.isDefault" class="css-2n86z e77s2kj1">
                기본 배송지
              </div>
              <div class="delivery-wrapper">
                <p class="css-zone-name e77s2kj2">
                  {{ delivery.name }}
                </p>

                <p class="css-zone-code e77s2kj2">
                  [{{ delivery.postNumber }}]
                </p>
                <p class="css-12stxlh e77s2kj2">
                  {{ delivery.address }} {{ delivery.addressDetail }}
                </p>
              </div>
            </div>
          </div>
        </ul>
        <!-- 변경하기 버튼 -->
        <div class="save-address-container">
          <button class="save-address" @click="confirmSelection">
            변경하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";

export default {
  name: "OrdersModalComponent",
  props: {
    preSelected: {
      type: Object,
      required: true,
    },
  },
  computed: {
    ...mapStores(useUserStore),
  },
  created() {
    this.selected = this.preSelected;
  },
  data() {
    return {
      selected: null,
    };
  },

  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    checkRadio(delivery) {
      this.selected = delivery;
    },
    confirmSelection() {
      this.$emit("confirmSelection", this.selected);
      this.closeModal();
    },
  },
};
</script>

<style scoped>
.modal {
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content p {
  display: inline;
  font-size: 18px;
}

.modal-content h2 {
  font-size: 25px;
  margin-bottom: 20px;
}

.product-item {
  margin-bottom: 30px;
}

.total-price {
  display: block;
  text-align: right;
}

.title {
  display: block;
  text-align: left;
  font-size: 18px;
}

.contents {
  display: block;
  text-align: right;
  margin-bottom: 10px;
}

.thick-line {
  border: none;
  border-top: 2px solid #888;
  margin-bottom: 30px;
}

.thin-line {
  border: none;
  border-top: 1.5px solid #ccc;
  margin: 20px 0;
}

.modal-content {
  background-color: #fff;
  margin: 15% auto;
  padding: 40px;
  border: 1px solid #888;
  width: 80%;
  max-width: 500px;
  border-radius: 10px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

ul {
  list-style-type: none;
}

.css-jxzkyr {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  width: 100%;
  padding: 10px 0px;
  border-bottom: 1px solid rgb(244, 244, 244);
}

.css-1nt6ns3 {
  flex-basis: 36px;
}

input[type="checkbox"],
input[type="radio"] {
  box-sizing: border-box;
  padding: 0;
}

.css-agvwxo {
  overflow: hidden;
  position: absolute;
  clip: rect(0px, 0px, 0px, 0px);
  clip-path: inset(50%);
  width: 1px;
  height: 1px;
}

.css-79hxr7 {
  margin-right: 12px;
}

.css-79hxr7 img {
  cursor: pointer;
  vertical-align: middle;
}

.delivery-wrapper {
  display: flex;
  flex-direction: column; /* 세로로 정렬 */
}

.css-upe1zs {
  flex: 1 1 0%;
}

.css-12stxlh {
  font-size: 16px;
  line-height: 20px;
  color: rgb(51, 51, 51);
  word-break: break-all;
  margin-top: 5px;
}

.css-d1hkno {
  display: inline-flex;
  padding-left: 16px;
}

.css-d1hkno img {
  cursor: pointer;
}

img {
  max-width: 100%;
}

button {
  overflow: visible;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

ul {
  padding-inline-start: 10px;
}

.save-address-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 650px;
  padding: 10px;
}

.save-address {
  background: #5f0080;
  border: 1px solid #5f0080;
  border-radius: 10px;
  padding: 15px 24px;
  color: #fff;
}

.css-zone-code {
  font-size: 13px;
  color: #a0a0a0;
  margin: 5px;
}

.css-zone-name {
  font-size: 13px;
  color: #505050;
  margin: 5px;
}

.css-2n86z {
  display: inline-block;
  padding: 4px 8px;
  border-radius: 11px;
  background-color: rgb(247, 247, 247);
  color: rgb(95, 0, 128);
  font-weight: 600;
  font-size: 12px;
  text-align: center;
  margin-top: 5px;
}

.empty-notice {
  text-align: center;
  padding: 40px;
  margin-top: 20px;
  line-height: 20px;
  letter-spacing: -0.2px;
  color: rgb(153, 153, 153);
}

.save-address-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 10px;
  margin-top: 20px;
}

.save-address {
  background: #5f0080;
  border: 1px solid #5f0080;
  border-radius: 10px;
  color: #fff;
  cursor: pointer;
  font-size: 14px;
}

.save-address:hover {
  background-color: #4a0069;
}
</style>
