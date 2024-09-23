
import { defineStore } from "pinia";
import axios from "axios";

export const useQnaStore = defineStore("qna", {
    state: () => ({
        inquiries: []
    }),
    actions: {
        async fetchInquiries() {
            try {
                const response = await axios.get('/api/qna/question/list'); 
                if (response.data.isSuccess) {
                    this.inquiries = response.data.result;
                    console.log(this.inquiries);
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
        async deleteInquiry(idx, index) {
            try {
                // console.log('삭제 요청 경로:', `/api/qna/question/delete/${idx}`);
                const response = await axios.delete(`/api/qna/question/delete/${idx}`);
                if (response.data.isSuccess) {
                    this.inquiries.splice(index, 1);  
                    console.log('문의가 성공적으로 삭제되었습니다.');
                } else {
                    console.error('문의 삭제 중 오류 발생:', response.data.message);
                }
            } catch (error) {
                console.error('문의 삭제 중 오류 발생:', error);
            }
        },
    },
});
