import { defineStore } from "pinia";
import axios from "axios";

export const useQnaStore = defineStore("qna", {
    state: () => ({
        inquiries: []
    }),
    actions: {
        async fetchInquiries() {
            try {
                const response = await axios.get(
                    //"https://run.mocky.io/v3/21785523-9c59-48aa-81e2-b23c209841e6"
                );
                this.inquiries = response.data
            } catch (error) {
                console.error("문의 목록을 불러오는 중 오류 발생:", error);
            }
        },
        addInquiry(newInquiry) {
            this.inquiries.push(newInquiry);
        },
        updateInquiry(index, updatedInquiry) {
            this.inquiries[index] = { ...this.inquiries[index], ...updatedInquiry };
        },
        deleteInquiry(index) {
            this.inquiries.splice(index, 1);
        },
    },
});
