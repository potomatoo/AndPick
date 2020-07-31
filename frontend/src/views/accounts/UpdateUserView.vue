<template>
  <div class="container my-5">
    <h1>회원정보수정</h1>
    <div class="form-group">
      <label for="userName">닉네임</label>
      <input
        v-model="updateData.userName"
        class="form-control"
        id="userName"
        type="text"
        placeholder="이메일"
      />
    </div>
    <div>
      <button @click="updateUser(updateData)">수정</button>
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
