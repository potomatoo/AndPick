<template>
  <div class="mt-10" v-if="news">
    <v-container>
      <!-- <v-layout> -->
      <h3>{{ news.newsTitle }}</h3>
      <div class="subtitle-1 text--secondary">{{ news.newsDate }}</div>
      <div class="text-right">
        <!-- ADD BOARD 메뉴 -->
        <v-menu offset-x :close-on-content-click="false" min-width="300px">
          <template v-slot:activator="{ on, attrs }">
            <v-icon v-bind="attrs" v-on="on">mdi-star-box-outline</v-icon>
            <!-- <v-icon
              outlined
              color="success"
              v-bind="attrs"
              v-on="on"
              class="float-right"
            >
              FOLLOW
            </v-icon> -->
          </template>
          <v-list>
            <v-list-item v-for="board in boardList" :key="board.boardId">
              <v-icon color="grey" class="mr-2">mdi-star-outline</v-icon>
              <v-list-item-title>{{ board.boardName }}</v-list-item-title>
              <v-btn
                v-if="checkInBoard(board.newsList, news.newsLink)"
                class="ml-3"
                outlined
                color="success"
                small
                @click="saveArticle(board.boardId, news)"
              >
                <v-icon left>mdi-plus</v-icon> ADD
              </v-btn>
              <v-btn
                v-else
                class="ml-3"
                outlined
                color="error"
                small
                @click="deleteArticle(board.newsList, news.newsLink)"
              >
                <v-icon left>mdi-window-close</v-icon> REMOVE
              </v-btn>
            </v-list-item>
          </v-list>
        </v-menu>

        <v-icon class="ml-3">mdi-replay mdi-flip-h</v-icon>
      </div>
      <!-- </v-layout> -->
    </v-container>
    <v-divider></v-divider>
    <v-container class="text-center">
      <p :v-html="news.newsDescription">
        {{ news.newsDescription }}
      </p>
      <v-btn
        color="grey"
        outlined
        dark
        block
        tile
        @click="openPage(news.newsLink)"
        >VISIT WEBSITE</v-btn
      >
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Board, News } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class BoardArticleDetail extends Vue {
  @feedModule.State boardList!: Board[];
  @feedModule.State news!: News;
  @feedModule.Action FETCH_ARTICLE_IN_BOARD: any;
  @feedModule.Action SAVE_IN_BOARD: any;
  @feedModule.Action DELETE_IN_BOARD: any;

  openPage(link: string) {
    window.open(link);
  }

  fetchData() {
    this.FETCH_ARTICLE_IN_BOARD(this.$route.params.newsId);
  }

  created() {
    this.fetchData();
  }

  saveArticle(boardId: number, article: News) {
    this.SAVE_IN_BOARD({ boardId, article, from: "board" });
  }

  checkInBoard(newsList: News[], link: string) {
    return !(newsList.length && newsList.some(news => news.newsLink === link));
  }

  deleteArticle(newsList: News[], link: string) {
    const newsId = newsList.filter(news => news.newsLink === link)[0].newsId;
    this.DELETE_IN_BOARD(newsId);
  }
}
</script>

<style></style>
