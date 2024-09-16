/* global IMP */

import { defineStore } from "pinia";
import axios from "axios";
import router from '@/router';

const backend = "/api/orders";

export const useOrderStore = defineStore('order', {
    state: () => ({
        orderedProducts: [], orderIdx: null, paymentId: "", boardInfo: null, orderInfo: null, paymentInfo: null, customData: null
    }),
    actions: {
        async submitOrder(orderRequest) {
            this.boardInfo = orderRequest.boardInfo;
            this.orderedProducts = orderRequest.cartItems;

            let customData = orderRequest.cartItems.map((product) => {
                return { idx: product.value, quantity: product.quantity };
            });

            let req = {
                boardIdx: orderRequest.boardInfo.idx,
                orderedProducts: customData
            };

            try {
                let response = await axios.post(backend + "/register", req, { withCredentials: true });
                console.log("Response : " + response.data);

                if (response.data.code !== 1000) {
                    return false;
                }

                this.orderIdx = response.data.result.orderIdx;
                this.customData = customData
                return true;

            } catch (error) {
                alert("주문에 실패했습니다\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.");
                return false;
            }
        },

        async makePayment(paymentRequest) {
            let pg = paymentRequest.paymentMethod === 'kakao' ? process.env.VUE_APP_KAKAOPAY_CID : process.env.VUE_APP_TOSSPAY_MID
            let payMethod = paymentRequest.paymentMethod === 'kakao' ? 'card' : 'tosspay'
            let paymentId = "order_no_000" + new Date().getMilliseconds();

            IMP.init(process.env.VUE_APP_PORTONE_STORE_ID); // 상점 식별코드
            IMP.request_pay({
                pg: pg,
                pay_method: payMethod,
                merchant_uid: paymentId,
                name: this.boardInfo.title,
                amount: paymentRequest.totalAmount,
                custom_data: this.customData
            }, (rsp) => {

                console.log(rsp)
                if (paymentRequest.paymentMethod === 'kakao') {
                    if (rsp.success) {
                        this.paymentInfo = paymentRequest;
                        alert("결제가 성공적으로 완료되었습니다.");
                        router.push('/');

                    } else {
                        alert("결제 실패: " + rsp.error_msg);
                        router.push('/');
                    }

                }

                if (paymentRequest.paymentMethod === 'toss') {
                    if (rsp.error_msg == null) {
                        this.paymentInfo = paymentRequest;
                        alert("결제가 성공적으로 완료되었습니다.");
                        router.push('/');

                    } else {
                        alert("결제 실패: " + rsp.error_msg);
                        router.push('/');
                    }

                }
            });
        }
    }
});
