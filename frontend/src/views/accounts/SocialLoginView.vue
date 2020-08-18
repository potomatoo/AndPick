<template>
  <div></div>
</template>

<script>
import axios from "axios";
import qs from "qs";
import { helpers } from "vuelidate/lib/validators";

export default {
  name: "test",
  methods: {
    handleClickGetAuth() {
      this.$gAuth
        .getAuthCode()
        .then((authToken) => {
          return this.$http.post(
            "http://i3b107.p.ssafy.io:8080/api/public/google/login",
            authToken
          );
        })
        .then((res) => {
          this.$store.commit("SET_TOKEN", res.data.data["userPassword"]);
          this.$store.commit("SET_NAME", res.data.data.userName);
          this.$router.push("/");
        })
        .catch((err) => {
          console.log("소셜에러", err);
          this.$router.push("/");
        });
    },
  },
  created() {
    this.handleClickGetAuth();
  },
};
</script>
