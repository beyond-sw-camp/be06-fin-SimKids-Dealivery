import { defineStore } from "pinia";
import axios from "axios";

const backend = "/api"

export const useUserStore = defineStore("user", {
  state: () => ({
    isLogined: false,
    roles: [],
    socialLoginResponse: {
      name: "",
      email: "",
      type: "",
    },
    userLoginRequest: {
      email: "",
      password: "",
    }

  }),
  actions: {
    async login(type, loginRequest) {
      try {
        this.userLoginRequest.email = loginRequest.email + ";" + type;
        this.userLoginRequest.password = loginRequest.password;
        let response = await axios.post(
          backend+"/login",
          this.userLoginRequest,
          { withCredentials: true }
        );
        if (response.status === 200) {
          this.roles = [response.data.result.role];
          this.isLogined = true;
          return true;
        }else{
          return false;
        }
      } catch (error) {
        return false;
      }
    },

    async userSignup(request) {
      try {
        let response = await axios.post(
          backend+"/user/signup",
          request,
          { withCredentials: true }
        );
        if (response.data.code === 2062){
          alert("이미 가입된 이메일입니다.");
          return false;
        }
        if (response.data.code === 2058) {
          alert("이메일 인증 코드가 일치하지 않거나 유효하지 않습니다.")
          return false;
        }
        if (response.data.code === 2059){
          alert("이메일 인증 코드가 만료되었습니다. 다시 인증 메일을 발송해주세요");
          return false;
        }
        if(response.data.code === 2060){
          alert("이메일 인증 코드가 일치하지 않습니다.");
          return false;
        }

        return true;
      
      } catch (error) {
        alert(
          "회원가입 요청에 실패했습니다. 입력한 정보를 다시 한 번 확인해주세요.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다."
        );
        return false;
      }
    },

    async companySignup(request) {
      try {
        let response = await axios.post(
          backend+"/company/signup",
          request,
          { withCredentials: true }
        );
        if (response.data.code === 2062){
          alert("이미 가입된 이메일입니다.");
          return false;
        }
        if (response.data.code === 2058) {
          alert("이메일 인증 코드가 일치하지 않거나 유효하지 않습니다.")
          return false;
        }
        if (response.data.code === 2059){
          alert("이메일 인증 코드가 만료되었습니다. 다시 인증 메일을 발송해주세요");
          return false;
        }
        if(response.data.code === 2060){
          alert("이메일 인증 코드가 일치하지 않습니다.");
          return false;
        }
        if(response.data.code === 2049){
          alert("사업자등록번호 인증에 실패했습니다.");
          return false;
        }
        return true;
      } catch (error) {
        alert(
          "이미 등록된 사업자 정보입니다."
        );
        return false;
      }
    },

    async sendUserEmailCode(userEmailAuthRequest) {
      try {
        let response = await axios.post(
          backend+"/user/email/verify",
          userEmailAuthRequest,
          { withCredentials: true }
        );
        if (response.data.code !== 1000) {
          return false;
        } else {
          return true;
        }
      } catch (error) {
        alert(
          "인증번호 전송에 실패했습니다. 입력한 이메일 주소를 다시 한 번 확인해주세요.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다."
        );
      }
    },

    async sendCompanyEmailCode(companyEmailAuthRequest) {
      try {
        let response = await axios.post(
          backend+"/company/email/verify",
          companyEmailAuthRequest,
          { withCredentials: true }
        );
        if (response.data.code !== 1000) {
          return false;
        } else {
          return true;
        }
      } catch (error) {
        alert(
          "인증번호 전송에 실패했습니다. 입력한 이메일 주소를 다시 한 번 확인해주세요.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다."
        );
      }
    },
    
    async socialLogin(loginType){
      try{
        window.location.href = backend + "/oauth2/authorization/"+loginType;
      }catch{
        alert("소셜 로그인 요청 수행중 문제가 발생했습니다.");
      }
      
    },

    async socialSignup(socialSignupRequest){
      try {
        let response = await axios.post(
          backend+"/user/social/signup",
          socialSignupRequest,
          { withCredentials: true }
        );
        if (response.data.code !== 1000 || response.status !== 200) {
          return false;
        } else {
          return true;
        }
      } catch (error) {
        alert(
          "회원가입에 실패했습니다. 입력한 정보를 다시 한 번 확인해주세요.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다."
        );
        return false;
      }
    },

    async logout(){
      try{
        let response = await axios.post(backend+"/logout", null, {withCredentials: true});
        if (response.status !== 200){
          return false;
        }else{
          this.isLogined = false;
          this.roles = [];
          return true;
        }
      }catch{
        alert("로그아웃 요청 수행중 문제가 발생했습니다.");
      }
    }
  },
});
