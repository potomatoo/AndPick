<template>
  <v-app-bar app clipped-left height="65px">
    <v-app-bar-nav-icon v-if="isLoggedIn" @click="TOGGLE_SIDEBAR()" />
    <v-toolbar-title
      class="a mr-5 justify-content-between"
      align="center"
      style="width: 100%"
    >
      <router-link class="router-link" :to="{ name: 'Home' }">
        <span
          style="font-family: 'Spectral SC', serif; font-size: 40px; color: #1e847f !important"
          >&</span
        >
        <span
          style="font-family: 'Faster One', cursive; font-size: 20px; color: rgba(0, 0, 0, 0.77) !important"
          >PICK</span
        >
      </router-link>
      <router-link
        v-if="!isLoggedIn"
        :to="{ name: 'Login' }"
        class="router-link ml-5"
      >
        <v-btn outlined color="#1e847f">로그인</v-btn>
      </router-link>
      <div v-if="isLoggedIn" class="mt-1 d-flex">
        <div class="cheer mt-2">
          <b>{{ userName }}</b> 님의 밝은 미래를 응원합니다!
        </div>
        <div class="ml-5">
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <span
                class="mdi-nav mdi mdi-account-edit mr-3 p-2"
                v-on="on"
              ></span>
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
import { mapMutations, mapGetters } from "vuex";
import { Axios } from "../../service/axios.service";

@Component({
  methods: mapMutations("mypageModule", ["TOGGLE_SIDEBAR"]),
  computed: { ...mapGetters(["isLoggedIn"]) }
})
export default class AppNavbar extends Vue {
  userName: string | null = "";

  // setName() {
  //   this.userName = this.$store.state.userName;
  // }
  @Watch("$store.state.JWT")
  setName() {
    if (this.$store.state.JWT) {
      Axios.instance
        .get("/api/user/detail")
        .then((res) => {
          this.userName = res.data.data.userName;
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }

  created() {
    if (this.$store.state.JWT) {
      this.setName();
    }
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
  border: 0;
  outline: none;
}
.mdi-nav:hover {
  cursor: pointer;
}
.a {
  background-color: whitesmoke;
  display: flex;
  align-items: center;
}
.cheer {
  font-size: 16px;
}
.mdi-nav {
  font-size: 27px;
}
</style>
