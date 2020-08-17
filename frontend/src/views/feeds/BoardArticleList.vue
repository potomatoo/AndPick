<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="board">
          <div class="caption mb-3">BOARD</div>
          <h1>{{ board.boardName }}</h1>
        </v-flex>
        <!-- <v-flex class="text-right">
          <v-icon @click="console.log('hi')">mdi-check</v-icon>
          <v-icon class="ml-3" @click="console.log('hi')"
            >mdi-replay mdi-flip-h</v-icon
          >
        </v-flex> -->
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 기사 없을때 -->
    <v-container v-if="board && !board.newsList.length" class="text-center">
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

    <!-- 기사 있을때 -->
    <v-container
      v-else-if="board && board.newsList.length"
      class="offset-lg3 lg6"
    >
      <v-list three-line>
        <v-list-item-group>
          <v-list-item v-for="news in board.newsList" :key="news.newsId">
            <v-list-item-content>
              <router-link
                class="router-link"
                :to="{
                  name: 'BoardArticleDetail',
                  params: { boardId: board.boardId, newsId: news.newsId }
                }"
              >
                <v-list-item-content class="mt-3">
                  <div class="h4">
                    {{ news.newsTitle }}
                  </div>
                  <div class="sumtitle-1 text--secondary">
                    {{ news.newsDate }}
                  </div>
                  <!-- <div class="sumtitle-1 text--secondary">
                    {{ article.rssTitle }}
                  </div> -->
                  <v-list-item-subtitle>
                    {{ news.newsDescription }}
                  </v-list-item-subtitle>
                </v-list-item-content>
              </router-link>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-container>

    <v-overlay :value="isLoading">
      <v-progress-circular
        indeterminate
        size="80"
        width="10"
      ></v-progress-circular>
    </v-overlay>
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
  @feedModule.State isLoading!: boolean;
  @feedModule.Mutation SET_LOADING: any;
  @feedModule.Action FETCH_ARTICLE_LIST_IN_BOARD: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.SET_LOADING();
    this.FETCH_ARTICLE_LIST_IN_BOARD(this.$route.params.boardId);
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
