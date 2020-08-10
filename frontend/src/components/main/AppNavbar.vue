<template>
  <v-app-bar app clipped-left style="height: 70px">
    <v-app-bar-nav-icon v-if="isLoggedIn" @click="TOGGLE_SIDEBAR()" />
    <v-toolbar-title
      class="a mr-5 mt-4 d-flex justify-content-between"
      style="width: 100%"
    >
      <router-link :to="{ name: 'Home' }">
        <a
          ><img
            class="mdi ml-5"
            src="@/assets/title.png"
            width="140px"
            height="auto"
        /></a>
      </router-link>
      <router-link
        v-if="!isLoggedIn"
        :to="{ name: 'Login' }"
        class="router-link ml-5"
      >
        <v-btn outlined color="success">로그인</v-btn>
      </router-link>
      <div v-if="isLoggedIn" class="mt-1 d-flex">
        <div class="cheer p-1">
          <b>{{ userName }}</b> 님의 취업성공을 응원합니다!
        </div>
        <div class="ml-5">
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
import { Vue, Component, Watch } from "vue-property-decorator";
import { mapMutations, mapGetters, mapState } from "vuex";
import { Axios } from "@/service/axios.service";

@Component({
  methods: mapMutations("mypageModule", ["TOGGLE_SIDEBAR"]),
  computed: { ...mapGetters(["isLoggedIn"]) },
})
export default class AppNavbar extends Vue {
  userName: string | null = null;

  setName() {
    Axios.instance
      .get("/api/user/detail")
      .then((res) => {
        this.userName = res.data.data.userName;
      })
      .catch((err) => console.log(err));
  }
  created() {
    this.setName();
  }

  @Watch("$store.state.userName")
  newName(name: string) {
    this.userName = name;
  }
}
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
}
.a {
  background-color: whitesmoke;
}
.cheer {
  font-size: 16px;
}
</style>
