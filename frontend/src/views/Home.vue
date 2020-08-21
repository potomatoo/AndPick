<template>
  <div>
    <v-app id="inspire">
      <app-sidebar v-if="this.$store.state.JWT" />
      <app-navbar />
      <app-main />
    </v-app>
    <app-footer />
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from "vue-property-decorator";
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
  isLoggedId = this.$store.getters.isLoggedIn;
  new = false;
  @Watch("$store.state.JWT")
  stateUpdate() {
    if (this.$store.state.JWT) {
      this.new = true;
    }
  }

  @Watch("isLoggedId", { immediate: true })
  fetchData() {
    if (this.isLoggedId) {
      this.$store.dispatch("feedModule/FETCH_FEED_LIST");
      this.$store.dispatch("feedModule/FETCH_BOARD_LIST");
      this.$store.dispatch("mypageModule/FETCH_POSTDIR_LIST");
    }
  }
}
</script>
