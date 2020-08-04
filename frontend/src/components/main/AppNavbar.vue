<template>
  <v-app-bar app clipped-left>
    <v-app-bar-nav-icon @click="TOGGLE_SIDEBAR()" />
    <v-toolbar-title
      class="mr-5 d-flex justify-content-between"
      style="width: 100%"
    >
      <router-link :to="{ name: 'Home' }">
        <a class="navbar-brand"
          ><img src="@/assets/logo.png" width="90px" height="auto"
        /></a>
      </router-link>
      <router-link
        v-if="!isLoggedIn"
        :to="{ name: 'Login' }"
        class="router-link ml-5 mt-1"
      >
        <v-btn color="#5cb85c">로그인</v-btn>
      </router-link>
      <div v-if="isLoggedIn" class="mt-2">
        <router-link
          v-if="isLoggedIn"
          :to="{ name: 'UpdateUser' }"
          class="mr-1 router-link"
          ><span style="width: 100px" class="mdi mdi-account-edit"></span
        ></router-link>
        |
        <router-link
          v-if="isLoggedIn"
          :to="{ name: 'Logout' }"
          class="mr-5 ml-1 router-link letters"
          >로그아웃</router-link
        >
      </div>
    </v-toolbar-title>
  </v-app-bar>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapMutations, mapGetters } from "vuex";

@Component({
  methods: mapMutations("mypageModule", ["TOGGLE_SIDEBAR"]),
  computed: { ...mapGetters(["isLoggedIn"]) },
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
.letters {
  font-size: 18px;
}
</style>
