<template>
  <div>
    소셜로그인
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "test",
  methods: {
    handleClickGetAuth() {
      this.$gAuth
        .getAuthCode()
        .then((authCode) => {
          // console.log("code", authCode);
          // return this.$http.get(
          //   "http://i3b107.p.ssafy.io/api/public/google/login",
          //   {
          //     // userId: "",
          //     // userPassword: "",
          //     // userType: 1,
          //     authToken: authCode,
          //     // redirect_uri: "postmessage",
          //   }
          // );
          axios
            .get("http://i3b107.p.ssafy.io/api/public/google/login", {
              authToken: authCode,
            })
            .then((res) => console.log(res));
        })
        // .then((res) => {
        //   console.log(res.data);
        //   // this.$store.commit("SET_TOKEN", res.data.data["userPassword"]);
        //   // this.$store.commit("SET_NAME", res.data.data.userName);
        //   // this.$router.push("/");
        // })
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
