import { defineStore } from "pinia";
import axios from "axios";
import * as PortOne from "@portone/browser-sdk/v2";

// const backend = "http://localhost:8080/api/orders";

export const useOrderStore = defineStore('order', {
    state: () => ({
        orderedProducts: [], orderIdx: null, paymentId: "", boardInfo: null, orderInfo: null, paymentInfo: null
    }),
    actions: {
        async submitOrder(orderRequest) { // 주문 생성 : 백엔드에 주문 가능하면 200 Ok, 재고 부족 or 이미 끝난 이벤트 : 400 error
            this.boardInfo = orderRequest.boardInfo

            try {
                let response = await axios.post("https://run.mocky.io/v3/bba611fe-6871-4be8-9e5d-c9251ba9090a", orderRequest, { withCredentials: true });
                console.log("Response : " + response);

                if (response.data.code !== 1000) {
                    return false;
                }

                this.orderedProducts = orderRequest.cartItems
                this.orderIdx = response.data.data.orderIdx

                return true;

            } catch (error) {
                alert("주문에 실패했습니다\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.");
            }
        },

        async makePayment(paymentRequest) {
            let channelKey = paymentRequest.paymentMethod === 'kakao' ? process.env.VUE_APP_KAKAOPAY_CHANNEL : process.env.VUE_APP_TOSSPAY_CHANNEL

            let paymentId = "order_no_000" + new Date().getMilliseconds()

            const response = await PortOne.requestPayment({
                customData: this.orderRequest,
                storeId: process.env.VUE_APP_PORTONE_STORE_ID,
                paymentId: paymentId,
                orderName: this.boardInfo.title,
                totalAmount: paymentRequest.totalAmount,
                currency: "KRW",
                channelKey: channelKey,
                payMethod: "EASY_PAY"
            });


            this.paymentInfo = paymentRequest

            if (response.code != null) { // 결제 오류 발생
                //TODO: backend에 refund하는 함수 호출
                alert(response.message);
                return false;
            }

            //TODO: backend에 결제 검증하는 함수 호출 -> paymentId 넘기고, Orderid 넘김
            alert("주문 완료되었습니다");
            return true;

        },
    }

});