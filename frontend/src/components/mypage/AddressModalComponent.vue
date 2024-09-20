<template>
  <div id="productModal" class="modal" >
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <div id="__next" data-reactroot="">
        <div class="css-sv3mbe e1u1sq1y0">
          <div class="css-ixlb9s ecqulhg2">
            <div class="css-0 e11n32x25">
              <strong class="css-1702mcg e11n32x24">배송지 추가
                <p class="css-pwbln2 e11n32x22">
                  상품을 전달받을 배송지를 입력해주세요.
                </p>
              </strong>
            </div>
            <div class="css-1hxvx8x e1uzxhvi6">
              <div height="44" class="css-t7kbxx e1uzxhvi3">
                <input maxlength="30"
                v-model="delivery.name" data-testid="input-box" id="addressName" name="addressName"
                  placeholder="배송지명을 입력해 주세요" type="text" height="44" class="css-1quw3ub e1uzxhvi2" />
              </div>
            </div>
            <div class="css-19u3hc5 e1n7pxx51">
              <p class="css-ks8pmw e1n7pxx50">
                {{ delivery.address === "" ? "주소를 검색해주세요" : delivery.address }}
              </p>
              <button @click="openPostcode" class="css-1w63xsn e4nu7ef3" type="button" width="120" height="44"
                radius="3">
                <span class="css-nytqmg e4nu7ef1"><img src="https://res.kurly.com/pc/service/common/2006/ico_search.svg"
                    alt="" class="css-1m3kac1 e4nu7ef0" />검색</span>
              </button>
            </div>
            <div class="css-1hxvx8x e1uzxhvi6">
              <div height="44" class="css-t7kbxx e1uzxhvi3">
                <input maxlength="30"
                v-model="delivery.addressDetail" data-testid="input-box" id="addressDetail" name="addressDetail"
                  placeholder="상세 주소를 입력해 주세요" type="text" height="44" class="css-1quw3ub e1uzxhvi2" />
              </div>

              <div class="css-a0w530 ecqulhg1"><label class="css-1wkyvs2 e1dcessg3"><input type="checkbox"
                    class="css-agvwxo e1dcessg2">
                  <div class="css-79hxr7 e1dcessg1">
                    <img :src="delivery.isDefault
                      ? require('@/assets/filled-custom-radio.svg')
                      : require('@/assets/outline-custom-radio.svg')
                    " @click="setDefault" alt="Icon" />

                  </div><span>기본 배송지로 저장</span>
                </label></div>
            </div>
            <button @click="validateAndSave" class="css-10voksq e4nu7ef3" type="button" height="44" radius="3">
              <span class="css-nytqmg e4nu7ef1">저장</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useUserStore } from '@/stores/useUserStore';
import { mapStores } from 'pinia';
import { Validator } from '@/util/validator';
export default {
  name: "AddressModalComponent",
  data() {
    return {
      delivery: {
        name: "",
        postNumber: "",
        address: "",
        addressDetail: "",
        isDefault: false
      },
    };
  },
  computed: {
    ...mapStores(useUserStore)
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    openPostcode() {
      let width = 500;
      let height = 600;

      // eslint-disable-next-line no-undef
      new daum.Postcode({
        width: width,
        height: height,
        oncomplete: (data) => {
          this.delivery.address = data.address;
          this.delivery.postNumber = data.zonecode;
        },
      }).open({
        left: window.screen.width / 2 - width / 2,
        top: window.screen.height / 2 - height / 2,
      });
    },
    validateAndSave() {
      try {
        const validInputRegex = /^(?!.*[!@#$%^&*()_+={}:;"'<>,.?/~`|\\-])(?=.*[^\n])[^\nㄱ-ㅎ]*$/;

        // 검증 로직
        new Validator(this.delivery.name, "배송지명을 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");

        new Validator(this.delivery.postNumber, "우편번호를 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");

        new Validator(this.delivery.address, "주소를 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");

        new Validator(this.delivery.addressDetail, "상세주소를 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");
        this.saveDelivery();
      } catch (error) {
        alert(error.message);
      }
    },
    saveDelivery() {
      this.$emit("saveDelivery", this.delivery);
      this.closeModal();
    },
    setDefault(){
      this.delivery.isDefault = !this.delivery.isDefault;
    }
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
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background-color: #fefefe;
  padding: 30px 30px 80px 30px;
  border: 1px solid #888;
  width: 30%;
  max-width: 500px;
  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

button {
  background-color: #4c4c4c;
  color: white;
  padding: 14px 30px;
  margin-top: 14px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

.submitButton {
  display: block;
  text-align: center;
}

label {
  display: block;
  margin: 20px 0 7px;
  font-size: 15px;
}

input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

h2 {
  font-size: 25px;
  margin-bottom: 30px;
}

.css-sv3mbe {
  padding: 0px 30px;
}

.css-ixlb9s {
  letter-spacing: -0.5px;
}

.css-1702mcg {
  display: block;
  padding: 40px 0px 34px;
  font-size: 24px;
  font-weight: 500;
  line-height: 30px;
  color: rgb(51, 51, 51);
  text-align: center;
}

.css-pwbln2 {
  display: block;
  padding-top: 8px;
  font-size: 16px;
  font-weight: 400;
  line-height: 22px;
  color: rgb(102, 102, 102);
}

.css-19u3hc5 {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding-bottom: 10px;
}

.css-ks8pmw {
  display: inline-flex;
  overflow: hidden;
  flex: 1 1 0%;
  margin-right: 10px;
  padding: 0px 12px;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 3px;
  background-color: rgb(250, 250, 250);
  font-size: 14px;
  line-height: 42px;
  color: rgb(153, 153, 153);
  white-space: nowrap;
  text-overflow: ellipsis;
}

.css-1w63xsn {
  cursor: pointer;
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 95px;
  height: 44px;
  border-radius: 3px;
  color: rgb(95, 0, 128);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(95, 0, 128);
}

.css-19u3hc5 button>span {
  line-height: 24px;
}

.css-1w63xsn>span {
  font-size: 14px;
  font-weight: 500;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-19u3hc5 button>span>img {
  display: inline-block;
  width: 24px;
  height: 24px;
  vertical-align: top;
}

.css-1w63xsn>span img {
  margin: 0px;
}

.css-1hxvx8x {
  padding-bottom: 12px;
}

.css-t7kbxx {
  position: relative;
  height: 44px;
}

.css-1hxvx8x input {
  font-size: 14px;
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
  color: rgb(51, 51, 51);
  outline: none;
  box-sizing: border-box;
}


.css-1wkyvs2 {
  position: relative;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  vertical-align: top;
  line-height: normal;
  color: rgb(51, 51, 51);
  padding: 8px 0px 0px;
  font-size: 14px;
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
  vertical-align: middle;
}

.css-10voksq {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 100%;
  height: 44px;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  background-color: rgb(95, 0, 128);
  border: 0px none;
}

.css-10voksq>span {
  font-size: 14px;
  font-weight: 500;
}

img {
  cursor: pointer;
}
</style>
