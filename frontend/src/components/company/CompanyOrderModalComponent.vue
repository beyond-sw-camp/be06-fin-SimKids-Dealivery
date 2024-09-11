<template>
  <div id="productModal" class="modal" @click.self="closeModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>상품 주문서</h2>
      <hr class="thick-line" />
      <div class="product-item">
        <div class="title"><strong>상품명: </strong> 망고</div>
        <div class="contents">
          <p><strong>수량:</strong> 2</p>
        </div>
        <div class="contents">
          <p><strong>가격:</strong> 4,000 원</p>
        </div>
      </div>
      <hr class="thin-line" />
      <div class="product-item">
        <div class="title"><strong>상품명: </strong> 딸기</div>
        <div class="contents">
          <p><strong>수량:</strong> 3</p>
        </div>
        <div class="contents">
          <p><strong>가격:</strong> 6,000 원</p>
        </div>
      </div>

      <hr class="thin-line" />
      <div class="total-price">
        <p><strong>총 결제 금액: 13,560원</strong></p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CompanyBoardModalComponent",
  data() {
    return {
      // productName: "",
      // productPrice: "",
      // productQuantity: "",
    };
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
  },
};
</script>

<style scoped>
.modal {
  /* display: none; */
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
</style>
