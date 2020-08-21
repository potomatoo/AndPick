<template>
  <div>
    <h4>비밀번호 변경</h4>
    <div class="form-group pt-5">
      <input
        v-model.trim="$v.updateData.userPassword.$model"
        class="form-control"
        id="userPassword"
        type="password"
        autocomplete="new-password"
        placeholder="현재 비밀번호"
        :class="{
          'is-invalid': $v.updateData.userPassword.$error,
          'is-valid': !$v.updateData.userPassword.$invalid
        }"
      />
      <div class="invalid-feedback">
        <span v-if="!$v.updateData.userPassword.minLength"
          >비밀번호는
          {{ $v.updateData.userPassword.$params.minLength.min }}
          글자 이상으로 입력해주세요.</span
        >
      </div>
    </div>
    <div class="form-group">
      <input
        v-model.trim="$v.updateData.changePassword.$model"
        class="form-control"
        id="changePassword"
        type="password"
        placeholder="새로운 비밀번호"
        :class="{
          'is-invalid': $v.updateData.changePassword.$error,
          'is-valid': !$v.updateData.changePassword.$invalid
        }"
      />
      <div class="invalid-feedback">
        <span v-if="!$v.updateData.changePassword.minLength"
          >비밀번호는
          {{ $v.updateData.changePassword.$params.minLength.min }}
          글자 이상으로 입력해주세요.</span
        >
      </div>
    </div>
    <div class="form-group">
      <input
        v-model.trim="$v.updateData.changePasswordCheck.$model"
        class="form-control"
        id="changePasswordCheck"
        type="password"
        placeholder="비밀번호 확인"
        :class="{
          'is-invalid': $v.updateData.changePasswordCheck.$error,
          'is-valid':
            updateData.changePassword != null
              ? !$v.updateData.changePasswordCheck.$invalid
              : null
        }"
      />
      <div class="invalid-feedback">
        <span v-if="!$v.updateData.changePasswordCheck.sameAsPassword"
          >비밀번호가 일치하지 않습니다.</span
        >
      </div>
    </div>
    <div class="d-flex justify-content-end mt-3">
      <v-btn
        text
        style="font-weight: bold"
        color="#1e847f"
        @click.prevent="submitPasswordForm"
        >수정</v-btn
      >
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { required, minLength, sameAs } from "vuelidate/lib/validators";
import { Axios } from "../../service/axios.service";

interface UpdateData {
  userId: string | null;
  userPassword: string | null;
  changePassword: string | null;
  changePasswordCheck: string | null;
}

@Component({
  validations: {
    updateData: {
      userPassword: {
        required,
        minLength: minLength(8)
      },
      changePassword: {
        required,
        minLength: minLength(8)
      },
      changePasswordCheck: {
        required,
        minLength: minLength(8),
        sameAsPassword: sameAs("changePassword")
      }
    }
  }
})
export default class PasswordUpdate extends Vue {
  updateData: UpdateData = {
    userId: null,
    userPassword: null,
    changePassword: null,
    changePasswordCheck: null
  };
  submitPasswordForm() {
    this.$v.$touch();
    if (!this.$v.$invalid) {
      this.$store.dispatch("updateUserPassword", this.updateData);
    }
  }
  created() {
    Axios.instance
      .get("/api/user/detail")
      .then((res) => {
        this.updateData.userId = res.data.data.userId;
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
</script>

<style></style>
