<template>
  <div>
    <!-- <router-link class="router-link" :to="{ name: 'Today' }">
        <v-list-item>
          <v-list-item-icon>
            <v-icon>mdi-update</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Today</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </router-link>

      <router-link class="router-link" :to="{ name: 'Later' }">
        <v-list-item>
          <v-list-item-icon>
            <v-icon>mdi-bookmark-outline</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Read Later</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
    </router-link>-->
    
    <v-list class="mt-5">
      <v-list-item
        @click="toAddPage($event)"
        class="px-6 sidebar-addrss"
        color="rgb(223, 50, 119)"
      >
        <v-list-item class="px-2">
          <v-list-item-icon
            class="ml-15"
            style="font-family: 'Do Hyeon', sans-serif;"
          >
            <v-icon left>mdi-plus-box-outline</v-icon>채널 구독
          </v-list-item-icon>
        </v-list-item>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class SidebarTop extends Vue {
  toAddPage($event: MouseEvent) {
    const boards = document.querySelectorAll(".sidebar-board");
    const mypages = document.querySelectorAll(".sidebar-mypage");
    const subscriptions = document.querySelectorAll(".sidebar-subscription");

    if (boards?.length) {
      boards.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    if (mypages?.length) {
      mypages.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    if (subscriptions?.length) {
      subscriptions.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    ($event.currentTarget as HTMLElement).classList.add("v-list-item--active");
    if (this.$route.name !== "AddRss") {
      this.$router.push({ name: "AddRss" });
    }
  }
}
</script>
<style scoped></style>
