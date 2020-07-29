<template>
  <div>
    <v-app id="inspire">
      <app-sidebar />
      <app-navbar />
      <app-main />
    </v-app>
    <app-footer />
  </div>
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
export default class Home extends Vue {
  created() {
    this.$store.dispatch("feedModule/initData");
  }
}
</script>
