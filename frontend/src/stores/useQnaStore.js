import { defineStore } from "pinia";
import axios from "axios";

export const useQnaStore = defineStore("qna", {
    state: () => ({
        inquiries: [],
        totalInquiries: 0, // 총 문의 개수 관리
    }),
    actions: {
        async fetchInquiries(productBoardIdx, page = 1) {
            try {
                const response = await axios.get(`/api/qna/question/list?productBoardIdx=${productBoardIdx}&page=${page}`);
                if (response.data.isSuccess) {
                    this.inquiries = response.data.result.content;
                    this.totalInquiries = response.data.result.totalElements;
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
            this.inquiries.splice(index, 1, { ...this.inquiries[index], ...updatedInquiry }); // 수정된 문의 반영
        },
        async deleteInquiry(idx, index) {
            try {
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
