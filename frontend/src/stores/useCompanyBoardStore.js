import { defineStore } from "pinia";
import axios from "axios";
const backend = "/api/product-boards";
// const mockyListURL =
//   "https://run.mocky.io/v3/785219a1-65f2-45ba-875d-46e0019566ae";
const mockyListOptionURL =
  "https://run.mocky.io/v3/3801fe56-0faf-4fe0-a765-b7e966f1af6f";
const mockyDetailURL =
  "https://run.mocky.io/v3/fa2ac704-87e9-4d42-b2c0-ddda0e9d2861";

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
    currentPage: 0,
    totalPages: 0,
  }),
  actions: {
    async getProductBoardList(page) {
      const response = await axios.get(backend + "/company/list", {
        params: {
          page: page,
        },
      });
      return response.data.result;
    },
    async getProductBoardListWithOption(page, status, month) {
      status = String(status);
      status = status.includes("전체") ? null : status;
      const response = await axios.get(backend + "/company/list", {
        params: {
          page: page,
          status: status,
          month: month,
        },
      });
      return response.data.result;
    },
    async getProductBoardListByDateRange(option) {
      console.log(option);
      // 백엔드 개발 후 구현
      const data = await axios.get(mockyListOptionURL);
      return data.data;
    },
    async getProductBoardListByOrderStatus(option) {
      console.log(option);
      // 백엔드 개발 후 구현
      const data = await axios.get(mockyListOptionURL);
      return data.data;
    },
    async getProductBoardDetail() {
      const data = await axios.get(mockyDetailURL);
      this.boardData = data.data;
      return data.data;
    },
    async createProductBoard(req) {
      const formData = new FormData();

      const boardCreateRequest = {
        title: req.title,
        discountRate: req.discountRate,
        products: req.products,
        startedAt: req.startedAt,
        endedAt: req.endedAt,
        category: req.category,
      };

      formData.append(
        "boardCreateRequest",
        new Blob([JSON.stringify(boardCreateRequest)], {
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
