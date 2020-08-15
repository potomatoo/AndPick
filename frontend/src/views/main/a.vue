<template>
  <div class="main-page"></div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

@Component
export default class A extends Vue {
  isLoggedIn = this.$store.getters.isLoggedIn;

  @Watch("isLoggedIn", { immediate: true })
  fetchData() {
    this.$store.dispatch("feedModule/FETCH_FEED_LIST");
    this.$store.dispatch("feedModule/FETCH_BOARD_LIST");
    this.$store.dispatch("mypageModule/FETCH_POSTDIR_LIST");
  }
}
</script>

<style scoped></style>
