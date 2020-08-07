<template>
  <div>
    <v-icon
      class="ml-3"
      style="color: black;"
      @click="modalActive = !modalActive"
      >mdi-folder-plus</v-icon
    >

    <v-dialog v-model="modalActive" max-width="500px">
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
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import { PostDir } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component
export default class CreateFolder extends Vue {
  @mypageModule.State postDirList!: [];
  @mypageModule.Action ADD_POSTDIR: any;

  newPostDirName = null;

  modalActive = false;

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
    this.modalActive = false;
  }

  addPostDir() {
    if (this.newPostDirName && !this.checkDuplication(this.newPostDirName)) {
      this.ADD_POSTDIR(this.newPostDirName);
      this.closeModal();
    }
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
