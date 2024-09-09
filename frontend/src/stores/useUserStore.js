import { defineStore } from "pinia";
import axios from "axios";

// const backend = "http://localhost:8080/api/login";
//로그인 성공 요청 mocky url: https://run.mocky.io/v3/cd7370a5-b1be-41a1-b680-4f93dd1a67bc

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
        }
    }

});