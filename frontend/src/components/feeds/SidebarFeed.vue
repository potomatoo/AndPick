<template>
  <div>
    <div class="d-flex">
      <v-subheader>Feed</v-subheader>
      <router-link
        :to="{
          name: 'FeedExplain'
        }"
        class="router-link explain"
      >
        <i class="mdi mdi-help-circle"></i>
      </router-link>
    </div>
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
                feedId: feed.feedId,
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
}
</script>

<style scoped>
.explain {
  opacity: 0.4;
  font-size: 20px;
  margin-top: 7px;
  margin-left: 180px;
}
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
