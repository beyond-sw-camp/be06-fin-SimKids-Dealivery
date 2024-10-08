import { defineStore } from "pinia";
import axios from "axios";
const backend = "/api/product-boards";

export const useBoardStore = defineStore("board", {
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
    async getMainList(page, status) {
      const tokenExists = await this.checkToken();
      const response = await axios.get(backend + "/main/list", {
        params: {
          page: page,
          status: status,
        },
        withCredentials: tokenExists,
      });
      console.log(response);
      return response.data.result;
    },
    async getList(page, category, search) {
      const tokenExists = await this.checkToken();
      const params = { page: page };
      if (category != "undefined" && category != "null" && category != "전체") {
        params.search = category;
      } else if (search != "undefined" && search != "null" && search != "") {
        params.search = search;
      }
      const response = await axios.get(backend + "/list", {
        params: params,
        withCredentials: tokenExists,
      });
      console.log(response);
      return response.data.result;
    },
    async getDetail(idx) {
      const tokenExists = await this.checkToken();
      const data = await axios.get(backend + `/${idx}/detail`, {
        withCredentials: tokenExists,
      });
      this.boardData = data.data.result;
      return data.data.result;
    },
    async getLikesList(page) {
      const response = await axios.get("/api/likes/list", {
        params: {
          page: page,
        },
        withCredentials: true,
      });
      console.log(response);
      return response.data.result;
    },

    async checkToken() {
      try {
        const response = await axios.get("/api/check-token", {
          withCredentials: true,
        });
        return response.data.isValid; // 서버에서 쿠키 유효성 검사 결과 반환
      } catch (error) {
        console.error(error);
        return false; // 오류가 발생하면 false 반환
      }
    },

    // --------- 판매자 ---------
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
    async getOrderListWithOption(page, status, month) {
      status = String(status);
      status = status.includes("전체") ? null : status;
      const response = await axios.get("/api/orders/company/history", {
        params: {
          page: page,
          status: status,
          month: month,
        },
      });
      return response.data.result;
    },
    async getOrderDetail(orderIdx) {
      const response = await axios.get(
        `/api/orders/company/${orderIdx}/detail`
      );
      return response.data.result;
    },

    async getProductBoardDetail(idx) {
      const data = await axios.get(backend + `/company/${idx}/detail`);
      this.boardData = data.data.result;
      return data.data.result;
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
          if (response.data.code != 1000) {
            alert(response.data.message);
            return false;
          } else {
            console.log("Success:", response.data);
            alert("상품이 성공적으로 등록되었습니다!");
            return true;
          }
        })
        .catch((error) => {
          console.error("There was an error!", error);
          alert("상품 등록 중 오류가 발생했습니다.");
          return false;
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
        return [this.boardData.productDetailUrl];
      }
      return null;
    },
    getDetailUrlSize() {
      if (this.boardData !== null && this.boardData.productDetailUrl) {
        return 1;
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
