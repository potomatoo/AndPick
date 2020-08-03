<template>
  <div class="card">
    <h3 class="card-header text-center">회원가입</h3>
    <div class="card-body">
      <form @submit.prevent="submitForm">
        <div class="form-row">
          <div class="form-group col-md-6">
            <label>아이디</label>
            <input
              type="email"
              class="form-control"
              v-model.trim="$v.loginData.userId.$model"
              :class="{
                'is-invalid': $v.loginData.userId.$error,
                'is-valid': !$v.loginData.userId.$invalid,
              }"
            />
            <div class="valid-feedback">Your ID is valid</div>
            <div class="invalid-feedback">
              <span v-if="!$v.loginData.userId.required"
                >ID는 필수(값) 입니다.</span
              >
              <span v-if="!$v.loginData.userId.isEmail"
                >이메일 형식이 아닙니다.</span
              >
            </div>
          </div>

          <div class="form-group col-md-6">
            <label>닉네임</label>
            <input
              type="text"
              class="form-control"
              v-model.trim="$v.loginData.userName.$model"
              :class="{
                'is-invalid': $v.loginData.userName.$error,
                'is-valid': !$v.loginData.userName.$invalid,
              }"
            />
            <div class="valid-feedback">Your nickname is valid</div>
            <div class="invalid-feedback">
              <span v-if="!$v.loginData.userName.required"
                >닉네임은 필수(값) 입니다.</span
              >
              <span v-if="!$v.loginData.userName.minLength"
                >닉네임은 {{ $v.loginData.userName.$params.minLength.min }} 글자
                이상으로 입력주세요.</span
              >
              <span v-if="!$v.loginData.userName.maxLength"
                >닉네임은 {{ $v.loginData.userName.$params.maxLength.max }} 글자
                이하로 입력해주세요.</span
              >
            </div>
          </div>

          <div class="form-group col-md-6">
            <label>비밀번호</label>
            <input
              type="password"
              class="form-control"
              v-model.trim="$v.loginData.userPassword1.$model"
              :class="{
                'is-invalid': $v.loginData.userPassword1.$error,
                'is-valid': !$v.loginData.userPassword1.$invalid,
              }"
            />
            <div class="valid-feedback">Your Password is valid</div>
            <div class="invalid-feedback">
              <span v-if="!$v.loginData.userPassword1.required"
                >비밀번호는 필수(값) 입니다.</span
              >
              <span v-if="!$v.loginData.userPassword1.minLength"
                >비밀번호는
                {{ $v.loginData.userPassword1.$params.minLength.min }}
                글자 이상으로 입력해주세요.</span
              >
            </div>
          </div>

          <div class="form-group col-md-6">
            <label>비밀번호 확인</label>
            <input
              type="password"
              class="form-control"
              v-model.trim="$v.loginData.userPassword2.$model"
              :class="{
                'is-invalid': $v.loginData.userPassword2.$error,
                'is-valid':
                  loginData.userPassword1 != null
                    ? !$v.loginData.userPassword2.$invalid
                    : null,
              }"
            />
            <div class="valid-feedback">Your password is identical!</div>
            <div class="invalid-feedback">
              <span v-if="!$v.loginData.userPassword2.sameAsPassword"
                >비밀번호가 일치하지 않습니다.</span
              >
            </div>
          </div>
          <button type="submit" class="btn btn-success">
            Submit {{ submitStatus }}
          </button>
        </div>
      </form>
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

interface LoginData {
  userId: string | null;
  userName: string | null;
  userPassword1: string | null;
  userPassword2: string | null;
}

@Component({
  methods: {
    ...mapActions(["signup"]),
  },
  validations: {
    loginData: {
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
      userPassword1: {
        required,
        minLength: minLength(4),
      },
      userPassword2: {
        minLength: minLength(4),
        sameAsPassword: sameAs("userPassword1"),
      },
    },
  },
})
export default class LoginTest extends Vue {
  loginData: LoginData = {
    userId: null,
    userName: null,
    userPassword1: null,
    userPassword2: null,
  };
  submitStatus: string | null = null;

  submitForm() {
    this.$v.$touch();
    if (this.$v.$invalid) {
      this.submitStatus = "FAIL";
    } else {
      this.submitStatus = "SUCCESS";
    }
  }
}
</script>

<style></style>
