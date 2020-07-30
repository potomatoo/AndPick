<template>
  <div>
<<<<<<< HEAD
    <v-list>
      <router-link class="router-link" :to="{ name: 'Today' }">
        <v-list-item @click="click">
=======
    <!-- <router-link class="router-link" :to="{ name: 'Today' }">
        <v-list-item>
>>>>>>> a57a79a90a6ffda792081a8b1afaff2732d52423
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
      </router-link> -->
    <v-list-item>
      <v-list-item-icon>
        <v-icon>mdi-plus-box-outline</v-icon>
      </v-list-item-icon>
      <router-link class="router-link" :to="{ name: 'AddRss' }">
        <v-list-item-content>
          <v-list-item-title>Follow New Sources</v-list-item-title>
        </v-list-item-content>
      </router-link>
    </v-list-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class SidebarTop extends Vue {}
</script>
<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
/* a.router-link-exact-active {
  text-decoration: none;
  color: inherit;
} */
</style>
