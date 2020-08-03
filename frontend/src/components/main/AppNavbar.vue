<template>
  <v-app-bar app clipped-left>
    <v-app-bar-nav-icon @click="TOGGLE_SIDEBAR()" />
    <v-toolbar-title class="mt-2">
      <router-link :to="{ name: 'Home' }">
        <a class="navbar-brand">Junho</a>
      </router-link>
      <router-link v-if="!isLoggedIn" :to="{ name: 'Signup' }" class="mr-2 router-link">
        <v-btn color="#5cb85c">Signup</v-btn>
      </router-link>
      <router-link v-if="!isLoggedIn" :to="{ name: 'Login' }" class="router-link">
        <v-btn color="#5cb85c">Login</v-btn>
      </router-link>
      <router-link v-if="isLoggedIn" :to="{ name: 'Logout' }" class="mr-2 router-link">Logout</router-link>
      <router-link v-if="isLoggedIn" :to="{ name: 'UpdateUser' }" class="mr-2 router-link">회원정보수정</router-link>
      <router-link v-if="isLoggedIn" :to="{ name: 'DeleteUser' }">회원탈퇴</router-link>
    </v-toolbar-title>
  </v-app-bar>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapMutations, mapGetters } from "vuex";

@Component({
  methods: mapMutations("mypageModule", ["TOGGLE_SIDEBAR"]),
  computed: { ...mapGetters(["isLoggedIn"]) }
})
export default class AppNavbar extends Vue {}
</script>

<style scoped>
.sidebarBtn {
  outline: none;
}
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
