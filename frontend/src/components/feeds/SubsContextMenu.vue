<template>
  <div v-if="subsItem">
    <v-menu
      v-model="subsContextMenu.showCtx"
      :position-x="subsContextMenu.x"
      :position-y="subsContextMenu.y"
      offset-y
      absolute
    >
      <v-list dense width="165px">
        <v-list-item @click="activeRenameModal">
          <!-- <v-list-item-icon> -->
          <v-icon class="mr-3">mdi-cursor-text</v-icon>
          <!-- </v-list-item-icon> -->
          <v-list-item-title style="font-size: 13px"
            >이름 변경</v-list-item-title
          >
        </v-list-item>
        <v-list-item @click="activeMoveModal">
          <v-icon class="mr-3">mdi-exit-to-app</v-icon>
          <v-list-item-title style="font-size: 13px"
            >다른 피드로 이동</v-list-item-title
          >
        </v-list-item>

        <v-list-item @click="unfollowModal = true" style="font-size: 13px">
          <v-icon class="mr-3" color="error">mdi-trash-can-outline</v-icon>
          <v-list-item-title class="red--text">구독 취소</v-list-item-title>
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
            <small class="grey--text">* 변경할 채널 이름을 작성하세요.</small>
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

    <!-- 피드 이동 모달 -->
    <v-dialog v-model="moveToModal" max-width="450px">
      <v-card>
        <v-card-title style="font-family: 'Do Hyeon', sans-serif;"
          >이동 혹은 추가할 피드를 선택해주세요.</v-card-title
        >
        <v-chip class="mx-6 mt-5" outlined label>
          <v-avatar left>
            <v-icon>mdi-checkbox-marked-circle</v-icon>
          </v-avatar>
          {{ subsItem.subscribeName }}
        </v-chip>

        <v-card-text class="mt-10">
          현재 "{{ subsItem.subscribeName }}"가(이) 저장되있는 피드입니다.
        </v-card-text>
        <v-card-text>
          <v-checkbox
            v-for="feed in selectedFeed"
            :key="feed.feed.feedId"
            v-model="addedFeed"
            :label="feed.feed.feedName"
            :value="feed.feed.feedId"
            class="mt-0"
          ></v-checkbox>
        </v-card-text>

        <v-card-text>
          전체 피드입니다.
        </v-card-text>
        <v-card-text>
          <v-checkbox
            v-for="feed in othersFeed"
            :key="feed.feedId"
            v-model="selectMoveToFeed"
            :label="feed.feedName"
            :value="feed.feedId"
            class="mt-0"
          ></v-checkbox>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            style="font-weight: bold"
            @click="moveToFeed"
            >저장</v-btn
          >
          <v-btn
            text
            color="error"
            style="font-weight: bold"
            @click="moveToModal = false"
            >취소</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 구독취소 모달 -->
    <v-dialog v-model="unfollowModal" max-width="450px">
      <v-card>
        <v-card-title style="font-family: 'Do Hyeon', sans-serif;"
          >{{ subsItem.subscribeName }} 구독을 취소 하시겠습니까?</v-card-title
        >
        <v-card-text>
          * "{{ feedItem.feedName }}" 피드에서 삭제됩니다.
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="error"
            style="font-weight: bold"
            @click="unfollowSubs"
            >삭제</v-btn
          >
          <v-btn
            text
            color="primary"
            style="font-weight: bold"
            @click="unfollowModal = false"
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
import { Context, FeedList, SubscribeList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class SubsContextMenu extends Vue {
  @feedModule.State feedList!: FeedList[];
  @feedModule.State subsContextMenu!: Context;
  @feedModule.Action UPDATE_SUBSCRIBE: any;
  @feedModule.Action UNFOLLOW_SUBSCRIPTION: any;
  @feedModule.Action FOLLOW_SUBSCRIPTION: any;

  @Prop({ type: Object }) readonly subsItem!: SubscribeList;
  @Prop({ type: Object }) readonly feedItem!: FeedList;

  inputSubsname = "";

  renameModal = false;
  moveToModal = false;
  unfollowModal = false;

  addedFeed: number[] = [];
  selectMoveToFeed: number[] = [];

  get selectedFeed() {
    const addedData: { feed: FeedList; subscribeId: number }[] = [];
    this.feedList.forEach(feed => {
      feed.subscribeList!.forEach(subs => {
        if (this.subsItem.rss && subs.rss.rssId === this.subsItem.rss.rssId) {
          addedData.push({ feed: feed, subscribeId: subs.subscribeId });
        }
      });
    });
    return addedData;
  }

  get othersFeed() {
    return this.feedList.filter(
      fl => !this.selectedFeed.some(({ feed }) => feed.feedId === fl.feedId)
    );
  }

  rules = [
    (value: string) => !!value || "글자를 입력해주세요.",
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
    if (!this.inputSubsname || this.checkDuplication(this.inputSubsname))
      return;
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

  unfollowSubs() {
    this.UNFOLLOW_SUBSCRIPTION(this.subsItem.subscribeId);
    this.unfollowModal = false;
  }

  activeMoveModal() {
    this.addedFeed = [];
    this.selectMoveToFeed = [];
    this.selectedFeed.forEach(feed => this.addedFeed.push(feed.feed.feedId));
    this.moveToModal = true;
  }

  moveToFeed() {
    this.selectedFeed.forEach(feed => {
      if (!this.addedFeed.includes(feed.feed.feedId)) {
        this.UNFOLLOW_SUBSCRIPTION(feed.subscribeId);
      }
    });

    if (this.selectMoveToFeed.length) {
      this.selectMoveToFeed.forEach(fid => {
        const payload = {
          feedId: fid,
          rss: this.subsItem.rss,
          subscribeName: this.subsItem.subscribeName
        };
        this.FOLLOW_SUBSCRIPTION(payload);
      });
    }
    this.moveToModal = false;
  }
}
</script>

<style scoped>
.v-label {
  margin-top: 2px;
}
</style>
