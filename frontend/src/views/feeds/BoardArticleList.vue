<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="board">
          <div class="caption">BOARD</div>
          <h1>{{ board.boardName }}</h1>
        </v-flex>
        <v-flex class="text-right">
          <v-icon @click="console.log('hi')">mdi-check</v-icon>
          <v-icon class="ml-3" @click="console.log('hi')"
            >mdi-replay mdi-flip-h</v-icon
          >
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>
    <v-container class="text-center">
      <v-layout justify-center>
        <h4>Save articles to this board</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          When you find an article in your Feed you want<br />to keep, use the
          icon to save it to this<br />board.
        </p>
      </v-layout>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Board } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class BoardArticleList extends Vue {
  @feedModule.State board!: Board;
  @feedModule.Action FETCH_ARTICLE_LIST_IN_BOARD: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.FETCH_ARTICLE_LIST_IN_BOARD(this.$route.params.boardId);
  }
}
</script>

<style></style>
