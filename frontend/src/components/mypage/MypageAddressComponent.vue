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
            @saveDelivery="saveDelivery"
          ></AddressModalComponent>
        </div>
        <button @click="displayModal" class="css-1y56l81 e1ss94ng0">
          <img src="../../assets/plus.svg" width="16" height="16" />
          새 배송지 추가
        </button>
      </div>
    </div>
    <div
      v-if="userStore.userDetail.deliveries.length === 0"
      class="empty-notice"
    >
      배송지가 없습니다. 배송지를 추가해주세요.
    </div>
    <div class="css-ehagcz eug5r8l0">
      <ul>
        <div
          class="css-jxzkyr enjmmt32"
          v-for="(delivery, index) in userStore.userDetail.deliveries"
          :key="index"
        >
          <div class="css-1nt6ns3 enjmmt31">
            <label class="css-1xdhyk6 e1dcessg3">
              <div class="css-79hxr7 e1dcessg1">
                <img
                  :id="index"
                  :src="
                    selectedAddress == index
                      ? require('../../assets/filled-custom-radio.svg')
                      : require('../../assets/outline-custom-radio.svg')
                  "
                  width="24"
                  height="24"
                  @click="checkRadio(delivery, index)"
                />
              </div>
              <span></span>
            </label>
          </div>
          <div data-testid="address-area" class="css-upe1zs e77s2kj4">
            <div v-if="delivery.isDefault" class="css-2n86z e77s2kj1">
              기본 배송지
            </div>
            <p class="css-zone-name e77s2kj2">
              {{ delivery.name }}
            </p>
            <p class="css-zone-code e77s2kj2">[{{ delivery.postNumber }}]</p>
            <p class="css-12stxlh e77s2kj2">
              {{ delivery.address }} {{ delivery.addressDetail }}
            </p>
          </div>
          <div data-testid="update-address-button" class="css-d1hkno enjmmt30">
            <div v-if="isDisplayEditModal">
              <AddressEditModalComponent
                :oldAddress="selectedDelivery"
                @closeModal="displayEditModal"
                @saveEditedAddress="saveEditedAddress"
              />
            </div>
            <button @click="displayEditModal(delivery)">
              <img src="../../assets/pencil.svg" width="48" height="48" />
            </button>
          </div>
        </div>
      </ul>
    </div>
  </div>
  <div
    v-if="userStore.userDetail.deliveries.length !== 0"
    class="save-address-container"
  >
    <button class="save-address" @click="setDefault">기본 배송지로 설정</button>
  </div>
</template>

<script>
import AddressModalComponent from "./AddressModalComponent.vue";
import AddressEditModalComponent from "./AddressEditModalComponent.vue";
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";

export default {
  name: "MypageAddressComponent",
  components: {
    AddressModalComponent,
    AddressEditModalComponent,
  },
  computed: {
    ...mapStores(useUserStore),
  },
  mounted() {
    this.setInitialSelectedAddress();
    window.scrollTo({
            top: 100,
            left: 0,
            behavior: 'smooth'
        });
  },
  data() {
    return {
      isDisplayModal: false,
      isDisplayEditModal: false,
      selectedAddress: null,
      selectedDelivery: {
        idx: null,
        name: "",
        postNumber: "",
        address: "",
        addressDetail: "",
        isDefault: false,
      },
    };
  },
  methods: {
    displayModal() {
      this.isDisplayModal = !this.isDisplayModal;
    },
    displayEditModal(data) {
      this.isDisplayEditModal = !this.isDisplayEditModal;
      if (data != null) {
        this.selectedDelivery = data;
      }
    },
    checkRadio(delivery, index) {
      this.selectedAddress = index;
      this.selectedDelivery = delivery;
    },
    async saveDelivery(data) {
      if (await this.userStore.createDelivery(data)) {
        await this.userStore.getDeliveryList();
        this.setInitialSelectedAddress();
      }
    },
    async saveEditedAddress(data) {
      if (await this.userStore.editDelivery(data)) {
        await this.userStore.getDeliveryList();
      } else {
        alert("회원정보 수정에 실패했습니다.");
      }
    },
    setInitialSelectedAddress() {
      const defaultDeliveryIndex =
        this.userStore.userDetail.deliveries.findIndex(
          (delivery) => delivery.isDefault === true
        );

      if (defaultDeliveryIndex !== -1) {
        this.selectedAddress = defaultDeliveryIndex;
        this.selectedDelivery =
          this.userStore.userDetail.deliveries[defaultDeliveryIndex];
      }
    },
    async setDefault() {
      if (!this.selectedDelivery.isDefault) {
        if (await this.userStore.setIsDefault(this.selectedDelivery.idx)) {
          await this.userStore.getDeliveryList();
        } else {
          alert("기본배송지 설정에 실패했습니다.");
        }
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
</style>
