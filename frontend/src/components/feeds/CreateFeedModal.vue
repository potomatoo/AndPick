<template>
  <v-dialog v-model="isActive" max-width="500px">
    <v-card>
      <v-form ref="form" onsubmit="return false;" :blur="closeModal">
        <v-card-text>
          <v-text-field
            v-model="newFeedName"
            label="Feed Name"
            autofocus
            clearable
            :rules="rules"
            @keypress.enter="addFeed"
          ></v-text-field>

          <small class="grey--text">* Create New Feed</small>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" @click="addFeed">Create</v-btn>
          <v-btn outlined color="grey" @click="closeModal">Cancle</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, Watch, PropSync } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { FeedList } from "@/store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class CreateFeedModal extends Vue {
  @feedModule.State feedList!: [];

  @PropSync("modalActive", { type: Boolean }) readonly isActive!: boolean;

  newFeedName = null;

  rules = [
    (value: any) => !!value || "This field is required.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 피드가 존재합니다."
  ];

  @Watch("isActive")
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

  addFeed() {
    if (this.newFeedName && !this.checkDuplication(this.newFeedName)) {
      this.$emit("addFeed", this.newFeedName);
    }
  }

  closeModal() {
    this.newFeedName = null;
    this.$emit("closeModal");
  }
}
</script>
