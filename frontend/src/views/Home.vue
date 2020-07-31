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

// import { mapGetters } from "vuex";

@Component({
  components: {
    AppNavbar,
    AppSidebar,
    AppMain,
    AppFooter,
  },
  // computed: { ...mapGetters(["isLoggedIn"]) },
})
export default class Home extends Vue {
  @Watch("$store.state.JWT")
  stateUpdate() {
    if (this.$store.state.JWT) {
      console.log("로그인감지");
    } else {
      console.log("토큰없음");
    }
  }
  created() {
    this.$store.dispatch("feedModule/FETCH_DATA");
  }
}
</script>
