<template>
  <div class="mt-10" v-if="article">
    <div :class="{ left: onEdit }">
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
          <v-menu
            offset-x
            :close-on-content-click="false"
            min-width="100px"
            :close-on-click="closeMenu"
            v-model="value"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon large v-bind="attrs" v-on="on">
                <!-- <v-icon v-bind="attrs" v-on="on">
                mdi-star-box-outline
              </v-icon> -->
                EDIT
              </v-btn>
            </template>
            <v-list class="py-0">
              <v-list-item
                v-for="postDir in postDirList"
                :key="postDir.postDirId"
              >
                <!-- post 메뉴 -->
                <v-menu offset-x close-on-content-click min-width="100px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-list-item-title v-bind="attrs" v-on="on">{{
                      postDir.postDirName
                    }}</v-list-item-title>
                  </template>

                  <v-list class="py-0">
                    <v-list-item
                      v-for="post in postDir.postList"
                      :key="post.postId"
                      @click="setEdit"
                    >
                      <router-link
                        class="router-link"
                        :to="{
                          name: 'EditScrapInFeed',
                          params: { postId: post.postId }
                        }"
                      >
                        <v-list-item-title>{{
                          post.postTitle
                        }}</v-list-item-title>
                      </router-link>
                    </v-list-item>

                    <hr class="ma-0" />
                    <router-link
                      class="router-link"
                      :to="{ name: 'NewScrapInFeed' }"
                    >
                      <v-list-item @click="setEdit">
                        <v-icon color="success" class="mr-2">mdi-plus</v-icon>
                        <v-list-item-title class="success--text"
                          >NEW Post</v-list-item-title
                        >
                      </v-list-item>
                    </router-link>
                  </v-list>
                </v-menu>
              </v-list-item>
              <hr class="ma-0" />
              <v-list-item @click="folderModalActive = !folderModalActive">
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
      <create-folder-modal :folderModalActive.sync="folderModalActive" />
    </div>
    <div :class="{ vl: onEdit }"></div>
    <div :class="{ right: onEdit }">
      <router-view @save="saveEdit" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article, Board, News } from "../../store/Feed.interface";
import { Post } from "@/store/MypageInterface";

import CreateBoardModal from "@/components/feeds/CreateBoardModal.vue";
import CreateFolderModal from "@/components/pages/CreateFolderModal.vue";

const feedModule = namespace("feedModule");

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateBoardModal,
    CreateFolderModal
  }
})
export default class ArticleDetailFeed extends Vue {
  @feedModule.State article!: Article;
  @feedModule.State boardList!: Board[];
  @feedModule.Action SAVE_IN_BOARD: any;
  @feedModule.Action DELETE_IN_BOARD: any;
  @feedModule.Action ADD_BOARD: any;
  @mypageModule.State postDirList!: Post[];

  boardModalActive = false;

  folderModalActive = false;

  closeMenu = true;

  onEdit = false;

  selectText = "";

  value = false;

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

  mounted() {
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
  preventBoardMenu() {
    if (this.boardModalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }

  @Watch("folderModalActive")
  preventPageMenu() {
    if (this.folderModalActive) {
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
      // this.showPostMenu = true;
    }
  }

  setEdit() {
    this.onEdit = true;
    this.value = false;
  }

  saveEdit() {
    this.$router.replace({
      name: "ArticleDetailInFeed",
      params: {
        feedId: this.$route.params.feedId,
        articleId: this.$route.params.articleId
      }
    });
    this.onEdit = false;
  }

  @Watch("$route", { immediate: true })
  checkRoute() {
    if (this.$route.name === "ArticleDetailInFeed") {
      this.onEdit = false;
    } else if (
      this.$route.name === "NewScrapInFeed" ||
      this.$route.name === "EditScrapInFeed"
    ) {
      this.onEdit = true;
    }
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}

.left {
  width: 50%;
  float: left;
}

.right {
  width: 50%;
  float: right;
}

.vl {
  border-left: 4px solid lightgrey;
  height: 100%;
  position: absolute;
  left: 50%;
  margin-left: -2px;
}
</style>
