<template>
  <div>
    <h4>비밀번호 변경</h4>
    <div class="form-group pt-5">
      <input
        v-model.trim="$v.updateData.userPassword.$model"
        class="form-control"
        id="userPassword"
        type="password"
        placeholder="새로운 비밀번호"
        :class="{
          'is-invalid': $v.updateData.userPassword.$error,
          'is-valid': !$v.updateData.userPassword.$invalid,
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
        v-model.trim="$v.updateData.userPasswordCheck.$model"
        class="form-control"
        id="userPasswordCheck"
        type="password"
        placeholder="비밀번호 확인"
        :class="{
          'is-invalid': $v.updateData.userPasswordCheck.$error,
          'is-valid':
            updateData.userPassword != null
              ? !$v.updateData.userPasswordCheck.$invalid
              : null,
        }"
      />
      <div class="invalid-feedback">
        <span v-if="!$v.updateData.userPasswordCheck.sameAsPassword"
          >비밀번호가 일치하지 않습니다.</span
        >
      </div>
    </div>
    <div class="d-flex justify-content-end mt-3">
      <!-- <v-btn color="success" @click="updateUser(updateData)">수정</v-btn> -->
      <v-btn color="success" @click.prevent="submitPasswordForm">수정</v-btn>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
// import { mapActions } from "vuex";
import { required, minLength, sameAs } from "vuelidate/lib/validators";

interface UpdateData {
  userPassword: string | null;
  userPasswordCheck: string | null;
}

@Component({
  validations: {
    updateData: {
      userPassword: {
        required,
        minLength: minLength(4),
      },
      userPasswordCheck: {
        required,
        minLength: minLength(4),
        sameAsPassword: sameAs("userPassword"),
      },
    },
  },
})
export default class PasswordUpdate extends Vue {
  updateData: UpdateData = {
    userPassword: null,
    userPasswordCheck: null,
  };
  submitPasswordForm() {
    this.$v.$touch();
    if (this.$v.$invalid) {
      console.log("데이터 검증 실패");
    } else {
      //   this.$store.dispatch("", this.updateData);
      console.log("데이터 검증 성공");
    }
  }
}
</script>

<style></style>
