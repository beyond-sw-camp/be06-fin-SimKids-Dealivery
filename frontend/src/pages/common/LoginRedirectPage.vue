<template>
  <br />
</template>

<script>
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";
export default {
  computed: {
    ...mapStores(useUserStore),
  },
  name: "LoginRedirectPage",
  mounted() {
    const query = new URLSearchParams(window.location.search);
    const isSuccess = query.get("isSuccess") === "true";
    if (isSuccess) {
      const isExist = query.get("isExist") === "true";
      if (isExist) {
        const role = query.get("role");
        this.userStore.isLogined = true;
        this.userStore.roles = [role];
        const redirect = this.userStore.socialRedirect || '/';
        this.$router.push(redirect); // 로그인 후 리다이렉트
        this.userStore.socialRedirect = "";
        console.log(this.userStore.socialRedirect);
      } else {
        this.userStore.socialLoginResponse.email = decodeURIComponent(
          query.get("email")
        );
        this.userStore.socialLoginResponse.name = decodeURIComponent(
          query.get("name")
        );
        this.userStore.socialLoginResponse.type = decodeURIComponent(
          query.get("type")
        );
        this.$router.push("/auth/social/signup");
      }
    } else {
      this.$router.push("/auth/login");
      alert(
        "로그인에 실패했습니다. 해당 이메일로 이미 가입된 아이디가 있습니다."
      );
    }
  },
};
</script>
<style scoped></style>
