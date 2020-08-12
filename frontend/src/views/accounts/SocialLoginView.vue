<template>
  <div>
    소셜로그인
  </div>
</template>

<script>
export default {
  name: "test",
  methods: {
    handleClickGetAuth() {
      this.$gAuth
        .getAuthCode()
        .then((authCode) => {
          console.log("code", authCode);
          return this.$http.post("http://i3b107.p.ssafy.io/api/login", {
            userId: "",
            userPassword: "",
            userType: 1,
            code: authCode,
            redirect_uri: "postmessage",
          });
        })
        .then((res) => {
          this.$store.commit("SET_TOKEN", res.data.data["userPassword"]);
          this.$store.commit("SET_NAME", res.data.data.userName);
          this.$router.push("/");
        })
        .catch((err) => {
          console.log("소셜에러", err);
        });
    },
  },
  created() {
    this.handleClickGetAuth();
  },
};
</script>
