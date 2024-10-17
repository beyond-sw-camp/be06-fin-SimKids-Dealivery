<template>
  <div id="productModal" class="modal" >
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <div data-reactroot="">
        <!-- oldAddress가 있을 때만 렌더링 -->
        <div v-if="oldAddress" class="css-y8vf74 e1k348234">
          <h2 class="css-3ljxig e1k348233">배송지 수정</h2>
          <div class="css-1holwxw e1k348232">
            <div class="css-1n57dna e1uzxhvi6">
              <label for="name" class="css-c3g9of e1uzxhvi4">배송지명</label>
              <div height="44" class="css-t7kbxx e1uzxhvi3">
                <input
                  maxlength="30"
                  v-model="editedAddress.name"
                  data-testid="input-box"
                  placeholder="배송지명을 입력해 주세요"
                  type="text"
                  height="44"
                  class="css-1quw3ub e1uzxhvi2"
                />
              </div>
              <label for="addressDetail" class="css-c3g9of e1uzxhvi4">상세주소</label>
              <div height="44" class="css-t7kbxx e1uzxhvi3">
                <input
                  maxlength="30"
                  v-model="editedAddress.addressDetail"
                  data-testid="input-box"
                  placeholder="상세주소를 입력해 주세요"
                  type="text"
                  height="44"
                  class="css-1quw3ub e1uzxhvi2"
                />
              </div>
            </div>
          </div>
          <div type="recent" class="css-1y14kop e1k348230">
            <button
              @click="validateAndSave"
              class="css-10voksq e4nu7ef3"
              type="button"
              height="44"
              radius="3"
            >
              <span class="css-nytqmg e4nu7ef1">저장</span>
            </button>
            <button
              :disabled="oldAddress.isDefault" 
              class="css-d85pyu e4nu7ef3 button"
              type="button"
              height="44"
              radius="3"
              @click="deleteOne"
            >
              <span class="css-nytqmg e4nu7ef1">삭제</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";
import { Validator } from "@/util/validator";
export default {
  name: "AddressEditModalComponent",
  data() {
    return {
      editedAddress: {
        idx: null,
        name: "",
        postNumber: "",
        address: "",
        addressDetail: "",
        isDefault: false
      },
    };
  },
  props: {
    oldAddress: {
      type: Object,
      default: null,
    },
  },
  computed: {
    ...mapStores(useUserStore),
  },
  mounted() {
    if (this.oldAddress) {
      this.editedAddress.idx = this.oldAddress.idx;
      this.editedAddress.name = this.oldAddress.name;
      this.editedAddress.postNumber = this.oldAddress.postNumber;
      this.editedAddress.address = this.oldAddress.address;
      this.editedAddress.addressDetail = this.oldAddress.addressDetail;
      this.editedAddress.isDefault = this.oldAddress.isDefault;
      console.log(this.editedAddress.isDefault);
      
    }
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    saveEditedAddress() {
      if(!this.areAddressesEqual()){
        this.$emit("saveEditedAddress", this.editedAddress);
      }
      this.closeModal();
    },
    areAddressesEqual() {
      // 모든 속성이 일치하는지 확인하는 함수
      return (
        this.editedAddress.idx === this.oldAddress.idx &&
        this.editedAddress.name === this.oldAddress.name &&
        this.editedAddress.postNumber === this.oldAddress.postNumber &&
        this.editedAddress.address === this.oldAddress.address &&
        this.editedAddress.addressDetail === this.oldAddress.addressDetail &&
        this.editedAddress.isDefault === this.oldAddress.isDefault
      );
    },
    validateAndSave() {
      try {
        const validInputRegex = /^(?!.*[!@#$%^&*()_+={}:;"'<>,.?/~`|\\-])(?=.*[^\n])[^\nㄱ-ㅎ]*$/;

        // 검증 로직
        new Validator(this.editedAddress.name, "배송지명을 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");

        new Validator(this.editedAddress.postNumber, "우편번호를 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");

        new Validator(this.editedAddress.address, "주소를 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");

        new Validator(this.editedAddress.addressDetail, "상세주소를 입력해 주세요")
            .isNotEmpty()
            .matches(validInputRegex, "특수문자 및 초성은 입력할 수 없습니다.");
        this.saveEditedAddress();
      } catch (error) {
        // 검증 실패 시 에러 메시지 출력
        alert(error.message);
      }
    },
    async deleteOne(){
      if(this.editedAddress.idx === null){
        alert("유효하지 않은 배송지입니다.");
        this.closeModal();
      }else{
        if(await this.userStore.deleteDelivery(this.editedAddress.idx)){
         this.userStore.getDeliveryList();
        }
        this.closeModal();
      }
    },
    validateAll(){

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
  background-color: rgba(0, 0, 0, 0.3);
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
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

h2 {
  font-size: 25px;
  margin-bottom: 30px;
}

.css-y8vf74 {
  padding: 30px;
  letter-spacing: -0.5px;
}

.css-3ljxig {
  font-weight: 500;
  font-size: 24px;
  line-height: 36px;
}


.css-1holwxw > div {
  padding-bottom: 24px;
}

.css-1holwxw > div > label {
  padding-top: 0px;
  font-size: 14px;
}

.css-c3g9of {
  display: inline-block;
  padding: 8px 0px 11px;
  font-size: 14px;
  font-weight: 500;
  line-height: 19px;
  color: rgb(51, 51, 51);
}

.css-t7kbxx {
  position: relative;
  height: 44px;
}

.css-1n57dna input {
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

.css-s480k4 input {
  font-size: 14px;
}

.css-1holwxw > div:last-of-type {
  padding-bottom: 0px;
}

.css-1y14kop > label {
  padding: 20px 0px;
}

.css-1xkjo8k {
  padding: 14px 0px 9px;
  position: relative;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  vertical-align: top;
  line-height: normal;
  color: rgb(51, 51, 51);
  font-size: 14px;
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

.css-10voksq > span {
  font-size: 14px;
  font-weight: 500;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-1y14kop button + button {
  margin-top: 12px;
}

.css-d85pyu {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 100%;
  height: 44px;
  border-radius: 3px;
  color: rgb(51, 51, 51);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
}

.css-d85pyu > span {
  font-size: 14px;
  font-weight: 500;
}

.button{
  position: relative;
}

.button:disabled {
  background-color: #d6d6d6; /* 회색 배경색 */
  color: #a1a1a1; /* 회색 글자색 */
  cursor: not-allowed; /* 금지 커서 */
}
</style>
