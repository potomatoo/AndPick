<template>
  <div>
    <v-list>
      <div class="d-flex">
        <v-subheader style="font-family: 'Do Hyeon', sans-serif; color: black"
          >보드</v-subheader
        >
        <router-link
          :to="{
            name: 'BoardExplain'
          }"
          class="router-link explain"
        >
          <i class="mdi mdi-help-circle"></i>
        </router-link>
      </div>
      <!-- <v-list-item-group> -->
      <v-list-item
        v-for="board in boardList"
        :key="board.title"
        @contextmenu.prevent="showBoardCtx($event, board)"
        @click="toBoardArticleList(board.boardId, $event)"
        class="sidebar-board"
        color="rgb(223, 50, 119)"
      >
        <v-list-item-icon>
          <v-icon>
            mdi-star-outline
          </v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title v-text="board.boardName"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <!-- </v-list-item-group> -->

      <v-list-item @click="modalActive = !modalActive">
        <v-list-item-content class="text-center">
          <v-list-item-title>새 보드 생성</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <create-board-modal
        :modalActive.sync="modalActive"
        @addBoard="addBoards"
        @closeModal="closeModal"
      />
    </v-list>
    <board-context-menu :boardItem="boardItem" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

import { Board } from "../../store/Feed.interface";
import BoardContextMenu from "@/components/feeds/BoardContextMenu.vue";
import CreateBoardModal from "@/components/feeds/CreateBoardModal.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    BoardContextMenu,
    CreateBoardModal
  }
})
export default class SidebarBoard extends Vue {
  @feedModule.State boardList!: [];
  @feedModule.Action ADD_BOARD: any;
  @feedModule.Mutation SET_BOARD_CONTEXT_MENU: any;

  modalActive = false;

  boardItem = {};

  closeModal() {
    this.modalActive = false;
  }

  addBoards(boardName: string) {
    this.ADD_BOARD(boardName);
    this.closeModal();
  }

  showBoardCtx(e: MouseEvent, board: Board) {
    this.boardItem = board;
    const ctx = {
      showCtx: true,
      x: e.clientX,
      y: e.clientY
    };
    this.SET_BOARD_CONTEXT_MENU(ctx);
  }

  toBoardArticleList(boardId: number, $event: MouseEvent) {
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
      this.$route.name === "BoardArticleList" &&
      Number(this.$route.params.boardId) === boardId
    )
      return;
    this.$router.push({
      name: "BoardArticleList",
      params: { boardId: boardId.toString() }
    });
  }
}
</script>

<style scoped>
.explain {
  opacity: 0.4;
  font-size: 20px;
  margin-top: 5px;
  margin-left: 170px;
  float: left;
}
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
