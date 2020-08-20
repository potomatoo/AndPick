<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="articleList[0]">
          <div class="caption mb-3">채널</div>
          <h1 style="font-family: 'Do Hyeon', sans-serif;">
            {{ articleList[0].subscribeName }}
          </h1>
        </v-flex>
        <v-flex class="text-right" align-self-end>
          <v-tooltip @click="fetchData" bottom open-delay="300" color="#EEEEEE">
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on">
                mdi-replay mdi-flip-h
              </v-icon>
            </template>
            <span class="grey--text text-darken-1">새로고침</span>
          </v-tooltip>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 기사 리스트 -->
    <v-container>
      <v-list three-line max-width="700px">
        <v-list-item-group>
          <v-list-item
            v-for="(article, idx) in articleList"
            :key="idx"
            @click="selectArticle(article, idx)"
            class="mb-3"
          >
            <v-row>
              <v-col sm="2">
                <img
                  v-if="article.imgsrc"
                  class="article-img"
                  :src="article.imgsrc"
                  onerror="this.src='http://localhost:8080/img/logo-img.91ab7a7f.png'"
                  :alt="article.title"
                />
                <img
                  v-else
                  class="article-img"
                  src="@/assets/logo-img.png"
                  :alt="article.title"
                />
              </v-col>
              <v-col>
                <v-list-item-content class="pt-0">
                  <div class="h4">{{ article.title }}</div>
                  <div class="text-caption text--secondary">
                    {{ setDate(article.pubDate) }}
                  </div>
                  <v-list-item-subtitle class="mt-2">
                    {{ article.description }}
                  </v-list-item-subtitle>
                </v-list-item-content>
              </v-col>
            </v-row>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-container>

    <v-overlay :value="isLoading">
      <v-progress-circular
        indeterminate
        size="80"
        width="10"
        color="rgb(236, 193, 156)"
      ></v-progress-circular>
    </v-overlay>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class ArticleListInRss extends Vue {
  @feedModule.State articleList!: Article[];
  @feedModule.State isLoading!: boolean;
  @feedModule.Mutation SET_ARTICLE_DETAIL: any;
  @feedModule.Mutation SET_LOADING: any;
  @feedModule.Action FETCH_ARTICLE_LIST: any;
  @feedModule.Action FETCH_ARTICLE_DETAIL: any;

  rssTitle: string | null = null;

  toArticleDetail(idx: number) {
    this.$router.push({
      name: "ArticleDetail",
      params: { articleId: idx.toString() }
    });
  }

  async selectArticle(article: Article, idx: number) {
    this.SET_LOADING();
    this.SET_ARTICLE_DETAIL();
    await this.FETCH_ARTICLE_DETAIL(article);
    this.toArticleDetail(idx);
  }

  @Watch("$route", { immediate: true })
  fetchData() {
    this.SET_LOADING();
    this.FETCH_ARTICLE_LIST(this.$route.params.subscribeId);
  }

  setDate(date: string) {
    return date.slice(0, 10) + " " + date.slice(11, 16);
  }
}
</script>

<style scoped>
.article-img {
  width: 100%;
  margin-top: 12px;
  border: 2px soild;
  border-radius: 7px;
  height: 100px;
}
</style>
