<template>
  <v-menu
    offset-y
    :close-on-content-click="false"
    min-width="100px"
    :close-on-click="closeMenu"
    v-model="value"
    nudge-bottom="5"
  >
    <template v-slot:activator="{ on, attrs }">
      <span v-on="on" v-bind="attrs">
        <v-tooltip top open-delay="300" color="#EEEEEE">
          <template v-slot:activator="{ on, attrs }">
            <v-icon v-bind="attrs" v-on="on">
              mdi-note-plus-outline
            </v-icon>
          </template>
          <span class="grey--text text--darken-1">글 작성하기</span>
        </v-tooltip>
      </span>
    </template>
    <v-list class="py-0">
      <v-list-item v-for="postDir in postDirList" :key="postDir.postDirId">
        <!-- post 메뉴 -->
        <v-menu
          offset-x
          close-on-content-click
          min-width="100px"
          nudge-right="15"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-icon color="grey" class="mr-2">mdi-folder-outline</v-icon>
            <v-list-item-title v-bind="attrs" v-on="on">{{
              postDir.postDirName
            }}</v-list-item-title>
          </template>

          <v-list class="py-0">
            <v-list-item
              v-for="post in postDir.postList"
              :key="post.postId"
              @click="setEdit(post.postId, null)"
            >
              <v-icon color="grey" class="mr-2">mdi-note-outline</v-icon>
              <v-list-item-title>{{ post.postTitle }}</v-list-item-title>
            </v-list-item>

            <hr class="ma-0" />

            <v-list-item @click="setEdit(null, postDir.postDirId)">
              <v-list-item-title class="success--text"
                >새 글 생성</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>
      </v-list-item>

      <hr class="ma-0" />
      <v-list-item @click="folderModalActive = !folderModalActive">
        <v-list-item-title class="success--text"
          >새 폴더 생성</v-list-item-title
        >
      </v-list-item>
    </v-list>

    <create-folder-modal :folderModalActive.sync="folderModalActive" />
  </v-menu>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import CreateFolderModal from "@/components/pages/CreateFolderModal.vue";
import { PostDir } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolderModal
  }
})
export default class AddScrapMenu extends Vue {
  @mypageModule.State postDirList!: PostDir[];

  folderModalActive = false;

  closeMenu = true;

  value = false;

  setEdit(postId: number, postDirId: number) {
    this.value = false;
    if (postId && !postDirId) {
      this.$emit("onPost", postId);
    } else if (postDirId && !postId) {
      this.$emit("onNewPost", postDirId);
    }
  }

  @Watch("folderModalActive")
  preventPageMenu() {
    if (this.folderModalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}

span:hover {
  background-color: #eeeeee;
}
</style>
