<template>
  <v-dialog v-model="isCreateFolderModalActive" max-width="500px">
    <v-card>
      <v-form ref="form" onsubmit="return false;">
        <v-card-text>
          <v-text-field
            v-model="newPostDirName"
            label="PostDirName"
            autofocus
            clearable
            :rules="rules"
            @keyup.enter="addPostDir"
          ></v-text-field>

          <small class="grey--text">* 생성할 폴더 이름을 작성해주세요</small>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="addPostDir">Create</v-btn>
          <v-btn text color="error" @click="closeModal">Cancle</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { PostDir } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component
export default class CreateFolderModeal extends Vue {
  @mypageModule.State isCreateFolderModalActive!: boolean;
  @mypageModule.State postDirList!: [];
  @mypageModule.Action ADD_POSTDIR: any;
  @mypageModule.Mutation TOGGLE_CREATEFOLDERMODAL: any;

  newPostDirName = null;

  rules = [
    (value: any) => !!value || "This field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 폴더가 존재합니다."
  ];

  @Watch("modalActive")
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
    this.TOGGLE_CREATEFOLDERMODAL();
  }

  addPostDir() {
    if (this.newPostDirName && !this.checkDuplication(this.newPostDirName)) {
      this.ADD_POSTDIR(this.newPostDirName);
      this.closeModal();
    }
  }
}
</script>
