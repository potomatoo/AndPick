<template>
  <v-app-bar app clipped-left>
    <v-app-bar-nav-icon @click="toggleSidebar()" />
    <v-toolbar-title>
      <router-link :to="{ name: 'Home' }">
        <a class="navbar-brand">Junho</a>
      </router-link>
      <router-link :to="{ name: 'Signup' }">Signup</router-link> |
      <router-link :to="{ name: 'Login' }">Login</router-link> |
      <router-link :to="{ name: 'Logout' }">Logout</router-link> |
    </v-toolbar-title>
  </v-app-bar>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapMutations } from "vuex";

@Component({
  methods: mapMutations("mypageModule", ["toggleSidebar"]),
})
export default class AppNavbar extends Vue {}
</script>
