<template>
  <div class="container my-5" style="width: 400px">
    <div class="d-flex">
      <h2><b>Welcome to JG!!</b></h2>
      <img src="@/assets/logo.png" width="90px" height="45px" />
    </div>
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
      <div class="mb-2">
        <v-btn
          style="width: 100%"
          large
          color="success"
          @click.prevent="submitForm"
          >Login</v-btn
        >
      </div>
    </form>
    <hr />
    <!-- <a
      href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Faccounts%2Flogin&client_id=476248660063-e2gk89ukcim2la7mbttisi10pq9ck5r6.apps.googleusercontent.com"
      ><img src="@/assets/google.png" style="width: 380px; height:40px" />
    </a> -->
    <router-link :to="{ name: 'SocialLogin' }"
      ><img src="@/assets/google.png" style="width: 380px; height: 40px;"
    /></router-link>
    <p>
      계정이 없으신가요?
      <router-link :to="{ name: 'Signup' }">
        <b style="color: #5cb85c">Signup</b>
      </router-link>
    </p>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { required, minLength, email } from "vuelidate/lib/validators";

interface LoginData {
  userId: string | null;
  userPassword: string | null;
  userType: number;
  code: string | null;
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
    userType: 0,
    code: null,
  };
  submitForm() {
    this.$v.$touch();
    if (this.$v.$invalid) {
      alert("입력이 옳지 않습니다.");
    } else {
      this.$store.dispatch("login", this.loginData);
    }
  }
}
</script>

<style></style>
