import { defineStore } from "pinia";
import axios from "axios";
const backend = "http://localhost:8080/product-boards";

export const useCompanyBoardStore = defineStore("companyBoard", {
  state: () => ({
    productBoardReq: {
      title: "",
      products: [],
      startedAt: "",
      endedAt: "",
      category: "",
    },
  }),
  actions: {
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
  },
});
