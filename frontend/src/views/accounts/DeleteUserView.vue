<template>
  <div>회원탈퇴</div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import { mapActions } from "vuex";

@Component({
  methods: {
    ...mapActions(["deleteUser"]),
  },
})
export default class DeleteUserView extends Vue {
  mounted() {
    const check = confirm("정말 탈퇴하시겠습니까?");
    if (check == true) {
      this.deleteUser();
    } else {
      this.$router.push("/accounts/update");
    }
  }
}
</script>

<style></style>
