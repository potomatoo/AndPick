<template>
  <div>
    <v-list>
      <div class="d-flex">
        <v-subheader>
          Mypage
          <create-folder />
        </v-subheader>
        <router-link
          :to="{
            name: 'MypageExplain'
          }"
          class="router-link explain"
        >
          <i class="mdi mdi-help-circle"></i>
        </router-link>
      </div>
      <v-list-item-group>
        <v-list-item
          v-for="postDir in postDirList"
          :key="postDir.postDirId"
          @contextmenu.prevent="showPostDirCtx($event, postDir)"
        >
          <v-list-item-content>
            <router-link
              :to="{
                name: 'PostDir',
                params: { postDirId: postDir.postDirId }
              }"
              class="router-link"
            >
              <v-list-item-title
                v-text="postDir.postDirName"
              ></v-list-item-title>
            </router-link>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
    <post-dir-context-menu :postDirItem="postDirItem" />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
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
}
</script>

<style scoped>
.modal-button {
  font-size: 15px;
  opacity: 0.5;
}
.router-link {
  text-decoration: none;
  color: inherit;
}
.explain {
  opacity: 0.4;
  font-size: 20px;
  margin-top: 5px;
  margin-left: 120px;
}
</style>
