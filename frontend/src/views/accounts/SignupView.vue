<template>
  <div class="container my-5" style="width: 600px">
    <h1>Signup</h1>
    <div class="form-group">
      <label for="userId">이메일</label>
      <input
        v-model.trim="$v.signupData.userId.$model"
        class="form-control"
        id="userId"
        type="email"
        placeholder="이메일"
        :class="{
          'is-invalid': $v.signupData.userId.$error,
          'is-valid': !$v.signupData.userId.$invalid,
        }"
      />
      <div class="valid-feedback">Your ID is valid</div>
      <div class="invalid-feedback">
        <span v-if="!$v.signupData.userId.required"
          >ID는 필수(값) 입니다.
        </span>
        <span v-if="!$v.signupData.userId.isEmail"
          >이메일 형식이 아닙니다.
        </span>
      </div>
    </div>

    <div class="form-group">
      <label for="userName">닉네임</label>
      <input
        v-model.trim="$v.signupData.userName.$model"
        class="form-control"
        id="userName"
        type="text"
        :class="{
          'is-invalid': $v.signupData.userName.$error,
          'is-valid': !$v.signupData.userName.$invalid,
        }"
      />
      <div class="valid-feedback">Your nickname is valid</div>
      <div class="invalid-feedback">
        <span v-if="!$v.signupData.userName.required"
          >닉네임은 필수(값) 입니다.</span
        >
        <span v-if="!$v.signupData.userName.minLength"
          >닉네임은 {{ $v.signupData.userName.$params.minLength.min }} 글자
          이상으로 입력주세요.</span
        >
        <span v-if="!$v.signupData.userName.maxLength"
          >닉네임은 {{ $v.signupData.userName.$params.maxLength.max }} 글자
          이하로 입력해주세요.</span
        >
      </div>
    </div>

    <div class="form-group">
      <label for="userPassword">비밀번호</label>
      <input
        v-model.trim="$v.signupData.userPassword.$model"
        class="form-control"
        id="userPassword"
        type="password"
        :class="{
          'is-invalid': $v.signupData.userPassword.$error,
          'is-valid': !$v.signupData.userPassword.$invalid,
        }"
      />
      <div class="valid-feedback">Your Password is valid</div>
      <div class="invalid-feedback">
        <span v-if="!$v.signupData.userPassword.required"
          >비밀번호는 필수(값) 입니다.</span
        >
        <span v-if="!$v.signupData.userPassword.minLength"
          >비밀번호는
          {{ $v.signupData.userPassword.$params.minLength.min }}
          글자 이상으로 입력해주세요.</span
        >
      </div>
    </div>

    <div class="form-group">
      <label for="userPassword">비밀번호 확인</label>
      <input
        v-model.trim="$v.signupData.userPasswordCheck.$model"
        class="form-control"
        id="userPasswordCheck"
        type="password"
        :class="{
          'is-invalid': $v.signupData.userPasswordCheck.$error,
          'is-valid':
            signupData.userPassword != null
              ? !$v.signupData.userPasswordCheck.$invalid
              : null,
        }"
      />
      <div class="valid-feedback">Your password is identical!</div>
      <div class="invalid-feedback">
        <span v-if="!$v.signupData.userPasswordCheck.sameAsPassword"
          >비밀번호가 일치하지 않습니다.</span
        >
      </div>
    </div>

    <div>
      <input v-model="signupData.userType" id="userType" type="hidden" />
    </div>
    <div>
      <button type="submit" @click="submitForm" @keyup.enter="submitForm">
        Signup
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapActions } from "vuex";
import {
  required,
  minLength,
  maxLength,
  email,
  sameAs,
} from "vuelidate/lib/validators";

interface SignupData {
  userId: string | null;
  userName: string | null;
  userPassword: string | null;
  userPasswordCheck: string | null;
  userType: number;
}

@Component({
  methods: {
    ...mapActions(["signup"]),
  },
  validations: {
    signupData: {
      userId: {
        required,
        email,
        isEmail(value) {
          if (value === "") return true;
          const emailRegex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;

          return new Promise((resolve) => {
            setTimeout(() => {
              resolve(emailRegex.test(value));
            }, 100);
          });
        },
      },
      userName: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(20),
      },
      userPassword: {
        required,
        minLength: minLength(4),
      },
      userPasswordCheck: {
        minLength: minLength(4),
        sameAsPassword: sameAs("userPassword"),
      },
    },
  },
})
export default class SignupView extends Vue {
  signupData: SignupData = {
    userId: null,
    userName: null,
    userPassword: null,
    userPasswordCheck: null,
    userType: 0,
  };
  submitForm() {
    this.$v.$touch();
    if (this.$v.$invalid) {
      console.log("데이터 검증 실패");
    } else {
      this.$store.dispatch("signup", this.signupData);
      console.log("데이터 검증 성공");
    }
  }
}
</script>

<style></style>
