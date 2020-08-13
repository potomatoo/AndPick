<template>
  <v-menu
    offset-y
    :close-on-content-click="false"
    min-width="100px"
    :close-on-click="closeMenu"
    v-model="value"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn icon large>
        <v-icon v-bind="attrs" v-on="on">
          mdi-note-plus-outline
        </v-icon>
      </v-btn>
    </template>
    <v-list class="py-0">
      <v-list-item v-for="postDir in postDirList" :key="postDir.postDirId">
        <!-- post 메뉴 -->
        <v-menu offset-x close-on-content-click min-width="100px">
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
              <v-icon color="success" class="mr-2">mdi-plus</v-icon>
              <v-list-item-title class="success--text"
                >NEW Post</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>
      </v-list-item>

      <hr class="ma-0" />
      <v-list-item @click="folderModalActive = !folderModalActive">
        <v-icon color="success" class="mr-2">mdi-plus</v-icon>
        <v-list-item-title class="success--text">NEW PAGE</v-list-item-title>
      </v-list-item>
    </v-list>

    <create-folder-modal :folderModalActive.sync="folderModalActive" />
  </v-menu>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import CreateFolderModal from "@/components/pages/CreateFolderModal.vue";
import { Post } from "@/store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolderModal
  }
})
export default class AddScrapMenu extends Vue {
  @mypageModule.State postDirList!: Post[];

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
</style>
