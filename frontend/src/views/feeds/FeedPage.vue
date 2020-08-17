<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="feed">
          <h1>{{ feed.feedName }}</h1>
        </v-flex>
        <v-flex class="text-right" align-self-end>
          <v-tooltip @click="fetchData" bottom open-delay="300" color="#EEEEEE">
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on">
                mdi-replay mdi-flip-h
              </v-icon>
            </template>
            <span class="grey--text text--darken-1">Refresh</span>
          </v-tooltip>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 기사 리스트 -->
    <v-container v-if="!articleList.length" class="text-center">
      <v-layout justify-center>
        <h4>Which sources would you like to follow?</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          You can follow publications, blogs feeds.
        </p>
      </v-layout>
      <v-btn color="success" dark @click="addRss">ADD CONTENT</v-btn>
    </v-container>

    <v-container v-else class="offset-lg3 lg6">
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
              <div class="sumtitle-1 text--secondary">
                {{ article.rssTitle }}
              </div>
              <v-list-item-subtitle>
                {{ article.description }}
              </v-list-item-subtitle>
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
import { Article, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class FeedPage extends Vue {
  @feedModule.State articleList!: Article[];
  @feedModule.State feed!: FeedList;
  @feedModule.State isLoading!: boolean;
  @feedModule.Mutation SELECT_ARTICLE: any;
  @feedModule.Mutation SET_LOADING: any;
  @feedModule.Action FETCH_FEED!: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.SET_LOADING();
    this.FETCH_FEED(this.$route.params.feedId);
  }

  toArticleDetail(idx: number) {
    this.$router.push({
      name: "ArticleDetailInFeed",
      params: { articleId: idx.toString() }
    });
  }

  selectArticle(article: Article, idx: number) {
    this.SET_LOADING();
    this.SELECT_ARTICLE(article);
    this.toArticleDetail(idx);
  }

  addRss() {
    this.$router.push({ name: "AddRss" });
  }
}
</script>

<style scoped></style>
