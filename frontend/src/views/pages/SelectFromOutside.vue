<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <div class="caption mb-3">Scrap</div>
          <h5 style="font-family: 'Do Hyeon', sans-serif;">
            스크랩을 저장할 폴더를 지정해주세요.
          </h5>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider class="mt-0"></v-divider>

    <div class="ml-2">
      <v-list>
        <v-list-item-group>
          <v-list-item
            v-for="postDir in postDirList"
            :key="postDir.postDirId"
            class="mb-5"
            @click="
              $router.replace({
                name: 'NewScrapFromGoole',
                params: { postDirId: postDir.postDirId }
              })
            "
          >
            <v-list-item-icon>
              <v-icon color="grey" class="mr-2">mdi-folder-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title
                v-text="postDir.postDirName"
              ></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Post } from "../../store/MypageInterface";
import router from "../../router";

const mypageModule = namespace("mypageModule");

@Component
export default class SelectFromOutside extends Vue {
  @mypageModule.Action SAVE_SCRAPDATA: any;
  @mypageModule.State postDirList!: Post[];

  isLoggedId = this.$store.getters.isLoggedIn;
  scrapData = localStorage.getItem("scrapData");
  lamda = false;

  @Watch("$route", { immediate: true })
  saveLocal() {
    this.SAVE_SCRAPDATA(this.$route.params.scrapKey);
  }

  @Watch("$store.state.JWT")
  stateUpdate() {
    if (this.$store.state.JWT) {
      this.lamda = true;
    }
  }

  @Watch("isLoggedId", { immediate: true })
  fetchData() {
    if (this.isLoggedId) {
      this.$store.dispatch("feedModule/FETCH_FEED_LIST");
      this.$store.dispatch("feedModule/FETCH_BOARD_LIST");
      this.$store.dispatch("mypageModule/FETCH_POSTDIR_LIST");
    }
  }
}
</script>

<style scoped></style>
