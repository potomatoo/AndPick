<template>
  <div v-if="subsItem">
    <v-menu
      v-model="subsContextMenu.showCtx"
      :position-x="subsContextMenu.x"
      :position-y="subsContextMenu.y"
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
        <v-list-item>
          <v-icon class="mr-3">mdi-exit-to-app</v-icon>
          <v-list-item-title>Move to...</v-list-item-title>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item>
          <v-icon class="mr-3">mdi-trash-can-outline</v-icon>
          <v-list-item-title>Unfollow</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- 이름 변경 모달 -->
    <v-dialog v-model="renameModal" max-width="500px">
      <v-card>
        <v-form ref="form" onsubmit="return false;">
          <v-card-text>
            <v-text-field
              v-model="inputSubsname"
              autofocus
              clearable
              :rules="rules"
              @keypress.enter="saveName"
            ></v-text-field>
            <small class="grey--text">* Change Subscribe Name</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="saveName">Save</v-btn>
            <v-btn text color="error" @click="closeFeedModal">Cancle</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context, FeedList, SubscribeList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class SubsContextMenu extends Vue {
  @feedModule.State subsContextMenu!: Context;
  @feedModule.Action UPDATE_SUBSCRIBE: any;

  @Prop({ type: Object }) readonly subsItem!: SubscribeList;
  @Prop({ type: Object }) readonly feedItem!: FeedList;

  inputSubsname = "";

  renameModal = false;

  rules = [
    (value: string) => !!value || "this field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 피드가 존재합니다."
  ];

  @Watch("feedItem")
  checkDuplication(name: string | null) {
    if (this.feedItem.subscribeList) {
      return this.feedItem.subscribeList.some(
        feed =>
          feed.subscribeName === name && this.subsItem.subscribeName !== name
      );
    }
    return false;
  }

  setTitle() {
    this.inputSubsname = this.subsItem.subscribeName;
  }

  activeRenameModal() {
    this.setTitle();
    this.renameModal = true;
  }

  closeFeedModal() {
    this.renameModal = false;
  }

  saveName() {
    if (this.inputSubsname === this.subsItem.subscribeName) {
      this.closeFeedModal();
      return;
    }
    const payload = {
      feedId: this.subsItem.feedId,
      subscribeId: this.subsItem.subscribeId,
      subscribeName: this.inputSubsname
    };
    this.UPDATE_SUBSCRIBE(payload);
    this.closeFeedModal();
  }
}
</script>

<style scoped></style>
