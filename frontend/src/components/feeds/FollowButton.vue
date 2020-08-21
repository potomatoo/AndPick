<template>
  <div>
    <v-menu
      offset-x
      :close-on-content-click="false"
      min-width="300px"
      :close-on-click="closeMenu"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          outlined
          color="success"
          v-bind="attrs"
          v-on="on"
          class="float-right"
        >
          팔로우
        </v-btn>
      </template>
      <v-list class="py-0">
        <v-list-item v-for="(feed, i) in feedList" :key="i">
          <v-icon color="grey" class="mr-2">mdi-rss</v-icon>
          <v-list-item-title>{{ feed.feedName }}</v-list-item-title>
          <v-btn
            v-if="checkSubscribe(feed.subscribeList, rss)"
            class="ml-3"
            outlined
            color="success"
            small
            @click="addRss(feed.feedId, rss)"
          >
            <v-icon left>mdi-plus</v-icon> 추가
          </v-btn>
          <v-btn
            v-else
            class="ml-3"
            outlined
            color="error"
            small
            @click="addRss(feed.feedId, rss)"
          >
            <v-icon left>mdi-window-close</v-icon> 삭제
          </v-btn>
        </v-list-item>
        <hr class="ma-0" />
        <v-list-item @click="modalActive = !modalActive">
          <v-icon color="success" class="mr-2">mdi-plus</v-icon>
          <v-list-item-title class="success--text"
            >새 피드 생성</v-list-item-title
          >
        </v-list-item>
      </v-list>
    </v-menu>
    <create-feed-modal
      :modalActive.sync="modalActive"
      @addFeed="addFeeds"
      @closeModal="closeModal"
    />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { SubscribeList, Rss } from "@/store/Feed.interface";

import CreateFeedModal from "@/components/feeds/CreateFeedModal.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    CreateFeedModal
  }
})
export default class FollowButton extends Vue {
  @feedModule.State feedList!: [];
  @feedModule.Action SUBSCRIBE_RSS: any;
  @feedModule.Action ADD_FEED: any;

  @Prop({ type: Object }) readonly rss!: Rss;

  modalActive = false;

  closeMenu = true;

  checkSubscribe(subscribedList: SubscribeList[], rss: Rss) {
    return !(
      subscribedList.length &&
      subscribedList.some(el => el.rss.rssId === rss.rssId)
    );
  }

  addRss(feedId: number, rss: Rss) {
    this.SUBSCRIBE_RSS({ feedId, rss });
  }

  closeModal() {
    this.modalActive = false;
  }

  @Watch("modalActive")
  prevent() {
    if (this.modalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }

  addFeeds(feedName: string) {
    this.ADD_FEED(feedName);
    this.closeModal();
  }
}
</script>

<style scoped></style>
