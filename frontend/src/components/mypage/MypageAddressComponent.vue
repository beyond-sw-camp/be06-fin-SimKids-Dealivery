<template>
  <div class="css-heioij eug5r8l1">
    <div class="css-oc8mjz ed9qr673">
      <div class="css-eq7f8j ed9qr672">
        <span class="css-1vbzf1d ed9qr670"
          >배송지에 따라 상품정보 및 배송유형이 달라질 수 있습니다.</span
        >
      </div>
      <div class="css-eq7f8j ed9qr672">
        <div v-if="isDisplayModal">
          <AddressModalComponent
            @closeModal="displayModal"
            @saveAddress="saveAddress"
          ></AddressModalComponent>
        </div>
        <button @click="displayModal" class="css-1y56l81 e1ss94ng0">
          <img src="../../assets/plus.svg" width="16" height="16" />
          새 배송지 추가
        </button>
      </div>
    </div>
    <div class="css-ehagcz eug5r8l0">
      <ul>
        <div
          class="css-jxzkyr enjmmt32"
          v-for="address in addresses"
          :key="address.id"
        >
          <div class="css-1nt6ns3 enjmmt31">
            <label class="css-1xdhyk6 e1dcessg3">
              <div class="css-79hxr7 e1dcessg1">
                <img
                  :id="address.id"
                  :src="
                    selectedAddress == address.id
                      ? require('../../assets/filled-custom-radio.svg')
                      : require('../../assets/outline-custom-radio.svg')
                  "
                  width="24"
                  height="24"
                  @click="checkRadio(address.id)"
                />
              </div>
              <span></span>
            </label>
          </div>
          <div data-testid="address-area" class="css-upe1zs e77s2kj4">
            <p class="css-zone-name e77s2kj2">
              {{ address.name }}
            </p>
            <p class="css-zone-code e77s2kj2">[{{ address.zonecode }}]</p>
            <p class="css-12stxlh e77s2kj2">
              {{ address.area }} {{ address.detail }}
            </p>
          </div>
          <div data-testid="update-address-button" class="css-d1hkno enjmmt30">
            <div v-if="isDisplayEditModal">
              <AddressEditModalComponent
                :oldAddress="currentAddress"
                @closeModal="displayEditModal"
                @saveNewAddress="saveNewAddress"
              />
            </div>
            <button @click="displayEditModal(address)">
              <img src="../../assets/pencil.svg" width="48" height="48" />
            </button>
          </div>
        </div>
      </ul>
    </div>
  </div>
  <div class="save-address-container">
    <button class="save-address">저장하기</button>
  </div>
</template>

<script>
import AddressModalComponent from "./AddressModalComponent.vue";
import AddressEditModalComponent from "./AddressEditModalComponent.vue";

export default {
  name: "MypageAddressComponent",
  components: {
    AddressModalComponent,
    AddressEditModalComponent,
  },
  data() {
    return {
      isDisplayModal: false,
      isDisplayEditModal: false,
      selectedAddress: null,
      currentAddress: {
        id: "1",
        name: "집",
        zonecode: "우편번호1",
        area: "서울 동작구 신대방동",
        detail: "심키즈 하우스",
      },
      addresses: [
        {
          id: "1",
          name: "집",
          zonecode: "우편번호1",
          area: "서울 동작구 신대방동",
          detail: "심키즈 하우스",
        },
        {
          id: "2",
          name: "직장",
          zonecode: "우편번호2",
          area: "서울 광진구 자양동 648-28",
          detail: "1004호",
        },
      ],
    };
  },
  methods: {
    displayModal() {
      this.isDisplayModal = !this.isDisplayModal;
    },
    displayEditModal(data) {
      this.isDisplayEditModal = !this.isDisplayEditModal;
      this.currentAddress = data;
    },
    checkRadio(id) {
      this.selectedAddress = id;
    },
    saveAddress(data) {
      this.addresses.push(data);
    },
    saveNewAddress(data) {
      const index = this.addresses.findIndex(
        (address) => address.id === data.id
      );

      if (index !== -1) {
        this.addresses[index] = data;
      } else {
        this.addresses.push(data);
      }
    },
  },
};
</script>

<style scoped>
.css-1ozh7ew.pc .menu-link > img {
  margin: 0px 8px 0px 0px;
}

.css-1y56l81 > img {
  margin-right: 6px;
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

.css-1vbzf1d {
  padding-left: 8px;
  font-size: 13px;
  line-height: 20px;
  letter-spacing: -0.2px;
  color: rgb(153, 153, 153);
}

.css-1y56l81 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  height: 24px;
  font-weight: 500;
  font-size: 16px;
  line-height: 24px;
  letter-spacing: -0.4px;
}

.css-ehagcz {
  padding: 0px 20px;
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
  color: #a0a0a0;
  margin: 5px;
}
</style>
