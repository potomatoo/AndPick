<template>
  <div>
    <h1>Login</h1>
    <div class="form-group">
      <label for="user_id">이메일</label>
      <input
        v-model="loginData.user_id"
        class="form-control"
        id="user_id"
        type="text"
        placeholder="이메일"
      />
    </div>
    <div class="form-group">
      <label for="user_password">비밀번호</label>
      <input
        v-model="loginData.user_password"
        class="form-control"
        id="user_password"
        type="password"
        placeholder="비밀번호"
      />
    </div>
    <div>
      <button @click="login(loginData)">Login</button>
    </div>
    <a
      href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&
            include_granted_scopes=true&response_type=token&state=state_parameter_passthrough_value&
            redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Faccounts%2Flogin&
            client_id=476248660063-e2gk89ukcim2la7mbttisi10pq9ck5r6.apps.googleusercontent.com"
      >Login with google
    </a>
    <!-- <div class="g-signin2" data-onsuccess="onSignIn"></div> -->
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import { mapActions } from "vuex";

interface LoginData {
  user_id: string | null;
  user_password: string | null;
}

@Component({
  methods: {
    ...mapActions(["login"]),
  },
})
export default class LoginView extends Vue {
  loginData: LoginData = {
    user_id: null,
    user_password: null,
  };
}
</script>

<style></style>
