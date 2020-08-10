<template>
  <div class="mt-10" v-if="article">
    <v-container>
      <!-- <v-layout> -->
      <h3>{{ article.title }}</h3>
      <div class="subtitle-1 text--secondary">{{ article.pubDate }}</div>
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
              <v-list-item-title class="success--text"
                >NEW BOARD</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>

        <!-- postDir 메뉴 -->
        <v-menu offset-x :close-on-content-click="false" min-width="100px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon large v-bind="attrs" v-on="on">
              <!-- <v-icon v-bind="attrs" v-on="on">
                mdi-star-box-outline
              </v-icon> -->
              EDIT
            </v-btn>
          </template>
          <v-list class="py-0">
            <!-- post 메뉴 -->
            <v-menu
              offset-x
              :close-on-content-click="false"
              min-width="100px"
              open-on-click
            >
              <template v-slot:activator="{ on, attrs }">
                <v-list-item
                  v-for="postDir in postDirList"
                  :key="postDir.postDirId"
                  v-bind="attrs"
                  v-on="on"
                  @click="selectPost(postDir)"
                >
                  <v-list-item-title>{{
                    postDir.postDirName
                  }}</v-list-item-title>
                </v-list-item>
              </template>

              <v-list class="py-0" v-if="selectPostDir">
                <v-list-item
                  v-for="post in selectPostDir.postList"
                  :key="post.postId"
                >
                  <v-list-item-title>{{ post.postTitle }}</v-list-item-title>
                </v-list-item>

                <hr class="ma-0" />
                <v-list-item>
                  <v-icon color="success" class="mr-2">mdi-plus</v-icon>
                  <v-list-item-title class="success--text"
                    >NEW Post</v-list-item-title
                  >
                </v-list-item>
              </v-list>
            </v-menu>

            <hr class="ma-0" />
            <v-list-item @click="dirModalActive = !dirModalActive">
              <v-icon color="success" class="mr-2">mdi-plus</v-icon>
              <v-list-item-title class="success--text"
                >NEW PAGE</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
      <!-- </v-layout> -->
    </v-container>

    <v-divider></v-divider>

    <v-container class="text-center">
      <p :v-html="article.description" @mouseup="drag">
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

    <create-board-modal
      :modalActive.sync="boardModalActive"
      @addBoard="addBoards"
      @closeModal="closeModal"
    />
    <create-folder />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article, Board, News } from "../../store/Feed.interface";
import { PostDir, Post } from "@/store/MypageInterface";

import CreateBoardModal from "@/components/feeds/CreateBoardModal.vue";
import CreateFolder from "@/components/pages/CreateFolder.vue";

const feedModule = namespace("feedModule");

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateBoardModal,
    CreateFolder
  }
})
export default class ArticleDetailFeed extends Vue {
  @feedModule.State article!: Article;
  @feedModule.State boardList!: Board[];
  @feedModule.Action SAVE_IN_BOARD: any;
  @feedModule.Action DELETE_IN_BOARD: any;
  @feedModule.Action ADD_BOARD: any;
  @mypageModule.State postDirList!: PostDir;

  boardModalActive = false;

  dirModalActive = false;

  pageModalActive = false;

  closeMenu = true;

  showPostMenu = false;

  selectText = "";

  selectPostDir: Post | null = null;

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

  closeModal() {
    this.boardModalActive = false;
  }

  addBoards(boardName: string) {
    this.ADD_BOARD(boardName);
    this.closeModal();
  }

  @Watch("boardModalActive")
  prevent() {
    if (this.boardModalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }

  drag() {
    const select = document.getSelection();
    if (select && select.toString()) {
      this.selectText = select.toString();
      console.log(select.toString());
      this.showPostMenu = true;
    }
  }

  selectPost(post: Post) {
    this.selectPostDir = null;
    this.selectPostDir = post;
  }
}
</script>

<style></style>
