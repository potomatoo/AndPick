<template>
  <div>
    <v-subheader>Feed</v-subheader>
    <v-list-group
      v-for="feed in feedList"
      :key="feed.feedId"
      no-action
      sub-group
      @click="toFeedPage(feed.feedId)"
    >
      <template v-slot:activator>
        <v-list-item-content @contextmenu.prevent="showFeedCtx($event, feed)">
          <v-list-item-title v-text="feed.feedName"></v-list-item-title>
        </v-list-item-content>
      </template>

      <v-list-item
        v-for="subItem in feed.subscribeList"
        :key="subItem.subscribeId"
        @contextmenu.prevent="showSubsCtx($event, subItem, feed)"
        @click="toArticleList(feed.feedId, subItem.subscribeId)"
      >
        <v-list-item-content>
          <v-list-item-title v-text="subItem.subscribeName"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list-group>

    <v-list-item @click="modalActive = !modalActive">
      <v-list-item-content class="text-center">
        <v-list-item-title>Create New Feed</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <create-feed-modal
      :modalActive.sync="modalActive"
      @addFeed="addFeeds"
      @closeModal="closeModal"
    />

    <feed-context-menu :feedItem="feedItem" />
    <subs-context-menu :subsItem="subsItem" :feedItem="feedItem" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

import { FeedList, SubscribeList } from "../../store/Feed.interface";
import SubsContextMenu from "@/components/feeds/SubsContextMenu.vue";
import FeedContextMenu from "@/components/feeds/FeedContextMenu.vue";
import CreateFeedModal from "@/components/feeds/CreateFeedModal.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    SubsContextMenu,
    FeedContextMenu,
    CreateFeedModal
  }
})
export default class SidebarFeed extends Vue {
  @feedModule.State feedList!: [];
  @feedModule.Mutation SET_SUB_CONTEXT_MENU: any;
  @feedModule.Mutation SET_FEED_CONTEXT_MENU: any;
  @feedModule.Action ADD_FEED: any;

  modalActive = false;

  isActiveSubsCtx = false;

  subsItem = {};

  feedItem = {};

  closeModal() {
    this.modalActive = false;
  }

  addFeeds(feedName: string) {
    this.ADD_FEED(feedName);
    this.closeModal();
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

  toFeedPage(feedId: number) {
    if (
      this.$route.name === "Feed" &&
      Number(this.$route.params.feedId) === feedId
    )
      return;
    this.$router.push({ name: "Feed", params: { feedId: feedId.toString() } });
  }

  toArticleList(feedId: number, subscribeId: number) {
    if (
      this.$route.name === "ArticleListInRss" &&
      Number(this.$route.params.subscribeId) === subscribeId
    )
      return;
    this.$router.push({
      name: "ArticleListInRss",
      params: { feedId: feedId.toString(), subscribeId: subscribeId.toString() }
    });
  }
}
</script>

<style scoped></style>
