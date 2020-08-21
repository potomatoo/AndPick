<template>
  <v-footer app clipped-left>
    <div class="container">
      <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp" target="_blank"
        >SSAFY</a
      ><span class="attribution">의 공통 프로젝트에 의해 개발되었습니다. </span>
    </div>
  </v-footer>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class AppFooter extends Vue {}
</script>

<style scoped>
footer {
  text-align: end;
}
a {
  color: #1e847f !important;
  height: 20px !important;
}
</style>
