<template>
  <div>
    <h1>Signup</h1>
    <div class="form-group">
      <label for="useremail">이메일</label>
      <input
        v-model="signupData.useremail"
        class="form-control"
        id="useremail"
        type="text"
        placeholder="이메일"
      />
    </div>
    <div class="form-group">
      <label for="username">닉네임</label>
      <input
        v-model="signupData.username"
        class="form-control"
        id="username"
        type="text"
        placeholder="닉네임"
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
    <div class="form-group">
      <input
        v-model="signupData.usertype"
        class="form-control"
        id="usertype"
        type="hidden"
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
  useremail: string | null;
  username: string | null;
  password1: string | null;
  password2: string | null;
  usertype: number;
}

@Component({
  methods: {
    ...mapActions(["signup"]),
  },
})
export default class SignupView extends Vue {
  signupData: SignupData = {
    useremail: null,
    username: null,
    password1: null,
    password2: null,
    usertype: 0,
  };
}
</script>

<style></style>
