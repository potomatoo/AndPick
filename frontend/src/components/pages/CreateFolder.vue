<template>
  <div>
    <v-icon
      class="ml-3"
      style="color: black;"
      @click="isCreateFolderActive = !isCreateFolderActive"
    >mdi-folder-plus</v-icon>

    <v-dialog v-model="isCreateFolderActive" max-width="500px">
      <v-card>
        <v-card-text>
          <v-text-field
            v-model="FolderName"
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
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const mypageModule = namespace("mypageModule");

@Component({})
export default class CreateFolder extends Vue {
  @mypageModule.State MyFolderList!: [];
  @mypageModule.Mutation addFolder: any;

  isCreateFolderActive = false;
  FolderName = "";
  rules = [(value: any) => !!value || "This field is required."];

  closeModal() {
    this.isCreateFolderActive = false;
  }
  addFolderItem() {
    if (this.FolderName) {
      this.addFolder(this.FolderName);
      this.FolderName = "";
      this.closeModal();
    }
  }
}
</script>

<style scoped>
</style>
