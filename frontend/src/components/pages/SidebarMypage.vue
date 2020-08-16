<template>
  <div>
    <v-list>
      <v-subheader>
        Mypage
        <create-folder />
      </v-subheader>
      <v-list-item-group>
        <v-list-item
          v-for="postDir in postDirList"
          :key="postDir.postDirId"
          @contextmenu.prevent="showPostDirCtx($event, postDir)"
          @click="toPostDir(postDir.postDirId)"
        >
          <v-list-item-content>
            <v-list-item-title v-text="postDir.postDirName"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
    <post-dir-context-menu :postDirItem="postDirItem" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import CreateFolder from "@/components/pages/CreateFolder.vue";
import PostDirContextMenu from "@/components/pages/PostDirContextMenu.vue";
import { Post } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolder,
    PostDirContextMenu
  }
})
export default class SidebarMypage extends Vue {
  @mypageModule.State postDirList!: [];
  @mypageModule.Mutation SET_POSTDIR_CONTEXT_MENU: any;

  postDirListItem = {};
  postDirItem = {};

  showPostDirCtx(e: MouseEvent, postDir: Post[]) {
    this.postDirItem = postDir;

    const ctx = {
      showCtx: true,
      x: e.clientX,
      y: e.clientY
    };
    this.SET_POSTDIR_CONTEXT_MENU(ctx);
  }

  toPostDir(postDirId: number) {
    if (
      this.$route.name === "PostDir" &&
      Number(this.$route.params.postDirId) === postDirId
    )
      return;
    this.$router.push({
      name: "PostDir",
      params: { postDirId: postDirId.toString() }
    });
  }
}
</script>

<style scoped></style>
