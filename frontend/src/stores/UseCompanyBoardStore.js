import { defineStore } from "pinia";
import axios from "axios";
const backend = "http://localhost:8080/product-boards";
const mockyURL = "https://run.mocky.io/v3/fa2ac704-87e9-4d42-b2c0-ddda0e9d2861";

export const useCompanyBoardStore = defineStore("companyBoard", {
  state: () => ({
    boardData: null,
    productBoardReq: {
      title: "",
      products: [],
      startedAt: "",
      endedAt: "",
      category: "",
    },
  }),
  actions: {
    async getProductBoardDetail() {
      const data = await axios.get(mockyURL);
      this.boardData = data.data;
      return data.data;
    },
    async createProductBoard(req) {
      const formData = new FormData();

      const productBoardRequest = {
        title: req.title,
        products: req.products,
        startedAt: req.startTime,
        endedAt: req.endTime,
        category: req.category,
      };

      formData.append(
        "productBoardRequest",
        new Blob([JSON.stringify(productBoardRequest)], {
          type: "application/json",
        })
      );

      req.thumbnailImages.forEach((image) => {
        formData.append(`productThumbnails`, image.file);
      });

      req.detailImage.forEach((image) => {
        formData.append("productDetail", image.file);
      });

      axios
        .post(backend, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          console.log("Success:", response.data);
          alert("상품이 성공적으로 등록되었습니다!");
        })
        .catch((error) => {
          console.error("There was an error!", error);
          alert("상품 등록 중 오류가 발생했습니다.");
        });
    },
    getThumbnailUrls() {
      if (this.boardData !== null) {
        return this.boardData.productThumbnailUrls;
      }
      return null;
    },
    getThumbnailUrlSize() {
      if (this.boardData !== null) {
        return this.boardData.productThumbnailUrls.length;
      }
      return 0;
    },
    setThumbnailUrls(urls) {
      if (this.boardData !== null) {
        this.boardData.productThumbnailUrls = urls;
      }
    },
    getDetailUrl() {
      if (this.boardData !== null) {
        return this.boardData.productDetailUrl;
      }
      return null;
    },
    getDetailUrlSize() {
      if (this.boardData !== null) {
        return this.boardData.productDetailUrl.length;
      }
      return 0;
    },
    resetDetailUrl() {
      if (this.boardData !== null) {
        this.boardData.productDetailUrl = [];
      }
    },
  },
});
