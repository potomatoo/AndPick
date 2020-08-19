<template>
  <div>
    <h4>닉네임 변경</h4>
    <div class="form-group py-5">
      <input
        v-model.trim="$v.userName.$model"
        class="form-control"
        id="userName"
        type="text"
        placeholder="새로운 닉네임"
        :class="{
          'is-invalid': $v.userName.$error,
          'is-valid': !$v.userName.$invalid
        }"
      />
      <div class="invalid-feedback">
        <span v-if="!$v.userName.minLength"
          >닉네임은 {{ $v.userName.$params.minLength.min }} 글자 이상으로
          입력주세요.</span
        >
        <span v-if="!$v.userName.maxLength"
          >닉네임은 {{ $v.userName.$params.maxLength.max }} 글자 이하로
          입력해주세요.</span
        >
      </div>
      <div class="d-flex justify-content-end mt-3">
        <v-btn
          text
          style="font-weight: bold"
          color="#1e847f"
          @click.prevent="submitNameForm"
          >수정</v-btn
        >
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
// import { mapActions } from "vuex";
import { required, minLength, maxLength } from "vuelidate/lib/validators";

@Component({
  validations: {
    userName: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(20)
    }
  }
})
export default class NameUpdate extends Vue {
  userName: string | null = null;

  submitNameForm() {
    this.$v.$touch();
    if (!this.$v.$invalid) {
      this.$store.dispatch("updateUser", this.userName);
    }
  }
}
</script>

<style></style>
