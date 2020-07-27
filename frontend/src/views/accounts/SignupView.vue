<template>
  <div>
    <h1>Signup</h1>
    <div>
      <label for="userid">userID:</label>
      <input v-model="signupData.userid" id="userid" type="text" />
    </div>
    <div>
      <label for="username">username:</label>
      <input v-model="signupData.username" id="username" type="text" />
    </div>
    <div>
      <label for="userpassword">password:</label>
      <input
        v-model="signupData.userpassword"
        id="userpassword"
        type="password"
      />
    </div>
    <div>
      <input v-model="signupData.usertype" id="usertype" type="hidden" />
    </div>
    <div>
      <button @click="signup(signupData)">Signup</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "SignupView",
  data() {
    return {
      signupData: {
        userid: null,
        username: null,
        userpassword: null,
        usertype: 1,
      },
    };
  },
  methods: {
    ...mapActions(["signup"]),
  },
};
</script>

<style></style>
