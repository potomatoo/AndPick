<template>
  <div>
    <v-list>
      <v-subheader>Board</v-subheader>
      <v-list-item-group>
        <v-list-item
          v-for="board in boardList"
          :key="board.title"
          @contextmenu.prevent="showBoardCtx($event, board)"
        >
          <v-list-item-icon>
            <v-icon>
              mdi-star-outline
            </v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <router-link
              :to="{
                name: 'BoardArticleList',
                params: { boardId: board.boardId }
              }"
              class="router-link"
            >
              <v-list-item-title v-text="board.boardName"></v-list-item-title>
            </router-link>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>

      <v-list-item @click="modalActive = !modalActive">
        <v-list-item-content class="text-center">
          <v-list-item-title>Create New Board</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-dialog v-model="modalActive" max-width="500px">
        <v-card>
          <v-form ref="form" onsubmit="return false;">
            <v-card-text>
              <v-text-field
                v-model="newBoardName"
                label="Board Name"
                autofocus
                clearable
                :rules="rules"
                @keyup.enter="addBoards"
              ></v-text-field>

              <small class="grey--text">* Create New Board</small>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="success" @click="addBoards">Create</v-btn>
              <v-btn outlined color="grey" @click="closeModal">Cancle</v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-dialog>
    </v-list>
    <board-context-menu :boardItem="boardItem" />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import { Board } from "../../store/Feed.interface";
import BoardContextMenu from "@/components/feeds/BoardContextMenu.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    BoardContextMenu
  }
})
export default class SidebarBoard extends Vue {
  @feedModule.State boardList!: [];
  @feedModule.Action ADD_BOARD: any;
  @feedModule.Mutation SET_BOARD_CONTEXT_MENU: any;

  newBoardName = null;

  modalActive = false;

  boardItem = {};

  rules = [
    (value: any) => !!value || "this filed is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 보드가 존재합니다."
  ];

  @Watch("modalActive")
  onModalClose(isActive: boolean) {
    if (isActive && this.$refs.form) {
      (this.$refs.form as HTMLFormElement).reset();
    }
  }

  checkDuplication(name: string | null) {
    if (this.boardList.length) {
      return this.boardList.some((board: Board) => board.boardName === name);
    }
    return false;
  }

  closeModal() {
    this.newBoardName = null;
    this.modalActive = false;
  }

  addBoards() {
    if (this.newBoardName && !this.checkDuplication(this.newBoardName)) {
      this.ADD_BOARD(this.newBoardName);
      this.closeModal();
    }
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
}
</script>

<style scoped>
a.router-link-exact-active {
  text-decoration: none;
  color: inherit;
}
</style>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
