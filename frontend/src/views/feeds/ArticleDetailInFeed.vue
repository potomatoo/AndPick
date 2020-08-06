<template>
  <div class="mt-10" v-if="article">
    <v-container>
      <!-- <v-layout> -->
      <h3>{{ article.title }}</h3>
      <div class="subtitle-1 text--secondary">{{ article.pubDate }}</div>
      <div class="text-right">
        <!-- ADD BOARD 메뉴 -->
        <v-menu offset-x :close-on-content-click="false" min-width="300px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon large>
              <v-icon v-bind="attrs" v-on="on">
                mdi-star-box-outline
              </v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item v-for="board in boardList" :key="board.boardId">
              <v-icon color="grey" class="mr-2">mdi-star-outline</v-icon>
              <v-list-item-title>{{ board.boardName }}</v-list-item-title>
              <v-btn
                v-if="checkInBoard(board.newsList, article.link)"
                class="ml-3"
                outlined
                color="success"
                small
                @click="saveArticle(board.boardId, article)"
              >
                <v-icon left>mdi-plus</v-icon> ADD
              </v-btn>
              <v-btn
                v-else
                class="ml-3"
                outlined
                color="error"
                small
                @click="deleteArticle(board.newsList, article.link)"
              >
                <v-icon left>mdi-window-close</v-icon> REMOVE
              </v-btn>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
      <!-- </v-layout> -->
    </v-container>
    <v-divider></v-divider>
    <v-container class="text-center">
      <p :v-html="article.description">
        {{ article.description }}
      </p>
      <v-btn
        color="grey"
        outlined
        dark
        block
        tile
        @click="openPage(article.link)"
        >VISIT WEBSITE</v-btn
      >
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article, Board, News } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class ArticleDetailFeed extends Vue {
  @feedModule.State article!: Article;
  @feedModule.State boardList!: Board[];
  @feedModule.Action SAVE_IN_BOARD: any;
  @feedModule.Action DELETE_IN_BOARD: any;

  checkArticle() {
    if (!this.article) {
      this.$router.replace({
        name: "Feed",
        params: {
          feedId: this.$route.params.feedId
        }
      });
    }
  }

  openPage(link: string) {
    window.open(link);
  }

  created() {
    // 새로고침시 article state가 초기화되면 상위 페이지로 이동
    this.checkArticle();
  }

  saveArticle(boardId: number, article: Article) {
    this.SAVE_IN_BOARD({ boardId, article });
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
