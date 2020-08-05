<template>
  <div v-if="postDirItem">
    <v-menu
      v-model="postDirContextMenu.showCtx"
      :position-x="postDirContextMenu.x"
      :position-y="postDirContextMenu.y"
      offset-y
      absolute
    >
      <v-list dense width="150px">
        <v-list-item @click="activeRenameModal">
          <!-- <v-list-item-icon> -->
          <v-icon class="mr-3">mdi-cursor-text</v-icon>
          <!-- </v-list-item-icon> -->
          <v-list-item-title>Rename</v-list-item-title>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item @click="deleteModal = true">
          <v-icon class="mr-3">mdi-trash-can-outline</v-icon>
          <v-list-item-title>Delete</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- 이름 변경 모달 -->
    <v-dialog v-model="renameModal" max-width="500px">
      <v-card>
        <v-form ref="form" onsubmit="return false;">
          <v-card-text>
            <v-text-field
              v-model="inputPostDirName"
              autofocus
              clearable
              :rules="rules"
              @keypress.enter="savePostDirName"
            ></v-text-field>
            <small class="grey--text">* Change Folder Name</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="savePostDirName">SAVE</v-btn>
            <v-btn text color="error" @click="renameModal = false"
              >CANCLE</v-btn
            >
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <!-- 구독취소 모달 -->
    <v-dialog v-model="deleteModal" max-width="450px">
      <v-card>
        <v-card-title
          >{{ postDirItem.postDirName }}를 삭제하시겠습니까?</v-card-title
        >

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="deletePostDir">OK</v-btn>
          <v-btn text color="error" @click="deleteModal = false">CANCLE</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context, PostDir } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({})
export default class PostDirMenu extends Vue {
  @mypageModule.State postDirList!: PostDir[];
  @mypageModule.State postDirContextMenu!: Context;
  @mypageModule.Action UPDATE_POSTDIR!: any;
  @mypageModule.Action DELETE_POSTDIR!: any;

  @Prop({ type: Object }) readonly postDirItem!: PostDir;

  inputPostDirName = "";

  renameModal = false;
  deleteModal = false;

  rules = [
    (value: any) => !!value || "This field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 폴더가 존재합니다."
  ];

  checkDuplication(name: string | null) {
    if (this.postDirList.length) {
      return this.postDirList.some(
        (PostDir: PostDir) => PostDir.postDirName === name
      );
    }
  }

  setPostDirName() {
    this.inputPostDirName = this.postDirItem.postDirName;
  }

  activeRenameModal() {
    this.setPostDirName();
    this.renameModal = true;
  }

  savePostDirName() {
    if (this.inputPostDirName === this.postDirItem.postDirName) {
      this.renameModal = false;
      return;
    }

    this.UPDATE_POSTDIR({
      postDirId: Number(this.postDirItem.postDirId),
      postDirName: this.inputPostDirName
    });
    this.renameModal = false;
  }

  deletePostDir() {
    this.DELETE_POSTDIR(this.postDirItem.postDirId);
    this.deleteModal = false;
  }
}
</script>
