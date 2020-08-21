<template>
  <v-dialog v-model="isActive" max-width="500px">
    <v-card>
      <v-form ref="form" onsubmit="return false;" :blur="closeModal">
        <v-card-text>
          <v-text-field
            v-model="newFeedName"
            label="피드"
            autofocus
            clearable
            :rules="rules"
            @keypress.enter="addFeed"
          ></v-text-field>

          <small class="grey--text">* 생성할 피드를 작성해주세요.</small>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" style="font-weight: bold" @click="addFeed"
            >생성</v-btn
          >
          <v-btn
            text
            color="error"
            style="font-weight: bold"
            @click="closeModal"
            >취소</v-btn
          >
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, Watch, PropSync } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class CreateFeedModal extends Vue {
  @feedModule.State feedList!: [];

  @PropSync("modalActive", { type: Boolean }) readonly isActive!: boolean;

  newFeedName = "";

  rules = [
    (value: any) => !!value || "글자를 입력해주세요.",
    (value: string) =>
      !this.checkDuplication(value) || "동일한 피드가 존재합니다.",
    (value: string) =>
      (value && value.length < 16) || "15자 이하로 입력해주세요."
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
    if (
      this.newFeedName &&
      !this.checkDuplication(this.newFeedName) &&
      this.newFeedName?.length < 16
    ) {
      this.$emit("addFeed", this.newFeedName);
    }
  }

  closeModal() {
    this.newFeedName = "";
    this.$emit("closeModal");
  }
}
</script>
