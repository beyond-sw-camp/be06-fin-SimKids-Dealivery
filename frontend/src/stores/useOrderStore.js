/* global IMP */

import { defineStore } from "pinia";
import axios from "axios";
import router from '@/router';

const backend = "/api/orders";

export const useOrderStore = defineStore('order', {
    state: () => ({
        boardInfo: null,
        orderInfo: { orderIdx: null, orderedProducts: [] },
        paymentInfo: { impUid: null, paymentMethod: null, addredss: null, usedPoint: null, totalAmount: null, receiverName: null, receiverPhoneNumber: null },
        customData: { products: [], discountRate: null, usedPoint: null }
    }),
    actions: {
        async submitOrder(orderRequest) {
            this.boardInfo = orderRequest.boardInfo;
            this.orderInfo.orderedProducts = orderRequest.cartItems;

            let customData = orderRequest.cartItems.map((product) => {
                return { idx: product.idx, quantity: product.quantity };
            });

            let req = {
                boardIdx: orderRequest.boardInfo.idx,
                orderedProducts: customData
            };

            try {
                let response = await axios.post(backend + "/register", req, { withCredentials: true });
                console.log("Response : " + response.data);

                if (response.data.code !== 1000) {
                    alert(`${response.data.message}`);
                    return false;
                }

                this.orderInfo.orderIdx = response.data.result.orderIdx;
                this.customData.products = customData
                this.customData.discountRate = this.boardInfo.discountRate

                return true;

            } catch (error) {
                alert("주문에 실패했습니다\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.");
                return false;
            }
        },

        async makePayment(paymentRequest) {
            let pg = paymentRequest.paymentMethod === 'kakaopay' ? process.env.VUE_APP_KAKAOPAY_CID : process.env.VUE_APP_TOSSPAY_MID
            let payMethod = paymentRequest.paymentMethod === 'kakaopay' ? 'card' : 'tosspay'
            let merchantUid = "order_no_000" + new Date().getMilliseconds();
            this.customData.usedPoint = paymentRequest.usedPoint;

            IMP.init(process.env.VUE_APP_PORTONE_STORE_ID); // 상점 식별코드
            IMP.request_pay({
                pg: pg,
                pay_method: payMethod,
                merchant_uid: merchantUid,
                name: this.boardInfo.title,
                amount: paymentRequest.totalAmount,
                custom_data: this.customData
            }, (rsp) => {

                if (paymentRequest.paymentMethod === 'kakaopay' && !rsp.success) {
                    this.cancelOrder(this.orderInfo.orderIdx, '/', '결제가 취소되었습니다. 주문이 취소됩니다.')

                }

                else if (paymentRequest.paymentMethod === 'tosspay' && rsp.error_msg != null) {
                    this.cancelOrder(this.orderInfo.orderIdx, '/', '결제가 취소되었습니다. 주문이 취소됩니다.')
                }

                else {
                    this.paymentInfo = { ...paymentRequest, impUid: rsp.imp_uid }
                    this.verifyPayment()
                }

            });
        },

        async verifyPayment() {

            let request = {
                orderIdx: this.orderInfo.orderIdx,
                address: this.paymentInfo.address,
                adderessDetail: "4층 강의실",
                postNumber: "01677",
                paymentId: this.paymentInfo.impUid,
                payMethod: this.paymentInfo.paymentMethod,
                usedPoint: this.paymentInfo.usedPoint,
                receiverName: this.paymentInfo.receiverName,
                receiverPhoneNumber: this.paymentInfo.receiverPhoneNumber,
            }

            try {
                let response = await axios.post(backend + "/complete", request, { withCredentials: true });
                console.log("Response : " + response.data);

                if (response.data.code !== 1000) {
                    alert(`결제에 실패했습니다\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.\n\n${response.data.message}`);
                    return router.push('/');
                }

                alert("결제가 성공적으로 완료되었습니다.");
                router.push('/');

            } catch (error) {
                alert("결제에 실패했습니다\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.");
                router.push('/');
            }
        },

        async cancelOrder(orderIdx, root, errMsg) {
            await axios.patch(backend + `/${orderIdx}/cancel`, { withCredentials: true });

            alert(errMsg);
            router.push(root);
        }
    }
});
