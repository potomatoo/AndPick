<template>
  <v-navigation-drawer v-if="isSidebarActive" app clipped>
    <div class="pt-3">
      <v-list-item-title class="text-center font-weight-bold">Welcome Junho!</v-list-item-title>
    </div>
    <v-list dense>
      <SidebarFeed />
      <SidebarBoard />
      <SidebarMypage />
    </v-list>
  </v-navigation-drawer>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapState } from "vuex";

import SidebarFeed from "@/components/feeds/SidebarFeed.vue";
import SidebarBoard from "@/components/feeds/SidebarBoard.vue";
import SidebarMypage from "@/components/pages/SidebarMypage.vue";

@Component({
  components: {
    SidebarFeed,
    SidebarBoard,
    SidebarMypage
  },
  computed: mapState("mypageModule", ["isSidebarActive"])
})
export default class AppSidebar extends Vue {}
</script>
