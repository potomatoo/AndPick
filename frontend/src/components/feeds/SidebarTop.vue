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
      <v-list-item class="px-6" @click="toAddPage">
        <v-list-item-icon>
          <v-icon>mdi-plus-box-outline</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>Follow New Sources</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class SidebarTop extends Vue {
  toAddPage() {
    if (this.$route.name !== "AddRss") {
      this.$router.push({ name: "AddRss" });
    }
  }
}
</script>
<style scoped></style>
