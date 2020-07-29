<template>
  <v-app-bar app clipped-left>
    <v-app-bar-nav-icon @click="toggleSidebar()" />
    <v-toolbar-title class="mt-2">
      <router-link v-if="!isLoggedIn" :to="{ name: 'Cover' }">
        <a class="navbar-brand">Junho</a>
      </router-link>
      <router-link v-if="isLoggedIn" :to="{ name: 'Home' }">
        <a class="navbar-brand">Junho</a>
      </router-link>
      <router-link v-if="!isLoggedIn" :to="{ name: 'Signup' }" class="mr-2"
        ><v-btn color="#5cb85c">Signup</v-btn>
      </router-link>
      <router-link v-if="!isLoggedIn" :to="{ name: 'Login' }"
        ><v-btn color="#5cb85c">Login</v-btn></router-link
      >
      <router-link v-if="isLoggedIn" :to="{ name: 'Logout' }"
        >Logout</router-link
      >
      <router-link v-if="isLoggedIn" :to="{ name: 'DeleteUser' }"
        >회원탈퇴</router-link
      >
    </v-toolbar-title>
  </v-app-bar>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapMutations, mapGetters } from "vuex";

@Component({
  methods: mapMutations("mypageModule", ["toggleSidebar"]),
  computed: { ...mapGetters(["isLoggedIn"]) },
})
export default class AppNavbar extends Vue {}
</script>
