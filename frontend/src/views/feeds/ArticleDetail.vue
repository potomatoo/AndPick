<template>
  <div class="mt-10" v-if="article">
    <div :class="{ left: onEdit }">
      <v-container>
        <h3>{{ article.title }}</h3>
        <div class="subtitle-1 text--secondary">{{ article.pubDate }}</div>
        <div class="text-right">
          <add-board-menu />

          <!-- postDir 메뉴 -->
          <v-menu
            offset-y
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
                          name: 'EditScrapInSubs',
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
                      :to="{ name: 'NewScrapInSubs' }"
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
      </v-container>
      <v-divider></v-divider>
      <v-container class="text-center">
        <p :v-html="article.description" :class="{ desc: !onEdit }">
          {{ article.description }}
        </p>
        <div :class="{ desc: !onEdit }">
          <v-btn
            color="grey"
            outlined
            dark
            block
            tile
            @click="openPage(article.link)"
            >VISIT WEBSITE</v-btn
          >
        </div>
      </v-container>

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
import { Article, Board } from "../../store/Feed.interface";
import { Post } from "@/store/MypageInterface";

import AddBoardMenu from "@/components/feeds/AddBoardMenu.vue";
import CreateFolderModal from "@/components/pages/CreateFolderModal.vue";

const feedModule = namespace("feedModule");

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolderModal,
    AddBoardMenu
  }
})
export default class ArticleDetail extends Vue {
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

  value = false;

  checkArticle() {
    if (!this.article) {
      this.$router.replace({
        name: "ArticleListInRss",
        params: {
          feedId: this.$route.params.feedId,
          subscribeId: this.$route.params.subscribeId
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

  @Watch("folderModalActive")
  preventPageMenu() {
    if (this.folderModalActive) {
      this.closeMenu = false;
    } else {
      this.closeMenu = true;
    }
  }

  setEdit() {
    this.value = false;
  }

  saveEdit() {
    this.$router.replace({
      name: "ArticleDetail",
      params: {
        feedId: this.$route.params.feedId,
        subscribeId: this.$route.params.subscribeId,
        articleId: this.$route.params.articleId
      }
    });
    this.onEdit = false;
  }

  @Watch("$route", { immediate: true })
  checkRoute() {
    if (this.$route.name === "ArticleDetail") {
      this.onEdit = false;
    } else if (
      this.$route.name === "NewScrapInSubs" ||
      this.$route.name === "EditScrapInSubs"
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
  padding-right: 40px;
}

.right {
  width: 50%;
  float: right;
  padding-left: 40px;
}

.vl {
  border-left: 4px solid lightgrey;
  height: 100%;
  position: absolute;
  left: 50%;
  margin-left: -2px;
}

.desc {
  margin: 30px 150px 40px 150px;
}
</style>
