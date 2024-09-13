import { defineStore } from "pinia";
import axios from "axios";

export const useQnaStore = defineStore("qna", {
    state: () => ({
        inquiries: [], test: "ddddddd"
    }),
    actions: {
        async fetchInquiries() {
            try {
                const response = await axios.get(
                    "https://run.mocky.io/v3/0fe8af94-1b49-48eb-8e57-e443f8d7ad83"
                );
                this.inquiries = response.data
            } catch (error) {
                console.error("문의 목록을 불러오는 중 오류 발생:", error);
            }
        },
        addInquiry(newInquiry) {
            newInquiry.created_at = new Date().toISOString().split("T")[0];
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
