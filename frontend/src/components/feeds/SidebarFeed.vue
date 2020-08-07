<template>
  <div>
    <v-subheader>Feed</v-subheader>
    <v-list-group
      v-for="feed in feedList"
      :key="feed.feedId"
      no-action
      sub-group
    >
      <template v-slot:activator>
        <v-list-item-content @contextmenu.prevent="showFeedCtx($event, feed)">
          <router-link
            :to="{
              name: 'Feed',
              params: { feedId: feed.feedId }
            }"
            class="router-link"
          >
            <v-list-item-title v-text="feed.feedName"></v-list-item-title>
          </router-link>
        </v-list-item-content>
      </template>

      <v-list-item
        v-for="subItem in feed.subscribeList"
        :key="subItem.subscribeId"
        @contextmenu.prevent="showSubsCtx($event, subItem, feed)"
      >
        <v-list-item-content>
          <router-link
            :to="{
              name: 'ArticleListInRss',
              params: {
                feedName: feed.feedName,
                subscribeId: subItem.subscribeId
              }
            }"
            class="router-link"
          >
            <v-list-item-title
              v-text="subItem.subscribeName"
            ></v-list-item-title>
          </router-link>
        </v-list-item-content>
      </v-list-item>
    </v-list-group>

    <v-list-item @click="modalActive = !modalActive">
      <v-list-item-content class="text-center">
        <v-list-item-title>Create New Feed</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-dialog v-model="modalActive" max-width="500px">
      <v-card>
        <v-form ref="form" onsubmit="return false;">
          <v-card-text>
            <v-text-field
              v-model="newFeedName"
              label="Feed Name"
              autofocus
              clearable
              :rules="rules"
              @keypress.enter="addFeeds"
            ></v-text-field>

            <small class="grey--text">* Create New Feed</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" @click="addFeeds">Create</v-btn>
            <v-btn outlined color="grey" @click="closeModal">Cancle</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
    <feed-context-menu :feedItem="feedItem" />
    <subs-context-menu :subsItem="subsItem" :feedItem="feedItem" />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

// import CreateFeedModal from "@/components/feeds/CreateFeedModal.vue";
import { FeedList, SubscribeList } from "../../store/Feed.interface";
import SubsContextMenu from "@/components/feeds/SubsContextMenu.vue";
import FeedContextMenu from "@/components/feeds/FeedContextMenu.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    SubsContextMenu,
    FeedContextMenu
  }
})
export default class SidebarFeed extends Vue {
  @feedModule.State feedList!: [];
  @feedModule.Mutation SET_SUB_CONTEXT_MENU: any;
  @feedModule.Mutation SET_FEED_CONTEXT_MENU: any;
  @feedModule.Action ADD_FEED: any;

  newFeedName = null;

  modalActive = false;

  isActiveSubsCtx = false;

  subsItem = {};

  feedItem = {};

  rules = [
    (value: any) => !!value || "This field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 피드가 존재합니다."
  ];

  @Watch("modalActive")
  onModalClose(isActive: boolean) {
    if (isActive && this.$refs.form) {
      (this.$refs.form as HTMLFormElement).reset();
    }
  }

  checkDuplication(name: string | null) {
    if (this.feedList.length) {
      return this.feedList.some((feed: FeedList) => feed.feedName === name);
    }
    return false;
  }

  closeModal() {
    this.newFeedName = null;
    this.modalActive = false;
  }

  addFeeds() {
    if (this.newFeedName && !this.checkDuplication(this.newFeedName)) {
      this.ADD_FEED(this.newFeedName);
      this.closeModal();
    }
  }

  showSubsCtx(e: MouseEvent, subsItem: SubscribeList, feedItem: FeedList) {
    this.subsItem = subsItem;
    this.feedItem = feedItem;
    const ctx = {
      showCtx: true,
      x: e.clientX,
      y: e.clientY
    };
    this.SET_SUB_CONTEXT_MENU(ctx);
  }

  showFeedCtx(e: MouseEvent, item: FeedList) {
    this.feedItem = item;
    const ctx = {
      showCtx: true,
      x: e.clientX,
      y: e.clientY
    };
    this.SET_FEED_CONTEXT_MENU(ctx);
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
