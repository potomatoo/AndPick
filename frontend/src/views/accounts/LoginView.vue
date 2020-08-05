<template>
  <div class="container my-5" style="width: 600px">
    <h2><b>Welcome to JG!!</b></h2>
    <form>
      <div class="form-group">
        <label for="userId">이메일</label>
        <input
          v-model.trim="$v.loginData.userId.$model"
          class="form-control"
          id="userId"
          type="email"
          placeholder="이메일"
          :class="{
            'is-invalid': $v.loginData.userId.$error,
            'is-valid': !$v.loginData.userId.$invalid,
          }"
        />
        <div class="valid-feedback">Your ID is valid</div>
        <div class="invalid-feedback">
          <span v-if="!$v.loginData.userId.required"
            >ID는 필수(값) 입니다.
          </span>
          <span v-if="!$v.loginData.userId.isEmail"
            >이메일 형식이 아닙니다.
          </span>
        </div>
      </div>

      <div class="form-group">
        <label for="userPassword">비밀번호</label>
        <input
          v-model.trim="$v.loginData.userPassword.$model"
          class="form-control"
          id="userPassword"
          type="password"
          placeholder="비밀번호"
          :class="{
            'is-invalid': $v.loginData.userPassword.$error,
            'is-valid': !$v.loginData.userPassword.$invalid,
          }"
          @keypress.enter="submitForm"
        />
        <div class="valid-feedback">Your Password is valid</div>
        <div class="invalid-feedback">
          <span v-if="!$v.loginData.userPassword.required"
            >비밀번호는 필수(값) 입니다.</span
          >
          <span v-if="!$v.loginData.userPassword.minLength"
            >비밀번호는
            {{ $v.loginData.userPassword.$params.minLength.min }}
            글자 이상입니다.</span
          >
        </div>
      </div>
      <div class="d-flex justify-content-between mb-2">
        <button type="button" class="ml-1" @click.prevent="submitForm">
          <b style="color: #5cb85c">Login</b>
        </button>
        <p class="my-auto">
          계정이 없으신가요?
          <router-link :to="{ name: 'Signup' }">
            <b style="color: #5cb85c">Signup</b>
          </router-link>
        </p>
      </div>
    </form>
    <a
      href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Faccounts%2Flogin&client_id=476248660063-e2gk89ukcim2la7mbttisi10pq9ck5r6.apps.googleusercontent.com"
      ><img src="@/assets/google.png" style="width: 120px; height: auto;" />
    </a>
    <hr />
    <!-- <router-link :to="{ name: 'SocialLogin' }"
      ><img src="@/assets/google.png" style="width: 120px; height: auto;"
    /></router-link> -->
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { required, minLength, email } from "vuelidate/lib/validators";

interface LoginData {
  userId: string | null;
  userPassword: string | null;
}

interface SocialData {
  accessToken: string | null;
  userType: number | null;
}

@Component({
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
      userPassword: {
        required,
        minLength: minLength(4),
      },
    },
  },
})
export default class LoginView extends Vue {
  loginData: LoginData = {
    userId: null,
    userPassword: null,
  };
  socialData: SocialData = {
    accessToken: window.location.href.split("&")[1],
    userType: 1,
  };
  submitForm() {
    this.$v.$touch();
    if (this.$v.$invalid) {
      console.log("데이터 검증 실패");
    } else {
      this.$store.dispatch("login", this.loginData);
      console.log("데이터 검증 성공");
    }
  }
  // created() {
  //   if (window.location.href.split("&")[1]) {
  //     this.$store.dispatch("social", this.socialData);
  //   }
  // }
}
</script>

<style></style>
