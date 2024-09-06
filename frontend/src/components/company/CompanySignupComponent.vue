<template>
  <div class="css-pculus e1ovi4141">
    <div class="css-o5dw7d e1ovi4140">업체회원가입</div>
    <div class="css-mhmtvt e15so55l1">
      <div class="css-rb0i47 e1yyjjij1"><span class="css-qq9ke6 e1yyjjij0">*</span> 필수입력사항</div>
      <div class="css-y8aj3r eo6ykj40">
        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">아이디<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="memberId" name="memberId"
                  placeholder="아이디를 입력해주세요" type="text" required="" class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"><button class="css-ufulao e4nu7ef3" type="button" @click="doEmailAuth"><span
                class="css-nytqmg e4nu7ef1">
                <img src="https://res.kurly.com/pc/service/cart/2007/ico_search.svg" alt=""
                  class="css-1m3kac1 e4nu7ef0">이메일 인증</span></button></div>
        </div>

        <!-- 안보이다가 이메일 인증버튼 클릭시 보여지게 -->
        <div v-if="isClickedEmailAuth" class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">인증코드<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="memberId" name="memberId"
                  placeholder="인증코드 6자리" type="text" required="" class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">비밀번호<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="password" name="password"
                  placeholder="비밀번호를 입력해주세요" type="password" autocomplete="off" class="css-u52dqk e1uzxhvi2" value="">
              </div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>
        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">비밀번호확인<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="passwordConfirm"
                  name="passwordConfirm" placeholder="비밀번호를 한번 더 입력해주세요" type="password" autocomplete="off"
                  class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>
        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">이름<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="name" name="name"
                  placeholder="이름을 입력해 주세요" type="text" required="" class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">휴대폰<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber" name="mobileNumber"
                  placeholder="-를 포함하여 입력해주세요." type="tel" required="" class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">주소<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber" name="mobileNumber"
                  readonly type="text" required="" class="css-u52dqk e1uzxhvi2 read-only" value="" v-model="address" ></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"><button class="css-ufulao e4nu7ef3" type="button" @click="openPostcode"><span
                class="css-nytqmg e4nu7ef1">
                <img src="https://res.kurly.com/pc/service/cart/2007/ico_search.svg" alt=""
                  class="css-1m3kac1 e4nu7ef0">주소 검색</span></button></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">우편번호<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber" name="mobileNumber"
                  readonly type="text" required="" class="css-u52dqk e1uzxhvi2 read-only" value="" v-model="postNumber"></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">상세주소<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber" name="mobileNumber"
                  placeholder="상세주소를 입력해주세요." type="text" required="" class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">업체이름<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber" name="mobileNumber"
                  placeholder="업체이름을 입력해주세요." type="text" required="" class="css-u52dqk e1uzxhvi2" value=""></div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">사업자등록번호<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-18n8lnw e1ke3ehm1">
              <div class="css-1dkwuq4 e1uzxhvi6">
                <div height="40" class="css-xsmgyi e1uzxhvi3"><input data-testid="input-box" name="birthYear"
                    placeholder="NNN" type="text" height="40" class="css-151eme7 e1uzxhvi2" maxlength="3" value="">
                </div>
              </div><span class="css-5lnvt6 e1ke3ehm0"></span>
              <div class="css-1dkwuq4 e1uzxhvi6">
                <div height="40" class="css-xsmgyi e1uzxhvi3"><input data-testid="input-box" name="birthMonth"
                    placeholder="NN" type="text" height="40" class="css-151eme7 e1uzxhvi2" maxlength="2" value=""></div>
              </div><span class="css-5lnvt6 e1ke3ehm0"></span>
              <div class="css-1dkwuq4 e1uzxhvi6">
                <div height="40" class="css-xsmgyi e1uzxhvi3"><input data-testid="input-box" name="birthDay"
                    placeholder="NNNNN" type="text" height="40" class="css-151eme7 e1uzxhvi2" maxlength="5" value="">
                </div>
              </div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">개업일자<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-18n8lnw e1ke3ehm1">
              <div class="css-1dkwuq4 e1uzxhvi6">
                <div height="40" class="css-xsmgyi e1uzxhvi3"><input data-testid="input-box" name="birthYear"
                    placeholder="YYYY" type="text" height="40" class="css-151eme7 e1uzxhvi2" maxlength="4" value="">
                </div>
              </div><span class="css-5lnvt6 e1ke3ehm0"></span>
              <div class="css-1dkwuq4 e1uzxhvi6">
                <div height="40" class="css-xsmgyi e1uzxhvi3"><input data-testid="input-box" name="birthMonth"
                    placeholder="MM" type="text" height="40" class="css-151eme7 e1uzxhvi2" maxlength="2" value=""></div>
              </div><span class="css-5lnvt6 e1ke3ehm0"></span>
              <div class="css-1dkwuq4 e1uzxhvi6">
                <div height="40" class="css-xsmgyi e1uzxhvi3"><input data-testid="input-box" name="birthDay"
                    placeholder="DD" type="text" height="40" class="css-151eme7 e1uzxhvi2" maxlength="2" value=""></div>
              </div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>

        <div class="css-1pjgd36 e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">통신판매업신고번호<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-jmalg e1uzxhvi6">
              <div class="css-176lya2 e1uzxhvi3"><input data-testid="input-box" id="mobileNumber" name="mobileNumber"
                  placeholder="통신판매업신고번호를 입력해주세요. (-포함)" type="text" required="" class="css-u52dqk e1uzxhvi2" value="">
              </div>
            </div>
          </div>
          <div class="css-1w0ksfz e744wfw2"></div>
        </div>
        <div class="css-1w0ksfz e744wfw2"></div>
      </div>

      <div class="css-1w0ksfz e744wfw2"></div>

      <div class="css-1eo0fey e1j7jtti0"></div>


      <div class="css-y8aj3r e1k9oeg10">
        <div class="css-2yzr8b e744wfw6">
          <div class="css-1y8737n e744wfw5"><label class="css-1obgjqh e744wfw4">이용약관동의<span
                class="css-qq9ke6 e744wfw0">*</span></label></div>
          <div class="css-82a6rk e744wfw3">
            <div class="css-ov2xfu e1sjmfnv6"><label class="css-msja7w e1dcessg3" for="TermsAgreeAll"><input
                  id="TermsAgreeAll" type="checkbox" class="css-agvwxo e1dcessg2">
                <div class="css-79hxr7 e1dcessg1">
                  <img :src="radioAllStatus ? require('@/assets/filled-custom-radio.svg') : require('@/assets/outline-custom-radio.svg')" @click="checkRadio('radioAll')" alt="Icon">  
                </div><span>전체 동의합니다.</span>
              </label>

            </div>
            <div class="css-ov2xfu e1sjmfnv6">
              <div class="css-s5xdrg e1sjmfnv3"><label class="css-1mjkje9 e1dcessg3" for="RequiredTermsCondition"><input
                    id="RequiredTermsCondition" type="checkbox" class="css-agvwxo e1dcessg2">
                  <div class="css-79hxr7 e1dcessg1">
                    <img :src="radioFirstStatus ? require('@/assets/filled-custom-radio.svg') : require('@/assets/outline-custom-radio.svg')" @click="checkRadio('radioFirst')" alt="Icon">
                  </div><span>이용약관 동의</span>
                </label><span class="css-64z8en e1sjmfnv4">(필수)</span></div><a class="css-7chi73 e1sjmfnv2">약관보기</a>
            </div>
            <div class="css-ov2xfu e1sjmfnv6">
              <div class="css-s5xdrg e1sjmfnv3"><label class="css-1mjkje9 e1dcessg3" for="RequiredTermsOfPrivacy"><input
                    id="RequiredTermsOfPrivacy" type="checkbox" class="css-agvwxo e1dcessg2">
                  <div class="css-79hxr7 e1dcessg1">
                    <img :src="radioSecondStatus ? require('@/assets/filled-custom-radio.svg') : require('@/assets/outline-custom-radio.svg')" @click="checkRadio('radioSecond')" alt="Icon">
                  </div><span>개인정보 수집∙이용 동의</span>
                </label><span class="css-64z8en e1sjmfnv4">(필수)</span></div><a class="css-7chi73 e1sjmfnv2">약관보기</a>
            </div>



            <div class="css-ov2xfu e1sjmfnv6">
              <div class="css-s5xdrg e1sjmfnv3"><label class="css-1mjkje9 e1dcessg3" for="RequiredSignupAge"><input
                    id="RequiredSignupAge" type="checkbox" class="css-agvwxo e1dcessg2">
                  <div class="css-79hxr7 e1dcessg1">
                    <img :src="radioThirdStatus ? require('@/assets/filled-custom-radio.svg') : require('@/assets/outline-custom-radio.svg')" @click="checkRadio('radioThird')" alt="Icon">
                  </div><span>본인은 만 14세 이상입니다.</span>
                </label><span class="css-64z8en e1sjmfnv4">(필수)</span></div>
            </div>
          </div>
        </div>
      </div>
      <div class="css-ud3rkg e1utgeno0"><button class="css-18m884r e4nu7ef3" type="submit" width="240" height="56"
          radius="3"><span class="css-nytqmg e4nu7ef1">가입하기</span></button></div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'CompanySignupComponent',
  data() {
    return {
      address: "", postNumber: "", isClickedEmailAuth: false,
      radioAllStatus: false, radioFirstStatus: false, radioSecondStatus: false, radioThirdStatus: false
    }
  },
  methods: {
        openPostcode() {
            let width = 500;
            let height = 600;

            // eslint가 daum을 전역변수로 인식할 수 있도록 끄는 주석
            console.log("gd");
            // eslint-disable-next-line no-undef
            new daum.Postcode({
                width: width,
                height: height,
                oncomplete: (data) => {
                    this.address = data.address;
                    this.postNumber = data.zonecode;
                }
            }).open({
                left: (window.screen.width / 2) - (width / 2),
                top: (window.screen.height / 2) - (height / 2)
            });
        },
        doEmailAuth() {
            this.isClickedEmailAuth = true;
        },
        checkRadio(target){
            if(target === "radioAll"){
                if(!this.radioAllStatus){
                    this.radioAllStatus = true;
                    this.radioFirstStatus = true;
                    this.radioSecondStatus = true;
                    this.radioThirdStatus = true;
                }else{
                    this.radioAllStatus = false;
                    this.radioFirstStatus = false;
                    this.radioSecondStatus = false;
                    this.radioThirdStatus = false;
                }
                
            }else if(target === "radioFirst"){
                this.radioFirstStatus = !this.radioFirstStatus
            }else if(target === "radioSecond"){
                this.radioSecondStatus = !this.radioSecondStatus
            }else{
                this.radioThirdStatus = !this.radioThirdStatus
            }
            
            this.checkRadioAll();
        },
        checkRadioAll(){
            if(this.radioFirstStatus && this.radioSecondStatus && this.radioThirdStatus){
                this.radioAllStatus = true;
            }else{
                this.radioAllStatus = false;
            }
        }
    }

}
</script>
<style scoped>
*{
    font-family: "Noto Sans KR", "malgun gothic", "AppleGothic", "dotum", "sans-serif";
}

img{
  cursor: pointer;
}

.read-only{
    background-color: #f1f3f5;
}

.css-pculus {
    min-width: 1050px;
    margin-top: 50px;
    margin-bottom: 60px;
    background-color: #fff;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

*, :after, :before, legend, td, th {
    padding: 0;
}

div {
    display: block;
    unicode-bidi: isolate;
}

body, button, input, select, textarea {
    font-size: 14px;
    color: #333;
}
body {
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-color: #fff;
    -webkit-tap-highlight-color: transparent;
}

html, button, input, select, textarea {
    font-family: "Noto Sans KR", "malgun gothic", AppleGothic, dotum, sans-serif;
}

html {
    line-height: 1.15;
    -webkit-text-size-adjust: 100%;
}

.css-o5dw7d {
    margin-bottom: 50px;
    font-size: 28px;
    line-height: 35px;
    font-weight: 500;
    text-align: center;
    letter-spacing: -1px;
    color: #333;
}

.css-mhmtvt {
    width: 640px;
    margin: 0px auto;
}

.css-rb0i47 {
    padding-bottom: 10px;
    border-bottom: 2px solid rgb(51, 51, 51);
    font-size: 12px;
    color: rgb(102, 102, 102);
    line-height: 17px;
    text-align: right;
}

.css-qq9ke6 {
    color: rgb(238, 106, 123);
}

.css-y8aj3r {
    padding: 0px;
}

.css-1pjgd36 {
    display: inline-flex;
    width: 100%;
    padding: 10px 20px;
}

.css-1y8737n {
    width: 150px;
    padding-top: 12px;
}

.css-1obgjqh {
    font-weight: 500;
    color: rgb(51, 51, 51);
    line-height: 20px;
}

.css-qq9ke6 {
    color: rgb(238, 106, 123);
}

.css-82a6rk {
    flex: 1 1 0%;
}

.css-jmalg {
    padding-bottom: 0px;
}

.css-176lya2 {
    position: relative;
    height: 48px;
}

.css-u52dqk {
    width: 100%;
    height: 46px;
    padding: 0px 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid rgb(221, 221, 221);
    font-weight: 400;
    font-size: 16px;
    line-height: 1.5;
    color: rgb(51, 51, 51);
    outline: none;
    box-sizing: border-box;
}

.css-1w0ksfz {
    width: 120px;
    margin-left: 8px;
}

.css-1pjgd36 {
    display: inline-flex;
    width: 100%;
    padding: 10px 20px;
}

.css-1y8737n {
    width: 150px;
    padding-top: 12px;
}

.css-82a6rk {
    flex: 1 1 0%;
}

.css-jmalg {
    padding-bottom: 0px;
}

.css-1w0ksfz {
    width: 120px;
    margin-left: 8px;
}
.css-ufulao:disabled {
    border-color: rgb(221, 221, 221);
    color: rgb(221, 221, 221);
}
.css-1w0ksfz > button {
    height: 44px;
    border-radius: 3px;
}

button[disabled], input[disabled] {
    cursor: default;
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

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

button, select {
    text-transform: none;
}

button, input, optgroup, select, textarea {
    font-family: inherit;
    font-size: 100%;
    line-height: 1.15;
    margin: 0;
}

button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.css-1w0ksfz > button > span {
    font-weight: 500;
    font-size: 14px;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}

.css-ufulao:disabled {
    border-color: rgb(221, 221, 221);
    color: rgb(221, 221, 221);
}

.css-1m3kac1 {
    display: inline-block;
    width: 20px;
    height: 20px;
    margin-right: 4px;
    vertical-align: middle;
}

img {
    border-style: none;
}

img, legend {
    border: 0;
    vertical-align: top;
}

canvas, img, video {
    max-width: 100%;
}

.css-1eo0fey {
    padding: 10px 0px;
    border-bottom: 1px solid rgb(51, 51, 51);
}

.css-y8aj3r {
    padding: 0px;
}

.css-2yzr8b {
    display: inline-flex;
    width: 100%;
    padding: 10px 0px 10px 20px;
}

.css-1y8737n {
    width: 150px;
    padding-top: 12px;
}

.css-ov2xfu:first-of-type {
    align-items: flex-start;
    flex-direction: column;
    padding-top: 12px;
}

.css-ov2xfu {
    display: flex;
    padding: 8px 0px;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    align-items: center;
}

.css-msja7w {
    position: relative;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    vertical-align: top;
    line-height: normal;
    color: rgb(51, 51, 51);
    padding: 0px;
    font-weight: 500;
    font-size: 18px;
}

input[type=checkbox], input[type=radio] {
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

svg:not(:root) {
    overflow-clip-margin: content-box;
    overflow: hidden;
}

:not(svg) {
    transform-origin: 0px 0px;
}
.css-s5xdrg {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
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

.css-y8aj3r {
    padding: 0px;
}

.css-2yzr8b {
    display: inline-flex;
    width: 100%;
    padding: 10px 0px 10px 20px;
}

*, :after, :before {
    box-sizing: border-box;
    margin: 0;
}

div {
    display: block;
    unicode-bidi: isolate;
}

.css-82a6rk {
    flex: 1 1 0%;
}

.css-ud3rkg {
    display: flex;
    -webkit-box-pack: center;
    justify-content: center;
    border-top: 1px solid rgb(247, 247, 247);
    padding: 40px 0px;
}

.css-18m884r {
    display: block;
    padding: 0px 10px;
    text-align: center;
    overflow: hidden;
    width: 240px;
    height: 56px;
    border-radius: 3px;
    color: rgb(255, 255, 255);
    background-color: rgb(95, 0, 128);
    border: 0px none;
}

[type=button], [type=reset], [type=submit], button {
    -webkit-appearance: button;
}

.css-nytqmg {
    display: inline-block;
    font-size: 16px;
    font-weight: 500;
}
button, input[type=button], input[type=reset], input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
}

.css-1pjgd36 {
    display: inline-flex;
    width: 100%;
    padding: 10px 20px;
}

.css-18n8lnw {
    height: 44px;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    width: 100%;
    border: 1px solid rgb(204, 204, 204);
    border-radius: 3px;
    padding: 0px 15px;
}
.css-1dkwuq4 {
    padding-bottom: 0px;
}

.css-xsmgyi {
    position: relative;
    height: 40px;
}

.css-1dkwuq4 input {
    text-align: center;
    font-size: 14px;
    border: none;
}

.css-151eme7 {
    width: 100%;
    height: 40px;
    padding: 0px 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid rgb(221, 221, 221);
    font-weight: 400;
    font-size: 16px;
    line-height: 38px;
    color: rgb(51, 51, 51);
    outline: none;
    box-sizing: border-box;
}

.css-5lnvt6 {
    height: 100%;
}

.css-5lnvt6::after {
    content: "/";
    font-size: 14px;
    color: rgb(204, 204, 204);
    text-align: center;
    line-height: 40px;
}
</style>
