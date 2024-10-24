import { defineStore } from "pinia";
import axios from "axios";

const backend = "/api";

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
    },
    userDetail: {
      userIdx: null,
      name: "",
      email: "",
      address: "",
      addressDetail: "",
      postNumber: "",
      phoneNumber: "",
      deliveries: [],
      point: null,
    },
    socialRedirect: "",
  }),
  persist: {
    storage: sessionStorage,
    paths: ['isLogined', 'roles', 'socialLoginResponse', 'userDetail', 'socialRedirect'] 
  },
  actions: {
    async login(type, loginRequest) {
      try {
        this.userLoginRequest.email = loginRequest.email + ";" + type;
        this.userLoginRequest.password = loginRequest.password;
        let response = await axios.post(
          backend + "/login",
          this.userLoginRequest,
          { withCredentials: true }
        );
        if (response.status === 200) {
          this.roles = [response.data.result.role];
          this.isLogined = true;
          return true;
        } else {
          return false;
        }
      } catch (error) {
        return false;
      }
    },

    async userSignup(request) {
      try {
        let response = await axios.post(backend + "/user/signup", request, {
          withCredentials: true,
        });
        if (response.data.code === 2062) {
          alert("이미 가입된 이메일입니다.");
          return false;
        }
        if (response.data.code === 2058) {
          alert("이메일 인증 코드가 일치하지 않거나 유효하지 않습니다.");
          return false;
        }
        if (response.data.code === 2059) {
          alert(
            "이메일 인증 코드가 만료되었습니다. 다시 인증 메일을 발송해주세요"
          );
          return false;
        }
        if (response.data.code === 2060) {
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
        let response = await axios.post(backend + "/company/signup", request, {
          withCredentials: true,
        });
        if (response.data.code === 2062) {
          alert("이미 가입된 이메일입니다.");
          return false;
        }
        if (response.data.code === 2058) {
          alert("이메일 인증 코드가 일치하지 않거나 유효하지 않습니다.");
          return false;
        }
        if (response.data.code === 2059) {
          alert(
            "이메일 인증 코드가 만료되었습니다. 다시 인증 메일을 발송해주세요"
          );
          return false;
        }
        if (response.data.code === 2060) {
          alert("이메일 인증 코드가 일치하지 않습니다.");
          return false;
        }
        if (response.data.code === 2049) {
          alert("사업자등록번호 인증에 실패했습니다.");
          return false;
        }
        return true;
      } catch (error) {
        alert("이미 등록된 사업자 정보입니다.");
        return false;
      }
    },

    async sendUserEmailCode(userEmailAuthRequest) {
      try {
        let response = await axios.post(
          backend + "/user/email/verify",
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
          backend + "/company/email/verify",
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

    async socialLogin(loginType) {
      try {
        window.location.href =
          backend + "/oauth/oauth2/authorization/" + loginType;
      } catch {
        alert("소셜 로그인 요청 수행중 문제가 발생했습니다.");
      }
    },

    async socialSignup(socialSignupRequest) {
      try {
        let response = await axios.post(
          backend + "/user/social/signup",
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

    async logout() {
      try {
        let response = await axios.post(backend + "/logout", null, {
          withCredentials: true,
        });
        if (response.status !== 200) {
          return false;
        } else {
          this.isLogined = false;
          this.roles = [];
          return true;
        }
      } catch {
        alert("로그아웃 요청 수행중 문제가 발생했습니다.");
      }
    },
    async getDetail() {
      try {
        let response = await axios.get(backend + "/user/detail", {
          withCredentials: true,
        });
        if (response.data.code === 1000) {
          this.userDetail = response.data.result;
          console.log("받아온 디테일 정보 ====>" + response.data.result)
          console.log("회원 디테일 정보 ====>" + this.userDetail.userIdx)
          return true;
        } else {
          return false;
        }
      } catch {
        alert("회원정보 조회에 실패했습니다.");
      }
    },

    async getDeliveryList() {
      try {
        let response = await axios.get(backend + "/delivery/list", {
          withCredentials: true,
        });
        if (response.data.code === 1000) {
          this.userDetail.deliveries = response.data.result;
          return true;
        } else {
          return false;
        }
      } catch {
        alert("배송지 목록 조회에 실패했습니다.");
      }
    },

    async createDelivery(request) {
      try {
        let response = await axios.post(backend + "/delivery", request, {
          withCredentials: true,
        });
        if (response.data.code === 2064) {
          alert("해당 정보로 가입된 회원이 없습니다.");
          return false;
        }
        if (response.data.code === 2040) {
          alert(
            "배송지는 최대 10개까지 등록이 가능합니다. 삭제를 먼저 진행해주세요"
          );
          return false;
        }
        if (response.data.code !== 1000) {
          alert("문제가 발생했습니다.");
          return false;
        }
        return true;
      } catch (error) {
        alert(
          "배송지 추가에 실패했습니다.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다."
        );
        return false;
      }
    },
    async deleteDelivery(idx) {
      try {
        let response = await axios.delete(backend + "/delivery/" + idx, null, {
          withCredentials: true,
        });
        if (response.data.code !== 1000) {
          return false;
        }
        return true;
      } catch {
        alert("배송지 삭제에 실패했습니다.");
        return false;
      }
    },
    async setIsDefault(idx) {
      try {
        let response = await axios.patch(
          backend + "/delivery",
          { idx: idx },
          { withCredentials: true }
        );
        if (response.data.code != 1000) {
          return false;
        }
        return true;
      } catch {
        return false;
      }
    },
    async editDelivery(request) {
      try {
        let response = await axios.put(backend + "/delivery", request, {
          withCredentials: true,
        });
        if (response.data.code !== 1000) {
          return false;
        }
        return true;
      } catch (error) {
        return false;
      }
    },


    async editDetail(request) {
      try {
        let response = await axios.put(backend + "/user/edit", request, {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });
        if (response.data.code !== 1000) {
          return false;
        }
        return true;
      } catch (error) {
        return false;
      }
    },

    async like(request) {
      try {
        let response = await axios.post(backend + "/likes", request, {
          withCredentials: true,
        });
        if (response.data.code !== 1000) {
          return false;
        }
        return true;
      } catch (error) {
        return false;
      }
    },
  },
});
