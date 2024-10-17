<template>
    <div class="css-heioij eug5r8l1">
        <div class="css-oc8mjz ed9qr673">
            <div class="css-eq7f8j ed9qr672">
        <span class="css-1vbzf1d ed9qr670"
          >정보 수정은 좌측 개인정보 수정 페이지를 이용해주세요.</span
        >
      </div>
        </div>
        <div class="css-ehagcz eug5r8l0">
            <div class="css-1c8rsf3 e1w140tr0">
                <form>
                    <div class="css-1pjgd36 e744wfw6">
                        <div class="css-1y8737n e744wfw5"><label for="userId"
                                class="css-1obgjqh e744wfw4">아이디(이메일)</label>
                        </div>
                        <div class="css-82a6rk e744wfw3">
                            <div class="css-1waqr6j e1uzxhvi6">
                                <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber"
                                        name="email" placeholder="" type="text" readonly
                                        class="css-u52dqk e1uzxhvi2 text-only" :value="userStore.userDetail.email" ></div>
                            </div>
                        </div>
                        <div class="css-1w0ksfz e744wfw2">
                            
                        </div>
                    </div>
                    <div class="css-1pjgd36 e744wfw6">
                        <div class="css-1y8737n e744wfw5"><label for="userId"
                                class="css-1obgjqh e744wfw4">이름</label>
                        </div>
                        <div class="css-82a6rk e744wfw3">
                            <div class="css-1waqr6j e1uzxhvi6">
                                <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="name"
                                        name="name" placeholder="" type="text" readonly
                                        class="css-u52dqk e1uzxhvi2 text-only" :value="userStore.userDetail.name" ></div>
                            </div>
                        </div>
                        <div class="css-1w0ksfz e744wfw2"></div>
                    </div>
                    

                    <div class="css-1pjgd36 e744wfw6">
                        <div class="css-1y8737n e744wfw5"><label for="mobileNumber"
                                class="css-1obgjqh e744wfw4">휴대폰 번호</label></div>
                        <div class="css-82a6rk e744wfw3">
                            <div class="css-1waqr6j e1uzxhvi6">
                                <div class="css-176lya2 e1uzxhvi3"><input v-model="editedUserDetail.phoneNumber" data-testid="input-box" id="mobileNumber"
                                        name="mobileNumber" placeholder="-를 포함하여 입력해 주세요" type="text" readonly
                                        class="css-u52dqk e1uzxhvi2 text-only" value=""></div>
                            </div>
                        </div>

                        <div class="css-1w0ksfz e744wfw2"></div>
                    </div>

                    <div class="css-1pjgd36 e744wfw6">
                        <div class="css-1y8737n e744wfw5"><label for="newPasswordConfirm" class="css-1obgjqh e744wfw4">
                            주소
                        </label></div>
                        <div class="css-82a6rk e744wfw3">
                            <div class="css-1waqr6j e1uzxhvi6">
                                <div class="css-176lya2 e1uzxhvi3"><input v-model="editedUserDetail.address" data-testid="input-box"
                                        id="newPasswordConfirm" name="newPasswordConfirm"
                                        placeholder="주소를 검색해주세요" type="text" autocomplete="off"
                                        class="css-u52dqk e1uzxhvi2 text-only" readonly value=""></div>
                            </div>
                        </div>
                        <div class="css-1w0ksfz e744wfw2">
                            
                        </div>
                    </div>

                    <div class="css-1pjgd36 e744wfw6">
                        <div class="css-1y8737n e744wfw5"><label for="newPasswordConfirm" class="css-1obgjqh e744wfw4">
                                우편번호</label></div>
                        <div class="css-82a6rk e744wfw3">
                            <div class="css-1waqr6j e1uzxhvi6">
                                <div class="css-176lya2 e1uzxhvi3"><input v-model="editedUserDetail.postNumber" data-testid="input-box"
                                        id="newPasswordConfirm" name="newPasswordConfirm"
                                        placeholder="우편번호를 검색해주세요." type="text" readonly autocomplete="off"
                                        class="css-u52dqk e1uzxhvi2 text-only" value=""></div>
                            </div>
                        </div>
                        <div class="css-1w0ksfz e744wfw2"></div>
                    </div>

                    <div class="css-1pjgd36 e744wfw6">
                        <div class="css-1y8737n e744wfw5"><label for="newAddress" class="css-1obgjqh e744wfw4">상세 주소
                                </label></div>
                        <div class="css-82a6rk e744wfw3">
                            <div class="css-1waqr6j e1uzxhvi6">
                                <div class="css-176lya2 e1uzxhvi3"><input v-model="editedUserDetail.addressDetail" data-testid="input-box"
                                        id="newPasswordConfirm" name="newAddressDetail"
                                        placeholder="상세주소를 입력해 주세요" type="text" autocomplete="off"
                                        class="css-u52dqk e1uzxhvi2 text-only" readonly value=""></div>
                            </div>
                        </div>
                        <div class="css-1w0ksfz e744wfw2"></div>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
</template>
<script>
import { useUserStore } from '@/stores/useUserStore';
import { mapStores } from 'pinia';
export default {
    name: "MypageDetailComponent",
    data(){
        return {
            editedUserDetail:{
                phoneNumber: "",
                address: "",
                addressDetail: "",
                postNumber: ""
            }
        }
    },
    mounted(){
        this.editedUserDetail.phoneNumber = this.userStore.userDetail.phoneNumber;
        this.editedUserDetail.address = this.userStore.userDetail.address;
        this.editedUserDetail.addressDetail = this.userStore.userDetail.addressDetail;
        this.editedUserDetail.postNumber = this.userStore.userDetail.postNumber;
        window.scrollTo({
            top: 100,
            left: 0,
            behavior: 'smooth'
        });
    },  
    computed: {
        ...mapStores(useUserStore)
     },
     methods:{
        openPostcode() {
      let width = 500;
      let height = 600;

      // eslint가 daum을 전역변수로 인식할 수 있도록 끄는 주석

      // eslint-disable-next-line no-undef
      new daum.Postcode({
        width: width,
        height: height,
        oncomplete: (data) => {
          this.editedUserDetail.address = data.address;
          this.editedUserDetail.postNumber = data.zonecode;
        },
      }).open({
        left: window.screen.width / 2 - width / 2,
        top: window.screen.height / 2 - height / 2,
      });
    },
    toEdit(){
        this.$router.push("/mypage/detail/edit");
    }
    }
}
</script>
<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body,
button,
input,
select,
textarea {
    font-size: 14px;
    color: #333;
}

a {
    text-decoration: none;
    color: inherit;
}

/*entire - container*/
.css-heioij {
    overflow: hidden;
    width: 650px;
    background-color: rgb(255, 255, 255);
    border-radius: 16px;
}

/*top - title section*/
.css-oc8mjz {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    margin: 0px 20px;
    padding: 25px 0px 20px;
    border-bottom: 2px solid rgb(51, 51, 51);
}

/*title*/
.css-eq7f8j {
    display: flex;
    align-items: flex-end;
}

.css-1lmd4kz {
    font-weight: 500;
    font-size: 20px;
    letter-spacing: -0.5px;
    line-height: 28px;
}

/*content - section */
.css-ehagcz {
    padding: 0px 20px;
}

.css-1c8rsf3 {
    overflow: hidden;
    padding: 7px 0px 20px;
}

/*form area*/
.css-1pjgd36 {
    /*row*/
    display: inline-flex;
    width: 100%;
    height: 77px;
    padding: 10px 20px;
}

.css-1y8737n {
    width: 139px;
    padding-top: 12px;
}

.css-1obgjqh {
    font-weight: 500;
    color: rgb(51, 51, 51);
    line-height: 20px;
}

/*input*/
.css-82a6rk {
    flex: 1 1 0%;
}

.css-1waqr6j {
    padding: 0px;
}

.css-176lya2 {
    position: relative;
    height: 48px;
}

.css-1waqr6j input {
    font-size: 14px;
}

.css-u52dqk {
    width: 100%;
    height: 46px;
    padding: 0px 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid rgb(221, 221, 221);
    font-weight: 400;
    font-size: 14px;
    line-height: 1.5;
    color: rgb(51, 51, 51);
    outline: none;
    box-sizing: border-box;
    background-color: #fff;
}

.css-1w0ksfz {
    width: 100px;
    margin-left: 8px;
}

/*전화번호 인증 버튼*/
.css-1w0ksfz>button {
    height: 44px;
    border-radius: 3px;
}

.css-ufulao {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 3px;
    color: rgb(95, 0, 128);
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(95, 0, 128);
}

.css-1w0ksfz>button>span {
    font-weight: 500;
    font-size: 14px;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

/*선택 이용약관 동의*/
.css-bcffy2 {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: justify;
    justify-content: space-between;
}

.css-1n729k6 {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    padding: 12px 0px;
}

.css-1mjkje9 {
    position: relative;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    vertical-align: top;
    line-height: normal;
    color: rgb(51, 51, 51);
    padding: 0px;
    font-size: 14px;
}

input[type=checkbox],
input[type=radio] {
    box-sizing: border-box;
    padding: 0;
}

.css-agvwxo {
    overflow: hidden;
    position: absolute;
    clip: rect(0px, 0px, 0px, 0px);
    clip-path: inset(50%);
    width: 1px;
    height: 1px;
}

.css-79hxr7 {
    margin-right: 12px;
}

.css-79hxr7 svg {
    vertical-align: middle;
}

svg:not(:root) {
    overflow: hidden;
}

.css-14kcwq8 {
    color: rgb(153, 153, 153);
}

.css-qj3jn7 {
    margin-left: 5px;
    padding-right: 10px;
    background: url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI2IiBoZWlnaHQ9IjExIiB2aWV3Qm94PSIwIDAgNiAxMSI+CiAgICA8cGF0aCBmaWxsPSIjNUYwMDgwIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGQ9Ik0xIDExTDYgNS41IDEgMCAwIDEuMSA0IDUuNSAwIDkuOXoiLz4KPC9zdmc+Cg==) 100% 50% no-repeat;
    color: rgb(95, 0, 128);
    cursor: pointer;
}

/*구분 선*/
.css-10a7gq2 {
    height: 1px;
    background-color: rgb(153, 153, 153);
}

/*이용 약관 동의*/
.css-w9v2bl {
    display: inline-flex;
    width: 100%;
    padding: 10px 0px 10px 20px;
}

.css-1y8737n {
    width: 139px;
    padding-top: 12px;
}

.css-1rgde34 {
    position: relative;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    padding: 2px 0px 0px 24px;
}

.css-1rgde34 label:first-of-type {
    padding-right: 80px;
}

input[type=checkbox],
input[type=radio] {
    box-sizing: border-box;
    padding: 0;
}

/*bottom section*/
.css-14332pf {
    display: flex;
    -webkit-box-pack: center;
    justify-content: center;
    margin-top: 20px;
    padding-top: 40px;
    border-top: 1px solid rgb(244, 244, 244);
}

.css-14332pf>button {
    width: 120px;
    height: 44px;
    margin: 0px 3px;
    border-radius: 3px;
}

.css-1qirdbn {
    /*회원정보 수정 버튼*/
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 3px;
    color: rgb(255, 255, 255);
    background-color: rgb(95, 0, 128);
    border: 0px none;
}

.css-1vbzf1d {
    padding-left: 8px;
    font-size: 13px;
    line-height: 20px;
    letter-spacing: -0.2px;
    color: rgb(153, 153, 153);
}

.readonly{
    background-color: #f1f3f5;
}

button{
    cursor: pointer;
}

.text-only{
    border: none;
}


</style>