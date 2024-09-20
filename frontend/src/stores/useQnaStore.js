
import { defineStore } from "pinia";
import axios from "axios";

export const useQnaStore = defineStore("qna", {
    state: () => ({
        inquiries: []
    }),
    actions: {
        async fetchInquiries() {
            try {
                const response = await axios.get('/api/qna/question/list'); // 실제 API URL
                if (response.data.isSuccess) {
                    this.inquiries = response.data.result;
                } else {
                    console.error('문의 목록을 불러오는 중 오류 발생:', response.data.message);
                }
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
