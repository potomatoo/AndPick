<template>
  <div>
    <v-menu
      v-model="boardContextMenu.showCtx"
      :position-x="boardContextMenu.x"
      :position-y="boardContextMenu.y"
      offset-y
      absolute
    >
      <v-list dense width="170px">
        <v-list-item @click="activeRenameModal">
          <v-icon class="mr-3">mdi-cursor-text</v-icon>
          <v-list-item-title>Rename</v-list-item-title>
        </v-list-item>
        <!-- <v-list-item>
          <v-icon class="mr-3">mdi-rss</v-icon>
          <v-list-item-title>Manage Sources</v-list-item-title>
        </v-list-item> -->
        <v-divider></v-divider>
        <v-list-item @click="deleteModal = true">
          <v-icon class="mr-3" color="error">mdi-trash-can-outline</v-icon>
          <v-list-item-title class="red--text">Delete</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- 이름 변경 모달 -->
    <v-dialog v-model="renameModal" max-width="500px">
      <v-card>
        <v-form onsubmit="return false;">
          <v-card-text>
            <v-text-field
              v-model="inputBoardName"
              autofocus
              clearable
              :rules="rules"
              @keypress.enter="saveName"
            ></v-text-field>
            <small class="grey--text">* Change Feed Name</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" @click="saveName">SAVE</v-btn>
            <v-btn outlined color="grey" @click="closeFeedModal">CANCLE</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <!-- 피드 삭제 모달 -->
    <v-dialog v-model="deleteModal" max-width="450px">
      <v-card>
        <v-card-title>Delete {{ boardItem.boardName }} board</v-card-title>
        <v-card-text>
          Are you sure you want to delete board? This operation cannot be
          undone.
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error" @click="deleteBoard">DELETE</v-btn>
          <v-btn outlined color="grey" @click="deleteModal = false"
            >CANCLE</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context, Board } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class BoardContextMenu extends Vue {
  @feedModule.State boardList!: Board[];
  @feedModule.State boardContextMenu!: Context;
  @feedModule.Action UPDATE_BOARD: any;
  @feedModule.Action DELETE_BOARD: any;

  @Prop({ type: Object }) readonly boardItem!: Board;

  inputBoardName = "";
  renameModal = false;
  deleteModal = false;

  rules = [
    (value: string) => !!value || "this field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 보드가 존재합니다."
  ];

  checkDuplication(name: string | null) {
    if (this.boardItem.boardName === name) return false;
    return this.boardList.filter(board => board.boardName === name).length;
  }

  setTitle() {
    this.inputBoardName = this.boardItem.boardName;
  }

  activeRenameModal() {
    this.inputBoardName = "";
    this.setTitle();
    this.renameModal = true;
  }

  saveName() {
    if (this.checkDuplication(this.inputBoardName)) return;
    if (this.boardItem.boardName !== this.inputBoardName) {
      this.UPDATE_BOARD({
        boardId: this.boardItem.boardId,
        boardName: this.inputBoardName
      });
    }
    this.closeFeedModal();
  }
  closeFeedModal() {
    this.renameModal = false;
  }

  deleteBoard() {
    this.DELETE_BOARD(this.boardItem.boardId);
    this.deleteModal = false;
  }
}
</script>

<style scoped></style>
