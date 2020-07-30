<template>
  <v-app-bar app clipped-left>
<<<<<<< HEAD
    <v-app-bar-nav-icon class="sidebarBtn" @click="toggleSidebar()" />
    <v-toolbar-title>
=======
    <v-app-bar-nav-icon @click="toggleSidebar()" />
    <v-toolbar-title class="mt-2">
>>>>>>> a57a79a90a6ffda792081a8b1afaff2732d52423
      <router-link :to="{ name: 'Home' }">
        <a class="navbar-brand">Junho</a>
      </router-link>
      <router-link
        v-if="!isLoggedIn"
        :to="{ name: 'Signup' }"
        class="mr-2 router-link"
        ><v-btn color="#5cb85c">Signup</v-btn>
      </router-link>
      <router-link
        v-if="!isLoggedIn"
        :to="{ name: 'Login' }"
        class="router-link"
        ><v-btn color="#5cb85c">Login</v-btn></router-link
      >
      <router-link
        v-if="isLoggedIn"
        :to="{ name: 'Logout' }"
        class="mr-2 router-link"
        >Logout</router-link
      >
      <router-link
        v-if="isLoggedIn"
        :to="{ name: 'UpdateUser' }"
        class="mr-2 router-link"
        >회원정보수정</router-link
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
<<<<<<< HEAD

<style scoped>
.sidebarBtn {
  outline: none;
=======
<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
>>>>>>> a57a79a90a6ffda792081a8b1afaff2732d52423
}
</style>
