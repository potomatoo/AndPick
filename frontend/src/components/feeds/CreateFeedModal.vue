<template>
  <v-dialog v-model="isCreateFolderActive" max-width="500px">
    <v-card>
      <v-card-text>
        <v-text-field v-model="FolderName" label="Folder name"></v-text-field>

        <small class="grey--text">* 생성할 폴더 이름을 작성해주세요</small>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text color="primary" @click="clearCreateFolder">clear</v-btn>
        <v-btn text color="primary" @click="createFolder(FolderName)"
          >Create</v-btn
        >
        <v-btn text color="primary" @click="exit">exit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const feedModule = namespace("feedModule");

@Component
export default class CreateFeedModal extends Vue {}
</script>
