import { defineStore } from "pinia";
import axios from "axios";

// const backend = "http://localhost:8080/api/login";
//로그인 성공 요청 mocky url: https://run.mocky.io/v3/cd7370a5-b1be-41a1-b680-4f93dd1a67bc
//일반회원가입 성공 요청 mocky url: https://run.mocky.io/v3/86385646-d65e-40fc-9173-b609a688da25
//이메일 인증 전송 요청 mocky url: https://run.mocky.io/v3/01cc84e7-5bef-41c8-8931-964f4b43fcbd

export const useUserStore = defineStore('user', {
    state: () => ({
        isLogined: false ,roles: []
    }),
    actions: {
        async login(type, loginRequest){
            try{
                loginRequest.id = loginRequest.id+";"+type;
                let response = await axios.post("https://run.mocky.io/v3/cd7370a5-b1be-41a1-b680-4f93dd1a67bc",loginRequest, {withCredentials: true});
                // let response = await axios.post(backend,loginRequest, {withCredentials: true});
                if (response.status === 200 && response.data.data.isLogin){
                    this.roles = [response.data.data.role];
                    this.isLogined = true;
                    return true;
                }else{
                    alert("로그인에 실패했습니다. 아이디 비밀번호를 확인해주세요.");
                }
            }catch(error){
                alert("로그인에 실패했습니다. 아이디 비밀번호를 확인해주세요.");
            }   
        },

        async userSignup(sigunupRequest){
            try{
                let response = await axios.post("https://run.mocky.io/v3/86385646-d65e-40fc-9173-b609a688da25",sigunupRequest, {withCredentials: true});
                // let response = await axios.post(backend,signupRequest, {withCredentials: true});
                console.log(sigunupRequest);
                console.log(response);
                if(response.data.code !== 1000){
                    return false;
                }else{
                    return true;
                }
            }catch(error){
                alert("회원가입에 실패했습니다. 입력한 정보를 다시 한 번 확인해주세요.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.");
            } 
        },

        async sendEmailCode(id){
            try{
                let response = await axios.post("https://run.mocky.io/v3/01cc84e7-5bef-41c8-8931-964f4b43fcbd",id, {withCredentials: true});
                console.log(response);
                // let response = await axios.post(backend, id, {withCredentials: true});
                if(response.data.code !== 1000){
                    return false;
                }else{
                    return true;
                }
            }catch(error){
                alert("인증번호 전송에 실패했습니다. 입력한 이메일 주소를 다시 한 번 확인해주세요.\n\n반복적인 문제 발생시 고객센터로 문의바랍니다.");
            } 
        }
    }

});