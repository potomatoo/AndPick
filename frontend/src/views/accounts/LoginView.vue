<template>
  <div>
    <h1>Login</h1>
    <div class="form-group">
      <label for="useremail">이메일</label>
      <input
        v-model="loginData.useremail"
        class="form-control"
        id="useremail"
        type="text"
        placeholder="이메일"
      />
    </div>
    <div class="form-group">
      <label for="password">비밀번호</label>
      <input
        v-model="loginData.password"
        class="form-control"
        id="password"
        type="password"
        placeholder="비밀번호"
      />
    </div>
    <div>
      <button @click="login(loginData)">Login</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import { mapActions } from "vuex";

interface LoginData {
  useremail: string | null;
  password: string | null;
}

@Component({
  methods: {
    ...mapActions(["login"]),
  },
})
export default class LoginView extends Vue {
  loginData: LoginData = {
    useremail: null,
    password: null,
  };
}
</script>

<style></style>
