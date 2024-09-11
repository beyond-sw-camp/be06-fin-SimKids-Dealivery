<template>
  <div id="productModal" class="modal" @click.self="closeModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <div data-reactroot="">
        <!-- oldAddress가 있을 때만 렌더링 -->
        <div v-if="oldAddress" class="css-y8vf74 e1k348234">
          <h2 class="css-3ljxig e1k348233">배송지 수정</h2>
          <div class="css-1holwxw e1k348232">
            <div class="css-1n57dna e1uzxhvi6">
              <div height="44" class="css-t7kbxx e1uzxhvi3">
                <input
                  v-model="newAddress.name"
                  data-testid="input-box"
                  placeholder="별칭을 입력해 주세요"
                  type="text"
                  height="44"
                  class="css-1quw3ub e1uzxhvi2"
                />
              </div>
              <label for="addressDetail" class="css-c3g9of e1uzxhvi4">{{
                newAddress.area
              }}</label>
              <div height="44" class="css-t7kbxx e1uzxhvi3">
                <input
                  v-model="newAddress.detail"
                  data-testid="input-box"
                  placeholder="상세 주소를 입력해 주세요"
                  type="text"
                  height="44"
                  class="css-1quw3ub e1uzxhvi2"
                />
              </div>
            </div>
          </div>
          <div type="recent" class="css-1y14kop e1k348230">
            <button
              @click="saveNewAddress"
              class="css-10voksq e4nu7ef3"
              type="button"
              height="44"
              radius="3"
            >
              <span class="css-nytqmg e4nu7ef1">저장</span>
            </button>
            <button
              class="css-d85pyu e4nu7ef3"
              type="button"
              height="44"
              radius="3"
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
export default {
  name: "AddressEditModalComponent",
  data() {
    return {
      newAddress: {
        id: "",
        name: "",
        zonecode: "",
        area: "",
        detail: "",
      },
    };
  },
  props: {
    oldAddress: {
      type: Object,
      default: null,
    },
  },
  created() {
    if (this.oldAddress) {
      this.newAddress.id = this.oldAddress.id;
      this.newAddress.name = this.oldAddress.name;
      this.newAddress.zonecode = this.oldAddress.zonecode;
      this.newAddress.area = this.oldAddress.area;
      this.newAddress.detail = this.oldAddress.detail;
    }
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    saveNewAddress() {
      console.log(this.newAddress);
      this.$emit("saveNewAddress", this.newAddress);
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

.css-1holwxw {
  padding-top: 24px;
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
</style>
