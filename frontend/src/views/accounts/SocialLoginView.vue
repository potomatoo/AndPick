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
          return this.$http.post("http://your-backend-server.com/auth/google", {
            code: authCode,
            redirect_uri: "postmessage",
          });
        })
        .then((response) => {
          //and then
        })
        .catch((error) => {
          console.log("소셜", error);
        });
    },
  },
  created() {
    this.handleClickGetAuth();
  },
};
</script>
