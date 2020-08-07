<template>
  <v-footer app clipped-left>
    <div class="container">
      <router-link :to="{ name: 'Home' }">
        <a class="logo-font">Junho</a>
      </router-link>
      <span class="attribution">
        An interactive learning project from
        <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp" target="_blank"
          >SSAFY</a
        >
        .
      </span>
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
  color: #5cb85c !important;
}
</style>
