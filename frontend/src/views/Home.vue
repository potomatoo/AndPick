<template>
  <div>
    <v-app v-if="isLoggedIn" id="inspire">
      <app-sidebar />
      <app-navbar />
      <app-main />
    </v-app>
    <div v-if="!isLoggedIn">
      <app-navbar />
      <router-view />
    </div>
    <app-footer />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import AppNavbar from "@/components/main/AppNavbar.vue";
import AppSidebar from "@/components/main/AppSidebar.vue";
import AppMain from "@/components/main/AppMain.vue";
import AppFooter from "@/components/main/AppFooter.vue";

import { mapGetters } from "vuex";

@Component({
  components: {
    AppNavbar,
    AppSidebar,
    AppMain,
    AppFooter
  },
  computed: { ...mapGetters(["isLoggedIn"]) }
})
export default class Home extends Vue {
  created() {
    this.$store.dispatch("feedModule/FETCH_DATA");
    this.$store.dispatch("mypageModule/FETCH_DATA");
  }
}
</script>
