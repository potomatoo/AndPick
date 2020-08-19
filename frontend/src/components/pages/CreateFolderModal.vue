<template>
  <v-dialog v-model="isActive" max-width="500px">
    <v-card>
      <v-form ref="form" onsubmit="return false;">
        <v-card-text>
          <v-text-field
            v-model="newPostDirName"
            label="마이페이지"
            autofocus
            clearable
            :rules="rules"
            @keyup.enter="addPostDir"
          ></v-text-field>

          <small class="grey--text">* 생성할 폴더를 작성해주세요</small>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            style="font-weight: bold"
            @click="addPostDir"
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
import { PostDir } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component
export default class CreateFolderModal extends Vue {
  @mypageModule.State isCreateFolderModalActive!: boolean;
  @mypageModule.State postDirList!: [];
  @mypageModule.Mutation TOGGLE_CREATEFOLDERMODAL: any;
  @mypageModule.Action ADD_POSTDIR: any;

  @PropSync("folderModalActive", { type: Boolean }) isActive!: boolean;

  newPostDirName = null;

  rules = [
    (value: any) => !!value || "글자를 입력해주세요.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 폴더가 존재합니다."
  ];

  @Watch("isActive")
  onModalClose(isActive: boolean) {
    if (isActive && this.$refs.form) {
      (this.$refs.form as HTMLFormElement).reset();
    }
  }

  checkDuplication(name: string | null) {
    if (this.postDirList.length) {
      return this.postDirList.some(
        (PostDir: PostDir) => PostDir.postDirName === name
      );
    }
  }

  closeModal() {
    this.newPostDirName = null;
    this.isActive = false;
  }

  addPostDir() {
    if (this.newPostDirName && !this.checkDuplication(this.newPostDirName)) {
      this.ADD_POSTDIR(this.newPostDirName);
      this.closeModal();
    }
  }
}
</script>
