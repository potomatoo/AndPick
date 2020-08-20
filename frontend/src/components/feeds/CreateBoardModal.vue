<template>
  <v-dialog v-model="isActive" max-width="500px">
    <v-card>
      <v-form ref="form" onsubmit="return false;" :blur="closeModal">
        <v-card-text>
          <v-text-field
            v-model="newBoardName"
            label="보드"
            autofocus
            clearable
            :rules="rules"
            @keyup.enter="addBoard"
          ></v-text-field>

          <small class="grey--text">* 생성할 보드를 작성해주세요.</small>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            style="font-weight: bold"
            @click="addBoard"
            >생성</v-btn
          >
          <v-btn
            text
            color="error"
            style="font-weight: bold"
            @click="closeModal"
            >취소</v-btn
          >
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, Watch, PropSync } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Board } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class CreateBoardModal extends Vue {
  @feedModule.State boardList!: [];

  @PropSync("modalActive", { type: Boolean }) readonly isActive!: boolean;

  newBoardName = "";

  rules = [
    (value: any) => !!value || "글자를 입력해주세요.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 보드가 존재합니다.",
    (value: string) => value.length < 16 || "15자 이하로 입력해주세요."
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
    if (
      this.newBoardName &&
      !this.checkDuplication(this.newBoardName) &&
      this.newBoardName?.length < 16
    ) {
      this.$emit("addBoard", this.newBoardName);
    }
  }

  closeModal() {
    this.newBoardName = "";
    this.$emit("closeModal");
  }
}
</script>
