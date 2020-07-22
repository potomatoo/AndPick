<template>
  <div>
    <h1>Signup</h1>
    <div class="form-group">
      <label for="username">아이디</label>
      <input
        v-model="signupData.username"
        class="form-control"
        id="username"
        type="text"
        placeholder="이메일을 입력하세요"
      />
    </div>
    <div class="form-group">
      <label for="password1">비밀번호</label>
      <input
        v-model="signupData.password1"
        class="form-control"
        id="password1"
        type="password"
        placeholder="비밀번호"
      />
    </div>
    <div class="form-group">
      <label for="password2">비밀번호 확인</label>
      <input
        v-model="signupData.password2"
        class="form-control"
        id="password2"
        type="password"
        placeholder="비밀번호 확인"
      />
    </div>
    <div>
      <button @click="signup(signupData)">Signup</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import { mapActions } from "vuex";

interface SignupData {
  username: string | null;
  password1: string | null;
  password2: string | null;
}

@Component({
  methods: {
    ...mapActions(["signup"]),
  },
})
@Component
export default class SignupView extends Vue {
  signupData: SignupData = {
    username: null,
    password1: null,
    password2: null,
  };
}
</script>

<style></style>
