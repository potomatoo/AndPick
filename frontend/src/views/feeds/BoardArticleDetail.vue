<template>
  <div class="mt-10" v-if="news">
    <v-container>
      <!-- <v-layout> -->
      <h3>{{ news.newsTitle }}</h3>
      <div class="subtitle-1 text--secondary">{{ news.newsDate }}</div>
      <div class="text-right">
        <!-- ADD BOARD 메뉴 -->
        <v-menu
          offset-x
          :close-on-content-click="false"
          min-width="300px"
          :close-on-click="closeMenu"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon large>
              <v-icon v-bind="attrs" v-on="on">
                mdi-star-box-outline
              </v-icon>
            </v-btn>
          </template>
          <v-list class="py-0">
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
            <v-list-item @click="modalActive = !modalActive">
              <v-icon color="success" class="mr-2">mdi-plus</v-icon>
              <v-list-item-title class="success--text"
                >NEW BOARD</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>
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

    <create-board-modal
      :modalActive.sync="modalActive"
      @addBoard="addBoards"
      @closeModal="closeModal"
    />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Board, News } from "../../store/Feed.interface";

import CreateBoardModal from "@/components/feeds/CreateBoardModal.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    CreateBoardModal
  }
})
export default class BoardArticleDetail extends Vue {
  @feedModule.State boardList!: Board[];
  @feedModule.State news!: News;
  @feedModule.Action FETCH_ARTICLE_IN_BOARD: any;
  @feedModule.Action SAVE_IN_BOARD: any;
  @feedModule.Action DELETE_IN_BOARD: any;
  @feedModule.Action ADD_BOARD: any;

  modalActive = false;

  closeMenu = true;

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

  closeModal() {
    this.modalActive = false;
  }

  addBoards(boardName: string) {
    this.ADD_BOARD(boardName);
    this.closeModal();
  }

  @Watch("modalActive")
  prevent() {
    if (this.modalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }
}
</script>

<style></style>
