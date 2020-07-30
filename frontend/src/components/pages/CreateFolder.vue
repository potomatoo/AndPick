<template>
  <div>
    <v-icon class="ml-3" style="color: black;" @click="modalActive = !modalActive">mdi-folder-plus</v-icon>

    <v-dialog v-model="modalActive" max-width="500px">
      <v-card>
        <v-form ref="form" onsubmit="return false;">
          <v-card-text>
            <v-text-field
              v-model="folderName"
              label="Folder name"
              autofocus
              clearable
              :rules="rules"
              @keyup.enter="addFolderItem"
            ></v-text-field>

            <small class="grey--text">* 생성할 폴더 이름을 작성해주세요</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="addFolderItem">Create</v-btn>
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
  @mypageModule.Mutation addPost: any;

  folderName = null;

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
        (folder: PostDir) => folder.postDirName === name
      );
    }
  }

  closeModal() {
    this.folderName = null;
    this.modalActive = false;
  }

  addFolderItem() {
    if (this.folderName && !this.checkDuplication(this.folderName)) {
      this.addPost({ title: this.folderName, articleList: [] });
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
