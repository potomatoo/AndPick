<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1 v-if="articleList[0]">{{ articleList[0].subscribeName }}</h1>
        </v-flex>
        <v-flex class="text-right" align-self-end>
          <v-tooltip @click="fetchData" bottom open-delay="300" color="#EEEEEE">
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on">
                mdi-replay mdi-flip-h
              </v-icon>
            </template>
            <span class="grey--text text-darken-1">Refresh</span>
          </v-tooltip>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 기사 리스트 -->
    <v-container class="offset-lg3 lg6">
      <v-list three-line>
        <v-list-item-group>
          <v-list-item
            v-for="(article, idx) in articleList"
            :key="idx"
            @click="selectArticle(article, idx)"
          >
            <v-list-item-content class="mt-3">
              <div class="h4">{{ article.title }}</div>
              <div class="sumtitle-1 text--secondary">
                {{ article.pubDate }}
              </div>
              <v-list-item-subtitle>{{
                article.description
              }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Rss, Article } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class ArticleListInRss extends Vue {
  @feedModule.State rssList!: Rss[];
  @feedModule.State articleList!: Article[];
  @feedModule.Mutation SELECT_ARTICLE: any;
  @feedModule.Action FETCH_ARTICLE_LIST: any;

  rssTitle: string | null = null;

  toArticleDetail(idx: number) {
    this.$router.push({
      name: "ArticleDetail",
      params: { articleId: idx.toString() }
    });
  }

  selectArticle(article: Article, idx: number) {
    this.SELECT_ARTICLE(article);
    this.toArticleDetail(idx);
  }

  @Watch("$route", { immediate: true })
  fetchData() {
    this.FETCH_ARTICLE_LIST(this.$route.params.subscribeId);
  }
}
</script>

<style scoped></style>
