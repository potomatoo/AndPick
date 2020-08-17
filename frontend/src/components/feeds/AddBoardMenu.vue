<template>
  <v-menu
    offset-y
    :close-on-content-click="false"
    min-width="300px"
    :close-on-click="closeMenu"
    nudge-bottom="5"
  >
    <template v-slot:activator="{ on, attrs }">
      <span v-on="on" v-bind="attrs" class="mx-3">
        <v-tooltip top open-delay="300" color="#EEEEEE">
          <template v-slot:activator="{ on, attrs }">
            <v-icon v-bind="attrs" v-on="on">
              mdi-star-outline
            </v-icon>
          </template>
          <span class="grey--text text--darken-1">Save to Board</span>
        </v-tooltip>
      </span>
    </template>

    <!-- 피드에서 접근 -->
    <v-list
      v-if="['ArticleDetail', 'ArticleDetailInFeed'].includes(this.$route.name)"
      class="py-0"
    >
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

      <hr class="ma-0" />
      <v-list-item @click="boardModalActive = !boardModalActive">
        <v-icon color="success" class="mr-2">mdi-plus</v-icon>
        <v-list-item-title class="success--text">NEW BOARD</v-list-item-title>
      </v-list-item>
    </v-list>

    <!-- 보드에서 접근 -->
    <v-list v-else-if="this.$route.name === 'BoardArticleDetail'" class="py-0">
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

      <hr class="ma-0" />
      <v-list-item @click="boardModalActive = !boardModalActive">
        <v-icon color="success" class="mr-2">mdi-plus</v-icon>
        <v-list-item-title class="success--text">NEW BOARD</v-list-item-title>
      </v-list-item>
    </v-list>
    <create-board-modal
      :modalActive.sync="boardModalActive"
      @addBoard="addBoards"
      @closeModal="closeModal"
    />
  </v-menu>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import CreateBoardModal from "@/components/feeds/CreateBoardModal.vue";
import { Board, Article, News } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component({
  components: {
    CreateBoardModal
  }
})
export default class AddBoardMenu extends Vue {
  @feedModule.State boardList!: Board[];
  @feedModule.State article!: Article;
  @feedModule.State news!: News;
  @feedModule.Action SAVE_IN_BOARD: any;
  @feedModule.Action DELETE_IN_BOARD: any;
  @feedModule.Action ADD_BOARD: any;

  boardModalActive = false;

  closeMenu = true;

  checkInBoard(newsList: News[], link: string) {
    return !(newsList.length && newsList.some(news => news.newsLink === link));
  }

  saveArticle(boardId: number, article: Article) {
    if (this.$route.name === "BoardArticleDetail") {
      this.SAVE_IN_BOARD({ boardId, article, from: "board" });
    } else {
      this.SAVE_IN_BOARD({ boardId, article });
    }
  }

  deleteArticle(newsList: News[], link: string) {
    const newsId = newsList.filter(news => news.newsLink === link)[0].newsId;
    this.DELETE_IN_BOARD(newsId);
  }

  closeModal() {
    this.boardModalActive = false;
  }

  addBoards(boardName: string) {
    this.ADD_BOARD(boardName);
    this.closeModal();
  }

  @Watch("boardModalActive")
  preventBoardMenu() {
    if (this.boardModalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }
}
</script>

<style scoped>
span:hover {
  background-color: #eeeeee;
}
</style>
