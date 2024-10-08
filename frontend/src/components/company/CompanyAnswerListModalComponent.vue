<template>
    <div class="modal" @click.self="closeModal">
        <div class="modal-content">
            <span class="close" @click="closeModal">&times;</span>
            <div class="answer-list-modal">
                <h3>답변 목록</h3>
                <ul v-if="selectedInquiry.answers && selectedInquiry.answers.length > 0">
                    <li v-for="(answer, index) in selectedInquiry.answers" :key="index">
                        <div v-if="answer" class="answer-item">
                            <p class="answer-content">{{ answer.content || '아직 답변이 없습니다.' }}</p>
                            <div class="answer-footer">
                                <span class="answer-date">{{ formatDate(answer.createdAt) }}</span>
                                <button class="delete-button" @click="deleteAnswer(answer.idx)">삭제</button>
                            </div>
                        </div>
                    </li>
                </ul>
                <!-- 답변이 없을 때 처리 -->
                <p v-if="!selectedInquiry.answers || selectedInquiry.answers.length === 0">
                    아직 답변이 없습니다.
                </p>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    props: {
        selectedInquiry: {
            type: Object,
            required: true,
        },
    },
    methods: {
        closeModal() {
            this.$emit("closeModal");
        },
        async deleteAnswer(answerId) {
    try {
        await axios.delete(`/api/qna/answer/delete/${answerId}`);
        
        const updatedAnswers = this.selectedInquiry.answers.filter(answer => answer.idx !== answerId);

        // 남은 답변이 없으면 상태를 '답변대기'로 변경
        const updatedStatus = updatedAnswers.length === 0 ? '답변대기' : this.selectedInquiry.answerStatus;

        // 부모 컴포넌트에게 상태 업데이트 요청
        this.$emit("answerDeleted", answerId, {
            answers: updatedAnswers,
            answerStatus: updatedStatus,
            });
            } catch (error) {
                console.error("답변 삭제 실패:", error);
            }
        },
        formatDate(dateString) {
            const date = new Date(dateString);
            return date.toLocaleDateString("ko-KR", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
            });
        },
    },
};
</script>

<style scoped>
.modal {
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 10% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 600px;
    border-radius: 10px;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    cursor: pointer;
}

.answer-list-modal {
    max-height: 400px;
    /* 스크롤이 가능하도록 최대 높이 설정 */
    overflow-y: auto;
    padding-right: 10px;
}

.answer-list-modal h3 {
    font-size: 20px;
    margin-bottom: 20px;
    border-bottom: 1px solid #ddd;
    /* 헤더 아래 줄 추가 */
    padding-bottom: 10px;
}

.answer-list-modal ul {
    list-style-type: none;
    padding: 0;
}

.answer-list-modal .answer-item {
    padding: 15px 0;
    border-bottom: 1px solid #ddd;
    /* 답변 사이에 줄 추가 */
}

.answer-content {
    flex: 1;
    margin-bottom: 10px;
}

.answer-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.answer-date {
    font-size: 12px;
    color: #888;
}

.delete-button {
    background-color: #a588af;
    color: white;
    border: none;
    padding: 6px 10px;
    cursor: pointer;
    border-radius: 5px;
    font-size: 12px;
}

.delete-button:hover {
    background-color: #5f0080;
}
</style>

