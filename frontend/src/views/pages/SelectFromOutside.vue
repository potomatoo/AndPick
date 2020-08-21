<template>
  <div class="mt-10" v-if="postDirList">
    <v-container>
      <v-layout>
        <v-flex class="text-left">
          <div class="caption mb-3">Scrap</div>
          <h5 style="font-family: 'Do Hyeon', sans-serif;">
            스크랩을 저장할 폴더를 지정해주세요.
          </h5>
        </v-flex>
        <v-flex class="text-right mb-3" align-self-end>
          <v-btn
            small
            outlined
            color="rgb(30, 132, 127)"
            @click="isActive = !isActive"
          >
            <v-icon left>mdi-plus</v-icon>새 폴더
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider class="mt-0"></v-divider>

    <div class="ml-2">
      <v-list v-if="postDirList.length">
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
      <v-list v-else>
        <p class="mt-5 ml-2" style="font-family: 'Do Hyeon', sans-serif;">
          폴더가 없습니다. 폴더를 생성해주세요.
        </p>
      </v-list>
    </div>
    <create-folder-modal :folderModalActive.sync="isActive" />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Post } from "../../store/MypageInterface";
import router from "../../router";
import CreateFolder from "../../components/pages/CreateFolder.vue";
import CreateFolderModal from "@/components/pages/CreateFolderModal.vue";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolderModal
  }
})
export default class SelectFromOutside extends Vue {
  @mypageModule.Action SAVE_SCRAPDATA: any;
  @mypageModule.State postDirList!: Post[];

  isLoggedId = this.$store.getters.isLoggedIn;
  scrapData = localStorage.getItem("scrapData");
  lamda = false;
  isActive = false;

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
