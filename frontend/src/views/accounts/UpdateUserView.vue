<template>
  <div class="container my-5" style="width: 600px">
    <h3>회원정보</h3>
    <div class="form-group">
      <label for="userName">닉네임</label>
      <input
        v-model="updateData.userName"
        class="form-control"
        id="userName"
        type="text"
        placeholder="닉네임"
      />
    </div>
    <div class="d-flex justify-content-end">
      <button @click="updateUser(updateData)">수정</button>
      <router-link :to="{ name: 'DeleteUser' }" class="ml-3"
        >회원탈퇴</router-link
      >
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapActions } from "vuex";

interface UpdateData {
  userName: string | null;
}

@Component({
  methods: {
    ...mapActions(["updateUser"]),
  },
})
export default class UpdateUserView extends Vue {
  updateData: UpdateData = {
    userName: null,
  };
}
</script>

<style></style>
