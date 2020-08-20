<template>
  <div>
    <v-menu
      v-model="feedContextMenu.showCtx"
      :position-x="feedContextMenu.x"
      :position-y="feedContextMenu.y"
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
        <!-- <v-list-item>
          <v-icon class="mr-3">mdi-rss</v-icon>
          <v-list-item-title>Manage Sources</v-list-item-title>
        </v-list-item> -->

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
        <v-form onsubmit="return false;">
          <v-card-text>
            <v-text-field
              v-model="inputFeedName"
              autofocus
              clearable
              :rules="rules"
              @keypress.enter="saveName"
            ></v-text-field>
            <small class="grey--text">* 변경할 피드 이름을 작성해주세요.</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              text
              color="primary"
              style="font-weight: bold"
              @click="saveName"
              >저장</v-btn
            >
            <v-btn
              text
              color="error"
              style="font-weight: bold"
              @click="closeFeedModal"
              >취소</v-btn
            >
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <!-- 피드 삭제 모달 -->
    <v-dialog v-model="deleteModal" max-width="450px">
      <v-card>
        <v-card-title style="font-family: 'Do Hyeon', sans-serif;"
          >"{{ feedItem.feedName }}" 피드를 삭제하시겠습니까?
        </v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="error"
            style="font-weight: bold"
            @click="deleteFeed"
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
import { Component, Vue, Prop } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class FeedContextMenu extends Vue {
  @feedModule.State feedList!: FeedList[];
  @feedModule.State feedContextMenu!: Context;
  @feedModule.Mutation SET_LOADING_TRUE: any;
  @feedModule.Action UPDATE_FEED: any;
  @feedModule.Action DELETE_FEED: any;

  @Prop({ type: Object }) readonly feedItem!: FeedList;

  inputFeedName = "";
  renameModal = false;
  deleteModal = false;

  rules = [
    (value: string) => !!value || "글자를 입력해주세요.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 피드가 존재합니다."
  ];

  checkDuplication(name: string | null) {
    if (this.feedItem.feedName === name) return false;
    return this.feedList.filter(feed => feed.feedName === name).length;
  }

  setTitle() {
    this.inputFeedName = this.feedItem.feedName;
  }

  activeRenameModal() {
    this.setTitle();
    this.renameModal = true;
  }

  saveName() {
    if (!this.inputFeedName || this.checkDuplication(this.inputFeedName))
      return;
    if (this.feedItem.feedName !== this.inputFeedName) {
      this.SET_LOADING_TRUE();
      this.UPDATE_FEED({
        feedId: this.feedItem.feedId,
        feedName: this.inputFeedName
      });
    }
    this.closeFeedModal();
  }
  closeFeedModal() {
    this.renameModal = false;
  }

  deleteFeed() {
    this.DELETE_FEED(this.feedItem.feedId);
    this.deleteModal = false;
  }
}
</script>

<style scoped>
v-list-item {
  height: 30px !important;
}
</style>
