<template>
  <div>
    <v-menu
      v-model="feedContextMenu.showCtx"
      :position-x="feedContextMenu.x"
      :position-y="feedContextMenu.y"
      offset-y
      absolute
    >
      <v-list dense width="170px">
        <v-list-item @click="activeRenameModal">
          <!-- <v-list-item-icon> -->
          <v-icon class="mr-3">mdi-cursor-text</v-icon>
          <!-- </v-list-item-icon> -->
          <v-list-item-title>Rename</v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-icon class="mr-3">mdi-rss</v-icon>
          <v-list-item-title>Manage Sources</v-list-item-title>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item>
          <v-icon class="mr-3">mdi-trash-can-outline</v-icon>
          <v-list-item-title>Delete</v-list-item-title>
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
            <small class="grey--text">* Change Feed Name</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="saveName">SAVE</v-btn>
            <v-btn text color="error" @click="closeFeedModal">CANCLE</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component({
  props: {
    item: Object
  }
})
export default class FeedContextMenu extends Vue {
  @feedModule.State feedList!: FeedList[];
  @feedModule.State feedContextMenu!: Context;
  @feedModule.Action UPDATE_FEED: any;
  @feedModule.Action DELETE_FEED: any;

  @Prop({ type: Object }) readonly feedItem!: FeedList;

  inputFeedName = "";
  renameModal = false;

  rules = [
    (value: string) => !!value || "this field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 피드가 존재합니다."
  ];

  checkDuplication(name: string | null) {
    if (this.feedItem.feedName === this.inputFeedName) return false;
    return this.feedList.filter(feed => feed.feedName === this.inputFeedName)
      .length;
  }

  setTitle() {
    this.inputFeedName = this.feedItem.feedName;
  }

  activeRenameModal() {
    this.setTitle();
    this.renameModal = true;
  }

  saveName() {
    if (this.checkDuplication(this.inputFeedName)) return;
    if (this.feedItem.feedName !== this.inputFeedName) {
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
}
</script>

<style scoped></style>
