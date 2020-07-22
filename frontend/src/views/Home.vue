<template>
  <v-app id="inspire">
    <AppNavbar />
    <AppSidebar />
    <AppMain />
    <AppFooter />
  </v-app>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import AppNavbar from "@/components/main/AppNavbar.vue";
import AppSidebar from "@/components/main/AppSidebar.vue";
import AppMain from "@/components/main/AppMain.vue";
import AppFooter from "@/components/main/AppFooter.vue";

@Component({
  components: {
    AppNavbar,
    AppSidebar,
    AppMain,
    AppFooter
  }
})
export default class Home extends Vue {}
</script>