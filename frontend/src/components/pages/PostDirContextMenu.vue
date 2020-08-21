<template>
  <div v-if="postDirItem">
    <v-menu
      v-model="postDirContextMenu.showCtx"
      :position-x="postDirContextMenu.x"
      :position-y="postDirContextMenu.y"
      offset-y
      absolute
    >
      <v-list dense width="130px">
        <v-list-item @click="activeRenameModal">
          <!-- <v-list-item-icon> -->
          <v-icon class="mr-3">mdi-cursor-text</v-icon>
          <!-- </v-list-item-icon> -->
          <v-list-item-title style="font-size: 13px"
            >이름 변경</v-list-item-title
          >
        </v-list-item>

        <v-list-item @click="deleteModal = true">
          <v-icon class="mr-3" color="error">mdi-trash-can-outline</v-icon>
          <v-list-item-title class="red--text" style="font-size: 13px"
            >삭제</v-list-item-title
          >
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
            <small class="grey--text">* 변경할 폴더 이름을 작성해주세요.</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              text
              color="primary"
              style="font-weight: bold"
              @click="savePostDirName"
              >저장</v-btn
            >
            <v-btn
              text
              color="error"
              style="font-weight: bold"
              @click="renameModal = false"
              >취소</v-btn
            >
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <!-- 구독취소 모달 -->
    <v-dialog v-model="deleteModal" max-width="450px">
      <v-card>
        <v-card-title style="font-family: 'Do Hyeon', sans-serif;"
          >"{{ postDirItem.postDirName }}" 폴더를
          삭제하시겠습니까?</v-card-title
        >

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="error"
            style="font-weight: bold"
            @click="deletePostDir"
            >삭제</v-btn
          >
          <v-btn
            text
            color="primary"
            style="font-weight: bold"
            @click="deleteModal = false"
            >취소</v-btn
          >
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
export default class PostDirContextMenu extends Vue {
  @mypageModule.State postDirList!: PostDir[];
  @mypageModule.State postDirContextMenu!: Context;
  @mypageModule.Action UPDATE_POSTDIR!: any;
  @mypageModule.Action DELETE_POSTDIR!: any;

  @Prop({ type: Object }) readonly postDirItem!: PostDir;

  inputPostDirName = "";

  renameModal = false;
  deleteModal = false;

  rules = [
    (value: any) => !!value || "글자를 입력해주세요.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 폴더가 존재합니다."
  ];

  checkDuplication(name: string | null) {
    if (this.postDirItem.postDirName === name) return false;
    return this.postDirList.filter(postDir => postDir.postDirName === name)
      .length;
  }

  setPostDirName() {
    this.inputPostDirName = this.postDirItem.postDirName;
  }

  activeRenameModal() {
    this.inputPostDirName = "";
    this.setPostDirName();
    this.renameModal = true;
  }

  savePostDirName() {
    if (!this.inputPostDirName || this.checkDuplication(this.inputPostDirName))
      return;
    if (this.postDirItem.postDirName !== this.inputPostDirName) {
      this.UPDATE_POSTDIR({
        postDirId: Number(this.postDirItem.postDirId),
        postDirName: this.inputPostDirName
      });
    }
    this.renameModal = false;
  }

  deletePostDir() {
    this.DELETE_POSTDIR({
      postDirId: this.postDirItem.postDirId,
      routeName: this.$route.name,
      routePostDirId: this.$route.params.postDirId
    });
    this.deleteModal = false;
  }
}
</script>

<style scoped>
v-list-item {
  height: 30px !important;
}
</style>
