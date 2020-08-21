<template>
  <div class="container my-5" style="width: 600px">
    <h2 align="center" style="font-family: 'Do Hyeon', sans-serif;">
      회원정보수정
    </h2>
    <hr />
    <NameUpdate />
    <div v-if="!user.userType">
      <hr />
      <PasswordUpdate />
    </div>
    <hr />
    <div class="form-group py-5">
      <h4>회원 탈퇴</h4>
      <p>회원 탈퇴시 모든 데이터가 사라집니다.</p>
      <router-link
        :to="{ name: 'DeleteUser' }"
        class="router-link d-flex justify-content-end"
        ><v-btn text style="font-weight: bold" color="#1e847f"
          >회원탈퇴</v-btn
        ></router-link
      >
      <hr />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import NameUpdate from "@/components/accounts/NameUpdate.vue";
import PasswordUpdate from "@/components/accounts/PasswordUpdate.vue";

import axios from "axios";

@Component({
  components: {
    NameUpdate,
    PasswordUpdate
  }
})
export default class UpdateUserView extends Vue {
  user = "";
  fetchUser() {
    axios
      .get(
        "http://i3b107.p.ssafy.io:8080/api/user/detail",
        this.$store.getters.config
      )
      .then(res => (this.user = res.data.data))
      .catch(err => console.log(err));
  }
  created() {
    this.fetchUser();
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
