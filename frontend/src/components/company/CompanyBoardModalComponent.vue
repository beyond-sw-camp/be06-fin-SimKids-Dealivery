<template>
  <div id="productModal" class="modal" @click.self="closeModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>상품 정보 입력</h2>
      <label for="newProductName">상품명:</label>
      <input
        type="text"
        v-model="productName"
        id="newProductName"
        placeholder="상품명을 입력해주세요"
      />
      <label for="newProductPrice">가격:</label>
      <input
        type="text"
        v-model="productPrice"
        id="newProductPrice"
        placeholder="가격을 입력해주세요"
      />
      <label for="newProductQuantity">수량:</label>
      <input
        type="text"
        v-model="productQuantity"
        id="newProductQuantity"
        placeholder="수량을 입력해주세요"
      />
      <div class="submitButton">
        <button id="submitProduct" @click="submitProduct">추가하기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CompanyBoardModalComponent",
  data() {
    return {
      productName: "",
      productPrice: "",
      productQuantity: "",
    };
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    submitProduct() {
      const regex = /^[1-9][0-9]*$/;
      if (!this.productName || !this.productPrice || !this.productQuantity) {
        alert("모든 필드를 입력해주세요.");
        return;
      }
      if (!regex.test(this.productPrice) || !regex.test(this.productQuantity)) {
        alert("가격과 수량은 숫자여야 합니다.");
        return;
      }
      if (
        parseInt(this.productPrice) <= 0 ||
        parseInt(this.productQuantity) <= 0
      ) {
        alert("가격과 수량은 1 이상의 숫자여야 합니다.");
        return;
      }
      this.$emit("addProduct", {
        name: this.productName,
        price: this.productPrice,
        stock: this.productQuantity,
      });
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
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
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
  margin-top: 30px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

button:hover {
  background-color: #4c4c4c;
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
</style>
