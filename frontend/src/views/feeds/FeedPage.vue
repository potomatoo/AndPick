<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="feed">
          <h1>{{ feed.feedName }}</h1>
        </v-flex>
        <v-flex class="text-right">
          <v-icon @click="console.log('hi')">mdi-check</v-icon>
          <v-icon class="ml-3" @click="console.log('hi')"
            >mdi-replay mdi-flip-h</v-icon
          >
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
          <v-list-item v-for="(article, idx) in articleList" :key="idx">
            <v-list-item-content @click="selectArticle(article)">
              <router-link
                class="router-link"
                :to="{
                  name: 'ArticleDetailInFeed',
                  params: { articleId: idx }
                }"
              >
                <v-list-item-content class="mt-3">
                  <div class="h4">
                    {{ article.title }}
                  </div>
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
              </router-link>
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
import { Article, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class FeedPage extends Vue {
  @feedModule.State articleList!: Article[];
  @feedModule.State feedTitle!: string;
  @feedModule.State feed!: FeedList;
  @feedModule.Mutation SELECT_ARTICLE: any;
  @feedModule.Action FETCH_FEED: any;
  @feedModule.Action FETCH_ARTICLE_LIST_IN_FEED!: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    // console.log("hihi");
    // this.FETCH_FEED(this.$route.params.feedId);
    // console.log("hihi");
    this.FETCH_ARTICLE_LIST_IN_FEED(this.$route.params.feedId);
  }

  selectArticle(article: Article) {
    this.SELECT_ARTICLE(article);
  }

  addRss() {
    this.$router.push({ name: "AddRss" });
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
