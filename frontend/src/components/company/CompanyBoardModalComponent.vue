<template>
  <div id="productModal" class="modal" @click.self="closeModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>상품 정보 입력</h2>
      <label for="newProductName">상품명:</label>
      <input
        type="text"
        id="newProductName"
        placeholder="상품명을 입력해주세요"
      />
      <label for="newProductPrice">가격:</label>
      <input
        type="text"
        id="newProductPrice"
        placeholder="가격을 입력해주세요"
      />
      <label for="newProductQuantity">수량:</label>
      <input
        type="text"
        id="newProductQuantity"
        placeholder="수량을 입력해주세요"
      />
      <button id="addProduct">추가하기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CompanyBoardModalComponent",
  props: {},
  methods: {
    closeModal() {
      this.$emit("closeModal");
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

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
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

button#addProduct {
  background-color: #5f0080;
  color: white;
  padding: 14px 30px;
  margin-top: 30px;
  margin-left: 170px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

button#addProduct:hover {
  background-color: #5f0080;
}

label {
  display: block;
  margin: 20px 0 7px;
  font-size: 15px;
}

input[type="text"] {
  width: calc(100% - 20px);
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
