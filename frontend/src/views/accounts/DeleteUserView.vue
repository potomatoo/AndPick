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
    this.deleteUser();
  }
}
</script>

<style></style>
