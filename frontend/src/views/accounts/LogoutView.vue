<template>
  <div>로그아웃</div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import { mapActions } from "vuex";

@Component({
  methods: {
    ...mapActions(["logout"]),
  },
})
export default class LogoutView extends Vue {
  mounted() {
    this.logout();
  }
}
</script>

<style></style>
