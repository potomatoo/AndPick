<template>
  <v-app-bar app clipped-left class="mb-0">
    <v-app-bar-nav-icon @click="TOGGLE_SIDEBAR()" />
    <v-toolbar-title
      class="mr-5 d-flex justify-content-between"
      style="width: 100%"
    >
      <router-link :to="{ name: 'Home' }">
        <a
          ><img class="mdi" src="@/assets/logo.png" width="90px" height="auto"
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
        <div>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <span class="mdi mdi-account-edit mr-3 p-2" v-on="on"></span>
            </template>
            <v-list class="p-3">
              <router-link
                v-if="isLoggedIn"
                :to="{ name: 'UpdateUser' }"
                class="router-link"
                >정보수정</router-link
              >
              <hr />
              <router-link
                v-if="isLoggedIn"
                :to="{ name: 'Logout' }"
                class="router-link"
                >로그아웃</router-link
              >
            </v-list>
          </v-menu>
        </div>
      </div>
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
.mdi:hover {
  cursor: pointer;
  background: rgba(230, 235, 229, 0.986);
}
</style>
