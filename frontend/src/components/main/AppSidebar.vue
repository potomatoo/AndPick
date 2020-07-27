<template>
  <v-navigation-drawer v-if="isSidebarActive" app clipped permanent>
    <v-list>
      <sidebar-top />
      <sidebar-feed />
      <SidebarBoard />
      <SidebarMypage />
    </v-list>
  </v-navigation-drawer>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapState } from "vuex";

import SidebarTop from "@/components/feeds/SidebarTop.vue";
import SidebarFeed from "@/components/feeds/SidebarFeed.vue";
import SidebarBoard from "@/components/feeds/SidebarBoard.vue";
import SidebarMypage from "@/components/pages/SidebarMypage.vue";

@Component({
  components: {
    SidebarTop,
    SidebarFeed,
    SidebarBoard,
    SidebarMypage
  },
  computed: mapState("mypageModule", ["isSidebarActive"])
})
export default class AppSidebar extends Vue {
  created() {
    // this.$store.dispatch("feedModule/initData");
  }
}
</script>


