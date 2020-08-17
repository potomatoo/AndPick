<template>
  <div>
    <v-list>
      <v-subheader>
        Mypage
        <create-folder />
      </v-subheader>
      <!-- <v-list-item-group> -->
      <v-list-item
        v-for="postDir in postDirList"
        :key="postDir.postDirId"
        @contextmenu.prevent="showPostDirCtx($event, postDir)"
        @click="toPostDir(postDir.postDirId, $event)"
        class="sidebar-mypage"
        color="rgb(223, 50, 119)"
      >
        <v-list-item-content>
          <v-list-item-title v-text="postDir.postDirName"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <!-- </v-list-item-group> -->
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

  toPostDir(postDirId: number, $event: MouseEvent) {
    const boards = document.querySelectorAll(".sidebar-board");
    const mypages = document.querySelectorAll(".sidebar-mypage");
    const subscriptions = document.querySelectorAll(".sidebar-subscription");
    const addrss = document.querySelectorAll(".sidebar-addrss");
    if (boards?.length) {
      boards.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    if (mypages?.length) {
      mypages.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    if (subscriptions?.length) {
      subscriptions.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    if (addrss?.length) {
      addrss.forEach(el =>
        el.classList.remove("v-item--active", "v-list-item--active")
      );
    }
    ($event.currentTarget as HTMLElement).classList.add("v-list-item--active");
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
