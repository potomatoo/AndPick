<template>
  <div class="container my-5" style="width: 600px">
    <h2 align="center">회원정보수정</h2>
    <hr />
    <NameUpdate />
    <hr />
    <PasswordUpdate />
    <hr />
    <div class="form-group py-5">
      <h4>회원 탈퇴</h4>
      <p>회원 탈퇴시 모든 데이터가 사라집니다.</p>
      <router-link
        :to="{ name: 'DeleteUser' }"
        class="router-link d-flex justify-content-end"
        ><v-btn color="error">회원탈퇴</v-btn></router-link
      >
      <hr />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import NameUpdate from "@/components/accounts/NameUpdate.vue";
import PasswordUpdate from "@/components/accounts/PasswordUpdate.vue";

@Component({
  components: {
    NameUpdate,
    PasswordUpdate
  }
})
export default class UpdateUserView extends Vue {}
</script>

<style>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
