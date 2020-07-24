<template>
  <div>
    <v-divider></v-divider>
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
        </v-list-item-content>
      </v-list-item>

      <v-dialog v-model="modalActive" max-width="500px">
        <v-card>
          <v-card-text>
            <v-text-field
              v-model="newFeedName"
              label="Feed Name"
              autofocus
              clearable
              :rules="rules"
              @keyup.enter="addFeeds"
            ></v-text-field>

            <small class="grey--text">* Create New Feed</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="addFeeds">Create</v-btn>
            <v-btn text color="error" @click="closeModal">Cancle</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

import CreateFeedModal from "@/components/feeds/CreateFeedModal.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    CreateFeedModal
  }
})
export default class SidebarFeed extends Vue {
  @feedModule.State feedList!: [];
  @feedModule.Mutation addFeed: any;

  newFeedName = null;

  modalActive = false;

  rules = [(value: any) => !!value || "This field is required."];

  closeModal() {
    this.newFeedName = null;
    this.modalActive = false;
  }

  addFeeds() {
    if (this.newFeedName) {
      this.addFeed({ title: this.newFeedName });
      this.newFeedName = null;
      this.closeModal();
    }
  }
}
</script>

<style scoped>
a.router-link-exact-active {
  text-decoration: none;
  color: inherit;
}
</style>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>

