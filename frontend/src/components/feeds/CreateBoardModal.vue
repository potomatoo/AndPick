<template>
  <v-dialog v-model="isActive" max-width="500px">
    <v-card>
      <v-form ref="form" onsubmit="return false;" :blur="closeModal">
        <v-card-text>
          <v-text-field
            v-model="newBoardName"
            label="Board Name"
            autofocus
            clearable
            :rules="rules"
            @keyup.enter="addBoard"
          ></v-text-field>

          <small class="grey--text">* Create New Board</small>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" @click="addBoard">Create</v-btn>
          <v-btn outlined color="grey" @click="closeModal">Cancle</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, Watch, PropSync } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Board } from "@/store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class CreateBoardModal extends Vue {
  @feedModule.State boardList!: [];

  @PropSync("modalActive", { type: Boolean }) readonly isActive!: boolean;

  newBoardName = null;

  rules = [
    (value: any) => !!value || "This field is required.",
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

  addBoard() {
    if (this.newBoardName && !this.checkDuplication(this.newBoardName)) {
      this.$emit("addBoard", this.newBoardName);
    }
  }

  closeModal() {
    this.newBoardName = null;
    this.$emit("closeModal");
  }
}
</script>
