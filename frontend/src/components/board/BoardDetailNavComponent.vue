<template>
  <div class="css-16c0d8l">
    <nav class="css-1le17tz en4zazl1">
      <ul class="css-tse2s2 en4zazl0">
        <li :class="[
          'css-1tzhzcg',
          'efe6b6j1',
          'tab',
          { active: activeTab === 'description' },
        ]" @click.prevent="activeTab = 'description'">
          <a class="css-1t0ft7s efe6b6j0"><span class="name">상품설명</span></a>
        </li>
        <li :class="[
          'css-1tzhzcg',
          'efe6b6j1',
          'tab',
          { active: activeTab === 'inquiries' },
        ]" @click.prevent="activeTab = 'inquiries'">
          <a class="css-1t0ft7s efe6b6j0"><span class="name">문의</span></a>
        </li>
      </ul>
    </nav>
    <!-- 상품 상세 설명 section -->
    <div class="css-0 el27cq1">
      <div id="description" class="css-18eozqj el27cq0" v-show="activeTab === 'description'">
        <div class="css-1d3w5wq e1d86arr0">
          <div class="css-1lyi66c">
            <div class="goods_wrap">
              <div class="goods_note">
                <div class="context">
                  <div class="pic">
                    <img
                      src="https://img-cf.kurly.com/hdims/resize/%3E1010x/quality/90/src/shop/data/goodsview/20240829/gv10001551896_1.jpg" />
                  </div>
                  <p class="words"></p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="css-8k5a6c ezy1ugy1"></div>
      </div>
    </div>

    <!-- 문의 하기 리스트 section -->
    <div class="css-30tvht eewa3w91" v-show="activeTab === 'inquiries'">
      <div class="css-17juoyc eewa3w90">
        <button class="css-mhrz8m e4nu7ef3" type="button" width="120" height="40" @click="openNewInquiryModal">
          <span class="css-nytqmg e4nu7ef1">문의하기</span>
        </button>
      </div>
      <div class="css-pncj1z eu9pqlw4">
        <strong class="css-83ihkd eu9pqlw3">상품 문의</strong>
        <ul class="css-8sw7yu eu9pqlw2">
          <li class="css-owypta eu9pqlw1">
            상품에 대한 문의를 남기는 공간입니다. 해당 게시판의 성격과 다른 글은 사전동의 없이 담당 게시판으로 이동될 수 있습니다.
          </li>
        </ul>
      </div>
      <table class="css-1d3w5wq e1wj4bbj1">
        <thead class="css-1xukm34 e1wj4bbj0">
          <tr>
            <th class="title">제목</th>
            <th class="author">작성자</th>
            <th class="created-date">작성일</th>
            <th class="status">답변상태</th>
          </tr>
        </thead>


        <tbody v-for="(row, index) in localTableData" :key="index">
          <tr @click="toggleInquiry(index)" class="css-atz965 e1l5ky7y9">
            <td class="css-1brd6ns e1l5ky7y8">{{ row.title }}</td>
            <td class="css-1pkqelu e1l5ky7y7">{{ maskAuthorName(row.author) }}</td>
            <td class="css-1pkqelu e1l5ky7y6">{{ row.modified_at || row.created_at }}</td>
            <td class="css-bhr3cq e1l5ky7y5">{{ row.answer_status }}</td>
          </tr>
          <tr v-show="expandedInquiryIndex === index" class="css-1mvq381 e61d7mt0">
            <td colspan="4">
              <div class="css-tnubsz e1ptpt003">
                <div class="css-1n83etr e1ptpt002">
                  <div class="css-m1wgq7 e1ptpt001">
                    <span class="css-z93ubq e1kayg9i0"></span>
                  </div>
                  <div class="css-1bv2zte e1ptpt000">
                    <span>{{ row.content }}<br></span>
                  </div>
                </div>
                <div class="css-1j49yxi e11ufodi1" v-if="row.answer_status !== '답변완료'">
                  <button type=" button" @click="openEditModal(index)">수정</button>
                  <button type="button" class="css-1ankuif e11ufodi0" @click="deleteInquiry(index)">삭제</button>
                </div>
              </div>
              <div class=" css-tnubsz e1ptpt003" v-if="row.answer_status !== '답변대기'">
                <div class="css-1n83etr e1ptpt002">
                  <div class="css-m1wgq7 e1ptpt001">
                    <span class="css-1non6l6 ey0f1wv0"></span>
                  </div>
                  <div class="css-1bv2zte e1ptpt000">
                    <div>{{ row.answer_content }}</div>
                  </div>
                </div>
                <div class="css-17g9jzg e1gk8zam0">{{ row.answer_modified_at || row.answer_created_at }}</div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 새 문의 작성 모달 -->
    <QnaRegisterModalComponent v-if="showNewInquiryModal" @close="closeModal" @submit="addNewInquiry" />

    <!-- 수정 모달 -->
    <QnaRegisterModalComponent v-if="showEditInquiryModal" :initialSubject="selectedInquiry.title"
      :initialContent="selectedInquiry.content" @close="closeModal" @submit="updateInquiry" />
  </div>
</template>

<script>
import QnaRegisterModalComponent from '../qna/QnaRegisterModalComponent.vue';

export default {
  name: "BoardDetailNavComponent",
  props: {
    tableData: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      activeTab: "description",
      hasPrev: false,
      hasNext: true,
      showNewInquiryModal: false,
      showEditInquiryModal: false,
      selectedInquiry: {
        title: "",
        content: "",
      },
      localTableData: [...this.tableData],
      expandedInquiryIndex: null,
      editingIndex: null, // 수정할 문의 인덱스
    };
  },
  methods: {
    openNewInquiryModal() {
      this.showNewInquiryModal = true;
    },
    openEditModal(index) {
      this.selectedInquiry = { ...this.localTableData[index] }; // 선택한 문의 데이터를 저장
      this.editingIndex = index; // 수정할 문의의 인덱스를 저장
      this.showEditInquiryModal = true;
    },
    closeModal() {
      this.showNewInquiryModal = false;
      this.showEditInquiryModal = false;
    },
    toggleInquiry(index) {
      this.expandedInquiryIndex = this.expandedInquiryIndex === index ? null : index;
    },
    maskAuthorName(name) {
      if (name.length <= 2) {
        return name[0] + "*";
      }
      return name[0] + "*".repeat(name.length - 2) + name[name.length - 1];
    },
    afterEnter(el) {
      el.style.maxHeight = 'none';
    },
    afterLeave(el) {
      el.style.maxHeight = '0px';
    },
    addNewInquiry(newInquiry) {
      newInquiry.created_at = new Date().toISOString().split("T")[0];
      this.localTableData.push(newInquiry);
      this.closeModal();
    },
    updateInquiry(updatedInquiry) {
      if (this.editingIndex !== null) {
        // 수정된 데이터를 배열에 직접 반영
        this.localTableData[this.editingIndex] = {
          ...this.localTableData[this.editingIndex],
          ...updatedInquiry,
          created_at: new Date().toISOString().split("T")[0], // 수정 날짜로 갱신
        };
        this.editingIndex = null;
        this.closeModal();
      }
    },
    deleteInquiry(index) {
      // 문의를 삭제할 때, 현재 토글된 인덱스를 초기화
      this.localTableData.splice(index, 1); // 해당 인덱스의 문의 삭제
      if (this.expandedInquiryIndex === index) {
        // 삭제된 인덱스가 현재 토글된 인덱스라면 초기화
        this.expandedInquiryIndex = null;
      } else if (this.expandedInquiryIndex > index) {
        // 삭제된 인덱스가 현재 토글된 인덱스보다 앞에 있을 경우, 인덱스 보정
        this.expandedInquiryIndex -= 1;
      }
    },
  },
  watch: {
    tableData(newData) {
      if (Array.isArray(newData)) {
        this.localTableData = newData.map(item => ({
          ...item,
          content: item.content || '내용이 없습니다.',
        }));
      } else {
        console.error("tableData is not an array:", newData);
      }
    },
  },
  components: {
    QnaRegisterModalComponent,
  },
};
</script>


<style scoped>
button {
  cursor: pointer;
}

.css-1le17tz {
  z-index: 20;
  top: 56px;
  width: 1010px;
  background-color: #fff;
  margin-top: 50px;
  margin-bottom: 50px;
}

.css-tse2s2 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-flex-wrap: wrap;
  -webkit-flex-wrap: wrap;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  height: 60px;
}

.css-1tzhzcg {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  border-width: 1px 0 1px 1px;
  border-color: #eee;
  border-style: solid;
  background-color: #fafafa;
  box-sizing: border-box;
}

.css-1tzhzcg:last-of-type {
  border-right-width: 1px;
}

.css-1t0ft7s {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  height: 100%;
}

.css-1tzhzcg .name {
  font-size: 16px;
  font-weight: 500;
  color: #666;
}

/*문의 하기*/

.css-30tvht {
  margin-bottom: 60px;
  margin-top: 60px;
}

.css-17juoyc {
  position: absolute;
  right: 0;
}

.css-17juoyc button {
  /*문의 하기 버튼*/
  border-radius: 3px;
}

.css-mhrz8m {
  display: block;
  padding: 0 10px;
  text-align: center;
  overflow: hidden;
  width: 120px;
  height: 40px;
  border-radius: 3px;
  color: #fff;
  background-color: #5f0080;
  border: 0 none;
}

.css-17juoyc span {
  font-size: 14px;
}

.css-nytqmg {
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
}

.css-pncj1z {
  padding-bottom: 40px;
  letter-spacing: -0.5px;
}

.css-83ihkd {
  display: block;
  padding-bottom: 16px;
  font-size: 24px;
  font-weight: 500;
  line-height: 21px;
  color: #333;
}

.css-8sw7yu {
  line-height: 19px;
  font-weight: 400;
  color: #999;
}

.css-owypta {
  padding-left: 10px;
}

/*문의 테이블*/
.css-1d3w5wq {
  width: 100%;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

.css-1xukm34 {
  height: 58px;
  border-top: 2px solid #333;
  border-bottom: 1px solid #333;
}

.css-atz965 {
  height: 64px;
  border-bottom: 1px solid rgb(244, 244, 244);
  line-height: 19px;
  letter-spacing: -0.5px;
  cursor: pointer;
}

.css-sno6ed {
  text-align: left;
  padding: 0px 20px;
  cursor: pointer;
  color: rgb(153, 153, 153);
}

.css-s5xdrg {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

.css-lrsm5m {
  display: block;
  width: 14px;
  height: 14px;
  background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTIiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxMiAxNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9IiNCNUI1QjUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTExLjA0MyAxMi41NzVhLjI1OS4yNTkgMCAwIDEtLjI1OC4yNTdIMS4yMTRhLjI1OS4yNTkgMCAwIDEtLjI1OC0uMjU3VjUuNDJjMC0uMTQyLjExNy0uMjU4LjI1OC0uMjU4aDkuNTdjLjE0NCAwIC4yNi4xMTYuMjYuMjU4djcuMTU1ek0zLjY4NSAzLjIzN0EyLjI4MyAyLjI4MyAwIDAgMSA1Ljk2Ni45NTdhMi4yODIgMi4yODIgMCAwIDEgMi4yODEgMi4yOHYuOTY4SDMuNjg1di0uOTY4em03LjEuOTY4aC0xLjU4di0uOTY4QTMuMjQxIDMuMjQxIDAgMCAwIDUuOTY1IDAgMy4yNCAzLjI0IDAgMCAwIDIuNzMgMy4yMzd2Ljk2OEgxLjIxNEMuNTQ0IDQuMjA1IDAgNC43NSAwIDUuNDJ2Ny4xNTVjMCAuNjY5LjU0NSAxLjIxNCAxLjIxNCAxLjIxNGg5LjU3Yy42NzEgMCAxLjIxNi0uNTQ1IDEuMjE2LTEuMjE0VjUuNDJjMC0uNjctLjU0NS0xLjIxNS0xLjIxNS0xLjIxNXoiLz4KICAgICAgICA8cGF0aCBkPSJNNSA4LjJjMCAuMzQzLjE4NC42MzIuNDQ4LjgxMnYxLjMzaDEuMTAzdi0xLjMzQS45ODYuOTg2IDAgMCAwIDcgOC4yYTEgMSAwIDAgMC0yIDB6Ii8+CiAgICA8L2c+Cjwvc3ZnPgo=) no-repeat;
  margin-left: 6px;
}

.css-bhr3cq {
  text-align: center;
  color: rgb(95, 0, 128);
}

.css-18ad0gx {
  position: relative;
  width: 100%;
  height: 44px;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-top: 20px;
}

.css-rzcdhr:disabled {
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-prev-disabled.svg);
  cursor: default;
}

.css-rzcdhr {
  width: 44px;
  height: 44px;
  padding: 0;
  margin: 0;
  border: 0;
  cursor: pointer;
  background-color: transparent;
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-prev-activated.svg);
  background-repeat: no-repeat;
  -webkit-background-size: cover;
  background-size: cover;
}

.css-1jwilit:disabled {
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-next-disabled.svg);
  cursor: default;
}

.css-sxxs1g button+button {
  margin-left: 12px;
}

.css-1jwilit {
  width: 44px;
  height: 44px;
  padding: 0;
  margin: 0;
  border: 0;
  cursor: pointer;
  background-color: transparent;
  background-image: url(https://res.kurly.com/kurly/ico/2021/paging-next-activated.svg);
  background-repeat: no-repeat;
  -webkit-background-size: cover;
  background-size: cover;
}

.css-7qb0sc {
  overflow: hidden;
  position: absolute;
  left: -9999px;
  width: 1px;
  height: 1px;
}

.tab {
  cursor: pointer;
}

.tab.active {
  background: #fff;
  /* 활성화된 탭 배경색 */
}

.tab.active a span {
  color: #5f0080;
  /* 활성화된 탭 텍스트 색상 */
}

.inquiry-detail {
  display: block;
  /* table-row에서 block으로 변경 */
  overflow: hidden;
  max-height: 0;
  transition: max-height 0.3s ease-out;
  /* 부드러운 애니메이션 */
}

.inquiry-detail-expanded {
  max-height: 500px;
  /* 최대 펼쳐질 높이 */
}

tbody {
  display: table-row-group;
  vertical-align: middle;
  unicode-bidi: isolate;
  border-color: inherit;
}

.css-atz965 {
  height: 64px;
  border-bottom: 1px solid rgb(244, 244, 244);
  line-height: 19px;
  letter-spacing: -0.5px;
}

tr {
  display: table-row;
  vertical-align: inherit;
  unicode-bidi: isolate;
  border-color: inherit;
}

.css-1brd6ns {
  text-align: left;
  padding: 0px 20px;
  cursor: pointer;
}

.css-1pkqelu {
  text-align: center;
  color: rgb(153, 153, 153);
}

td {
  display: table-cell;
  vertical-align: inherit;
  unicode-bidi: isolate;
}

.css-1mvq381 {
  background-color: rgb(250, 250, 250);
  border-bottom: 1px solid rgb(244, 244, 244);
  max-width: 100px;
}

.css-tnubsz {
  max-width: 1010px;
}

div {
  display: block;
  unicode-bidi: isolate;
}

.css-1n83etr {
  display: flex;
  padding: 22px 20px 10px;
  align-items: flex-start;
}

.css-m1wgq7 span {
  width: 24px;
  height: 24px;
  display: block;
}

.css-z93ubq {
  width: 28px;
  height: 28px;
  background-image: url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGc+CiAgICAgICAgICAgIDxnPgogICAgICAgICAgICAgICAgPGc+CiAgICAgICAgICAgICAgICAgICAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTQ0NSAtODc2KSB0cmFuc2xhdGUoNDIzIDI3NikgdHJhbnNsYXRlKDIgMzE2KSB0cmFuc2xhdGUoMjAgMjg0KSI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxjaXJjbGUgY3g9IjEyIiBjeT0iMTIiIHI9IjEyIiBmaWxsPSIjQTg2NEQ4Ii8+CiAgICAgICAgICAgICAgICAgICAgICAgIDxwYXRoIGZpbGw9IiNGRkYiIGQ9Ik0xMS45NDYgNy41YzIuNDU2IDAgNC40NDYgMS45OSA0LjQ0NiA0LjQ0NiAwIC45MDctLjI3MSAxLjc1LS43MzcgMi40NTNsLjg0NS44NDMtMS4yNTggMS4yNTgtLjg0My0uODQ1Yy0uNzAzLjQ2Ni0xLjU0Ni43MzctMi40NTMuNzM3LTIuNDU1IDAtNC40NDYtMS45OS00LjQ0Ni00LjQ0NkM3LjUgOS40OTEgOS40OSA3LjUgMTEuOTQ2IDcuNXptMCAxLjc3OGMtMS40NzMgMC0yLjY2OCAxLjE5NS0yLjY2OCAyLjY2OHMxLjE5NSAyLjY2OCAyLjY2OCAyLjY2OGMuNDEyIDAgLjgwMi0uMDk0IDEuMTUtLjI2bC0uMzY5LS4zNyAxLjI1OC0xLjI1Ny4zNjkuMzY5Yy4xNjYtLjM0OC4yNi0uNzM4LjI2LTEuMTUgMC0xLjQ3My0xLjE5NS0yLjY2OC0yLjY2OC0yLjY2OHoiLz4KICAgICAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgIDwvZz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPgo=");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: contain;
}

.css-1bv2zte {
  margin-left: 12px;
  padding-top: 2px;
  font-size: 14px;
  word-break: break-all;
  line-height: 19px;
  letter-spacing: -0.5px;
}

.css-1non6l6 {
  width: 28px;
  height: 28px;
  background-image: url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGc+CiAgICAgICAgICAgIDxnPgogICAgICAgICAgICAgICAgPGc+CiAgICAgICAgICAgICAgICAgICAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTQ0NSAtMTkxOCkgdHJhbnNsYXRlKDQyMyAyNzYpIHRyYW5zbGF0ZSgyIDE2MjIpIHRyYW5zbGF0ZSgyMCAyMCkiPgogICAgICAgICAgICAgICAgICAgICAgICA8Y2lyY2xlIGN4PSIxMiIgY3k9IjEyIiByPSIxMiIgZmlsbD0iIzVGMDA4MCIvPgogICAgICAgICAgICAgICAgICAgICAgICA8cGF0aCBmaWxsPSIjRkZGIiBmaWxsLXJ1bGU9Im5vbnplcm8iIGQ9Ik05Ljc2IDE1LjcwOGwuNTQtMS40MDRoMy40NTZsLjU1MiAxLjQwNGgyLjE0OEwxMyA3LjVoLTEuOTMyTDcuNiAxNS43MDhoMi4xNnptMy4zMjQtMy4xMzJoLTIuMTEybDEuMDU2LTIuNzEyIDEuMDU2IDIuNzEyeiIvPgogICAgICAgICAgICAgICAgICAgIDwvZz4KICAgICAgICAgICAgICAgIDwvZz4KICAgICAgICAgICAgPC9nPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+Cg==");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: contain;
}

.css-17g9jzg {
  padding: 0px 16px 20px;
  margin-left: 40px;
  font-size: 14px;
  line-height: 22px;
  color: rgb(153, 153, 153);
}

.css-1j49yxi {
  width: 100%;
  display: flex;
  -webkit-box-pack: end;
  justify-content: flex-end;
}

.css-1j49yxi button {
  position: relative;
  padding: 0px 11px;
  font-size: 14px;
  line-height: 20px;
  border: 0px;
  background: none;
  color: rgb(153, 153, 153);
}

.css-1ankuif::before {
  position: absolute;
  top: 5px;
  left: 0px;
  width: 1px;
  height: 12px;
  background: rgb(238, 238, 238);
  vertical-align: top;
  content: "";
}
</style>
