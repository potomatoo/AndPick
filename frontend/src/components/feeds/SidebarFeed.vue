<template>
  <div>
<<<<<<< HEAD
    <v-list>
      <v-subheader>Feed</v-subheader>
      <v-list-group
        v-for="feed in feedList"
        :key="feed.title"
        v-model="feed.active"
        :prepend-icon="feed.action"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="feed.title"></v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list-item v-for="subItem in feed.items" :key="subItem.title">
          <v-list-item-content>
            <v-list-item-title v-text="subItem.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>

      <v-list-item @click="modalActive = !modalActive">
        <v-list-item-content class="text-center">
          <v-list-item-title>Create New Feed</v-list-item-title>
=======
    <v-subheader>Feed</v-subheader>
    <v-list-group
      v-for="feed in feedList"
      :key="feed.feedName"
      no-action
      sub-group
    >
      <template v-slot:activator>
        <v-list-item-content>
          <router-link
            :to="{ name: 'Feed', params: { feedName: feed.feedName } }"
            class="router-link"
          >
            <v-list-item-title v-text="feed.feedName"></v-list-item-title>
          </router-link>
        </v-list-item-content>
      </template>

      <v-list-item v-for="subItem in feed.subscribeList" :key="subItem.title">
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
>>>>>>> a57a79a90a6ffda792081a8b1afaff2732d52423
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
            <v-btn text color="primary" @click="addFeeds">Create</v-btn>
            <v-btn text color="error" @click="closeModal">Cancle</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

// import CreateFeedModal from "@/components/feeds/CreateFeedModal.vue";
import { SidebarList, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class SidebarFeed extends Vue {
  @feedModule.State feedList!: [];
  @feedModule.Action ADD_FEED: any;

  newFeedName = null;

  modalActive = false;

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
      // subscribeList 없어도 될듯
      this.ADD_FEED(this.newFeedName);
      this.closeModal();
    }
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
